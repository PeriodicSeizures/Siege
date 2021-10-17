package com.crazicrafter1.siege.commands;

import com.crazicrafter1.siege.util.ItemBuilder;
import com.crazicrafter1.siege.util.ItemUtil;
import com.crazicrafter1.siege.util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CmdGlow extends BaseCommand {

    public CmdGlow() {
        super("glow");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return error(sender, "Must be a player");

        Player p = (Player)sender;

        ItemStack item = p.getInventory().getItemInMainHand();

        //item = ItemUtil.makeFastItem(item); //.addGlow(item);
        //item = ItemUtil.addGlow(item);
        //ItemUtil.hideFlag(item);
        p.getInventory().setItemInMainHand(ItemBuilder.builder(item).glow().toItem());

        p.getInventory().setItemInMainHand(item);

        return false;
    }
}
