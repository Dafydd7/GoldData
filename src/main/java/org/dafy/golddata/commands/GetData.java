package org.dafy.golddata.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.dafy.golddata.GoldData;
import org.dafy.golddata.data.Gold;
import org.dafy.golddata.data.GoldManager;

import java.util.UUID;

@CommandAlias("UGold")
public class GetData extends BaseCommand {
    @Dependency
    private GoldData plugin;
    @Subcommand("GetData|Data") @CommandPermission("goldStats.admin.getData") @Syntax("<player>  &a- Get players gold data.")
    public void getGoldData(Player sender, OfflinePlayer target){
        //Check to see whether the player has actually been on the server.
        if(!target.hasPlayedBefore()){
            sender.sendMessage(ChatColor.RED + "Error: " + target.getName() + " has not played before.");
            return;
        }
        //Player has played, we can move on to the next step:
        final UUID uuid = target.getUniqueId();
        final Gold gold = plugin.getGoldManager().getGold(uuid);
        final GoldManager goldManager = plugin.getGoldManager();
        //Check for entries. If no entries exist, then return
        if(goldManager.getKeys().isEmpty()){
            sender.sendMessage(ChatColor.RED + "No entries found yet!");
            return;
        } else if (!goldManager.getKeys().contains(target.getUniqueId())) {
            sender.sendMessage(ChatColor.RED + "No data found for player.");
            return;
        }
        //Else, send the desired players' data.
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&b-------------------\n"
                + "&a&l" + target.getName() + "'s Data:\n" +
                "&aTotal Spent: &r" + gold.getTotalSpent() + "\n" +
                "&aItems Purchased: &r"));

        //Loop through all entries in the StringList, & output them on separate lines.
        for(String item : gold.getItemsPurchased()){
            sender.sendMessage(item); //TODO: Paginated list.
        }
        sender.sendMessage(ChatColor.AQUA + "-------------------");
    }
}
