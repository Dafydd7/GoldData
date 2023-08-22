package org.dafy.golddata.data;

import java.util.*;
import java.util.stream.Collectors;

public class GoldManager {
    private final HashMap<UUID,Gold> userValues = new HashMap<>();

    public Set<UUID> getKeys(){
        return userValues.keySet();
    }
    public void clear(){
        userValues.clear();
    }
    public void addUser(UUID uuid,Gold gold){
        userValues.put(uuid,gold);
    }
    public Gold getGold(UUID uuid){
       return userValues.get(uuid);
    }

    public List<Map.Entry<UUID, Gold>> getTop10Entries() {
        return userValues.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(
                        Comparator.comparing(Gold::getTotalSpent).reversed()))
                .limit(Math.min(10, userValues.size())) // Limit to the smallest of 10 and map size
                .collect(Collectors.toList());
    }
}
