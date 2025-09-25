import connection.DatabaseConnection;
import service.ClientService;
import view.ClientView;
import view.ConseillerView;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DatabaseConnection connection = new DatabaseConnection();
        connection.getConnection();
        ClientView clientView = new ClientView();
        ConseillerView conseillerView = new ConseillerView();
//        clientView.searchClient();
//        clientView.addClientMenu();
        conseillerView.getClientsByConseillerId();
    }
}