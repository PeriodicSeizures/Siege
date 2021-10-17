package com.crazicrafter1.siege.game;

import com.crazicrafter1.siege.Bomb;
import com.crazicrafter1.siege.BombCarrier;
import com.crazicrafter1.siege.Main;
import com.crazicrafter1.siege.util.Util;
import com.crazicrafter1.siege.util.ScoreboardAPI;
import com.crazicrafter1.siege.game.team.Team;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class GameManager {

    public enum State {
        INACTIVE, PREGAME, GAME, POSTGAME
    }

    //public static King king = null;

    private static HashMap<Team.Type, HashMap<UUID, Team>> teams; //ashMultimap.create();

    public static HashSet<Material> barricades = new HashSet<>(Arrays.asList(
            Material.OAK_FENCE,
            Material.IRON_BARS,
            Material.NETHER_BRICK_FENCE));


    //public static int bomberTime = 0;

    //public static int defenderCount = 0;
    //public static int invaderCount = 0;
    public static long gameStart = 0;

    public static State gameState = State.INACTIVE;

    private static Main plugin = Main.get();

    public static void onEnable() {
        //WorldManager.helmsDeep.setPVP(false);

        reset();
        TeamAssigner.reset();

        KingManager.onEnable();
        SpawnManager.load();
        BombManager.load();

    }

    /****************************

            Utility Methods

     ****************************/

    public static void reset() {

        teams = new HashMap<>();

        teams.put(Team.Type.DEFENDER, new HashMap<>());
        teams.put(Team.Type.INVADER, new HashMap<>());

    }


    public static Collection<Team> getDefenders() {
        return teams.get(Team.Type.DEFENDER).values();
    }

    public static Collection<Team> getInvaders() {
        return teams.get(Team.Type.INVADER).values();
    }

    public static boolean gameContains(UUID uuid) {

        for (HashMap<UUID, Team> players : teams.values()) {
            if (players.containsKey(uuid)) return true;
        }

        return false;
    }

    public static boolean isDefender(UUID uuid) {

        for (HashMap<UUID, Team> players : teams.values()) {
            if (players.containsKey(uuid) && players.get(uuid).getType() == Team.Type.DEFENDER) return true;
        }

        return false;
    }

    public static boolean isInvader(UUID uuid) {

        for (HashMap<UUID, Team> players : teams.values()) {
            if (players.containsKey(uuid) && players.get(uuid).getType() == Team.Type.INVADER) return true;
        }

        return false;
    }

    public static Team.Type getTeamType(UUID uuid) {

        for (HashMap<UUID, Team> players : teams.values()) {
            if (players.containsKey(uuid)) return players.get(uuid).getType();
        }

        return null;
    }


    /*
    public static Defender getDefender(UUID uuid) {

        if (teams.get(Team.Type.DEFENDER).containsKey(uuid)) return (Defender) teams.get(Team.Type.DEFENDER).get(uuid);

        return null;
    }

    public static Invader getInvader(UUID uuid) {

        if (teams.get(Team.Type.INVADER).containsKey(uuid)) return (Invader) teams.get(Team.Type.DEFENDER).get(uuid);

        return null;
    }
     */

    public static Team getTeam(UUID uuid) {

        for (HashMap<UUID, Team> players : teams.values()) {
            if (players.containsKey(uuid)) return players.get(uuid);
        }

        return null;
    }

    /*
        add a validator method
     */

    public static boolean addPlayer(Team team) {
        /*

            add a fail-safe similar to the addToGame(UUID, Team, Kit) metohd.

         */
        if (!TeamUtils.isValid(team)) {
            System.out.println("Failed adding player to game (GameManager.java - addPlayer#Team)");
            return false;
        }
            
        removeFromGame(team.getUuid());
        teams.get(team.getType()).put(team.getUuid(), team);
        teams.get(team.getType()).get(team.getUuid()).reset();

        System.out.println("Successfully added player to game (GameManager.java - addPlayer#Team)");

        return true;
    }

    public static boolean addDefender(UUID uuid, Team.Kit kit) {
        return addPlayer(uuid, Team.Type.DEFENDER, kit);
    }

    public static boolean addInvader(UUID uuid, Team.Kit kit) {
        return addPlayer(uuid, Team.Type.INVADER, kit);
    }

    public static boolean addPlayer(UUID uuid, Team.Type type, Team.Kit kit) {
        Team team = TeamUtils.matchTeam(uuid, type, kit);

        return addPlayer(team);
    }

    public static boolean removeFromGame(UUID uuid) {

        if (uuid == null) return false;

        for (HashMap<UUID, Team> players : teams.values()) {
            if (players.containsKey(uuid)) {
                if (BombManager.bombers.containsKey(uuid)) {
                    BombManager.bombers.get(uuid).detonate();
                    BombManager.bombers.remove(uuid);
                }
                players.remove(uuid);
                return true;
            }
        }
        return false;
    }

    /*
            Bomb Methods
     */

    /*
    public static Team.Type getTeamType(UUID uuid) {

        for (HashMap<UUID, Team> players : teams.values()) {
            if (players.containsKey(uuid) && players.get(uuid) instanceof Invader) return true;
        }

        return false;
    }
     */

    public static void onDisable() {
        KingManager.onDisable();
        BombManager.save();
        SpawnManager.save();

    }

    /*
    public static void addAblePlayers() {
        if (gameState == State.INACTIVE) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                TeamAssigner.joinGame(p.getUniqueId());
            }
        }
    }
     */

    private static final float gameDuration = 10f; // IN FRACTIONS OF A MINUTE

    public static boolean preStartGame(final int startInSeconds) {

        if (gameState != State.INACTIVE) {
            //System.out.println("Game tried to preStart when not inactive");
            return false;
        }

        Bukkit.getScheduler().cancelTasks(plugin);

        reset();

        gameState = State.PREGAME;

        TeamAssigner.assignTeams(); //.arrangeQueuedTeams();



        new BukkitRunnable() {
            int time = startInSeconds;
            @Override
            public void run() {
                if (time == 0) {
                    this.cancel();
                    startGame();
                }
                else {
                    if (time == 180 || time == 120 || time == 60 || time == 30 || time == 10 || time == 5 || time <= 3)
                        Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Game is starting in " + time + " seconds");
                    time--;
                }
            }
        }.runTaskTimer(plugin, 40, 20);

        return true;
    }

    public static boolean startGame() {
        if (gameState != State.PREGAME) {
            return false;
        }

        gameState = State.GAME;
        gameStart = System.currentTimeMillis();

        //WorldManager.helmsDeep.setPVP(true);
        //WorldManager.helmsDeep.sa

        reset();

        TeamAssigner.assignQueuedTeams();
        TeamAssigner.assignGameTeams();

        KingManager.king.reset();

        /*
            Free memory
         */
        TeamAssigner.reset();

        Bukkit.getScheduler().cancelTasks(plugin);

        // 22900 ticks

        WorldManager.helmsDeep.setTime((long) (23460.0-((gameDuration*1000.0)*24000.0/20000.0)));

        new BukkitRunnable() {
            @Override
            public void run() {
                for (HashMap<UUID, Team> players : teams.values()) {
                    for (Team team : players.values()) {

                        Player p = team.getPlayer();
                        if (p == null || !p.isOnline()) {
                            removeFromGame(team.getUuid());
                            continue;
                        }

                        team.tick();
                    }
                }

                for (BombCarrier bomber : BombManager.bombers.values()) {
                    bomber.tick();
                }

                for (Bomb bomb : BombManager.bombSpawns.values()) bomb.tick();

                if (KingManager.king != null) KingManager.king.tick();

            }
        }.runTaskTimer(plugin, 10, 1);


        new BukkitRunnable() {

            @Override
            public void run() {
                //updateTeamCounts();

                int kingHealth = -1;

                if (KingManager.king != null) {
                    kingHealth = KingManager.king.getHealth();
                }

                long remainingTimeMillis = (gameStart + (long)(gameDuration*60*1000.0))-System.currentTimeMillis();

                String gameTime = String.format("%.01f", ((remainingTimeMillis / 1000.0f) / 60.0f));

                for (HashMap<UUID, Team> players : teams.values()) {
                    for (Team team : players.values()) {
                        Player p = team.getPlayer();

                        ScoreboardAPI.setSideBar(p, "    SIEGE    ",
                                new String[]{
                                        "",
                                        ChatColor.RED + "Invaders",
                                        ChatColor.RED + "" + teams.get(Team.Type.INVADER).size() + " Players",
                                        "",
                                        ChatColor.AQUA + "Defenders",
                                        ChatColor.AQUA + "" + teams.get(Team.Type.DEFENDER).size() + " Players",
                                        "",
                                        ChatColor.YELLOW + "" + ChatColor.BOLD + "King",
                                        kingHealth + " Health",
                                        "",
                                        ChatColor.YELLOW + "" + ChatColor.BOLD + "Sunrise",
                                        gameTime + " Minutes"});
                    }
                }

                if (remainingTimeMillis <= 0) {

                    for (Team team : getInvaders()) {
                        Player p = team.getPlayer();
                        p.sendMessage(ChatColor.RED + "The undead are burning!");
                        p.setFireTicks(20*10);
                        p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100*20, 5), true);
                    }

                    stopGame(Team.Type.DEFENDER);
                }

            }
        }.runTaskTimer(plugin, 10, 20);

        return true;
    }

    private static void roundup() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (GameManager.gameContains(p.getUniqueId())) {

                System.out.println("Rounding up " + p.getName());

                p.teleport(WorldManager.helmsDeep.getSpawnLocation());
                Util.cure(p);
                p.setGameMode(GameMode.ADVENTURE);
                ScoreboardAPI.removeScoreboard(p);
                p.getInventory().clear();
            }
        }
        reset();
        //teams.clear();
    }

    public static void stopGame(Team.Type winner) {

        if (gameState == State.INACTIVE) return;

        //WorldManager.helmsDeep.setPVP(false);

        Bukkit.getScheduler().cancelTasks(plugin);

        TeamAssigner.reset();

        gameState = State.INACTIVE;

        if (winner != null) {

            //Util.clearChat();
            //ChatColor.
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(ChatColor.DARK_GREEN + "The " + winner.name().toLowerCase() + "'s have won!!!");
                p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1, 1);
            }

            //


            new BukkitRunnable() {
                @Override
                public void run() {
                    roundup();
                }
            }.runTaskLater(plugin, 20*7);


        } else {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(ChatColor.RED + "The game has been stopped)");
            }
            //Util.clearChat(ChatColor.RED + "The game has been stopped)");
            roundup();
        }
    }

}
