package com.crazicrafter1.siege.game;

import com.crazicrafter1.siege.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

public class WorldManager {

    public static World helmsDeep = null;
    private static Main plugin = Main.get();

    public static void onEnable() {
        helmsDeep = Bukkit.getWorld("helms_deep");
        //helmsDeep.setAutoSave(false);
    }


    public static void reset() {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            p.kickPlayer(ChatColor.DARK_GREEN + "World is resetting ...\nRejoin in a few seconds");
        }

        //Bukkit.shutdown();
        //Bukkit.getServer().

        //.unloadWorld(helmsDeep, false);
        helmsDeep = Bukkit.createWorld(new WorldCreator("helms_deep"));

    }
}
