package com.crazicrafter1.siege.team.defender;

import com.crazicrafter1.siege.Util;
import com.crazicrafter1.siege.team.Team;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Paladin extends Team implements Defender {

    public Paladin(Player player) {
        super(player, Kit.PALADIN, "", 0);
    }

    private static ItemStack bow =     Util.item(Material.BOW);
    private static ItemStack sword =   Util.item(Material.IRON_SWORD);
    private static ItemStack helm =    Util.item(Material.DIAMOND_HELMET);
    private static ItemStack chest =   Util.item(Material.DIAMOND_CHESTPLATE);
    private static ItemStack legs =    Util.item(Material.DIAMOND_LEGGINGS);
    private static ItemStack boots =   Util.item(Material.IRON_BOOTS);

    static {
        sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
        helm.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        legs.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
    }

    @Override
    public void reset() {
        super.clearPotionEffects();

        getPlayer().getInventory().clear();

        PlayerInventory inv = getPlayer().getInventory();

        inv.addItem(sword);
        getPlayer().getInventory().addItem(bow);

        inv.setHelmet(helm);
        inv.setChestplate(chest);
        inv.setLeggings(legs);
        inv.setBoots(boots);
    }

    @Override
    protected void ability() {

    }

    @Override
    protected void passiveAbility() {

    }

    @Override
    public void passiveItems() {

    }

}
