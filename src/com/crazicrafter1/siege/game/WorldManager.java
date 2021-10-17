package com.crazicrafter1.siege.game;

import com.crazicrafter1.siege.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

public class WorldManager {

    public static World helmsDeep = null;
    private static Main plugin = Main.getInstance();

    public static void onEnable() {
        helmsDeep = Bukkit.getWorld("helms_deep");
    }

    /*
    public static void reset() {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            p.kickPlayer(ChatColor.DARK_GREEN + "World is resetting ...\nRejoin in a few seconds");
        }

        //Bukkit.shutdown();
        //Bukkit.getServer().

        helmsDeep.setAutoSave(false);
        //.unloadWorld(helmsDeep, false);
        Bukkit.createWorld(new WorldCreator("helms_deep").)

    }

     */

}
