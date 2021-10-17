package com.crazicrafter1.siege.listeners;

import com.crazicrafter1.siege.game.BombManager;
import com.crazicrafter1.siege.game.GameManager;
import com.crazicrafter1.siege.game.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;

public class PlayerInteractListener extends BaseListener {

    @EventHandler
    public void event(PlayerInteractEvent e) {

        Player p = e.getPlayer();

        UUID uuid = p.getUniqueId();
        if (GameManager.gameContains(uuid)) {

            if (GameManager.isInvader(uuid)) {

                if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    if (!BombManager.isBomber(uuid)) {
                        if (e.getClickedBlock() != null && e.getClickedBlock().getType() == Material.TNT) {
                            e.getClickedBlock().setType(Material.AIR);
                            BombManager.addBomber(uuid);
                            return;
                        }
                        Team team = GameManager.getTeam(uuid);
                        team.onClickAbility();
                    } else BombManager.detonateBomber(uuid);
                }
            } else {

                if (e.getAction() == Action.RIGHT_CLICK_BLOCK && (!GameManager.barricades.contains(e.getMaterial())) && e.getClickedBlock()!=null && GameManager.barricades.contains(e.getClickedBlock().getType())) {

                    // then "open" barricade temporarily
                    Material original = e.getClickedBlock().getType();

                    e.getClickedBlock().setType(Material.AIR);

                    Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, () -> e.getClickedBlock().setType(original), (long)(1.5 * 20));

                }
            }


        }

    }

}
