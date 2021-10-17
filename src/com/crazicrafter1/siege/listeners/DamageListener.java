package com.crazicrafter1.siege.listeners;

import com.crazicrafter1.siege.game.GameManager;
import com.crazicrafter1.siege.game.KingManager;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class DamageListener extends BaseListener {

    /*
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();

        p.spigot().respawn();
    }

     */

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        //e.getCause()
//e.getCause() == EntityDamageEvent.DamageCause;
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (GameManager.gameContains(p.getUniqueId())) {
                double post = p.getHealth() - e.getFinalDamage(); //.getDamage();
                //p.sendMessage("Health : " + p.getHealth() + ", Damage : " + e.getDamage() + ", Post : " + post);
                //p.sendMessage("Your health : " + p.getHealth());
                if (post <= 0.0D) {
                    if (!(e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK || e.getCause() == EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK)) {
                        e.setCancelled(true);
                        System.out.println("Damage cancelled 0 (onDamage)");
                        //kill(p);
                        GameManager.getTeam(p.getUniqueId()).respawn();
                    }
                }
            } else {
                System.out.println("Damage cancelled 1 (onDamage)");
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onArrowHit(ProjectileHitEvent e) {
        e.getEntity().remove();
    }

    @EventHandler
    public void onDamagedByAnother(EntityDamageByEntityEvent e) {

        if (e.getDamager() instanceof Player || e.getDamager() instanceof Projectile) {
            Player damager = null; // = (Player) e.getDamager();

            if (e.getDamager() instanceof Projectile && ((Projectile) e.getDamager()).getShooter() instanceof Player)
                damager = (Player) ((Projectile) e.getDamager()).getShooter();
            else damager = (Player)e.getDamager();


            if (e.getEntity() instanceof Player) {
                Player damaged = (Player) e.getEntity();
                if (GameManager.gameContains(damaged.getUniqueId()) && GameManager.gameContains(damager.getUniqueId())) {



                    if (GameManager.getTeamType(damaged.getUniqueId()) == GameManager.getTeamType(damager.getUniqueId())) {
                        System.out.println("Damage cancelled 0");
                        e.setCancelled(true);
                        return;
                    }




                    double post = damaged.getHealth() - e.getFinalDamage(); //.getDamage();
                    //p.sendMessage("Health : " + p.getHealth() + ", Damage : " + e.getDamage() + ", Post : " + post);
                    //p.sendMessage("Your health : " + p.getHealth());
                    if (post <= 0.0D) {
                        e.setCancelled(true);
                        System.out.println("Damage cancelled 1");
                        //kill(damaged);
                        GameManager.getTeam(damaged.getUniqueId()).respawn();
                        GameManager.getTeam(damager.getUniqueId()).onOtherKill(damaged);

                    }

                }
            } else {

                if (GameManager.gameContains(damager.getUniqueId())) {
                    if (KingManager.king != null && e.getEntity() == KingManager.king.getEntity()) {
                        System.out.println("Damage cancelled 2");
                        e.setCancelled(true);
                        // then "damage"
                        KingManager.king.onDamage(damager);
                    }
                }
            }
        }
    }

    /*
    private void kill(Player p) {
        //p.teleport(p.getWorld().getSpawnLocation());
        //p.teleport()
        Team team = GameManager.getTeam(p.getUniqueId());
        if (team != null) {
            GameManager.toSpawn(team);
        } else p.teleport(p.getWorld().getSpawnLocation());

        Util.cure(p);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            if (GameManager.gameContains(p.getUniqueId())) GameManager.getTeam(p.getUniqueId()).reset();
        }, 1);
    }

     */

}
