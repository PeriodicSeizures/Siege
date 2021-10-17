package com.crazicrafter1.siege.game;

import com.crazicrafter1.siege.Main;
import com.crazicrafter1.siege.util.Util;
import com.crazicrafter1.siege.game.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class SpawnManager {

    //public static ArrayListMultimap<Team.Type, Location> spawns = ArrayListMultimap.create();
    public static HashMap<Team.Type, ArrayList<Location>> spawns = new HashMap<>();

    static {
        spawns.put(Team.Type.DEFENDER, new ArrayList<>());
        spawns.put(Team.Type.INVADER, new ArrayList<>());
    }

    public static void save() {
        if (spawns.isEmpty()) return;

        //int health = king.getHealth();

        File file = Paths.get(Main.getInstance().getDataFolder() + "\\spawns.csv").toFile();

        if (file.exists()) file.delete();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (Team.Type type : spawns.keySet()) {
                for (Location loc : spawns.get(type)) {
                    writer.append(type.name() + "," + loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ());
                    writer.newLine();
                }
            }
            writer.close();

        } catch (Exception e) {e.printStackTrace();}

    }

    public static void load() {
        try {

            File file = Paths.get(Main.getInstance().getDataFolder() + "\\spawns.csv").toFile();

            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));

                String line = "";
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");

                    Location location = new Location(null, 0, 0, 0);
                    Team.Type type = null;

                    for (int i = 0; i < data.length; i++) {

                        switch (i) {

                            case 0:
                                type = Team.Type.valueOf(data[i]);
                                break;
                            case 1:
                                location.setWorld(Bukkit.getWorld(data[i]));
                                break;
                            case 2:
                                if (Util.isNumeric(data[i]))
                                    location.setX(Util.safeToFloat(data[i]));
                                break;
                            case 3:
                                if (Util.isNumeric(data[i]))
                                    location.setY(Util.safeToFloat(data[i]));
                                break;
                            case 4:
                                if (Util.isNumeric(data[i]))
                                    location.setZ(Util.safeToFloat(data[i]));
                                break;
                        }
                    }

                    spawns.get(type).add(location);

                }
                reader.close();
            }

        } catch (Exception e) {e.printStackTrace();}


    }

}
