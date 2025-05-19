package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class TeamFilterView extends JFrame {
    public JComboBox<String> teamDropdown;
    public JTextField teamIdField, stadiumNameField, cityField, managerNameField, assistantManagerField, captainNameField;
    public JTable playerTable;
    public DefaultTableModel playerTableModel;

    public TeamFilterView() {
        setTitle("Team and Player Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(800, 600);

        // Team Selection Panel
        JPanel teamPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        teamDropdown = new JComboBox<>();

        teamIdField = new JTextField();
        stadiumNameField = new JTextField();
        cityField = new JTextField();
        managerNameField = new JTextField();
        assistantManagerField = new JTextField();
        captainNameField = new JTextField();

        teamPanel.add(new JLabel("Team Name"));
        teamPanel.add(teamDropdown);
        teamPanel.add(new JLabel("Team ID"));
        teamPanel.add(teamIdField);
        teamPanel.add(new JLabel("Stadium Name"));
        teamPanel.add(stadiumNameField);
        teamPanel.add(new JLabel("City"));
        teamPanel.add(cityField);
        teamPanel.add(new JLabel("Manager Name"));
        teamPanel.add(managerNameField);
        teamPanel.add(new JLabel("Ass. Manager Name"));
        teamPanel.add(assistantManagerField);
        teamPanel.add(new JLabel("Captain Name"));
        teamPanel.add(captainNameField);

        add(teamPanel, BorderLayout.NORTH);

        // Player Table

        String[] playerColumns = {"Team Number", "Player Name", "Position", "Nationality"};
        playerTableModel = new DefaultTableModel(playerColumns, 0);
        playerTable = new JTable(playerTableModel);
        JScrollPane playerScrollPane = new JScrollPane(playerTable);
        add(playerScrollPane, BorderLayout.CENTER);
    }
}