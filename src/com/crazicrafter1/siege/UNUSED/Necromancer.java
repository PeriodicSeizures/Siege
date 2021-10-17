package com.crazicrafter1.siege.team.invader;

import com.crazicrafter1.siege.DurationText;
import com.crazicrafter1.siege.Util;
import com.crazicrafter1.siege.team.Team;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Necromancer extends Team implements Invader {

    public Necromancer(Player player) {
        super(player, Kit.NECROMANCER, "Crowd Summon", 20*15);
    }

    private static ItemStack sword3 =   Util.item(Material.IRON_AXE);
    private static ItemStack helm3 =    Util.item(Material.WITHER_SKELETON_SKULL);
    private static ItemStack chest3 =   Util.item(Material.GOLDEN_CHESTPLATE);
    private static ItemStack legs3 =    Util.item(Material.GOLDEN_LEGGINGS);
    private static ItemStack boots3 =   Util.item(Material.GOLDEN_BOOTS);

    static  {
        chest3.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        legs3.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        boots3.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
    }

    @Override
    public void reset() {

        super.clearPotionEffects();

        getPlayer().getInventory().clear();

        PlayerInventory inv = getPlayer().getInventory();

        inv.addItem(sword3);
        inv.setHelmet(helm3);
        inv.setChestplate(chest3);
        inv.setLeggings(legs3);
        inv.setBoots(boots3);
    }

    @Override
    protected void ability() {

    }

    @Override
    protected void passiveAbility() {

        Player p = getPlayer();

        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 3 * 20, 3), true);
    }

    @Override
    public void passiveItems() {

    }

}
