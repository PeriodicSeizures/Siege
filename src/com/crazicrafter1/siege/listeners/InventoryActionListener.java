package com.crazicrafter1.siege.listeners;

import com.crazicrafter1.siege.game.GameManager;
import com.crazicrafter1.siege.game.TeamAssigner;
import com.crazicrafter1.siege.game.TempPlayer;
import com.crazicrafter1.siege.menu.KitMenu;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryActionListener extends BaseListener {

    @EventHandler
    public void closeInventory(InventoryCloseEvent e) {
        Player p = (Player)e.getPlayer();
        if (KitMenu.menus.containsKey(p.getUniqueId())) {
            TempPlayer tempPlayer = TeamAssigner.getAssigned(p.getUniqueId());

            p.sendMessage("Assigned to team " + tempPlayer.getType().name() + " with kit " + tempPlayer.getKit().name());

            KitMenu.menus.remove(p.getUniqueId());
        }
    }

    @EventHandler
    public void onInventoryMoveItem(InventoryClickEvent e) {

        Player p = (Player)e.getWhoClicked();

        if (p.getGameMode() == GameMode.SURVIVAL && GameManager.gameContains(p.getUniqueId())) {
            e.setCancelled(true);
        } else if (KitMenu.menus.containsKey(p.getUniqueId())) {

            // call event
            KitMenu.menus.get(p.getUniqueId()).onInteract(e);

        }
    }

}
