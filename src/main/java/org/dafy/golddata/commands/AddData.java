package org.dafy.golddata.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.annotation.Syntax;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import org.bukkit.configuration.Configuration;
import org.dafy.golddata.GoldData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@CommandAlias("Gold")
public class AddData extends BaseCommand {
    @Dependency
    private GoldData plugin;
    @CommandAlias("AddData") @CommandPermission("goldStats.admin.addData") @Syntax("<player> <gold> <item name> &a - Add info to players gold history.")
    public void addGoldData(OnlinePlayer player, int gold, String itemName){
        //Initialise variables:
        final Configuration config = plugin.getConfig();
        final UUID uuid = player.getPlayer().getUniqueId();
        final String path = "users." + uuid + ".";
        List<String> words = new ArrayList<>();
        Date date = new Date();

        //Crate new section if no previous data found.
        if (config.getConfigurationSection("users." + uuid) == null) {
            config.createSection("users." + uuid);
            config.set(path + "Gold Total",gold);
            words.add(itemName + " - " + date);
            config.set(path + "Items Purchased",words);
        }else {
            //Else, update the existing players' data.
            words = config.getStringList(path + "Items Purchased");
            words.add(itemName + " - " + date);
            int currentAmount = config.getInt(path + "Gold Total", gold);
            config.set(path + "Gold Total", currentAmount + gold);
            config.set(path + "Items Purchased", words);
        }
        //Save & reload new/updated config.yml
        plugin.saveConfig();
        plugin.reloadConfig();
    }
}
