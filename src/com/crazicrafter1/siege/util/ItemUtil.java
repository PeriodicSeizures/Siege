package com.crazicrafter1.siege.util;

import net.minecraft.server.v1_14_R1.*;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_14_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemUtil {

    public static ItemStack removeGlow(ItemStack item){
        net.minecraft.server.v1_14_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = null;
        if (!nmsStack.hasTag()) {
            tag = new NBTTagCompound();
            nmsStack.setTag(tag);
        }
        if (tag == null) tag = nmsStack.getTag();
        //NBTTagList ench = new NBTTagList();
        //tag.set("Enchantments", ench);
        tag.remove("Enchantments");
        nmsStack.setTag(tag);
        return CraftItemStack.asCraftMirror(nmsStack);
    }

    public static void addGlow(ItemStack item){
        item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 0);
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
    }

    public static ItemStack makeFastItem(ItemStack item){
        net.minecraft.server.v1_14_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);

        NBTTagCompound nbt = nmsStack.hasTag() ? nmsStack.getTag() : new NBTTagCompound();

        NBTTagList nbtTags = new NBTTagList();
        NBTTagCompound speed = new NBTTagCompound();

        speed.set("AttributeName", new NBTTagString("generic.attackSpeed"));
        speed.set("Name", new NBTTagString("Blah"));
        speed.set("Amount", new NBTTagDouble(9.8));
        speed.set("Operation", new NBTTagInt(0));
        speed.set("UUIDLeast", new NBTTagInt(1));
        speed.set("UUIDMost", new NBTTagInt(1));

        nbtTags.add(speed);
        nbt.set("AttributeModifiers", nbtTags);
        nmsStack.setTag(nbt);
        return CraftItemStack.asCraftMirror(nmsStack);
    }


    public static Enchantment matchEnchant(String enchant)
    {
        String e = enchant.toUpperCase();

        if (e.equals("DURABILITY") || e.equals("UNBREAKING"))
            return org.bukkit.enchantments.Enchantment.DURABILITY;
        if (e.equals("ARROW_DAMAGE") || e.equals("POWER"))
            return org.bukkit.enchantments.Enchantment.ARROW_DAMAGE;
        if (e.equals("ARROW_FIRE") || e.equals("FLAME"))
            return org.bukkit.enchantments.Enchantment.ARROW_FIRE;
        if (e.equals("ARROW_INFINITE") || e.equals("INFINITY"))
            return org.bukkit.enchantments.Enchantment.ARROW_INFINITE;
        if (e.equals("ARROW_KNOCKBACK") || e.equals("PUNCH"))
            return org.bukkit.enchantments.Enchantment.ARROW_KNOCKBACK;
        if (e.equals("BINDING_CURSE") || e.equals("CURSE_OF_BINDING"))
            return org.bukkit.enchantments.Enchantment.BINDING_CURSE;
        if (e.equals("CHANELLING"))
            return org.bukkit.enchantments.Enchantment.CHANNELING;
        if (e.equals("DAMAGE_ALL") || e.equals("SHARPNESS"))
            return org.bukkit.enchantments.Enchantment.DAMAGE_ALL;
        if (e.equals("DAMAGE_ANTHROPODS") || e.equals("BANE_OF_ANTHROPODS"))
            return org.bukkit.enchantments.Enchantment.DAMAGE_ARTHROPODS;
        if (e.equals("DAMAGE_UNDEAD") || e.equals("SMITE"))
            return org.bukkit.enchantments.Enchantment.DAMAGE_UNDEAD;
        if (e.equals("DEPTH_STRIDER"))
            return org.bukkit.enchantments.Enchantment.DEPTH_STRIDER;
        if (e.equals("DIG_SPEED") || e.equals("EFFICIENCY"))
            return org.bukkit.enchantments.Enchantment.DIG_SPEED;
        if (e.equals("FIRE_ASPECT"))
            return org.bukkit.enchantments.Enchantment.FIRE_ASPECT;
        if (e.equals("FROST_WALKER"))
            return org.bukkit.enchantments.Enchantment.FROST_WALKER;
        if (e.equals("IMPALING"))
            return org.bukkit.enchantments.Enchantment.IMPALING;
        if (e.equals("KNOCKBACK"))
            return org.bukkit.enchantments.Enchantment.KNOCKBACK;
        if (e.equals("LOOT_BONUS_BLOCKS") || e.equals("FORTUNE"))
            return org.bukkit.enchantments.Enchantment.LOOT_BONUS_BLOCKS;
        if (e.equals("LOOT_BONUS_MOBS") || e.equals("LOOTING"))
            return org.bukkit.enchantments.Enchantment.LOOT_BONUS_MOBS;
        if (e.equals("LOYALTY"))
            return org.bukkit.enchantments.Enchantment.LOYALTY;
        if (e.equals("LUCK") || e.equals("LUCK_OF_THE_SEA"))
            return org.bukkit.enchantments.Enchantment.LUCK;
        if (e.equals("LURE"))
            return org.bukkit.enchantments.Enchantment.LURE;
        if (e.equals("MENDING"))
            return org.bukkit.enchantments.Enchantment.MENDING;
        if (e.equals("MULTISHOT"))
            return org.bukkit.enchantments.Enchantment.MULTISHOT;
        if (e.equals("OXYGEN") || e.equals("RESPIRATION"))
            return org.bukkit.enchantments.Enchantment.OXYGEN;
        if (e.equals("PIERCING"))
            return org.bukkit.enchantments.Enchantment.PIERCING;
        if (e.equals("PROTECTION_ENVIRONMENTAL") || e.equals("PROTECTION"))
            return org.bukkit.enchantments.Enchantment.PROTECTION_ENVIRONMENTAL;
        if (e.equals("PROTECTION_FIRE") || e.equals("FIRE_PROTECTION"))
            return org.bukkit.enchantments.Enchantment.PROTECTION_FIRE;
        if (e.equals("PROTECTION_FALL") || e.equals("FEATHER_FALLING"))
            return  org.bukkit.enchantments.Enchantment.PROTECTION_FALL;
        if (e.equals("PROTECTION_EXPLOSIONS") || e.equals("BLAST_PROTECTION"))
            return org.bukkit.enchantments.Enchantment.PROTECTION_EXPLOSIONS;
        if (e.equals("PROTECTION_PROJECTILE") || e.equals("PROJECTILE_PROTECTION"))
            return org.bukkit.enchantments.Enchantment.PROTECTION_PROJECTILE;
        if (e.equals("QUICK_CHARGE"))
            return org.bukkit.enchantments.Enchantment.QUICK_CHARGE;
        if (e.equals("RIPTIDE"))
            return org.bukkit.enchantments.Enchantment.RIPTIDE;
        if (e.equals("SILK_TOUCH"))
            return org.bukkit.enchantments.Enchantment.SILK_TOUCH;
        if (e.equals("SWEEPING_EDGE"))
            return org.bukkit.enchantments.Enchantment.SWEEPING_EDGE;
        if (e.equals("THORNS"))
            return org.bukkit.enchantments.Enchantment.THORNS;
        if (e.equals("VANISHING_CURSE") || e.equals("CURSE_OF_VANISHING"))
            return org.bukkit.enchantments.Enchantment.VANISHING_CURSE;
        if (e.equals("WATER_WORKER") || e.equals("AQUA_AFFINITY"))
            return Enchantment.WATER_WORKER;
        return null;
    }

    public static ItemStack customItem(@Nonnull Material material, String name, String[] lores) {

        ItemStack item = new ItemStack(material);
        setName(item, name);
        setLore(item, lores);

        return item;
    }

    public static ItemStack blankItem(Material material) {
        return customItem(material, " ", null);
    }

    public static ItemStack item(Material material) {
        ItemStack item = new ItemStack(material);
        setUnbreakable(item);

        /*
        if (isTool(item)) {
            setDamage(item, 20);
            System.out.println("Is a tool " + material.name());
        }
         */

        //item.g
        return item;
    }

    public static boolean isTool(ItemStack item) {
        String n = item.getType().name().toLowerCase();
        //System.out.println(n);
        return n.contains("pickaxe") || n.contains("shovel") || n.contains("hoe") || n.contains("axe") || n.contains("sword");
    }

    @Deprecated
    public static void setDamage(ItemStack item, int i) {

        net.minecraft.server.v1_14_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = nmsStack.getOrCreateTag(); //(nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();

        NBTTagList modifiers = new NBTTagList();
        NBTTagCompound damage = new NBTTagCompound();

        damage.set("AttributeName", new NBTTagString("generic.attackDamage"));
        damage.set("Name", new NBTTagString("generic.attackDamage"));
        damage.set("Amount", new NBTTagInt(20));
        damage.set("Operation", new NBTTagInt(0));
        damage.set("UUIDLeast", new NBTTagInt(894654));
        damage.set("UUIDMost", new NBTTagInt(2872));

        //damage.set("Slot", "");

        modifiers.add(damage);
        compound.set("AttributeModifiers", modifiers);
        nmsStack.setTag(compound);
        item = CraftItemStack.asBukkitCopy(nmsStack);
    }

    public static void dyeArmor(ItemStack item, int r, int g, int b) {

        if (item.getItemMeta() instanceof LeatherArmorMeta) {

            LeatherArmorMeta meta = ((LeatherArmorMeta)item.getItemMeta());

            meta.setColor(Color.fromRGB(r, g, b));
            item.setItemMeta(meta);
        }

    }

    public static void dyeArmor(ItemStack item, Color color) {

        if (item.getItemMeta() instanceof LeatherArmorMeta) {

            LeatherArmorMeta meta = ((LeatherArmorMeta)item.getItemMeta());

            meta.setColor(color);
            item.setItemMeta(meta);
        }

    }

    public static void setUnbreakable(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
    }


    public static void setName(ItemStack item, String name)
    {
        /*
        name
         */
        if (name == null) return;
        ItemMeta meta = item.getItemMeta();

        //noinspection ConstantConditions
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&r"+name));
        item.setItemMeta(meta);
    }


    public static void setLore(ItemStack item, List<String> lores)
    {
        if (lores == null) return;

        ItemMeta meta = item.getItemMeta();

        ArrayList<String> loreList = new ArrayList<>(lores);

        for (int i=0; i<loreList.size(); i++)
        {
            loreList.set(i, ChatColor.translateAlternateColorCodes('&', "&r"+loreList.get(i)));
        }

        meta.setLore(loreList);

        item.setItemMeta(meta);
    }

    public static void setLore(ItemStack item, String[] lores)
    {
        if (lores == null) return;

        ItemMeta meta = item.getItemMeta();

        ArrayList<String> loreList = new ArrayList<>(Arrays.asList(lores));

        for (int i=0; i<loreList.size(); i++)
        {
            loreList.set(i, ChatColor.translateAlternateColorCodes('&', "&r"+loreList.get(i)));
        }

        meta.setLore(loreList);

        item.setItemMeta(meta);
    }

}
