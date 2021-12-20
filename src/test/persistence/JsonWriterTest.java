package persistence;

import model.UserList;
import model.User;
import model.Item;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            UserList ul = new UserList("nn");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyUserList() {
        try {
            UserList ul = new UserList("aa");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyUserList.json");
            writer.open();
            writer.write(ul);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyUserList.json");
            ul = reader.read();
            assertEquals("aa", ul.getName());
            assertEquals(0, ul.userNameList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralUserList() {
        try {
            UserList ul = new UserList("44");
            List<Item> li = new ArrayList<>();
            Item aa = new Item("aa", 100);
            li.add(aa);

            User sam = new User("sam", 1000, li);
            ul.addUser(sam);
            User bob = new User("bob", 2000, li);
            ul.addUser(bob);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralUserList.json");
            writer.open();
            writer.write(ul);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralUserList.json");
            ul = reader.read();
            assertEquals("44", ul.getName());
            assertEquals(2, ul.userNameList().size());

            Map<String, User> users = ul.getUsers();
            checkUser("sam", 1000, ul.chooseUser("sam").getItems(), users.get("sam"));
            checkUser("bob", 2000, ul.chooseUser("bob").getItems(), users.get("bob"));

            List<Item> items = ul.getUsers().get("sam").getItems();
            assertEquals("aa", items.get(0).toJson().getString("itemName"));
            assertEquals(100, items.get(0).toJson().getInt("price"));
            checkItem("aa", 100, items.get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
