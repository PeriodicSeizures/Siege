package com.crazicrafter1.siege.UNUSED;

import com.crazicrafter1.siege.Main;
import com.crazicrafter1.siege.team.defender.Defender;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.HashSet;

public class BarricadeListener implements Listener {

    private Main plugin;

    public BarricadeListener(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private HashSet<Material> barricades = new HashSet<>(Arrays.asList(
            Material.OAK_FENCE,
            Material.IRON_BARS,
            Material.NETHER_BRICK_FENCE));

    @EventHandler(priority = EventPriority.LOW)
    public void onDestroyBarricade(BlockBreakEvent e) {
        System.out.println("BarricadeBreak called");

        Player p = e.getPlayer();

        //e.setCancelled(false);

        if (p.getGameMode() == GameMode.SURVIVAL && Main.teams.containsKey(p.getUniqueId()) && Main.teams.get(p.getUniqueId()) instanceof Defender) {

            if (barricades.contains(e.getBlock().getType())) e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onOpenBarricade(PlayerInteractEvent e) {

        Player p = e.getPlayer();

        if (Main.teams.containsKey(p.getUniqueId()) && (Main.teams.get(p.getUniqueId()) instanceof Defender)) {

            if (e.getAction() == Action.RIGHT_CLICK_BLOCK && (e.getMaterial() == Material.AIR || !e.getMaterial().isBlock()) && e.getClickedBlock()!=null && barricades.contains(e.getClickedBlock().getType())) {

                // then "open" barricade temporarily
                Material original = e.getClickedBlock().getType();

                e.getClickedBlock().setType(Material.AIR);

                Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, () -> e.getClickedBlock().setType(original), (long)(1.5 * 20));

            }
        }


    }

}
