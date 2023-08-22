package org.dafy.golddata.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.dafy.golddata.GoldData;
import org.dafy.golddata.data.Gold;
import org.dafy.golddata.data.GoldManager;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@CommandAlias("UGold")
public class TopCustomers extends BaseCommand {
    @Dependency
    private GoldData plugin;
    @Subcommand("toplist") @CommandPermission("goldStats.admin.getData")
    public void getTopCustomers(Player player) {
        final GoldManager goldManager = plugin.getGoldManager();

        //Check for entries. If no entries exist, then return
        if(goldManager.getKeys().isEmpty()){
            player.sendMessage(ChatColor.RED + "No entries found yet!");
            return;
        }

        //Loop through and send the player the sorted index.
        final List<Map.Entry<UUID, Gold>> entries = goldManager.getTop10Entries();
        player.sendMessage(ChatColor.AQUA + "-----------------------");
        player.sendMessage(ChatColor.BOLD + "Top Customer(s):" + "\n" + " ");
        for (int i = 0; i < entries.size(); i++) {
            String username = Bukkit.getOfflinePlayer(entries.get(i).getKey()).getName();
            int totalSpent = entries.get(i).getValue().getTotalSpent();
            player.sendMessage(ChatColor. GREEN + "#" + (i+1)  + ": " + username + ", Total Spent: " + totalSpent);
        }
        player.sendMessage(ChatColor.AQUA + "-----------------------");
    }
}
