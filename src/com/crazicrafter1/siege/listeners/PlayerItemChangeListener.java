package com.crazicrafter1.siege.listeners;

import com.crazicrafter1.siege.game.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerItemChangeListener extends BaseListener {

    @EventHandler
    public void onDropItem(PlayerDropItemEvent e) {
        if (GameManager.gameContains(e.getPlayer().getUniqueId()))
            e.setCancelled(true);
    }

    @EventHandler
    public void gainItem(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (GameManager.gameContains(p.getUniqueId()))
                e.setCancelled(true);
        }
    }


}
