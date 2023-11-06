package org.dafy.golddata.data;

import org.bukkit.configuration.Configuration;
import org.dafy.golddata.GoldData;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataHandler {
    private final GoldData plugin;
    private final GoldManager goldManager;
    private final Logger logger;
    public DataHandler(GoldData plugin){
        this.plugin = plugin;
        this.goldManager = plugin.getGoldManager();
        this.logger = plugin.getLogger();
    }
    public void initialiseData(){
        final Configuration config = plugin.getConfig();

        //Check for entries. If no entries exist, then return
        if(config.getConfigurationSection("users.") == null) {
            logger.log(Level.INFO,"No gold data currently present.");
            return;
        }

        //Clear the map, in case someone has reloaded.
        goldManager.clear();

        //Iterate through all keys and add their 'Gold Total' value to the map.
        for (String key : config.getConfigurationSection("users.").getKeys(false)) {
            UUID uuid = UUID.fromString(key);
            Gold gold = new Gold();
            String path = "users." + key + ".";
            gold.setTotalSpent(config.getInt(path + "Gold Total"));
            gold.setItemsPurchased(config.getStringList(path + "Items Purchased"));
            goldManager.addUser(uuid,gold);
        }
        logger.log(Level.INFO, plugin.getName() + ": Data found/loaded successfully!");
    }
    public void saveAll(){
        //Check to see if there's any entries, no need to save if none exist.
        if(goldManager.getKeys().isEmpty()) return;
        //Loop through the entries and save to config.
        final Configuration config = plugin.getConfig();
        for (UUID uuid:goldManager.getKeys()) {
            //Get the gold data for the player.
            Gold gold = plugin.getGoldManager().getGold(uuid);
            String path = "users." + uuid + ".";
            //Check to see whether the section for the user has been created previously.
            if (config.getConfigurationSection("users." + uuid) == null) {
                config.createSection("users." + uuid);
            }
            //Set the new data.
            config.set(path + "Gold Total",gold.getTotalSpent());
            config.set(path + "Items Purchased",gold.getItemsPurchased());
        }
        //Save the config
        plugin.saveConfig();
        logger.log(Level.INFO, plugin.getName() + ": Player data saved successfully!");
    }
}
