package ui;

import model.UserList;
import ui.LoginStart;

import javax.swing.JButton;
import javax.swing.JComponent;

// Abstract tool for JButton
public abstract class Tool {
    protected JButton button;
    protected LoginStart loginStart;
    protected UserList list;

    public Tool(LoginStart loginStart, JComponent parent, UserList list) {
        this.loginStart = loginStart;
        this.list = list;
        createButton(parent);
        addToParent(parent);
        addListener();
    }


    // EFFECTS: creates a button for the tool
    protected abstract void createButton(JComponent parent);

    // EFFECTS: adds listener to the button
    protected abstract void addListener();

    // MODIFIES: parent
    // EFFECTS: adds the button to the parent component with the given constraints
    public void addToParent(JComponent parent) {
        parent.add(button);
    }
}
