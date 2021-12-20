package persistence;

import model.Item;
import model.UserList;
import model.User;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            UserList ul = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyUserList() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyUserList.json");
        try {
            UserList ul = reader.read();
            assertEquals("aa", ul.getName());
            assertEquals(0, ul.userNameList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralUserList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralUserList.json");
        try {
            UserList ul = reader.read();
            assertEquals("44", ul.getName());

            Map<String, User> users = ul.getUsers();
            assertEquals(2, ul.userNameList().size());
            checkUser("sam", 1000, ul.chooseUser("sam").getItems(), users.get("Sam"));
            checkUser("bob", 2000, ul.chooseUser("bob").getItems(), users.get("bob"));

            List<Item> list = new ArrayList<>();
            Item aa = new Item("aa", 100);
            list.add(aa);
            assertEquals(list, ul.chooseUser("sam").getItems());

            Item i = ul.chooseUser("sam").getItems().get(0);
            assertEquals("aa", i.toJson().getString("itemName"));
            assertEquals(100, i.toJson().getInt("price"));
            checkItem("aa", i.getPrice(), i);
        } catch (IOException e) {
            //pass
        }
    }
}
