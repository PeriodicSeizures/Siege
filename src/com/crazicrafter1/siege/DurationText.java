package com.crazicrafter1.siege;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import com.crazicrafter1.siege.util.Util;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class DurationText {

    public static void display(Player p, String text, int current, int time) {

        StringBuilder s = new StringBuilder("████████████████████");

        // get the fraction between current and time as a ratio
        int i = Util.clamp((int) (s.length()*((float)current)/((float)time)), 1, s.length());

        //if (i != 0) s..setCharAt(0, ChatColor.GREEN);

        s.insert(i, ChatColor.RED);
        s.insert(0, ChatColor.GREEN);
        ActionBarAPI.sendActionBar(p, text + " " + s.toString() + ChatColor.RESET + " " +
                String.format("%.01f/%.01f", (current/20.0), (time/20.0)));

    }

}
