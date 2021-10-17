package com.crazicrafter1.siege.listeners;

import com.crazicrafter1.siege.game.GameManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class PlayerStatListener extends BaseListener {

/*
    @EventHandler
    public void onRegen(EntityRegainHealthEvent e) {
        if (e.getEntity() instanceof Player) {

            Player p = (Player) e.getEntity();
            if (GameManager.gameContains(p.getUniqueId())) {

                Team team = GameManager.getTeam(p.getUniqueId());

                if (team.getKit() == Team.Kit.NEMESIS && (e.getRegainReason() == EntityRegainHealthEvent.RegainReason.REGEN || e.getRegainReason() == EntityRegainHealthEvent.RegainReason.SATIATED))
                    e.setCancelled(true);
            }
        }

    }

 */

    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {

        e.setCancelled(true);

        /*
        if (GameManager.gameContains(e.getEntity().getUniqueId())) {



        }

         */
    }

}
