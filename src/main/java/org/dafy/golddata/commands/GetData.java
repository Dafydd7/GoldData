package org.dafy.golddata.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.dafy.golddata.GoldData;

import java.util.UUID;

@CommandAlias("Gold")
public class GetData extends BaseCommand {
    @Dependency
    private GoldData plugin;
    @Subcommand("GetData|Data") @CommandPermission("goldStats.admin.getData") @Syntax("<player>  &a- Get players gold data.")
    public void getGoldData(Player sender, OnlinePlayer playerData){

        final Configuration config = plugin.getConfig();
        final Player target = playerData.getPlayer();
        final UUID uuid = target.getUniqueId();

        //If no data exists, tell the player & return.
        if (config.getConfigurationSection("users." + uuid) == null) {
            sender.sendMessage(ChatColor.RED + "Player's shop data is null");
            return;
        }

        //Path for the config section
        final String path = "users." + uuid + ".";

        //Else, send the desired players' data.
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&b-------------------\n"
                + "&a&l" + target.getName() + "'s Data:\n" +
                "&aTotal Spent: &r" + config.get(path + "Gold Total") + "\n" +
                "&aItems Purchased: &r"));

        //Loop through all entries in the StringList, & output them on separate lines.
        for(String msg : plugin.getConfig().getStringList(path + "Items Purchased")){
            sender.sendMessage(msg);
        }
        sender.sendMessage(ChatColor.AQUA + "-------------------");
    }

}
