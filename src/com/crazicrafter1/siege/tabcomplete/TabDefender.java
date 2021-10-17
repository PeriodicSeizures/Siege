package com.crazicrafter1.siege.tabcomplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class TabDefender extends BaseTabCompleter {

    public TabDefender() {
        super("defender");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        ArrayList<String> list = new ArrayList<>();

        if (args.length == 1) {
            list.add("marksman");
            list.add("knight");
        }

        return list;
    }
}
