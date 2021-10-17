package com.crazicrafter1.siege.listeners;

import com.crazicrafter1.siege.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class BaseListener implements Listener {

    protected Main plugin = Main.get();

    public BaseListener() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

}
