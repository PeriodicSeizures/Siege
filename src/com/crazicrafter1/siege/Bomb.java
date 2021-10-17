package com.crazicrafter1.siege;

import com.crazicrafter1.siege.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import java.io.*;
import java.nio.file.Paths;

public class Bomb {

    private Location location;
    private long lastTaken = System.currentTimeMillis();

    public Bomb(Location location) {
        this.location = location;
    }

    public void tick() {
        if (location.getWorld().getBlockAt(location).getType() == Material.TNT) {
            lastTaken = System.currentTimeMillis();
        } else if (System.currentTimeMillis() - 20*1000 >= lastTaken) {

            lastTaken = System.currentTimeMillis();
            reset();

        }
    }

    public void reset() {
        location.getWorld().getBlockAt(location).setType(Material.TNT);
    }

    public Location getLocation() {
        return location;
    }



}
