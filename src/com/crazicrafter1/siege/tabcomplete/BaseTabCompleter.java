package com.crazicrafter1.siege.tabcomplete;

import com.crazicrafter1.siege.Main;
import com.mysql.fabric.xmlrpc.base.Array;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class BaseTabCompleter implements TabCompleter {

    protected static Main plugin = Main.getInstance();

    public BaseTabCompleter(String name) {
        plugin.getCommand(name).setTabCompleter(this);
    }

    @Override
    public abstract List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args);

    /*
    protected ArrayList<String> auto(String arg, String[] args) {

        ArrayList<String> list = new ArrayList<>();

        for (String s : args) {
            if (arg.length() > 0) {
                if (s.equalsIgnoreCase(arg)) return new ArrayList<>(Collections.singletonList(s));
                else if (s.length() > arg.length()){

                    if (s.substring(0, arg.length()+1).equals(arg))
                        list.add(s);
                }
            } else list.add(s);
        }

        return list;
    }

     */
}
