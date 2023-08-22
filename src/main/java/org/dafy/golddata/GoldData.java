package org.dafy.golddata;

import co.aikar.commands.BukkitCommandManager;
import lombok.Getter;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.plugin.java.JavaPlugin;
import org.dafy.golddata.commands.AddData;
import org.dafy.golddata.commands.GetData;
import org.dafy.golddata.commands.Reload;
import org.dafy.golddata.commands.TopCustomers;
import org.dafy.golddata.data.DataHandler;
import org.dafy.golddata.data.GoldManager;
import org.dafy.golddata.placeholders.GoldPlaceholder;

import java.util.concurrent.CompletableFuture;

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
        //Enable help command.
        manager.enableUnstableAPI("help");
        //Register all commands.
        manager.registerCommand(new GetData());
        manager.registerCommand(new AddData());
        manager.registerCommand(new Reload());
        manager.registerCommand(new TopCustomers());
        //Save config.
        saveDefaultConfig();
        //Register placeholder.
        registerPlaceholders();
    }
    @Override
    public void onDisable() {
        //Save all cached data to the config.
        dataHandler.saveAll();
    }

    public void registerPlaceholders(){
        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
             new GoldPlaceholder(this).register();
            getLogger().info("Registered Gold Data placeholder(s)!");
        } else {
            getLogger().warning("PlaceholderAPI not found! Top 10 placeholder(s) will not be registered.");
        }
    }
}
