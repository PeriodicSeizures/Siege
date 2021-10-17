package com.crazicrafter1.siege.tabcomplete;

import com.crazicrafter1.siege.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class TabSiege extends BaseTabCompleter {

    public TabSiege() {
        super("siege");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        ArrayList<String> list = new ArrayList<>();

        System.out.println("Args length : " + args.length);

        if (args.length == 1) {

            String[] temp = new String[] {"game", "reload"};

            for (String s : temp) {
                if (s.equalsIgnoreCase(args[0])) list.add(s);
                else if (s.contains(args[0])) list.add(s);
            }

        } else if (args.length == 2) {

            if (args[0].equalsIgnoreCase("game")) {
                String[] temp = new String[]{"start", "stop", "time"};

                for (String s : temp) {
                    if (s.equalsIgnoreCase(args[1])) list.add(s);
                    else if (s.contains(args[1])) list.add(s);
                }
            }


        } /*else if (args.length == 3) {

            if (args[2].equalsIgnoreCase("start") || args[2].equalsIgnoreCase("time")) list.add("")
            String[] temp = new String[] {"game", "reload"};

            for (String s : temp) {
                if (s.equalsIgnoreCase(args[0])) list.add(s);
                else if (s.contains(args[0])) list.add(s);
            }

        }
        */

        return list;
    }
}
