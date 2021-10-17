package com.crazicrafter1.siege.tabcomplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class TabJoinTeam extends BaseTabCompleter {

    public TabJoinTeam() {
        super("jointeam");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        ArrayList<String> list = new ArrayList<>();

        if (args.length == 1) {
            list.add("defender");
            list.add("invader");
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("defender")) {
                list.add("marksman");
                list.add("knight");
            } else if (args[0].equalsIgnoreCase("invader")) {
                list.add("undead_ghoul");
                list.add("zombie");
                list.add("nemesis");
            }
        }

        return list;
    }
}
