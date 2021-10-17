package com.crazicrafter1.siege.UNUSED;

import com.crazicrafter1.siege.Main;
import com.crazicrafter1.siege.team.invader.Invader;
import com.crazicrafter1.siege.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PreventionListener implements Listener {

    private Main plugin;

    public PreventionListener(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryMoveItem(InventoryClickEvent e) {

        Player p = (Player)e.getWhoClicked();

        if (p.getGameMode() == GameMode.SURVIVAL && Main.teams.containsKey(p.getUniqueId())) {
            e.setCancelled(true);
        }

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBreakBlock(BlockBreakEvent e) {

        System.out.println("PreventionBreak called");

        Player p = e.getPlayer();

        e.setCancelled(false);

        if (p.getGameMode() == GameMode.SURVIVAL && Main.teams.containsKey(p.getUniqueId())) {

            if (e.getBlock().getType().isSolid()) e.setCancelled(true);
        }

    }

    @EventHandler
    public void onRegen(EntityRegainHealthEvent e) {
//e.getRegainReason()
        if (e.getEntity() instanceof Player) {

            Player p = (Player) e.getEntity();
            if (Main.teams.containsKey(p.getUniqueId())) {

                Team team = Main.teams.get(p.getUniqueId());

                if (team.getKit() == Team.Kit.NECROMANCER && (e.getRegainReason() == EntityRegainHealthEvent.RegainReason.REGEN || e.getRegainReason() == EntityRegainHealthEvent.RegainReason.SATIATED))
                    e.setCancelled(true);
            }
        }

    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {

        if (Main.teams.containsKey(e.getEntity().getUniqueId())) {

            e.setCancelled(true);

        }
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent e) {

        e.setCancelled(true);

    }

}
