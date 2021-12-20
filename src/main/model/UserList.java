package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// Represents a list of users
public class UserList implements Writable {
    private Map<String, User> users;
    private String name;

    // EFFECTS: name on UserList is set to name
    //          users on UserList is set to ArrayList
    public UserList(String name) {
        this.name = name;
        users = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: add all User name to nameList and return updated nameList
    public Set<String> userNameList() {
        Set<String> nameList = new HashSet<>();
        for (Map.Entry<String, User> u: users.entrySet()) {
            nameList.add(u.getKey());
        }
        return nameList;
    }

    // EFFECTS: - find String that matches User name in users and return matched User
    //          - if not matches then returns null
    public User chooseUser(String name) {
        for (Map.Entry<String, User> u: users.entrySet()) {
            if (u.getKey().equals(name)) {
                return u.getValue();
            }
        }
        return null;
    }

    // REQUIRES: name length > 0
    // MODIFIES: this
    // EFFECTS: add a new user with name, 0 initialBalance, and empty item list
    //          - return true if name does not exists in userNameList
    //          - return false if name exists in userNameList
    public boolean createUser(String name) {
        User user = new User(name, 0, new ArrayList<>());
        if (!(userNameList().contains(name))) {
            users.put(name, user);
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: add User to users and return updated users
    public void addUser(User user) {
        users.put(user.getName(), user);
    }

    // EFFECTS: get UserList name
    public String getName() {
        return name;
    }

    // EFFECTS: get list of Users
    public Map<String, User> getUsers() {
        return users;
    }

    // Method and ideas from JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("list", name);
        json.put("users", userToJson());
        return json;
    }

    // EFFECTS: returns things in this userlist as a JSON array
    private JSONArray userToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Map.Entry<String, User> u: users.entrySet()) {
            jsonArray.put(u.getValue().toJson());
        }

        return jsonArray;
    }
}
