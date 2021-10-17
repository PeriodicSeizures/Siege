package com.crazicrafter1.siege.commands;

import com.crazicrafter1.siege.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class BaseCommand implements CommandExecutor {

    protected static Main plugin = Main.get();

    public BaseCommand(String name) {
        plugin.getCommand(name).setExecutor(this);
    }

    @Override
    public abstract boolean onCommand(CommandSender sender, Command command, String label, String[] args);

    static boolean error(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.DARK_GREEN + "[Siege] " + ChatColor.RED + " ERROR : " + message);
        return true;
    }

    static boolean feedback(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.DARK_GREEN + "[Siege] " + ChatColor.DARK_AQUA + message);
        return true;
    }

}
