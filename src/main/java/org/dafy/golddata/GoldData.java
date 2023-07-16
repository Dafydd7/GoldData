package org.dafy.golddata;

import co.aikar.commands.BukkitCommandManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.dafy.golddata.commands.AddData;
import org.dafy.golddata.commands.GetData;
import org.dafy.golddata.commands.Reload;
import org.dafy.golddata.commands.TopCustomers;

public final class GoldData extends JavaPlugin {
    @Override
    public void onEnable() {
        //Register commands
        BukkitCommandManager manager = new BukkitCommandManager(this);
        manager.registerCommand(new GetData());
        manager.registerCommand(new AddData());
        manager.registerCommand(new Reload());
        manager.registerCommand(new TopCustomers());
        this.saveDefaultConfig();
    }
    @Override
    public void onDisable() {
    }
}
