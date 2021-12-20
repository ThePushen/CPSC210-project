package ui;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

public class DepositTool extends TradeTool {
    private JTextField balance;

    public DepositTool(Trade trade, JComponent parent, User user, JTextField balance, GridBagConstraints gc) {
        super(trade, parent, user, gc);
        this.balance = balance;
    }

    // MODIFIES: this, parent
    // EFFECTS: creates JButton for "Deposit" with given constraints and adds it to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Deposit");
        button.setBounds(10, 80, 80, 25);
        parent.add(button);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object and adds it to the button
    @Override
    protected void addListener() {
        button.addActionListener(new DoDeposit());
    }

    private class DoDeposit implements ActionListener {

        // EFFECTS: conducts a deposit transaction
        @Override
        public void actionPerformed(ActionEvent e) {
            String depositInt = balance.getText();
            int deposit = Integer.parseInt(depositInt);

            if (deposit > 0) {
                user.deposit(deposit);
                trade.setText("Deposit: $" + deposit);
            } else {
                trade.setText("Deposit fail");
            }
            trade.setBalance();
        }
    }
}
