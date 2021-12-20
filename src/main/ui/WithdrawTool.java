package ui;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WithdrawTool extends TradeTool {
    private JTextField balance;

    public WithdrawTool(Trade trade, JComponent parent, GridBagConstraints gc, User user, JTextField balance) {
        super(trade, parent, user, gc);
        this.balance = balance;
    }

    // MODIFIES: this, parent
    // EFFECTS: creates JButton for "Withdraw" with given constraints and adds it to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Withdraw");
        button.setBounds(10, 80, 80, 25);
        parent.add(button);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object and adds it to the button
    @Override
    protected void addListener() {
        button.addActionListener(new DoWithdraw());
    }

    private class DoWithdraw implements ActionListener {

        // EFFECTS: conducts a withdraw transaction
        @Override
        public void actionPerformed(ActionEvent e) {
            String withdrawInt = balance.getText();
            int withdraw = Integer.parseInt(withdrawInt);

            if (withdraw > 0 && withdraw <= user.getBalance()) {
                user.withdraw(withdraw);
                trade.setText("Withdraw: $" + withdraw);
            } else {
                trade.setText("Withdraw fail");
            }
            trade.setBalance();
        }
    }
}
