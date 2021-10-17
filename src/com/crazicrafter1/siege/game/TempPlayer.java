package com.crazicrafter1.siege.game;

import com.crazicrafter1.siege.game.team.Team;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TempPlayer {


    private UUID uuid;
    private Team.Type type;
    private Team.Kit kit;
    private QueuedPlayer queued = null;

    public TempPlayer(UUID uuid, Team.Type type, Team.Kit kit) {
        this.uuid = uuid;
        this.type = type;
        this.kit = kit;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Team.Type getType() {
        return type;
    }

    public Team.Kit getKit() {
        return kit;
    }

    public void setKit(Team.Kit kit) {
        this.kit = kit;
    }

    public QueuedPlayer getQueued() {
        return queued;
    }

    public void setQueued(Team.Type type, Team.Kit kit) {
        queued = new QueuedPlayer(uuid, type, kit);
    }

    public void unQueue() {
        queued = null;
    }

    @Override
    public String toString() {
        return type.name().toLowerCase() + "/" + kit.name().toLowerCase();
    }

    public static class QueuedPlayer {

        private UUID uuid;
        private Team.Type type;
        private Team.Kit kit;

        QueuedPlayer(UUID uuid, Team.Type type, Team.Kit kit) {
            this.uuid = uuid;
            this.type = type;
            this.kit = kit;
        }

        public UUID getUuid() {
            return uuid;
        }

        public Team.Type getType() {
            return type;
        }

        public Team.Kit getKit() {
            return kit;
        }
    }

}
