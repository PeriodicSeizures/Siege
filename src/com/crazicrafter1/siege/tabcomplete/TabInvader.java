package com.crazicrafter1.siege.tabcomplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class TabInvader extends BaseTabCompleter {

    public TabInvader() {
        super("invader");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        ArrayList<String> list = new ArrayList<>();

        if (args.length == 1) {
            list.add("undead_ghoul");
            list.add("zombie");
            list.add("nemesis");
        }

        return list;
    }
}
