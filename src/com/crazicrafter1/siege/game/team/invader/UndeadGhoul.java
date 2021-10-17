package com.crazicrafter1.siege.game.team.invader;

import com.crazicrafter1.siege.Main;
import com.crazicrafter1.siege.util.ItemBuilder;
import com.crazicrafter1.siege.util.Util;
import com.crazicrafter1.siege.game.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class UndeadGhoul extends Team implements Invader {

    public UndeadGhoul(Player player) {
        super(player, Type.INVADER, Kit.UNDEAD_GHOUL, "Leap", 20*7);
    }
    public UndeadGhoul(UUID uuid) {
        super(uuid, Type.INVADER, Kit.UNDEAD_GHOUL, "Leap", 20*7);
    }

    private static ItemStack sword0 =   ItemBuilder.builder(Material.WOODEN_AXE).unbreakable().fast().toItem();
    private static ItemStack helm0 =    ItemBuilder.builder(Material.SKELETON_SKULL).toItem(); //Util.item(Material.SKELETON_SKULL);
    private static ItemStack chest0 =   ItemBuilder.builder(Material.LEATHER_CHESTPLATE).unbreakable().dye(110, 110, 110).toItem();
    private static ItemStack legs0 =    ItemBuilder.builder(Material.LEATHER_LEGGINGS).unbreakable().dye(70, 70, 70).toItem();
    private static ItemStack boots0 =   ItemBuilder.builder(Material.LEATHER_BOOTS).unbreakable().dye(30, 30, 30).toItem();

    @Override
    public void reset() {
        Util.removeAllPotionEffects(getPlayer());

        getPlayer().getInventory().clear();

        PlayerInventory inv = getPlayer().getInventory();

        inv.addItem(sword0);
        inv.setHelmet(helm0);
        inv.setChestplate(chest0);
        inv.setLeggings(legs0);
        inv.setBoots(boots0);
    }

    @Override
    protected void ability() {
        Player p = getPlayer();

        p.setFallDistance(-100);

        p.setVelocity(p.getLocation().getDirection().multiply(1.3));
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 1, 1);

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> p.setFallDistance(-100), 1);

    }

    @Override
    protected void passiveAbility() {
        Player p = getPlayer();

        p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100000, 1), true);
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 1), true);
    }

}
