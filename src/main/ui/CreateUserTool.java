package ui;

import model.User;
import model.UserList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CreateUserTool extends Tool {
    private UserList list;
    private JTextField txtName;

    public CreateUserTool(LoginStart loginStart, JComponent parent, UserList list, JTextField txtName) {
        super(loginStart, parent, list);
        this.list = list;
        this.txtName = txtName;
    }

    // MODIFIES: this, parent
    // EFFECTS: creates JButton for "Create User" with given constraints and adds it to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Create User");
        button.setBounds(100, 80, 100, 25);
        parent.add(button);
    }

    // MODIFIES: list
    // EFFECTS: constructs a new listener object and adds it to the button
    @Override
    protected void addListener() {
        button.addActionListener(new CreateUserToolListener());
    }

    public class CreateUserToolListener implements ActionListener {

        // EFFECTS: - if entered User name does not exist in UserList then shows JOptionPane message
        //            "Create success！" and run Trade
        //          - else just return JOptionPane message "User existed" and stays on Start frame
        @Override
        public void actionPerformed(ActionEvent e) {
            String userName = txtName.getText();

            if (list.createUser(userName)) {
                if (!(userName.length() == 0)) {
                    JOptionPane.showMessageDialog(null, "Create success！", "Create Info",
                            JOptionPane.INFORMATION_MESSAGE);
                    User user = list.chooseUser(userName);
                    loginStart.setVisible(false);
                    Trade trade = new Trade(user, list);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid！", "Create Info", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "User existed", "Create Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
