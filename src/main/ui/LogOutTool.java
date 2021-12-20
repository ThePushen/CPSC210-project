package ui;

import model.User;
import model.UserList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogOutTool extends TradeTool {
    private Trade trade;
    private UserList list;

    public LogOutTool(Trade trade, JComponent parent, User user, GridBagConstraints gc, UserList list) {
        super(trade, parent, user, gc);
        this.trade = trade;
        this.list = list;
    }

    // MODIFIES: this, parent
    // EFFECTS: creates JButton for "Logout" with given constraints and adds it to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Logout");
        button.setBounds(10, 80, 80, 25);
        parent.add(button);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object and adds it to the button
    @Override
    protected void addListener() {
        button.addActionListener(new LogOutToolListener());
    }

    // EFFECTS: print status of owned items
    private class LogOutToolListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "You have Logout", "Logout", JOptionPane.INFORMATION_MESSAGE);
            trade.setVisible(false);
            LoginStart loginStart = new LoginStart(list);
        }
    }
}
