package model;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    public DAO(){
        try(Connection conn = Database.getConnection()) {
            System.out.println("--Connected to database--");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getTeams(){
        String sql = "select team_name from team";
        List<String> teams = new ArrayList<String>();
        try{
            Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                teams.add(rs.getString("team_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teams;
    }

    public String[] getTeamDetails(String teamName){
        String sql = "select t.team_id,\n" +
                "       t.team_name,\n" +
                "       t.home_stadium,\n" +
                "       t.city,\n" +
                "       m.manager_name as main_manager,\n" +
                "       am.manager_name as ass_manager,\n" +
                "       cap.player_name as captain\n" +
                "from team t\n" +
                "join manager m on t.manager_id=m.manager_id\n" +
                "left join manager am on t.assmanager_id = am.manager_id\n" +
                "join player cap on t.captain_id = cap.player_id\n" +
                "where t.team_name='"+teamName+"'";

        String[] data = new String[7];
        try(Connection conn = Database.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()){
                data[0] = rs.getString("team_id");
                data[1] = rs.getString("team_name");
                data[2] = rs.getString("home_stadium");
                data[3] = rs.getString("city");
                data[4] = rs.getString("main_manager");
                data[5] = rs.getString("ass_manager");
                data[6] = rs.getString("captain");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return data;
    }

    public DefaultTableModel getPlayers(String team_name){
        String sql="select p.team_number,\n" +
                "       p.player_name,\n" +
                "       case\n" +
                "           when p.playing_pos='G' then 'Goalkeeper'\n" +
                "           when p.playing_pos='M' then 'Midfielder'\n" +
                "           when p.playing_pos='F' then 'Forward'\n" +
                "           when p.playing_pos='D' then 'Defender'\n" +
                "       end as playing_pos,\n" +
                "       n.nation\n" +
                "from team t\n" +
                "join player p on t.team_id = p.team_id\n" +
                "join nationality n on p.nationality_id = n.nationality_id\n" +
                "where t.team_name='"+team_name+"'\n" +
                "order by p.team_number ASC;";

        DefaultTableModel model = new DefaultTableModel(new String[]{"Team Number", "Player Name", "Position", "Nationality"},0);
        try(Connection conn = Database.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()){
                String teamNumber = rs.getString("team_number");
                String playerName = rs.getString("player_name");
                String position = rs.getString("playing_pos");
                String nationality = rs.getString("nation");
                model.addRow(new Object[]{teamNumber,playerName,position,nationality});
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return model;

    }


}
