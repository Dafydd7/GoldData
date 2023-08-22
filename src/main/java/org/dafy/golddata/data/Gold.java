package org.dafy.golddata.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class Gold {
    private Integer totalSpent;
    private List<String> itemsPurchased;

    public Gold() {
        this.totalSpent = 0;
        this.itemsPurchased = new ArrayList<>(); // Initialize the list
    }

    public void addItemPurchased(String string){
        itemsPurchased.add(string);
    }
    public void addTotalSpent(int amount){
        totalSpent += amount;
    }
}
