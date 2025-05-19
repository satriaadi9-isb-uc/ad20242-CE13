import controller.TeamController;
import model.DAO;
import view.TeamFilterView;

public class Main {
    public static void main(String[] args) {
        DAO dao = new DAO();
        TeamFilterView teamFilterView = new TeamFilterView();
        new TeamController(dao, teamFilterView);
        teamFilterView.setVisible(true);
    }
}