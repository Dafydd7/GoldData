package org.dafy.golddata.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.dafy.golddata.GoldData;

@CommandAlias("UGold")
public class Reload extends BaseCommand {
    @Dependency
    private GoldData plugin;
    @Subcommand("Reload") @CommandPermission("goldStats.admin.reload")
    public void reload(Player sender){
        //Reload the config.yml & send the player a message.
        plugin.reloadConfig();
        sender.sendMessage(ChatColor.GREEN + "Reloaded successfully!");
    }
}
