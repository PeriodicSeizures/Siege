package com.crazicrafter1.siege.game;

import com.crazicrafter1.siege.util.Util;
import com.crazicrafter1.siege.game.team.Team;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

public class King {

    private int health = 40;
    private Zombie entity;
    private long lastDamaged = System.currentTimeMillis();
    private String name;
    private Location location;

    public King(Location loc) {
        this.location = loc;
    }

    public void onDamage(Entity d) {

        if (!(d instanceof Player) || !GameManager.isInvader(d.getUniqueId())) return;

        Player damager = (Player)d;

        if (health > 0) {
            if (System.currentTimeMillis() - 500 >= lastDamaged) {
                //System.out.println("Damaged!!!");
                health--;
                lastDamaged = System.currentTimeMillis();
            }
        }

        if (health == 0) {
            killEntity();
            System.out.println(ChatColor.AQUA + "The king has fallen...");

            GameManager.stopGame(Team.Type.INVADER);

            health = -1;
        }
    }

    public void reset() {
        lastDamaged = System.currentTimeMillis();
        health = 40;
        killEntity();
        summonEntity();
    }

    public Location getLocation() {
        return location;
    }

    public int getHealth() {
        return health;
    }

    public Zombie getEntity() {
        return entity;
    }

    public void summonEntity() {
        if (entity == null || entity.isDead())
        try {
            entity = location.getWorld().spawn(location, Zombie.class);

            name = KingManager.kings[Util.randomRange(0, 99)].toUpperCase();

            entity.setCustomNameVisible(true);
            entity.setAI(false);
            entity.setBaby(false);
            entity.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
            entity.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
            entity.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
            entity.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
            entity.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_SWORD));
            entity.setCanPickupItems(false);
        } catch (Exception e) {e.printStackTrace();}

    }

    public void killEntity() {
        if (entity == null) return;

        if (!entity.isDead()) entity.remove();
    }

    public void tick() {
        //BossBar bar =
        //for ()
        if (entity != null) {
            entity.setCustomName(ChatColor.YELLOW + "" + ChatColor.BOLD + "KING " + name +
                    ChatColor.RESET + " " + ChatColor.WHITE + health + "HP");
        }

    }

}
