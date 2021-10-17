package com.crazicrafter1.siege.UNUSED;

import com.crazicrafter1.siege.Bomber;
import com.crazicrafter1.siege.Main;
import com.crazicrafter1.siege.team.invader.Invader;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;

public class BombListener implements Listener {

    private Main plugin;

    public BombListener(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onClick(PlayerInteractEvent e) {

        Player p = e.getPlayer();

        UUID uuid = p.getUniqueId();

        if (Main.teams.containsKey(uuid) && (Main.teams.get(uuid) instanceof Invader)) {
            if (Main.bombers.containsKey(uuid)) {
                if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    Main.bombers.get(uuid).detonate();
                }
            } else {
                if (e.getClickedBlock() != null && e.getClickedBlock().getType() == Material.TNT) {
                    e.getClickedBlock().setType(Material.AIR);
                    Main.bombers.put(uuid, new Bomber(p));
                    //p.getWorld().getBlockAt(e.getClickedBlock().getLocation())

                }
            }
        }

    }

}
