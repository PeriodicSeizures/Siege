package com.crazicrafter1.siege.game.team.invader;

import com.crazicrafter1.siege.game.GameManager;
import com.crazicrafter1.siege.Main;
import com.crazicrafter1.siege.util.ItemBuilder;
import com.crazicrafter1.siege.util.Util;
import com.crazicrafter1.siege.game.team.Team;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.UUID;

public class Nemesis extends Team implements Invader {

    //private UUID revengeEntity = null;
    private int power = 1;

    public Nemesis(Player player) {
        super(player, Type.INVADER, Kit.NEMESIS, "Vile", 15*20);
    }
    public Nemesis(UUID uuid) {
        super(uuid, Type.INVADER, Kit.NEMESIS, "Vile", 15*20);
    }

    private static ItemStack sword2 =   ItemBuilder.builder(Material.GOLDEN_SWORD).hideFlags(ItemFlag.HIDE_ATTRIBUTES).unbreakable().fast().enchant(Enchantment.DAMAGE_ALL, 3).toItem(); //Util.item(Material.WOOD
    private static ItemStack helm2 =    ItemBuilder.builder(Material.WITHER_SKELETON_SKULL).toItem();
    private static ItemStack chest2 =   ItemBuilder.builder(Material.LEATHER_CHESTPLATE).unbreakable().dye(Color.BLACK).toItem();
    private static ItemStack legs2 =    ItemBuilder.builder(Material.LEATHER_LEGGINGS).unbreakable().dye(Color.BLACK).toItem();
    private static ItemStack boots2 =   ItemBuilder.builder(Material.LEATHER_BOOTS).unbreakable().dye(Color.BLACK).toItem();

    @Override
    public void reset() {

        Util.removeAllPotionEffects(getPlayer());

        getPlayer().getInventory().clear();

        PlayerInventory inv = getPlayer().getInventory();

        inv.addItem(sword2);
        inv.setHelmet(helm2);
        inv.setChestplate(chest2);
        inv.setLeggings(legs2);
        inv.setBoots(boots2);
    }

    @Override
    protected void ability() {
        new BukkitRunnable() {
            @Override
            public void run() {
                int count = 0;
                for (Entity entity : getPlayer().getNearbyEntities(8,8,8)) {

                    if (entity instanceof Player && GameManager.isDefender(entity.getUniqueId())) {

                        Player other = (Player)entity;

                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                other.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Util.clamp(power, 3, 5)*20, 0), true);
                                other.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 4 * 20, Util.clamp(power, 0, 5)), true);
                            }
                        }.runTask(Main.getInstance());
                        count++;
                    }
                    if (count >= power) {
                        break;
                    }

                }
            }
        }.runTaskAsynchronously(Main.getInstance());
    }

    @Override
    protected void passiveAbility() {

        Player p = getPlayer();

        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100000, 0), true);

        if (!p.hasPotionEffect(PotionEffectType.REGENERATION))
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100000, 0), true);

        //p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 3 * 20, 0), true);
        //p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 3 * 20, 0), true);
        //p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 17 * 20, 0), true);
        //p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 3 * 20, 0), true);
    }

    private HashSet<UUID> viled = new HashSet<>();

    @Override
    public void onOtherKill(Player other) {

        Player p = getPlayer();

        if (!viled.contains(other.getUniqueId())) {
            new BukkitRunnable() {

                long start = System.currentTimeMillis();

                @Override
                public void run() {
                    if (System.currentTimeMillis() - 3*1000 >= start) this.cancel();

                    p.spawnParticle(Particle.SQUID_INK, p.getLocation(), 10);
                }
            }.runTaskTimer(Main.getInstance(), 1, 5);
            power++; // = Util.clamp(power+1, 1, 3);
        }

        viled.add(other.getUniqueId());
    }

}
