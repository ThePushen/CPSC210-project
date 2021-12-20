package ui;

import model.UserList;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class LoginTool extends Tool {
    private UserList list;
    private JTextField txtName;

    public LoginTool(LoginStart loginStart, JComponent parent, UserList list, JTextField txtName) {
        super(loginStart, parent, list);
        this.list = list;
        this.txtName = txtName;
    }

    // MODIFIES: this, parent
    // EFFECTS: creates JButton for "Login" with given constraints and adds it to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Login");
        button.setBounds(10, 80, 80, 25);
        parent.add(button);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object and adds it to the button
    @Override
    protected void addListener() {
        button.addActionListener(new LoginToolListener());
    }

    private class LoginToolListener implements ActionListener {
        // EFFECTS: - if entered User name exists in UserList then shows JOptionPane message
        //            "Login Success！" and run Trade
        //          - else just return JOptionPane message "Login Fail！" and stays on Start frame
        @Override
        public void actionPerformed(ActionEvent e) {
            String userName = txtName.getText();
            User user = list.chooseUser(userName);

            if (list.userNameList().contains(userName)) {
                JOptionPane.showMessageDialog(null, "Login Success！", "Login Info", JOptionPane.INFORMATION_MESSAGE);
                loginStart.setVisible(false);
                Trade trade = new Trade(user, list);
            } else {
                JOptionPane.showMessageDialog(null, "Login Fail！", "Login Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
