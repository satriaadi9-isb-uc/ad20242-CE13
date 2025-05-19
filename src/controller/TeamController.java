package controller;

import model.DAO;
import view.TeamFilterView;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TeamController {
    private DAO dao;
    private TeamFilterView frame;

    public TeamController(DAO dao, TeamFilterView frame) {
        this.dao = dao;
        this.frame = frame;
        initController();

        frame.teamDropdown.addActionListener(e -> {
            getTeamDetails(frame.teamDropdown.getSelectedItem().toString());
            DefaultTableModel newmodel = dao.getPlayers(frame.teamDropdown.getSelectedItem().toString());
            frame.playerTable.setModel(newmodel);
        });
    }

    public void initController(){
        List<String> teams = dao.getTeams();
        for (String team : teams) {
            frame.teamDropdown.addItem(team);
        }
        getTeamDetails(frame.teamDropdown.getSelectedItem().toString());
        DefaultTableModel newmodel = dao.getPlayers(frame.teamDropdown.getSelectedItem().toString());
        frame.playerTable.setModel(newmodel);
    }

    public void getTeamDetails(String teamName){
        String[] teamDetails = dao.getTeamDetails(teamName);
        frame.teamIdField.setText(teamDetails[0]);
        frame.stadiumNameField.setText(teamDetails[2]);
        frame.cityField.setText(teamDetails[3]);
        frame.managerNameField.setText(teamDetails[4]);
        frame.assistantManagerField.setText(teamDetails[5]);
        frame.captainNameField.setText(teamDetails[6]);
    }
}
