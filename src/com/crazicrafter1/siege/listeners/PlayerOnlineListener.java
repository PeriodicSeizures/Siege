package com.crazicrafter1.siege.listeners;

import com.crazicrafter1.siege.Main;
import com.crazicrafter1.siege.util.ScoreboardAPI;
import com.crazicrafter1.siege.game.GameManager;
import com.crazicrafter1.siege.game.WorldManager;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerChannelEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerOnlineListener extends BaseListener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        ScoreboardAPI.removeScoreboard(e.getPlayer());
        if (!e.getPlayer().isOp()) {
            e.getPlayer().setGameMode(GameMode.ADVENTURE);
            e.getPlayer().teleport(WorldManager.helmsDeep.getSpawnLocation());
        }

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        GameManager.removeFromGame(e.getPlayer().getUniqueId());
        /*
        if (Main.players.containsKey(e.getPlayer().getUniqueId())) {
            if (Main.players.get(e.getPlayer().getUniqueId()) instanceof Defender) {
                //

            } else {


            }
        }

         */
    }

}
