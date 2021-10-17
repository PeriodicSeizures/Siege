package com.crazicrafter1.siege.commands;

import com.crazicrafter1.siege.game.GameManager;
import com.crazicrafter1.siege.game.TeamAssigner;
import com.crazicrafter1.siege.game.TeamUtils;
import com.crazicrafter1.siege.game.TempPlayer;
import com.crazicrafter1.siege.game.team.Team;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdDefender extends BaseCommand {

    public CmdDefender() {
        super("defender");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return error(sender, "Must be a player");

        if (args.length == 0) return false; //error(sender, "");

        Player p = (Player)sender;

        Team.Kit kit;

        try {
            kit = Team.Kit.valueOf(args[0].toUpperCase());
        } catch (Exception e) { return false; }

        Team team = TeamUtils.matchTeam(p.getUniqueId(), Team.Type.DEFENDER, kit);

        if (team == null) return error(sender, "Invalid kit");

        int success = TeamAssigner.addToQueue(new TempPlayer(p.getUniqueId(), team.getType(), team.getKit()));

        if (success != -1)
            return feedback(p, "Number " + success + " in queue for " + ChatColor.AQUA + "defenders" + ChatColor.DARK_AQUA + " with kit " + ChatColor.YELLOW + team.getKit().name().toLowerCase());
        return error(sender, "Was unable to add you to queue");
    }
}
