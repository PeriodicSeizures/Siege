package com.crazicrafter1.siege.game;

import com.crazicrafter1.siege.util.Util;
import com.crazicrafter1.siege.game.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class TeamAssigner {

//    public static HashMap<UUID, TempPlayer> teams = new HashMap<>();
    private static HashMap<Team.Type, HashMap<UUID, TempPlayer>> teams;

    //public static boolean notifyAssign

    public static void reset() {
        teams = new HashMap<>();

        teams.put(Team.Type.INVADER, new HashMap<>());
        teams.put(Team.Type.DEFENDER, new HashMap<>());
    }

    public static TempPlayer getAssigned(UUID uuid) {

        for (Team.Type type : teams.keySet()) {
            if (teams.get(type).containsKey(uuid)) return teams.get(type).get(uuid);
        }

        return null;

    }

    public static boolean isAssigned(UUID uuid) {

        for (Team.Type type : teams.keySet()) {

            if (teams.get(type).containsKey(uuid)) return true;

        }
        return false;
    }

    public static boolean setKit(UUID uuid, Team.Kit kit) {
        if (GameManager.gameState != GameManager.State.PREGAME || !isAssigned(uuid)) return false;

        Team.Type type = TeamUtils.matchType(kit);

        if (type != null && TeamUtils.isValid(teams.get(type).get(uuid).getType(), kit)) {
            teams.get(type).get(uuid).setKit(kit);
            return true;
        }

        return false;
    }

    public static int addToQueue(TempPlayer tempPlayer) {
        if (GameManager.gameState == GameManager.State.PREGAME) {
            if (teams.get(tempPlayer.getType()).containsKey(tempPlayer.getUuid())) {

                teams.get(tempPlayer.getType()).get(tempPlayer.getUuid()).setQueued(tempPlayer.getType(), tempPlayer.getKit());
                return teams.get(tempPlayer.getType()).size();
            }
        }
        return -1;
    }

    public static boolean removeFromQueue(TempPlayer tempPlayer) {
        if (GameManager.gameState == GameManager.State.PREGAME) {
            if (teams.get(tempPlayer.getType()).containsKey(tempPlayer.getUuid())) {

                if (teams.get(tempPlayer.getType()).get(tempPlayer.getUuid()).getQueued() != null) {
                    teams.get(tempPlayer.getType()).get(tempPlayer.getUuid()).unQueue();
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    private static TempPlayer assignTeam(UUID uuid, Team.Type type, Team.Kit kit) {

        //teams.get(temp.getType()).put(uuid, temp);
        TempPlayer tempPlayer = new TempPlayer(uuid, type, kit);
        teams.get(type).put(uuid, tempPlayer);
        return tempPlayer;
    }

    /*
        Per round methods
     */

    // Final call
    public static void assignGameTeams() {

        //GameManager.reset();

        Util.clearChat(ChatColor.GREEN + "The game has started!)");
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {

            if (isAssigned(p.getUniqueId())) p.setGameMode(GameMode.SURVIVAL);
        }

        for (Team.Type type : teams.keySet()) {

            for (TempPlayer tempPlayer : teams.get(type).values()) {

                Team team = TeamUtils.matchTeam(tempPlayer.getUuid(), tempPlayer.getType(), tempPlayer.getKit());
                try {
                    GameManager.addPlayer(team);
                    team.respawn();
                } catch (Exception e) {e.printStackTrace();}

            }

        }

    }

    public static void assignTeams() {

        reset();

        //HashSet<UUID> toPlay = new HashSet<>();
        int s = Bukkit.getOnlinePlayers().size();
        final int requiredDefenders = Util.clamp((int)(s*(1.0/3.0)), 1, s);

        int defenderCount = 0;
        for (Player p : Bukkit.getOnlinePlayers()) {
            //toPlay.add(p.getUniqueId());

            TempPlayer tempPlayer;

            if (defenderCount < requiredDefenders) {
                tempPlayer = assignTeam(p.getUniqueId(), Team.Type.DEFENDER, Team.Kit.MARKSMAN);

            } else {
                tempPlayer = assignTeam(p.getUniqueId(), Team.Type.INVADER, Team.Kit.UNDEAD_GHOUL);
            }

            p.sendMessage(ChatColor.GREEN + "You have been assigned to " + tempPlayer);

            p.sendMessage(ChatColor.LIGHT_PURPLE + "Change kit with " +
                    ChatColor.DARK_PURPLE + "/kit <kit>" +
                    ChatColor.LIGHT_PURPLE + " or queue for another team with " +
                    ChatColor.DARK_PURPLE + "/defender <kit>" + ChatColor.LIGHT_PURPLE + " or " +
                    ChatColor.DARK_PURPLE + "/invader <kit>" + ChatColor.LIGHT_PURPLE + " !" );

            defenderCount++;

        }

    }

    public static void assignQueuedTeams() {

        ArrayList<TempPlayer.QueuedPlayer> defenders = new ArrayList<>();
        ArrayList<TempPlayer.QueuedPlayer> invaders = new ArrayList<>();

        for (Team.Type type : teams.keySet()) {
            for (TempPlayer tempPlayer : teams.get(type).values()) {
                TempPlayer.QueuedPlayer queued = tempPlayer.getQueued();
                if (queued != null) {
                    if (queued.getType() == Team.Type.DEFENDER) {
                        defenders.add(queued);
                    } else invaders.add(queued);
                }
            }
        }

        final int size = Math.min(defenders.size(), invaders.size());

        for (int i=0; i<size; i++) {

            TempPlayer.QueuedPlayer D = defenders.get(i);
            TempPlayer.QueuedPlayer I = invaders.get(i);

            assignTeam(D.getUuid(), D.getType(), D.getKit());
            assignTeam(I.getUuid(), I.getType(), I.getKit());
        }

    }

}
