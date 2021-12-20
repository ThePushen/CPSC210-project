package ui;

import model.UserList;

// runs Start system
public class Main {
    public static void main(String[] args) {
        new LoginStart(new UserList("new"));
    }
}
