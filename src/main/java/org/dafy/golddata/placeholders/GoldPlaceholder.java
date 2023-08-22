package org.dafy.golddata.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.dafy.golddata.GoldData;
import org.dafy.golddata.data.Gold;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GoldPlaceholder extends PlaceholderExpansion {
    private final GoldData plugin;

    public GoldPlaceholder(GoldData plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "gold";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Dafydd";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.2";
    }

    @Override
    public String onPlaceholderRequest(Player player, String params) {
        final List<Map.Entry<UUID, Gold>> entries = plugin.getGoldManager().getTop10Entries();
        if (params.startsWith("totalspent_")) {
            int position;
            try {
                position = Integer.parseInt(params.replace("totalspent_", ""));
            } catch (NumberFormatException e) {
                return null; // Placeholder not found
            }
            if (position >= 1 && position <= entries.size()) {
                Map.Entry<UUID, Gold> entry = entries.get(position - 1);
                Gold gold = entry.getValue();
                int totalSpent = gold.getTotalSpent();

                // Return the total spent value for the specified position
                return String.valueOf(totalSpent);
            } else {
                // Return "empty" for positions without data
                return "0";
            }
        }
        if (params.startsWith("top_name_")) {
            int position;
            try {
                position = Integer.parseInt(params.replace("top_name_", ""));
            } catch (NumberFormatException e) {
                return null; // Placeholder not found
            }
            if (position >= 1 && position <= entries.size()) {
                Map.Entry<UUID, Gold> entry = entries.get(position - 1);
                UUID uuid = entry.getKey();

                // Return the UUID for the specified position
                return Bukkit.getOfflinePlayer(uuid).getName();
            } else {
                // Return "empty" for positions without data
                return "Null";
            }
        }
        return null; // Placeholder not found}
    }
}