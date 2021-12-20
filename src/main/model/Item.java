package model;

import com.sun.corba.se.spi.ior.Writeable;
import org.json.JSONObject;
import persistence.Writable;

import java.sql.Wrapper;

// Represents an item having an item name and price (in cents)
public class Item implements Writable {
    private final String name;
    private final int price;

    // REQUIRES: name length > 0; price >= 0, every item have different names
    // MODIFIES: this
    // EFFECTS: name on Item is set to name
    //          price on Item is set to price
    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    // EFFECTS: returns Item name
    public String getName() {
        return name;
    }

    // EFFECTS: returns Item price
    public int getPrice() {
        return price;
    }


    // Method and ideas from JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("itemName", name);
        json.put("price", price);
        return json;
    }
}
