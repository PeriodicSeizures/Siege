package com.crazicrafter1.siege.tabcomplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class TabKing extends BaseTabCompleter {

    public TabKing() {
        super("king");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        ArrayList<String> list = new ArrayList<>();

        if (args.length == 1) {
            list.add("set");
            list.add("summon");
        }

        return list;
    }
}
