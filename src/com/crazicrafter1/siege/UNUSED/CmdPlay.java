package com.crazicrafter1.siege.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CmdPlay extends BaseCommand {

    public CmdPlay() {
        super("play");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        return true;
        /*

        if (!(sender instanceof Player)) return error(sender, "Must be a player");

        Player p = (Player)sender;

        if (TeamAssigner.joinGame(p.getUniqueId())) return feedback(sender, "You joined the next game");
        return error(sender, "A game is already starting or started");

         */
    }
}
