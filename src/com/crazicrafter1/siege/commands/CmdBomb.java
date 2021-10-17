package com.crazicrafter1.siege.commands;

import com.crazicrafter1.siege.game.BombManager;
import com.crazicrafter1.siege.game.GameManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdBomb extends BaseCommand {

    public CmdBomb() {
        super("bomb");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return error(sender, "Must be a player");

        if (args.length == 0) return false;

        Player p = (Player)sender;

        switch (args[0]) {
            case "add":
                BombManager.addBomb(p.getLocation());
                break;
            case "remove":
                BombManager.removeBomb(p.getLocation());
                break;
            case "resetall":
                //GameManager.re...
                BombManager.resetBombs();
                break;
            case "removeall":
                BombManager.removeBombs();
                break;
            default: return error(sender, "Invalid argument");
        }

        return true;
    }
}
