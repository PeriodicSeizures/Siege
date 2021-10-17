package com.crazicrafter1.siege.commands;

import com.crazicrafter1.siege.game.GameManager;
import com.crazicrafter1.siege.game.TeamUtils;
import com.crazicrafter1.siege.game.team.Team;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdJoinTeam extends BaseCommand {

    public CmdJoinTeam() {
        super("jointeam");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // execute
        if (!(sender instanceof Player)) return error(sender, "Only a player can execute this command");

        if (args.length != 2) return false; //error(sender, "Input team");

        Player p = (Player)sender;

        Team team; //GameManager.matchTeam();

        try {
            team = TeamUtils.matchTeam(p.getUniqueId(),
                    Team.Type.valueOf(args[0].toUpperCase()),
                    Team.Kit.valueOf(args[1].toUpperCase()));
            GameManager.addPlayer(team);

        } catch (Exception e) {return error(sender, "" + e.getMessage());}

        if (team == null) return error(sender, "Invalid team");

        //GameManager.addToGame(team);

        //team.reset(); //p.getInventory().addItem(new ItemStack(Material.));

        return feedback(sender, "You were put on team " + team.getType().name().toLowerCase() + " with kit " + team.getKit().name().toLowerCase());
    }
}
