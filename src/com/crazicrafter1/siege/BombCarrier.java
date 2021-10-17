package com.crazicrafter1.siege;

import com.crazicrafter1.siege.game.BombManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class BombCarrier {

    private Player player;
    private int t = 0;
    //private ArmorStand ar;

    private static final int DETONATE_TIME = 20*60;

    public BombCarrier(Player p) {
        this.player = p;

        p.getInventory().setHelmet(new ItemStack(Material.TNT));
    }

    public BombCarrier(UUID uuid) {
        this.player = Main.get().getServer().getPlayer(uuid);



        player.getInventory().setHelmet(new ItemStack(Material.TNT));
    }

    public void tick() {
        if (t%2==0) {
            player.getWorld().spawnParticle(Particle.FLAME, player.getLocation(), 7);
            //player.getWorld().par
            player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, 1);
        }
        DurationText.display(player, "TNT", this.t, DETONATE_TIME);

        t++;

        if (t >= DETONATE_TIME) detonate();

        Location loc = player.getLocation().clone();

        loc.setYaw(0);
        loc.setPitch(0);

        //ar.teleport(loc);
    }

    public void detonate() {

        player.getWorld().createExplosion(player.getLocation(), 5);
        player.damage(100000);
        player.getInventory().setHelmet(new ItemStack(Material.AIR));

        BombManager.removeBomber(player.getUniqueId());
        //ar.remove();
    }



}
