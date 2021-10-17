package com.crazicrafter1.siege.commands;

import com.crazicrafter1.siege.util.Util;
import com.crazicrafter1.siege.game.SpawnManager;
import com.crazicrafter1.siege.game.team.Team;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdSpawn extends BaseCommand {

    public CmdSpawn() {
        super("spawn");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        /*
            add a spawn marker for a team
         */

        if (args.length != 2) return false;

        if (!(sender instanceof Player)) return error(sender, "Must be a player");

        try {
            SpawnManager.spawns.get(Team.Type.valueOf(args[1].toUpperCase())).add(
                    Util.getSimpleLocation(((Player) sender).getLocation()));

            return feedback(sender, "Added spawn for " + args[1] + " at location.");
        } catch (Exception e) {return error(sender, "An error occurred");}
    }
}
