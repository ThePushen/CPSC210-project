package ui;

import model.UserList;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
/*
 * A basic swing example with JButton
 */

public class LoginStart extends JFrame {
    private static int WIDTH = 350;
    private static int HEIGHT = 200;
    private UserList list;
    private JTextField txtName;
    private FileMenu createMenu;

    // The tools in the application
    private List<Tool> tools;

    // EFFECTS: create window for Start application
    public LoginStart(UserList list) {
        super("Trade System");
        this.list = list;
        tools = new LinkedList<Tool>();
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS: draws the JFrame window for Start, and adds tools for Start
    private void initializeGraphics() {
        createMenu = new FileMenu(this, list);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        createStartTool();
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: declares and instantiates all the tool buttons
    private void createStartTool() {
        JPanel panel = new JPanel();
        add(panel, BorderLayout.CENTER);

        panel.setLayout(null);

        // Creating JLabel
        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(50,20,80,25);
        panel.add(userLabel);

        txtName = new JTextField(20);
        txtName.setBounds(100,20,165,25);
        panel.add(txtName);

        // Define new buttons
        LoginTool login = new LoginTool(this, panel, list, txtName);
        tools.add(login);

        CreateUserTool create = new CreateUserTool(this, panel, list, txtName);
        tools.add(create);
    }
}