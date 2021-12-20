package ui;

import model.UserList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// file menu for save and load
public class FileMenu {
    private static final String JSON_STORE = "./data/UserList.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private UserList list;
    private JFrame frame;

    public FileMenu(JFrame frame, UserList list) {
        this.list = list;
        this.frame = frame;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        createMenus(frame);
    }

    // MODIFIES: this, mb
    // EFFECTS: constructs the file menu with constraints and add it to frame
    private void createMenus(JFrame frame) {
        JMenuBar mb = new JMenuBar();

        // Define the main menu
        JMenu file = new JMenu("File");

        // Define submenu for file
        JMenuItem load = new JMenuItem("Load");
        JMenuItem save = new JMenuItem("Save");

        load.addActionListener(new LoadAction());
        save.addActionListener(new SaveAction());

        // Add submenu to file
        file.add(load);
        file.add(save);

        // Add menu to mb
        mb.add(file);

        frame.setJMenuBar(mb);
    }

    private class LoadAction implements ActionListener {
        // EFFECTS: runs loadSystem method
        @Override
        public void actionPerformed(ActionEvent e) {
            loadSystem();
        }
    }

    private class SaveAction implements ActionListener {
        // EFFECTS: run saveSystem method
        @Override
        public void actionPerformed(ActionEvent e) {
            saveSystem();
        }
    }


    // MODIFIES: this
    // EFFECTS: loads system from JSon
    private void loadSystem() {
        try {
            list = jsonReader.read();
            System.out.println("Loaded " + list.getName() + " from " + JSON_STORE);
            JOptionPane.showMessageDialog(null, "Loaded", "Load Info", JOptionPane.INFORMATION_MESSAGE);
            frame.setVisible(false);
            LoginStart loginStart = new LoginStart(list);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the system to JSon
    private void saveSystem() {
        try {
            jsonWriter.open();
            jsonWriter.write(list);
            jsonWriter.close();
            System.out.println("Saved " + list.getName() + " to " + JSON_STORE);
            JOptionPane.showMessageDialog(null, "Saved", "Save Info", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }
}
