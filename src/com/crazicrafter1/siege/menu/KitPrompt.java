package com.crazicrafter1.siege.menu;

import com.crazicrafter1.crutils.ItemBuilder;
import com.crazicrafter1.gapi.Button;
import com.crazicrafter1.gapi.SimpleMenu;
import com.crazicrafter1.siege.game.TeamAssigner;
import com.crazicrafter1.siege.game.team.Team;
import com.crazicrafter1.siege.game.team.defender.Knight;
import com.crazicrafter1.siege.game.team.defender.Marksman;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KitPrompt {

    static ItemStack MARKSMAN_ITEM = new ItemBuilder(Material.BOW).name("&b&lMarksman").lore(new String[] {"&7 - One arrow every &8" + String.format("%.1f", Marksman.ARROW_DELAY /1000.0) + "&7s",
            "&7 - Chainmail armor"}, true).toItem();

    static ItemStack KNIGHT_ITEM = new ItemBuilder(Material.IRON_SWORD).name("&3&lKnight").lore(new String[] {  "&7 - One arrow every &8" + String.format("%.1f", Knight.ARROW_DELAY /1000.0) + "&7s",
            "&7 - One barricade every &8" + String.format("%.1f", Knight.FENCE_DELAY /1000.0) + "&7s",
            "&7 - Iron armor"}, true).toItem();

    // WIP
    static ItemStack BOMBER_ITEM = new ItemBuilder(Material.TNT).name("&3&lKnight").lore(new String[] {  "&7 - One arrow every &8" + String.format("%.1f", Knight.ARROW_DELAY /1000.0) + "&7s",
            "&7 - One barricade every &8" + String.format("%.1f", Knight.FENCE_DELAY /1000.0) + "&7s",
            "&7 - Iron armor"}, true).toItem();

    static ItemStack GHOUL_ITEM = new ItemBuilder(Material.STONE_SWORD).name("&4&lUndead Ghoul").lore(new String[] {  "&7 - Permanent speed",
            "&7 - Leap ability"}, true).toItem();

    static ItemStack ZOMBIE_ITEM = new ItemBuilder(Material.ROTTEN_FLESH).name("&c&lZombie").lore(new String[] {  "&7 - Permanent regeneration III",
            "&7 - Fortification ability"}, true).toItem();

    static ItemStack NEMESIS_ITEM = new ItemBuilder(Material.GOLDEN_SWORD).name("&8&lNemesis").lore(new String[] {  "&7 - Permanent regeneration I",
            "&7 - Vile ability"}, true).toItem();

    public static void open(Player p) {

        new SimpleMenu.SBuilder(5)
                .title("Select kit")
                .background()
                // Defenders
                .button(1, 1,
                        new Button.Builder()
                                .icon(() -> MARKSMAN_ITEM)
                                .lmb(interact -> TeamAssigner.setKit(interact.player.getUniqueId(), Team.Kit.MARKSMAN)))
                .button(1, 2, new Button.Builder()
                        .icon(() -> KNIGHT_ITEM)
                        .lmb(interact -> TeamAssigner.setKit(interact.player.getUniqueId(), Team.Kit.KNIGHT)))
                .button(1, 3, new Button.Builder()
                        .icon(() -> BOMBER_ITEM)
                        .lmb(interact -> TeamAssigner.setKit(interact.player.getUniqueId(), Team.Kit.BOMBER)))
                // Invaders
                .button(7, 1, new Button.Builder()
                        .icon(() -> GHOUL_ITEM)
                        .lmb(interact -> TeamAssigner.setKit(interact.player.getUniqueId(), Team.Kit.BOMBER)))
                .button(7, 2, new Button.Builder()
                        .icon(() -> ZOMBIE_ITEM)
                        .lmb(interact -> TeamAssigner.setKit(interact.player.getUniqueId(), Team.Kit.ZOMBIE)))
                .button(7, 3, new Button.Builder()
                        .icon(() -> NEMESIS_ITEM)
                        .lmb(interact -> TeamAssigner.setKit(interact.player.getUniqueId(), Team.Kit.NEMESIS)))
                .open(p);

    }

}
