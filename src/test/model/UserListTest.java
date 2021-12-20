package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class UserListTest {
    private UserList testUserList;
    private User danny;
    private User qq;

    @BeforeEach
    void runBefore() {
        testUserList = new UserList("new");
        danny = new User("danny",1000, new ArrayList<>());
        testUserList.addUser(danny);

        qq = new User("qq", 2000, new ArrayList<>());
        testUserList.addUser(qq);
    }

    @Test
    void testUserGetName() {
        assertEquals("new", testUserList.getName());
    }

    @Test
    void testUserNameList() {
        Set<String> list = new HashSet<>();
        list.add("danny");
        list.add("qq");

        assertEquals(list, testUserList.userNameList());
    }

    @Test
    void testChooseUser() {
        User sasuke = new User("sasuke",1000, new ArrayList<>());
        testUserList.addUser(sasuke);

        assertEquals(sasuke, testUserList.chooseUser("sasuke"));
        assertNull(testUserList.chooseUser("as"));
    }

    @Test
    void testCreateUser() {
        assertTrue(testUserList.createUser("naruto"));
        assertFalse(testUserList.createUser("danny"));
    }


    @Test
    void testGetUsers() {
        Map<String, User> u = new HashMap<>();
        u.put("danny", danny);
        u.put("qq", qq);

        assertEquals(u, testUserList.getUsers());
    }
}
