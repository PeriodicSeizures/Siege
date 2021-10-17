package com.crazicrafter1.siege.UNUSED;

import com.crazicrafter1.siege.Main;
import com.crazicrafter1.siege.team.Team;
import com.crazicrafter1.siege.team.invader.Invader;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class AbilityListener implements Listener {

    private Main plugin;

    public AbilityListener(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onAbilityUse(PlayerInteractEvent e) {

        Player p = e.getPlayer();

        if (Main.teams.containsKey(p.getUniqueId()) && e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Team team = Main.teams.get(p.getUniqueId());
            team.onClickAbility();

        }

    }

}
