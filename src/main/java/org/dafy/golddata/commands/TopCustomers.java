package org.dafy.golddata.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.dafy.golddata.GoldData;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@CommandAlias("UGold")
public class TopCustomers extends BaseCommand {
    @Dependency
    private GoldData plugin;
    @Subcommand("toplist") @CommandPermission("goldStats.admin.getData")
    public void getTopCustomers(Player player) {

        final Configuration config = plugin.getConfig();
        final HashMap<String,Integer> map = new HashMap<>();
        int listSize = 0;

        //Check for entries. If no entries exist, then return
        if(config.getConfigurationSection("users.") == null){
            player.sendMessage(ChatColor.RED + "No entries found yet!");
            return;
        }

        //Iterate through all keys and add their 'Gold Total' value to the map.
        for (String key : config.getConfigurationSection("users.").getKeys(false)) {
            UUID uuid = UUID.fromString(key);
            map.put(Bukkit.getOfflinePlayer(uuid).getName(),config.getInt("users." + key + ".Gold Total"));
            listSize++;
        }

        //Turn the map into a sorted list using stream
        List<Map.Entry<String, Integer>> test =
                map.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).collect(Collectors.toList());

        //Loop through and send the player the sorted index. Checks if listSize < 5 then outputs based off of that.
        player.sendMessage(ChatColor.AQUA + "-----------------------");
        player.sendMessage(ChatColor.BOLD + "Top Customer(s):" + "\n" + " ");
        for (int i = 0; i < (listSize < 5 ? listSize:10); i++) {
            player.sendMessage(ChatColor. GREEN + "#" + (i+1)  + ": " + test.get(i));
        }
        player.sendMessage(ChatColor.AQUA + "-----------------------");
    }
}
