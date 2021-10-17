package com.crazicrafter1.siege.game.team.defender;

import com.crazicrafter1.siege.util.ItemBuilder;
import com.crazicrafter1.siege.util.Util;
import com.crazicrafter1.siege.game.team.Team;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class Marksman extends Team implements Defender {

    public Marksman(Player player) {
        super(player, Type.DEFENDER, Kit.MARKSMAN, "", 0);
    }
    public Marksman(UUID uuid) {
        super(uuid, Type.DEFENDER, Kit.MARKSMAN, "", 0);
    }

    private static ItemStack bow =      ItemBuilder.builder(Material.BOW).unbreakable().enchant(Enchantment.ARROW_DAMAGE, 3).enchant(Enchantment.ARROW_KNOCKBACK, 2).toItem();
    private static ItemStack sword =    ItemBuilder.builder(Material.STONE_SWORD).hideFlags(ItemFlag.HIDE_ATTRIBUTES).fast().unbreakable().enchant(Enchantment.DAMAGE_ALL, 1).toItem();
    private static ItemStack helm =     ItemBuilder.builder(Material.CHAINMAIL_HELMET).unbreakable().enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItem();
    private static ItemStack chest =    ItemBuilder.builder(Material.CHAINMAIL_CHESTPLATE).unbreakable().enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItem();
    private static ItemStack legs =     ItemBuilder.builder(Material.CHAINMAIL_HELMET).unbreakable().enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItem();
    private static ItemStack boots =    ItemBuilder.builder(Material.CHAINMAIL_BOOTS).unbreakable().enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItem();

    @Override
    public void reset() {
        Util.removeAllPotionEffects(getPlayer());

        getPlayer().getInventory().clear();

        PlayerInventory inv = getPlayer().getInventory();

        /*
        for (int slot=0; slot<36; slot++) {
            ItemStack renamed = new ItemStack(Material.STONE);
            Util.setName(renamed, "" + slot);
            inv.setItem(slot, renamed);
        }
         */

        inv.setItem(0, sword);
        inv.setItem(1, bow);
        inv.setItem(8, new ItemStack(Material.ARROW, 8));

        inv.setHelmet(helm);
        inv.setChestplate(chest);
        inv.setLeggings(legs);
        inv.setBoots(boots);

    }

    @Override
    protected void passiveAbility() {
        Player p = getPlayer();

        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 0), true);
    }

    private long lastArrow = System.currentTimeMillis();
    public static final int arrowDelay = 3000;

    @Override
    public void passiveItems() {
        PlayerInventory inv = getPlayer().getInventory();

        if (System.currentTimeMillis() - arrowDelay >= lastArrow) {
            ItemStack item = inv.getItem(8);
            if (item != null && item.getType() != Material.AIR) {
                int a = inv.getItem(8).getAmount();

                if (a < 16) inv.getItem(8).setAmount(a + 1);
            } else inv.setItem(8, new ItemStack(Material.ARROW));

            lastArrow = System.currentTimeMillis();

        }
    }

    @Override
    public void onOtherKill(Player other) {
        getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 4 * 20, 1), true);
    }
}
