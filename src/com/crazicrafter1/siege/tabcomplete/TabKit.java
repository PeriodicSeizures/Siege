package com.crazicrafter1.siege.tabcomplete;

import com.crazicrafter1.siege.game.GameManager;
import com.crazicrafter1.siege.game.TeamAssigner;
import com.crazicrafter1.siege.game.team.Team;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TabKit extends BaseTabCompleter {

    public TabKit() {
        super("kit");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return null;

        ArrayList<String> list = new ArrayList<>();

        Player p = (Player)sender;


        if (GameManager.gameState == GameManager.State.PREGAME) {
            if (args.length == 1 && TeamAssigner.isAssigned(p.getUniqueId())) {
                if (TeamAssigner.getAssigned(p.getUniqueId()).getType() == Team.Type.INVADER) {
                    list.add("undead_ghoul");
                    list.add("zombie");
                    list.add("nemesis");
                } else {
                    list.add("marksman");
                    list.add("knight");
                }
            }
        }

        return list;
    }
}
