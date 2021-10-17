package com.crazicrafter1.siege.commands;

import com.crazicrafter1.siege.Util;
import com.crazicrafter1.siege.game.GameManager;
import com.crazicrafter1.siege.Main;
import com.crazicrafter1.siege.game.TeamAssigner;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CmdSiege extends BaseCommand {

    public CmdSiege() {
        super("siege");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) return false; //error(sender, "Input arguments");

        switch (args[0].toLowerCase()) {

            case "reload":
                Main.getInstance().reloadConfig();
                return true;
            case "game": {

                if (args.length == 1) return false;

                if (args[1].equalsIgnoreCase("start")) {

                    if (args.length == 3) {
                        try {
                            GameManager.preStartGame(Util.safeToInt(args[2]));
                            return true;
                            //return feedback(sender, "");
                        } catch (Exception e) { return error(sender, "Input a valid time as integer"); }
                    } else GameManager.preStartGame(60);

                    return true;
                }

                if (args[1].equalsIgnoreCase("stop")) {
                    GameManager.stopGame(null);
                    return true;
                }

                if (args[1].equalsIgnoreCase("time")) {
                    if (args.length == 3) {
                        try {
                            long t = Util.safeToFloat(args[2]).longValue();
                            GameManager.gameStart = System.currentTimeMillis() - t*60*1000;
                            return true;
                        } catch (Exception e) {return error(sender, "Enter time as a decimal in minutes.");}
                    }
                    return false;
                }
            }

            default: return false;

        }
    }
}
