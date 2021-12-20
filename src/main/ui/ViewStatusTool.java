package ui;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewStatusTool extends TradeTool {

    public ViewStatusTool(Trade trade, JComponent parent, User user, GridBagConstraints gc) {
        super(trade, parent, user, gc);
    }

    // MODIFIES: this, parent
    // EFFECTS: creates JButton for "Status" with given constraints and adds it to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Status");
        button.setBounds(10, 80, 80, 25);
        parent.add(button);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object and adds it to the button
    @Override
    protected void addListener() {
        button.addActionListener(new ViewStatusToolListener());
    }

    // EFFECTS: print status of owned items
    private class ViewStatusToolListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Toolkit.getDefaultToolkit().beep();
            trade.viewTradingStatus();
        }
    }
}
