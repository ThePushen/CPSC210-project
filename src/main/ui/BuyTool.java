package ui;

import model.Item;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

public class BuyTool extends TradeTool {
    private JTextField nameText;
    private JTextField priceText;

    public BuyTool(Trade trade, JComponent parent, User user, GridBagConstraints gc,
                   JTextField nameText, JTextField priceText) {
        super(trade, parent, user, gc);
        this.nameText = nameText;
        this.priceText = priceText;
    }

    // MODIFIES: this, parent
    // EFFECTS: creates JButton for "Buy" with given constraints and adds it to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Buy");
        button.setBounds(10, 80, 80, 25);
        parent.add(button);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object and adds it to the button
    @Override
    protected void addListener() {
        button.addActionListener(new DoBuy());
    }

    private class DoBuy implements ActionListener {

        // MODIFIES: user
        // EFFECTS: buy item with itemName and itemPrice
        //          if entered name does not exist in user item list and price >= 0 them adds item to user item list
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameText.getText();
            String price = priceText.getText();
            int itemPrice = Integer.parseInt(price);
            Item item = new Item(name, itemPrice);

            if (!(user.getItemNames().contains(name)) && 0 <= item.getPrice() && item.getPrice() <= user.getBalance()) {
                user.buyItem(item);
                trade.setText("Buy Success!" + trade.printItem(item));
            } else {
                trade.setText("Fail to buy!");
            }
            trade.setAddNameField();
            trade.setItemPrice();
        }
    }
}
