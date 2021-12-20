package ui;

import model.Item;
import model.User;
import model.UserList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Scanner method from TellerApp and is to get users input
// Trade system application
public class TradeSystem {
    private static final String JSON_STORE = "./data/UserList.json";
    private UserList list;
    private final Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // MODIFIES: this
    // EFFECTS: initializes list of users
    //          runs the teller application
    public TradeSystem() {
        list = new UserList("new");
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        runTrade();
    }

    // Code quoted from TellerApp, while method is to loop users operation
    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTrade() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            displayMenu1();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("c")) {
                System.out.println("Enter new User name");
                doCreateUser();
            } else if (command.equals("l")) {
                System.out.println("Enter User name");
                loginUser();
            } else if (command.equals("q")) {
                keepGoing = false;
            } else if (command.equals("1")) {
                saveWorkRoom();
            } else if (command.equals("2")) {
                loadWorkRoom();
            }
        }
        System.out.println("Good Bye!");
    }

    // Code quoted from TellerApp, while method is to loop users operation
    // MODIFIES: this
    // EFFECTS: processes user input and command
    private void processCommand(User user) {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            displayMenu2();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("d")) {
                printBalanceItems(user);
                doDeposit(user);
            } else if (command.equals("w")) {
                printBalanceItems(user);
                doWithdraw(user);
            } else if (command.equals("b")) {
                printBalanceItems(user);
                doBuy(user);
            } else if (command.equals("s")) {
                printBalanceItems(user);
                doSell(user);
            } else if (command.equals("q")) {
                keepGoing = false;
            }
        }
        System.out.println("Good Bye!");
    }

    // REQUIRES: input.next() length > 0
    // MODIFIES: this
    // EFFECTS: - create user that it's name is not duplicated in user list
    //          - if duplicated then runTrade application
    private void doCreateUser() {
        String name = input.next();
        if (list.createUser(name)) {
            processCommand(list.chooseUser(name));
        } else {
            runTrade();
        }
    }

    // REQUIRES: input.next() length > 0
    // MODIFIES: this
    // EFFECTS: - login user that it's name is in user list
    //          - if does not matches then runTrade application
    private void loginUser() {
        String name = input.next();
        if (list.userNameList().contains(name)) {
            processCommand(list.chooseUser(name));
        } else {
            runTrade();
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts a deposit transaction
    private void doDeposit(User user) {
        System.out.print("Enter amount: $");
        int amount = input.nextInt();

        if (amount > 0) {
            user.deposit(amount);
            printBalance(user);
        } else {
            System.out.print("Deposit fail");
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts a withdraw transaction
    private void doWithdraw(User user) {
        System.out.print("Withdraw amount: $");
        int amount = input.nextInt();

        if (amount > 0 && amount <= user.getBalance()) {
            user.withdraw(amount);
            printBalance(user);
        } else {
            System.out.print("Withdraw fail");
        }
    }

    // REQUIRES: input.next() length > 0, input.nextInt() > 0
    // MODIFIES: this
    // EFFECTS: item is set to Item
    //          - buy item with itemName and itemPrice
    private void doBuy(User user) {
        System.out.print("Enter Item name to buy: ");
        String itemName = input.next();

        if (!(user.getItemNames().contains(itemName))) {
            System.out.print("Enter price of item: $");
            int itemPrice = input.nextInt();
            Item item = new Item(itemName, itemPrice);
            if (item.getPrice() >= 0) {
                user.buyItem(item);
                System.out.print("Buy success!");
                printBalanceItems(user);
            }
        } else {
            System.out.println("Cannot Buy");
        }
    }

    // REQUIRES: input.next() length > 0, input.nextInt() > 0
    // MODIFIES: this
    // EFFECTS: item is set to Item
    //          - sell item with itemName and itemPrice
    private void doSell(User user) {
        System.out.print("Enter item name to sell: ");
        String itemName = input.next();

        if (user.checkExistItem(itemName)) {
            user.sellItem(user.chooseItem(itemName));
            System.out.print("Sell success!");
            printBalanceItems(user);
        } else {
            System.out.println("Cannot Sell");
        }
    }

    // EFFECTS: displays menu of options to login or signup
    private void displayMenu1() {
        System.out.println("Save               (1)");
        System.out.println("Load               (2)");
        System.out.println("Login              (l)");
        System.out.println("Create new Account (c)");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu2() {
        System.out.println("\nSelect from:");
        System.out.println("Deposit   (d)");
        System.out.println("Withdraw  (w)");
        System.out.println("Buy       (b)");
        System.out.println("Sell      (s)");
        System.out.println("LogOut    (q)");
    }

    // EFFECTS: prints balance of account to the screen
    private void printBalance(User user) {
        System.out.println(" - Balance:" + user.getBalance());
    }

    // EFFECTS: prints owned items of account to the screen
    private void printItems(User user) {
        System.out.println(" - Items:" + user.getItemNames());
    }

    // EFFECTS: prints owned items and balance of account to the screen
    private void printBalanceItems(User user) {
        printBalance(user);
        printItems(user);
    }

    // EFFECTS: saves the workroom to file
    private void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(list);
            jsonWriter.close();
            System.out.println("Saved " + list.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadWorkRoom() {
        try {
            list = jsonReader.read();
            System.out.println("Loaded " + list.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
