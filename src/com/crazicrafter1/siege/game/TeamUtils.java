package com.crazicrafter1.siege.game;

import com.crazicrafter1.siege.game.team.Team;
import com.crazicrafter1.siege.game.team.defender.Knight;
import com.crazicrafter1.siege.game.team.defender.Marksman;
import com.crazicrafter1.siege.game.team.invader.Nemesis;
import com.crazicrafter1.siege.game.team.invader.UndeadGhoul;
import com.crazicrafter1.siege.game.team.invader.Zombie;

import java.util.UUID;

public class TeamUtils {


    public static Team matchTeam(UUID uuid, Team.Type type, Team.Kit kit) {

        if (type == Team.Type.DEFENDER) {
            switch (kit) {

                case MARKSMAN:
                    return new Marksman(uuid);
                case KNIGHT:
                    return new Knight(uuid);
            }
        } else if (type == Team.Type.INVADER) {
            switch (kit) {
                case UNDEAD_GHOUL:
                    return new UndeadGhoul(uuid);
                case ZOMBIE:
                    return new Zombie(uuid);
                case NEMESIS:
                    return new Nemesis(uuid);
            }
        }

        return null;

    }

    public static boolean isValid(Team.Type type, Team.Kit kit) {

        if (type == null || kit == null) return false;

        if (type == Team.Type.DEFENDER)
            return kit == Team.Kit.MARKSMAN || kit == Team.Kit.KNIGHT;
        else if (type == Team.Type.INVADER)
            return kit == Team.Kit.UNDEAD_GHOUL || kit == Team.Kit.ZOMBIE || kit == Team.Kit.NEMESIS;

        return false;

    }

    public static boolean isValid(Team team) {

        if (team == null) return false;

        Team.Kit kit = team.getKit();

        if (team.getType() == Team.Type.DEFENDER)
            return kit == Team.Kit.MARKSMAN || kit == Team.Kit.KNIGHT;
        else if (team.getType() == Team.Type.INVADER)
            return kit == Team.Kit.UNDEAD_GHOUL || kit == Team.Kit.ZOMBIE || kit == Team.Kit.NEMESIS;

        return false;

    }


    public static Team.Type matchType(Team.Kit kit) {

        if (kit == Team.Kit.MARKSMAN || kit == Team.Kit.KNIGHT) return Team.Type.DEFENDER;
        if (kit == Team.Kit.UNDEAD_GHOUL || kit == Team.Kit.ZOMBIE || kit == Team.Kit.NEMESIS) return Team.Type.INVADER;

        return null;
    }

    public static Team.Type getOther(Team.Type type) {

        return (type == Team.Type.INVADER) ? Team.Type.DEFENDER : Team.Type.INVADER;

    }

}
