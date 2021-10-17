package com.crazicrafter1.siege.commands;

import com.crazicrafter1.siege.game.GameManager;
import com.crazicrafter1.siege.game.King;
import com.crazicrafter1.siege.game.KingManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdKing extends BaseCommand {

    public CmdKing() {
        super("king");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return error(sender, "Only a player can execute this command");

        if (args.length == 0) return false;

        switch (args[0]) {
            case "summon":
                if (KingManager.king != null)
                    KingManager.king.summonEntity();
                break;
            case "set":
                KingManager.king = new King(((Player) sender).getLocation());
                KingManager.save();
                break;
            default:
                return error(sender, "Invalid argument");


        }
        //Main.king = new King(((Player) sender).getLocation());

        //Main.startGame();

        return true;
    }
}
