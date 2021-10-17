package com.crazicrafter1.siege.menu;

import com.crazicrafter1.crutils.ItemBuilder;
import com.crazicrafter1.siege.game.team.Team;
import com.crazicrafter1.siege.game.team.defender.Knight;
import com.crazicrafter1.siege.game.team.defender.Marksman;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

class KitMenuData {


    static ItemStack d1 = new ItemBuilder(Material.BOW).name("&b&lMarksman").lore(new String[] {"&7 - One arrow every &8" + String.format("%.1f", Marksman.arrowDelay/1000.0) + "&7s",
            "&7 - Chainmail armor"}, true).toItem();

    static ItemStack d2 = new ItemBuilder(Material.IRON_SWORD).name("&3&lKnight").lore(new String[] {  "&7 - One arrow every &8" + String.format("%.1f", Knight.arrowDelay/1000.0) + "&7s",
            "&7 - One barricade every &8" + String.format("%.1f", Knight.fenceDelay/1000.0) + "&7s",
            "&7 - Iron armor"}, true).hideFlags(ItemFlag.HIDE_ATTRIBUTES).toItem();

    static ItemStack i1 = new ItemBuilder(Material.STONE_SWORD).name("&4&lUndead Ghoul").lore(new String[] {  "&7 - Permanent speed",
            "&7 - Leap ability"}, true).hideFlags(ItemFlag.HIDE_ATTRIBUTES).toItem();

    static ItemStack i2 = new ItemBuilder(Material.ROTTEN_FLESH).name("&c&lZombie").lore(new String[] {  "&7 - Permanent regeneration III",
            "&7 - Fortification ability"}, true).toItem();

    static ItemStack i3 = new ItemBuilder(Material.GOLDEN_SWORD).name("&8&lNemesis").lore(new String[] {  "&7 - Permanent regeneration I",
            "&7 - Vile ability"}, true).hideFlags(ItemFlag.HIDE_ATTRIBUTES).toItem();

    static HashMap<Integer, ItemStack> itemBySlot = new HashMap<>(); //new HashMap<>(Arrays.asList(1, 7, 13, 16, 19, 25));
    static HashMap<Team.Kit, ItemStack> itemByKit = new HashMap<>(); //new HashMap<>(Arrays.asList(1, 7, 13, 16, 19, 25));
    static HashMap<Integer, Team.Kit> kitBySlot = new HashMap<>();
    static HashMap<Team.Kit, Integer> slotByKit = new HashMap<>();

    static {
        // Defender side
        itemBySlot.put(1, d1);
        itemBySlot.put(19, d2);

        // Invader side
        itemBySlot.put(7, i1);
        itemBySlot.put(16, i2);
        itemBySlot.put(25, i3);



        itemByKit.put(Team.Kit.MARKSMAN, d1);
        itemByKit.put(Team.Kit.KNIGHT, d2);
        itemByKit.put(Team.Kit.UNDEAD_GHOUL, i1);
        itemByKit.put(Team.Kit.ZOMBIE, i2);
        itemByKit.put(Team.Kit.NEMESIS, i3);


        kitBySlot.put(1, Team.Kit.MARKSMAN);
        kitBySlot.put(19, Team.Kit.KNIGHT);
        kitBySlot.put(7, Team.Kit.UNDEAD_GHOUL);
        kitBySlot.put(16, Team.Kit.ZOMBIE);
        kitBySlot.put(25, Team.Kit.NEMESIS);


        slotByKit.put(Team.Kit.MARKSMAN     ,1);
        slotByKit.put(Team.Kit.KNIGHT       ,19);
        slotByKit.put(Team.Kit.UNDEAD_GHOUL ,7);
        slotByKit.put(Team.Kit.ZOMBIE       ,16);
        slotByKit.put(Team.Kit.NEMESIS      ,25);

    }


    protected static ItemStack[] items = new ItemStack[27]; // = Bukkit.createInventory(null, 27, "");

    private static int pitchToX(int pitch, int w) {
        return pitch%w;
    }

    private static int pitchToY(int pitch, int h) {
        return pitch/h;
    }

    private static ItemStack left = new ItemBuilder(Material.LIGHT_BLUE_STAINED_GLASS_PANE).name(" ").toItem();
    private static ItemStack mid = new ItemBuilder(Material.LIME_STAINED_GLASS_PANE).name(" ").toItem();
    private static ItemStack right = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).name(" ").toItem();

    static Inventory getDefaultInventory() {
        Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Select Kit");

        inventory.setContents(items);

        return inventory;
    }

    static  {

        //defaultInventory = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Select Kit");

        for (int i=0; i<27; i++) {
            if (KitMenuData.itemBySlot.containsKey(i)) continue;
            if (pitchToX(i, 9) <= 2) items[i] = left;
            else if (pitchToX(i, 9) >= 6) items[i] = right;
            else items[i] = mid;
        }

        for (int i : KitMenuData.itemBySlot.keySet()) {
            items[i] = KitMenuData.itemBySlot.get(i);
        }

        /*
        ItemStack selectedKit = null; //(kit == Team.Kit.MARKSMAN) ?
        switch (kit) {
            case MARKSMAN:          selectedKit = KitMenuData.d1; break;
            case KNIGHT:            selectedKit = KitMenuData.d2; break;
            case UNDEAD_GHOUL:      selectedKit = KitMenuData.i1; break;
            case ZOMBIE:            selectedKit = KitMenuData.i2; break;
            case NEMESIS:           selectedKit = KitMenuData.i3; break;

        }

        if (selectedKit != null) {
            inventory.setItem(kitSlot, selectedKit);
        }

         */
    }

}
