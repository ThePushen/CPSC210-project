package persistence;

import model.User;
import model.Item;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkUser(String name, int balance, List<Item> items, User user) {
        assertEquals(name, user.getName());
        assertEquals(balance, user.getBalance());
        assertEquals(items, user.getItems());
    }

    protected void checkItem(String name, int price, Item item) {
        assertEquals(name, item.getName());
        assertEquals(price, item.getPrice());
    }
}
