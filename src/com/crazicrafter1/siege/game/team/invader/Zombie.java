package com.crazicrafter1.siege.game.team.invader;

import com.crazicrafter1.crutils.ItemBuilder;
import com.crazicrafter1.siege.util.Util;
import com.crazicrafter1.siege.game.team.Team;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class Zombie extends Team implements Invader {

    public Zombie(Player player) {
        super(player, Type.INVADER, Kit.ZOMBIE, "Endure", 20*9);
    }
    public Zombie(UUID uuid) {
        super(uuid, Type.INVADER, Kit.ZOMBIE, "Endure", 20*9);
    }

    private static ItemStack sword1 =   new ItemBuilder(Material.STONE_AXE).hideFlags(ItemFlag.HIDE_ATTRIBUTES).unbreakable().toItem(); //Util.item(Material.WOODEN_SWORD);
    private static ItemStack helm1 =    new ItemBuilder(Material.ZOMBIE_HEAD).toItem();
    private static ItemStack chest1 =   new ItemBuilder(Material.LEATHER_CHESTPLATE).unbreakable().dye(Color.GREEN).toItem();
    private static ItemStack legs1 =    new ItemBuilder(Material.LEATHER_LEGGINGS).unbreakable().dye(Color.GREEN).toItem();
    private static ItemStack boots1 =   new ItemBuilder(Material.LEATHER_BOOTS).unbreakable().dye(Color.GREEN).toItem();

    @Override
    public void reset() {

        Util.removeAllPotionEffects(getPlayer());

        getPlayer().getInventory().clear();

        PlayerInventory inv = getPlayer().getInventory();

        inv.addItem(sword1);
        inv.setHelmet(helm1);
        inv.setChestplate(chest1);
        inv.setLeggings(legs1);
        inv.setBoots(boots1);
    }

    @Override
    protected void ability() {

        getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 4 * 20, 2), true);
        getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 4 * 20, 1), true);

        /*
        Player p = getPlayer();

        org.bukkit.entity.Zombie zombie = p.getWorld().spawn(p.getLocation(), org.bukkit.entity.Zombie.class);
        zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30 * 20, 1), true);
        zombie.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60 * 20, 1), true);
        zombie.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 30 * 20, 5), true);

        ArrayList<Entity> entities = (ArrayList<Entity>) p.getNearbyEntities(40, 10, 40);

        Player nearest = null;
        int dist = 1000000;
        for (Entity entity : entities) {

            if (GameManager.gameContains((entity.getUniqueId())) && GameManager.isDefender(p.getUniqueId())) {

                int x1 = entity.getLocation().getBlockX();
                int z1 = entity.getLocation().getBlockZ();

                int x2 = p.getLocation().getBlockX();
                int z2 = p.getLocation().getBlockZ();

                int d = Util.sqDist(x1, z1, x2, z2);
                if (d < dist) {
                    dist = d;
                    nearest = (Player) entity;
                }
            }

        }

        if (nearest != null) {
            System.out.println();
            zombie.setTarget(nearest);
        }
         */
    }

    @Override
    protected void passiveAbility() {
        Player p = getPlayer();

        if (!p.hasPotionEffect(PotionEffectType.REGENERATION))
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 10000, 2), true);

        //p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 3 * 20, 1), true);
        //p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 3 * 20, 0), true);
    }

}
