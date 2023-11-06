package org.dafy.golddata;

import co.aikar.commands.BukkitCommandManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.dafy.golddata.commands.*;
import org.dafy.golddata.data.DataHandler;
import org.dafy.golddata.data.GoldManager;

@Getter
public final class GoldData extends JavaPlugin {
    private GoldManager goldManager;
    private DataHandler dataHandler;
    @Override
    public void onEnable() {
        //Register dependencies.
        goldManager = new GoldManager();
        dataHandler = new DataHandler(this);
        dataHandler.initialiseData();
        BukkitCommandManager manager = new BukkitCommandManager(this);
        //Enable help api & register help command.
        manager.enableUnstableAPI("help");
        manager.registerCommand(new GoldHelp());
        //Register all commands.
        manager.registerCommand(new GetData());
        manager.registerCommand(new AddData());
        manager.registerCommand(new Reload());
        manager.registerCommand(new TopCustomers());
        manager.registerCommand(new TotalGold());
        //Save config.
        saveDefaultConfig();
    }
    @Override
    public void onDisable() {
        //Save all cached data to the config.
        dataHandler.saveAll();
    }
}
