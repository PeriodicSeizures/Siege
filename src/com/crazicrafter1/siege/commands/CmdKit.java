package com.crazicrafter1.siege.commands;

import com.crazicrafter1.siege.game.GameManager;
import com.crazicrafter1.siege.game.TeamAssigner;
import com.crazicrafter1.siege.game.team.Team;
import com.crazicrafter1.siege.menu.KitMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdKit extends BaseCommand {

    public CmdKit() {
        super("kit");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return error(sender, "Must be a player");

        Player p = (Player)sender;
        /*
        if (args.length == 0) return false;

        if (GameManager.gameState == GameManager.State.PREGAME) {
            try {
                Team.Kit kit = Team.Kit.valueOf(args[0].toUpperCase());
                TeamAssigner.setKit(((Player) sender).getUniqueId(), kit);
                return feedback(sender, "Set kit to " + kit.name().toLowerCase());
            } catch (Exception e) {
                return false;
            }
        }
        return error(sender, "Its not the time for that");
         */

        // Open KIT menu


        if (GameManager.gameState == GameManager.State.PREGAME) {
            try {
                KitMenu.menus.put(p.getUniqueId(), new KitMenu(p));
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        return error(sender, "Its not the time for that");
    }
}
