package com.crazicrafter1.siege.game;

import com.crazicrafter1.siege.Main;
import com.crazicrafter1.siege.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.*;
import java.nio.file.Paths;

public class KingManager {

    public static King king = null;
    public static Location location = null;

    public static String[] kings; // = new String[];

    public static void onEnable() {
        File file = Paths.get(Main.getInstance().getDataFolder() + "\\kings.csv").toFile();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            kings = reader.readLine().split(",");
            reader.close();
        } catch (Exception e) {e.printStackTrace();}

        load();
    }

    public static void onDisable() {
        if (KingManager.king != null)
            KingManager.king.killEntity();
        //save();
    }

    public static void save() {
        if (king == null) return;

        Location loc = king.getLocation();
        //int health = king.getHealth();

        File file = Paths.get(Main.getInstance().getDataFolder() + "\\king.csv").toFile();

        if (file.exists()) file.delete();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            writer.append(loc.getWorld().getName() + "," + loc.getX() + "," + loc.getY() + "," + loc.getZ() + "," + loc.getYaw() + "," + loc.getPitch());

            writer.close();

        } catch (Exception e) {e.printStackTrace();}

    }

    private static void load() {
        try {

            File file = Paths.get(Main.getInstance().getDataFolder() + "\\king.csv").toFile();

            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));

                String[] data = reader.readLine().split(","); //(loc.getWorld() + "," + loc.getX() + "," + loc.getY() + "," + loc.getZ());

                location = new Location(null, 0, 0, 0, 0, 0);

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
                        case 4:
                            if (Util.isNumeric(data[i]))
                                location.setYaw(Util.safeToFloat(data[i]));
                            break;
                        case 5:
                            if (Util.isNumeric(data[i]))
                                location.setPitch(Util.safeToFloat(data[i]));
                            break;

                    }

                }

                king = new King(location);

                reader.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
