package org.dafy.golddata.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.dafy.golddata.GoldData;
import org.dafy.golddata.data.Gold;

import java.text.NumberFormat;

@CommandAlias("UGold")
public class TotalGold extends BaseCommand {
    @Dependency
    private GoldData plugin;
    @Subcommand("TotalGold") @Syntax("Lists the total amount of gold spent on the server.") @CommandPermission("goldStats.admin.totalgold")
    public void onTotalGoldRequest(Player player){
        int totalGoldSpent = 0;
        for (Gold data:plugin.getGoldManager().getValue()) {
            totalGoldSpent += data.getTotalSpent();
        }
        String formattedNumber = NumberFormat.getNumberInstance().format(totalGoldSpent);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7&l[&b&lGOLD&7&l]&7 Total Spent: &b" + formattedNumber));
    }
}
