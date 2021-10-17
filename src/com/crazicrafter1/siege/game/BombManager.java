package com.crazicrafter1.siege.game;

import com.crazicrafter1.siege.Bomb;
import com.crazicrafter1.siege.BombCarrier;
import com.crazicrafter1.siege.Main;
import com.crazicrafter1.siege.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.UUID;

public class BombManager {

    public static HashMap<Location, Bomb> bombSpawns = new HashMap<>();
    public static HashMap<UUID, BombCarrier> bombers = new HashMap<>();

    public static boolean isBomber(UUID uuid) {
        return bombers.containsKey(uuid);
    }

    public static boolean addBomber(UUID uuid) {
        if (uuid == null) return false;
        bombers.put(uuid, new BombCarrier(uuid));
        return true;
    }

    public static boolean removeBomber(UUID uuid) {
        return bombers.remove(uuid) != null;
    }

    public static boolean detonateBomber(UUID uuid) {
        if (bombers.containsKey(uuid)) {
            bombers.get(uuid).detonate();
            return true;
        }
        return false;
    }

    public static void addBomb(Location loc) {
        Location simple = Util.getSimpleLocation(loc);
        bombSpawns.put(simple, new Bomb(simple));
    }

    public static boolean removeBomb(Location loc) {
        return bombSpawns.remove(Util.getSimpleLocation(loc)) != null;
    }

    public static void resetBombs() {
        for (Bomb bomb : bombSpawns.values()) bomb.reset(); //ick();
    }

    public static boolean removeBombs() {
        if (bombSpawns.isEmpty()) return false;
        bombSpawns.clear();
        return true;
    }


    public static void save() {
        if (bombSpawns.isEmpty()) return;

        //int health = king.getHealth();

        File file = Paths.get(Main.get().getDataFolder() + "\\bombs.csv").toFile();

        if (file.exists()) file.delete();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (Bomb bomb : bombSpawns.values()) {
                Location loc = bomb.getLocation();
                writer.append(loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ());
                writer.newLine();
            }
            writer.close();

        } catch (Exception e) {e.printStackTrace();}

    }

    public static void load() {
        try {

            File file = Paths.get(Main.get().getDataFolder() + "\\bombs.csv").toFile();

            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));

                String line = "";
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");

                    Location location = new Location(null, 0, 0, 0);

                    for (int i = 0; i < data.length; i++) {

                        switch (i) {

                            case 0:
                                location.setWorld(Bukkit.getWorld(data[i]));
                            case 1:
                                if (Util.isNumeric(data[i]))
                                    location.setX(Util.safeToFloat(data[i]));
                                break;
                            case 2:
                                if (Util.isNumeric(data[i]))
                                    location.setY(Util.safeToFloat(data[i]));
                                break;
                            case 3:
                                if (Util.isNumeric(data[i]))
                                    location.setZ(Util.safeToFloat(data[i]));
                                break;
                        }
                    }

                    bombSpawns.put(location, new Bomb(location));

                }
                reader.close();
            }

        } catch (Exception e) {e.printStackTrace();}


    }

}
