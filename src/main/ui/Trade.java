package ui;

import model.Item;
import model.User;
import model.UserList;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;

public class Trade extends JFrame {

    private static final int WIDTH = 700;
    private static final int HEIGHT = 400;
    private JPanel textPanel;
    private JPanel toolArea;
    private JTextArea textArea;
    private JTextField nameText;
    private JTextField priceText;
    private JTextField atmText;
    private JLabel name;
    private JLabel price;
    private JLabel atm;
    private List<TradeTool> tools;
    private FileMenu createMenu;
    private Font textFieldFont;
    private User user;
    private UserList list;

    // EFFECTS: create window for Trade
    public Trade(User user, UserList list) {
        super("Trade System");
        this.user = user;
        this.list = list;
        textFieldFont = new Font("Helvetica",Font.PLAIN,14);
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS: draws the JFrame window for the TradeSystem, and add tools that will work on it
    private void initializeGraphics() {
        tools = new LinkedList<TradeTool>();
        createMenu = new FileMenu(this, list);

        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        createTextPanel();
        createTools();
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates the area where all messages will be printed to
    //          has a title showing User name
    private void createTextPanel() {
        textPanel = new JPanel();
        textArea = new JTextArea();
        textArea.setFont(textFieldFont);
        textPanel.setPreferredSize(new Dimension(WIDTH * 2 / 3, HEIGHT));
        Color c = new Color(210, 255, 129);
        textPanel.setBackground(c);

        JEditorPane editorPane = new JEditorPane();
        editorPane.setText("User: " + user.getName());
        editorPane.setEditable(false);
        setPreferredSize(new Dimension(WIDTH * 2 / 3, HEIGHT));

        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setPreferredSize(new Dimension(WIDTH * 2 / 3 - 10, HEIGHT - 100));

        textArea.setEditable(false);
        textArea.setBackground(Color.WHITE);
        viewTradingStatus();

        textPanel.add(editorPane);
        textPanel.add(scroll);

        add(textPanel, BorderLayout.WEST);
    }

    // MODIFIES: this
    // EFFECTS: sets the text in the text area to the given text
    public void setText(String txt) {
        textArea.setText(txt);
    }

    // MODIFIES: this
    // EFFECTS: declares and instantiates all the tool buttons on the right side of the window
    private void createTools() {
        Container toolContainer = getContentPane();
        toolArea = new JPanel();
        toolArea.setLayout(new GridBagLayout());
        toolArea.setPreferredSize(new Dimension(WIDTH / 3, HEIGHT));
        toolContainer.add(toolArea, BorderLayout.EAST);

        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0.5;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy = 0;
        JPanel tradePanel = new JPanel();
        createAddPanel(tradePanel);
        toolArea.add(tradePanel, gc);

        gc.gridy = 2;
        JPanel depositWithdraw = new JPanel();
        depositWithdraw(depositWithdraw);
        toolArea.add(depositWithdraw, gc);

        gc.gridy = 3;
        TradeTool viewStatusTool = new ViewStatusTool(this, toolArea, user, gc);
        tools.add(viewStatusTool);

        gc.gridy = 4;
        TradeTool logoutTool = new LogOutTool(this, toolArea, user, gc, list);
        tools.add(logoutTool);
    }

    // MODIFIES: this
    // EFFECTS: constructs the area to trade items
    private void createAddPanel(JPanel tradePanel) {
        tradePanel.setLayout(new GridBagLayout());
        tradePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Trade: "),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        GridBagConstraints tradeGC = new GridBagConstraints();
        tradeGC.weightx = 0.5;
        tradeGC.weighty = 0.5;
        tradeGC.insets = new Insets(0,0,10,0);

        nameLabel(tradePanel, tradeGC);
        nameText(tradePanel, tradeGC);
        priceLabel(tradePanel, tradeGC);
        priceText(tradePanel, tradeGC);
        buyButton(tradePanel, tradeGC);
        sellButton(tradePanel, tradeGC);
    }

    // MODIFIES: this
    // EFFECTS: constructs the name label and add it to tradePanel
    private void nameLabel(JPanel tradePanel, GridBagConstraints tradeGC) {
        name = new JLabel("Name: ");
        tradeGC.gridx = 0;
        tradeGC.anchor = GridBagConstraints.LINE_END;
        tradePanel.add(name, tradeGC);
    }

    // MODIFIES: this
    // EFFECTS: constructs the name text and add it to tradePanel
    private void nameText(JPanel tradePanel, GridBagConstraints tradeGC) {
        tradeGC.anchor = GridBagConstraints.LINE_START;
        tradeGC.gridx = 1;
        nameText = new JTextField(20);
        nameText.setMinimumSize(new Dimension(80, 25));
        tradePanel.add(nameText, tradeGC);
    }

    // MODIFIES: this
    // EFFECTS: constructs the price label and add it to tradePanel
    private void priceLabel(JPanel tradePanel, GridBagConstraints tradeGC) {
        price = new JLabel("Price: ");
        tradeGC.anchor = GridBagConstraints.LINE_END;
        tradeGC.gridx = 0;
        tradeGC.gridy = 1;
        tradePanel.add(price, tradeGC);
    }

    // MODIFIES: this
    // EFFECTS: constructs the price text and add it to tradePanel
    private void priceText(JPanel tradePanel, GridBagConstraints tradeGC) {
        tradeGC.anchor = GridBagConstraints.LINE_START;
        tradeGC.gridx = 1;
        tradeGC.gridy = 1;
        priceText = new JTextField(20);
        priceText.setMinimumSize(new Dimension(80, 25));
        tradePanel.add(priceText, tradeGC);
    }

    // MODIFIES: this
    // EFFECTS: constructs the buy button and add it to tradePanel
    private void buyButton(JPanel tradePanel, GridBagConstraints tradeGC) {
        tradeGC.anchor = GridBagConstraints.LINE_START;
        tradeGC.gridx = 0;
        tradeGC.gridy = 3;
        BuyTool buyTool = new BuyTool(this, tradePanel, user, tradeGC, nameText, priceText);
        tools.add(buyTool);
    }

    // MODIFIES: this
    // EFFECTS: constructs the sell button and add it to tradePanel
    private void sellButton(JPanel tradePanel, GridBagConstraints tradeGC) {
        tradeGC.anchor = GridBagConstraints.LINE_START;
        tradeGC.gridx = 1;
        tradeGC.gridy = 3;
        SellTool sellTool = new SellTool(this, tradePanel, user, tradeGC,  nameText);
        tools.add(sellTool);
    }

    // MODIFIES: this
    // EFFECTS: constructs the area to deposit or withdraw money
    private void depositWithdraw(JPanel atmPanel) {
        atmPanel.setLayout(new GridBagLayout());
        atmPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Deposit or Withdraw:"),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        GridBagConstraints atmBC = new GridBagConstraints();
        atmBC.weightx = 0.5;
        atmBC.weighty = 0.5;
        atmBC.insets = new Insets(0,0,10,0);

        atmLabel(atmPanel, atmBC);
        atmText(atmPanel, atmBC);
        depositButton(atmPanel, atmBC);
        withdrawButton(atmPanel, atmBC);
    }

    // MODIFIES: this
    // EFFECTS: constructs the atm label and add it to atmPanel
    private void atmLabel(JPanel atmPanel, GridBagConstraints atmBC) {
        atm = new JLabel("$: ");
        atmBC.anchor = GridBagConstraints.LINE_END;
        atmBC.gridx = 0;
        atmBC.gridy = 0;
        atmPanel.add(atm, atmBC);
    }

    // MODIFIES: this
    // EFFECTS: constructs the atm text and add it to atmPanel
    private void atmText(JPanel atmPanel, GridBagConstraints atmBC) {
        atmBC.anchor = GridBagConstraints.LINE_START;
        atmBC.gridx = 1;
        atmBC.gridy = 0;
        atmText = new JTextField(20);
        atmText.setMinimumSize(new Dimension(80, 25));
        atmPanel.add(atmText);
    }

    // MODIFIES: this
    // EFFECTS: constructs the deposit button and add it to atmPanel
    private void depositButton(JPanel atmPanel, GridBagConstraints atmBC) {
        atmBC.gridx = 0;
        atmBC.gridy = 1;
        DepositTool depositTool = new DepositTool(this, atmPanel, user, atmText, atmBC);
        tools.add(depositTool);
    }

    // MODIFIES: this
    // EFFECTS: constructs the withdraw button and add it to atmPanel
    private void withdrawButton(JPanel atmPanel, GridBagConstraints atmBC) {
        atmBC.gridx = 1;
        atmBC.gridy = 1;
        WithdrawTool withdrawTool = new WithdrawTool(this, atmPanel, atmBC,  user, atmText);
        tools.add(withdrawTool);
    }

    // EFFECTS: print user's status (items, balance)
    public void viewTradingStatus() {
        String status = "Balance: $" + user.getBalance() + "\nOwned items: " + printOwnedItems();
        setText(status);
    }

    // EFFECTS: prints out owned items in the Item list
    public String printOwnedItems() {
        StringBuilder sb = new StringBuilder();
        if (user.getItems().size() >= 1) {
            for (Item i : user.getItems()) {
                sb.append("\nName: ").append(i.getName()).append("    Price: ")
                        .append(i.getPrice());
            }
        } else {
            sb.append("\nNo items owned");
        }
        return sb.toString();
    }

    // EFFECTS: print the given item
    public String printItem(Item i) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nName: ").append(i.getName()).append("    Price: ")
                .append(i.getPrice());
        return sb.toString();
    }

    // MODIFIES: this
    // EFFECTS: sets the name field to empty string
    public void setAddNameField() {
        nameText.setText("");
    }

    // MODIFIES: this
    // EFFECTS: sets the price field to empty string
    public void setItemPrice() {
        priceText.setText("");
    }

    // MODIFIES: this
    // EFFECTS: sets the deposit/withdraw field to empty string
    public void setBalance() {
        atmText.setText("");
    }
}
