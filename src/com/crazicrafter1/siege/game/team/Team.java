package com.crazicrafter1.siege.game.team;

import com.crazicrafter1.siege.DurationText;
import com.crazicrafter1.siege.Main;
import com.crazicrafter1.siege.util.Util;
import com.crazicrafter1.siege.game.SpawnManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.ArrayList;
import java.util.UUID;

public abstract class Team {

    public enum Type {
        INVADER, DEFENDER
    }

    public enum Kit {
        UNDEAD_GHOUL, ZOMBIE, NEMESIS,
        MARKSMAN, KNIGHT, BOMBER
    }

    private UUID uuid;
    private Kit kit;
    private String abilityName;
    private final int cooldown;

    private Type type;

    private int time = 0;

    private int ticks = 0;

    public Team(Player player, Type type, Kit kit, String abilityName, int abilityCooldown) {
        this.uuid = player.getUniqueId();
        this.kit = kit;
        this.abilityName = abilityName;
        cooldown = abilityCooldown;
        time = abilityCooldown;
        this.type = type;
    }

    public Team(UUID uuid, Type type, Kit kit, String abilityName, int abilityCooldown) {
        this.uuid = uuid;
        this.kit = kit;
        this.abilityName = abilityName;
        cooldown = abilityCooldown;
        time = abilityCooldown;
        this.type = type;
    }

/*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return this.uuid.equals(team.getUuid()) &&
                z == team.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, z);
    }

 */

    public void respawn() {

        Player p = getPlayer();

        ArrayList<Location> locations = SpawnManager.spawns.get(getType());

        Location loc = locations.get(Util.randomRange(0, locations.size() - 1));
        p.teleport(loc, PlayerTeleportEvent.TeleportCause.PLUGIN);

        Util.cure(p);

        reset();
    }

    public Type getType() {
        return type;
    }

    public UUID getUuid() {
        return uuid;
    }

    public final Player getPlayer() {
        return Main.get().getServer().getPlayer(uuid);
    }

    public abstract void reset();

    protected void ability() {}
    protected void passiveAbility() {}
    protected void passiveItems() {}
    public void onOtherKill(Player other) {}

    public final void onClickAbility() {
        if (this.time >= cooldown) {
            ability();
            this.time = 0;
        }
    }

    public final void tick() {
        if (this.time < this.cooldown) {
            DurationText.display(getPlayer(), abilityName, time, cooldown);
            time++;
        }
        if (this.ticks%20==0) {
            passiveItems();
            passiveAbility();
            ticks = 0;
            //time = 0;
        }
        ticks++;
    }

    public final Kit getKit() {
        return kit;
    }


}
