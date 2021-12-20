package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Represents an user having owner name, balance (in cents), and owned items
public class User implements Writable {
    private final String userName;
    private final List<Item> items;

    private int balance;

    // REQUIRES: userAccount length > 0 and in lower case; initialBalance >= 0
    // MODIFIES: this
    // EFFECTS: account on User is set to userAccount
    //          balance on User is set to initialBalance
    //          items on User is set to ownedItems
    public User(String userAccount, int initialBalance, List<Item> ownedItems) {
        this.userName = userAccount;
        this.balance = initialBalance;
        this.items = ownedItems;
    }


    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: amount is added to balance and returned updated balance
    public void deposit(int amount) {
        balance = balance + amount;
    }

    // REQUIRES: amount > 0, balance >= amount
    // MODIFIES: this
    // EFFECTS: amount is withdraw from User and returned updated balance
    public void withdraw(int amount) {
        this.balance = balance - amount;
    }


    // REQUIRES: item.getPrice() >= 0
    // MODIFIES: this
    // EFFECTS: if getPrice() is smaller or equal to balance
    //           - item.getPrice is deducted from balance and updated balance
    //           - item is added to items
    //           - return true
    //          else returns false
    public boolean buyItem(Item item) {
        if (item.getPrice() <= balance && !getItems().contains(item)) {
            balance = balance - item.getPrice();
            items.add(item);
            return true;
        }
        return false;
    }

    // REQUIRES: item.getPrice() >= 0, item.getPrice() <= balance
    // MODIFIES: this
    // EFFECTS: if item name is contained in getItemNames()
    //           - item.getPrice() is added to balance and returned updated balance
    //           - item is removed from items
    //           - return true
    //          else returns false
    public boolean sellItem(Item item) {
        if (getItemNames().contains(item.getName())) {
            balance = balance + item.getPrice();
            items.remove(item);
            return true;
        }
        return false;
    }

    // EFFECTS: return the Item that matches name, if not return null
    public Item chooseItem(String name) {
        for (Item i:items) {
            if (i.getName().equals(name)) {
                return i;
            }
        }
        return null;
    }

    // EFFECTS: return true if item name matches name, else return false
    public boolean checkExistItem(String name) {
        for (Item i:items) {
            if (i.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }


    // EFFECTS: returns User account name
    public String getName() {
        return userName;
    }

    // EFFECTS: returns remaining balance in cents
    public int getBalance() {
        return balance;
    }

    // MODIFIES: this
    // EFFECTS: returns User owned items name list in account repository
    public List<String> getItemNames() {
        List<String> itemNames = new ArrayList<>();
        for (Item i: items) {
            itemNames.add(i.getName());
        }
        return itemNames;
    }

    // EFFECTS: returns user owned item list
    public List<Item> getItems() {
        return items;
    }

    // Method and ideas from JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("user", userName);
        json.put("balance", balance);
        json.put("items", items);
        return json;
    }
}
