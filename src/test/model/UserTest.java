package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User testUser;
    private Item dogo;
    private Item catty;
    private List<Item> itemList;
    private final int BAL = 10000;

    @BeforeEach
    void runBefore() {
        dogo = new Item("dogo", 1000);
        catty = new Item("catty", 1500);
        itemList = new ArrayList<>();
        itemList.add(dogo);
        itemList.add(catty);
        testUser = new User("sam", BAL, itemList);
    }

    @Test
    void testConstructor() {
        List<String> list = Arrays.asList("dogo", "catty");

        assertEquals("sam", testUser.getName());
        assertEquals(10000, testUser.getBalance());
        assertEquals(list, testUser.getItemNames());
    }

    @Test
    void testDeposit() {
        testUser.deposit(5000);
        assertEquals(15000, testUser.getBalance());
    }

    @Test
    void testDeposit1() {
        testUser.deposit(10000);
        assertEquals(20000, testUser.getBalance());
    }

    @Test
    void testDeposit2() {
        testUser.deposit(20000);
        assertEquals(30000, testUser.getBalance());
    }

    @Test
    void testWithdraw() {
        testUser.withdraw(5000);
        assertEquals(5000, testUser.getBalance());
    }

    @Test
    void testBuyItem() {
        Item rat = new Item("rat", 100000);
        Item mice = new Item("mice", 1000);
        List<String> list = Arrays.asList("dogo", "catty", "mice");
        testUser.buyItem(mice);

        assertEquals(9000, testUser.getBalance());
        assertEquals(list, testUser.getItemNames());
        assertFalse(testUser.buyItem(mice));
        assertFalse(testUser.buyItem(rat));
    }

    @Test
    void testSellItem() {
        Item fat = new Item("fat", 1000);
        testUser.sellItem(dogo);
        assertEquals(11000, testUser.getBalance());

        assertTrue(testUser.sellItem(catty));
        assertEquals(12500, testUser.getBalance());
        assertFalse(testUser.sellItem(fat));
    }

    @Test
    void testChooseItem() {
        assertEquals(dogo, testUser.chooseItem("dogo"));
        assertNull(testUser.chooseItem("aa"));
    }

    @Test
    void testCheckExistItem() {
        assertTrue(testUser.checkExistItem("dogo"));
        assertFalse(testUser.checkExistItem("aa"));
    }

    @Test
    void testGetItems() {
        assertEquals(itemList, testUser.getItems());
    }

}