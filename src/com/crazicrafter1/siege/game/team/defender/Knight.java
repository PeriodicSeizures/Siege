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

public class Knight extends Team implements Defender {

    public Knight(Player player) {
        super(player, Type.DEFENDER, Kit.KNIGHT, "", 0);
    }
    public Knight(UUID uuid) {
        super(uuid, Type.DEFENDER, Kit.KNIGHT, "", 0);
    }

    private static ItemStack bow =      ItemBuilder.builder(Material.BOW).unbreakable().enchant(Enchantment.ARROW_DAMAGE, 1).toItem();
    private static ItemStack sword =    ItemBuilder.builder(Material.IRON_SWORD).hideFlags(ItemFlag.HIDE_ATTRIBUTES).fast().unbreakable().enchant(Enchantment.DAMAGE_ALL, 2).toItem(); //Util.item(Material.IRON_SWORD);
    private static ItemStack helm =     ItemBuilder.builder(Material.IRON_HELMET).unbreakable().enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItem();
    private static ItemStack chest =    ItemBuilder.builder(Material.IRON_CHESTPLATE).unbreakable().enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItem();
    private static ItemStack legs =     ItemBuilder.builder(Material.IRON_LEGGINGS).unbreakable().enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItem();
    private static ItemStack boots =    ItemBuilder.builder(Material.IRON_BOOTS).unbreakable().enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItem();

    @Override
    public void reset() {
        Util.removeAllPotionEffects(getPlayer());

        getPlayer().getInventory().clear();

        PlayerInventory inv = getPlayer().getInventory();

        inv.setItem(0, sword);
        inv.setItem(1, bow);
        inv.setItem(2, new ItemStack(Material.OAK_FENCE));
        inv.setItem(8, new ItemStack(Material.ARROW, 8));

        inv.setHelmet(helm);
        inv.setChestplate(chest);
        inv.setLeggings(legs);
        inv.setBoots(boots);
    }

    @Override
    protected void passiveAbility() {
        Player p = getPlayer();

        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100000, 0), true);
    }

    private long lastArrow = System.currentTimeMillis();
    private long lastFence = System.currentTimeMillis();
    public static final long arrowDelay = 6000;
    public static final long fenceDelay = 10000;

    private static ItemStack barricade = ItemBuilder.builder(Material.OAK_FENCE).name("&bBarricade").toItem();

    @Override
    public void passiveItems() {
        PlayerInventory inv = getPlayer().getInventory();

        if (System.currentTimeMillis() - arrowDelay >= lastArrow) {
            ItemStack item = inv.getItem(8);
            if (item != null && item.getType() != Material.AIR) {
                int a = inv.getItem(8).getAmount();

                if (a < 8) inv.getItem(8).setAmount(a + 1);
            } else inv.setItem(8, new ItemStack(Material.ARROW));

            lastArrow = System.currentTimeMillis();

        }

        // FENCES
        if (System.currentTimeMillis() - fenceDelay >= lastFence) {
            ItemStack item = inv.getItem(2);
            if (item != null && item.getType() != Material.AIR) {
                int a = inv.getItem(2).getAmount();

                if (a < 3) inv.getItem(2).setAmount(a + 1);
            } else inv.setItem(2, barricade);

            lastFence = System.currentTimeMillis();

        }
    }

    @Override
    public void onOtherKill(Player other) {
        getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 4 * 20, 1), true);
    }

}
