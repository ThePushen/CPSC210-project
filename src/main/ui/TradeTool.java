package ui;

import model.User;
import ui.Trade;

import javax.swing.*;
import java.awt.*;

// Abstract tool for JButton
public abstract class TradeTool {
    protected JButton button;
    protected Trade trade;
    protected User user;

    public TradeTool(Trade trade, JComponent parent, User user, GridBagConstraints gc) {
        this.trade = trade;
        this.user = user;
        createButton(parent);
        addToParent(parent, gc);
        addListener();
    }

    // EFFECTS: creates a button for the tool
    protected abstract void createButton(JComponent parent);

    // EFFECTS: adds listener to the button
    protected abstract void addListener();

    // MODIFIES: parent
    // EFFECTS: adds the button to the parent component with the given constraints
    public void addToParent(JComponent parent, GridBagConstraints gc) {
        parent.add(button, gc);
    }
}
