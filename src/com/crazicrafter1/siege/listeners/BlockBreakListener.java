package com.crazicrafter1.siege.listeners;

import com.crazicrafter1.siege.game.GameManager;
import com.crazicrafter1.siege.game.team.defender.Defender;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener extends BaseListener {

    @EventHandler
    public void event(BlockBreakEvent e) {
        Player p = e.getPlayer();

        if (p.getGameMode() == GameMode.SURVIVAL && GameManager.gameContains(p.getUniqueId())) {

            if (GameManager.barricades.contains(e.getBlock().getType())) {
                if (GameManager.getTeam(p.getUniqueId()) instanceof Defender) e.setCancelled(true);
            } else if (e.getBlock().getType().isSolid()) e.setCancelled(true);
        }

    }

}
