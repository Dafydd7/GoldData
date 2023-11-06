package org.dafy.golddata.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.annotation.Syntax;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import org.dafy.golddata.GoldData;
import org.dafy.golddata.data.Gold;
import org.dafy.golddata.data.GoldManager;

import java.util.Date;
import java.util.UUID;


@CommandAlias("UGold")
public class AddData extends BaseCommand {
    @Dependency
    private GoldData plugin;
    @CommandAlias("AddData") @CommandPermission("goldStats.admin.addData") @Syntax("<Player> <Gold Amount> <Item Name> - &a Add info to players gold history.")
    public void addGoldData(OnlinePlayer player, int amount, String... itemNameArgs){
        final UUID uuid = player.getPlayer().getUniqueId();
        Date date = new Date();
        final GoldManager goldManager = plugin.getGoldManager();

        String itemName = String.join(" ", itemNameArgs); // Join the arguments into a single string
        String itemInfo = itemName + " - " + date;

        Gold gold;
        if (!goldManager.getKeys().contains(uuid)) {
            gold = new Gold();
        } else {
            gold = goldManager.getGold(uuid);
        }
        gold.addTotalSpent(amount);
        gold.addItemPurchased(itemInfo);
        goldManager.addUser(uuid, gold);
    }
}
