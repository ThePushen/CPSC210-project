package persistence;

import model.Item;
import model.UserList;
import model.User;

import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// Method and ideas from JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads UserList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public UserList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUserList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses list from JSON object and returns it
    private UserList parseUserList(JSONObject jsonObject) {
        String name = jsonObject.getString("list");
        UserList ul = new UserList(name);
        addUsers(ul, jsonObject);
        return ul;
    }

    // MODIFIES: ul
    // EFFECTS: parses users from JSON object and adds them to userlist
    private void addUsers(UserList ul, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("users");
        for (Object json : jsonArray) {
            JSONObject nextUser = (JSONObject) json;
            addUser(ul, nextUser);
        }
    }

    // MODIFIES: ul
    // EFFECTS: parses user from JSON object and adds it to userlist
    private void addUser(UserList ul, JSONObject jsonObject) {
        String name = jsonObject.getString("user");
        int balance = jsonObject.getInt("balance");

        User user = new User(name, balance, parseItems(jsonObject));
        ul.addUser(user);
    }

    // EFFECTS: parses items from JSON object and returns it
    private List<Item> parseItems(JSONObject jsonObject) {
        List<Item> il = new ArrayList<>();
        addItems(il, jsonObject);
        return il;
    }

    // MODIFIES: il
    // EFFECTS: parses items from JSON object and adds them to items
    private void addItems(List<Item> il, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(il, nextItem);
        }
    }

    // MODIFIES: il
    // EFFECTS: parses item from JSON object and adds it to items
    private void addItem(List<Item> il, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int price = jsonObject.getInt("price");
        Item item = new Item(name, price);
        il.add(item);
    }
}
