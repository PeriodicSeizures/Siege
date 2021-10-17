package com.crazicrafter1.siege;

import com.crazicrafter1.siege.commands.*;
import com.crazicrafter1.siege.game.GameManager;
import com.crazicrafter1.siege.game.WorldManager;
import com.crazicrafter1.siege.listeners.*;
import com.crazicrafter1.siege.tabcomplete.*;
import org.bukkit.*;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main inst;

    @Override
    public void onEnable() {
        inst = this;

        this.saveDefaultConfig();

        new CmdBomb();
        //new CmdDefender();
        //new CmdInvader();
        new CmdJoinTeam();
        new CmdKing();
        new CmdKit();
        //new CmdPlay();
        new CmdSiege();
        new CmdSpawn();
        //new CmdGlow();

        //King king = new King()

        new BlockBreakListener();
        new InventoryActionListener();
        new DamageListener();
        new PlayerInteractListener();
        new PlayerItemChangeListener();
        new PlayerOnlineListener();
        new PlayerStatListener();

        new TabBomb();
        //new TabDefender();
        //new TabInvader();
        new TabJoinTeam();
        new TabKing();
        new TabKit();
        new TabSiege();

        WorldManager.onEnable();
        GameManager.onEnable();
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        GameManager.onDisable();
    }

    public static Main getInstance() {
        return inst;
    }

}
