package com.crazicrafter1.siege.util;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.Vector;

import java.util.*;

public class Util {


    public static void cure(Player p) {
        removeAllPotionEffects(p);
        p.setFireTicks(0);
        p.setHealth(20); //.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 10), true);
        p.setFallDistance(0);
        p.setVelocity(new Vector(0, 0, 0));
        p.setFoodLevel(20);
        p.setLevel(0);
    }

    public static void removeAllPotionEffects(Player p) {

        for (PotionEffect effect : p.getActivePotionEffects()) {
            p.removePotionEffect(effect.getType());
        }

    }

    public static void clearChat(String message) {

        for (Player p : Bukkit.getOnlinePlayers()) {
            for (int i = 0; i < 30; i++)
                p.sendMessage("");

            p.sendMessage(message);
            //p.sendMessage();
        }

    }

    public static void clearChat() {

        for (Player p : Bukkit.getOnlinePlayers()) {
            for (int i = 0; i < 30; i++)
                p.sendMessage("");

            //p.sendMessage();
        }

    }
    public static Color matchColor(String c)
    {
        String color = c.toUpperCase();
        // BLUE, RED, WHITE, GRAY, GREEN, YELLOW, AQUA, BLACK, FUCHSIA, LIME, MAROON, NAVY, OLIVE
        // ORANGE, PURPLE, SILVER, TEAL
        if (color.equals("BLUE"))
            return Color.BLUE;
        if (color.equals("RED"))
            return Color.RED;
        if (color.equals("WHITE"))
            return Color.WHITE;
        if (color.equals("GRAY"))
            return Color.GRAY;
        if (color.equals("GREEN"))
            return Color.GREEN;
        if (color.equals("YELLOW"))
            return Color.YELLOW;
        if (color.equals("AQUA"))
            return Color.AQUA;
        if (color.equals("BLACK"))
            return Color.BLACK;
        if (color.equals("FUCHSIA"))
            return Color.FUCHSIA;
        if (color.equals("LIME"))
            return Color.LIME;
        if (color.equals("MAROON"))
            return Color.MAROON;
        if (color.equals("NAVY"))
            return Color.NAVY;
        if (color.equals("OLIVE"))
            return Color.OLIVE;
        if (color.equals("ORANGE"))
            return Color.ORANGE;
        if (color.equals("PURPLE"))
            return Color.PURPLE;
        if (color.equals("SILVER"))
            return Color.SILVER;
        if (color.equals("TEAL"))
            return Color.TEAL;
        return null;
    }

    public static boolean isOnline(String name)
    {
        //Bukkit.getServer().getOnlinePlayers().
        //Bukkit.getServer().getPlayer("")
        return Bukkit.getServer().getPlayer(name) == null;
    }

    public static UUID getUUID(String name)
    {
        if (Bukkit.getServer().getPlayer(name) != null)
            return Bukkit.getServer().getPlayer(name).getUniqueId();
        return null;
    }


    public static int randomRange(int min, int max)
    {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public static int randomRange(int min, int max, int min1, int max1)
    {
        if ((int)(Math.random()*2) == 0)
            return min + (int)(Math.random() * ((max - min) + 1));
        return min1 + (int)(Math.random() * ((max1 - min1) + 1));
    }

    public static int randomRange(int min, int max, Random random)
    {
        return random.nextInt((max - min) + 1) + min;
    }

    public static int normalizeAngle(int angle) {

        int a = angle%360;

        return (a<0)?a+360:a;

    }

    public static int sqDist(int x1, int y1, int x2, int y2) {
        return (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
    }

    public static double dist(int x1, int y1, int x2, int y2) {
        return Math.sqrt(sqDist(x1, y1, x2, y2));
    }

    public static Integer safeToInt(String s)
    {
        //if ()
        // test if is numeric

        if (isNumeric(s))
        {
            return Integer.parseInt(s);
        }

        return null;
    }

    public static Float safeToFloat(String s)
    {
        //if ()
        // test if is numeric

        if (isNumeric(s))
        {
            return Float.parseFloat(s);
        }

        return null;
    }

    public static boolean isNumeric(String s)
    {
        try {
            Float.parseFloat(s);
            return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }

    public static Location getSimpleLocation(Location loc) {
        return new Location(loc.getWorld(), loc.getX(),  loc.getY(), loc.getZ());
    }

    public static int sign(int i) {

        if (i>0) return 1;
        if (i<0) return -1;
        return 0;
    }

    public static int strictSign(int i) {

        if (i>=0) return 1;
        return -1;
    }

    public static boolean inRange(int i, int min, int max) {
        return (i <= max && min >= i);
    }

    /*
    static <T> void clamp (T i, T min, T max)
    {
        if (i instanceof Integer)
            return Math.max(min, Math.min((int)i, (int)max));
    }
     */


    public static int clamp(int i, int min, int max) {
        return Math.max(min, Math.min(i, max));
    }

    public static float clamp(float i, float min, float max) {
        return Math.max(min,
                Math.min(i, max));
    }

}
