package ui;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SellTool extends TradeTool {
    private JTextField nameText;

    public SellTool(Trade trade, JComponent parent, User user, GridBagConstraints gc, JTextField nameText) {
        super(trade, parent, user, gc);
        this.nameText = nameText;
    }

    // MODIFIES: this, parent
    // EFFECTS: creates JButton for "Sell" with given constraints and adds it to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Sell");
        button.setBounds(10, 80, 80, 25);
        parent.add(button);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object and adds it to the button
    @Override
    protected void addListener() {
        button.addActionListener(new DoSell());
    }

    private class DoSell implements ActionListener {
        // MODIFIES: user
        // EFFECTS: sell item with itemName and itemPrice
        //          - if entered name exists in user item list them remove item from user item list and update balance
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameText.getText();

            if (user.checkExistItem(name)) {
                user.sellItem(user.chooseItem(name));
                trade.setText("Sell success!");
            } else {
                trade.setText("Sell fail");
            }
            trade.setAddNameField();
            trade.setItemPrice();
        }
    }
}
