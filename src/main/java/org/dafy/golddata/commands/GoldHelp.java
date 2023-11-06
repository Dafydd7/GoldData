package org.dafy.golddata.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.HelpCommand;

@CommandAlias("UGold") @CommandPermission("ugold.admin.help")
public class GoldHelp extends BaseCommand {
    @HelpCommand
    public void onHelp(CommandHelp help){
        help.showHelp();
    }
}
