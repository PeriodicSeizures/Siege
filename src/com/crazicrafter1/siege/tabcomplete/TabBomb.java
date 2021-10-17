package com.crazicrafter1.siege.tabcomplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class TabBomb extends BaseTabCompleter {

    public TabBomb() {
        super("bomb");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        ArrayList<String> list = new ArrayList<>();

        if (args.length == 1) {
            list.add("resetall");
            list.add("add");
            list.add("removeall");
            list.add("remove");
        }

        return list;
    }
}
