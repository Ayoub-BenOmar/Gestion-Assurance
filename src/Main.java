import connection.DatabaseConnection;
import service.ClientService;
import view.ClientView;
import view.ConseillerView;
import view.ContratView;
import view.SinistreView;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DatabaseConnection connection = new DatabaseConnection();
        connection.getConnection();
//        ClientView clientView = new ClientView();
//        ConseillerView conseillerView = new ConseillerView();
//        ContratView contratView = new ContratView();
        SinistreView sinistreView = new SinistreView();


//        contratView.getContractsByClientId();
//        clientView.searchClient();
//        clientView.addClientMenu();
//        conseillerView.getClientsByConseillerId();
//        clientView.searchClientById();
        sinistreView.getTotalCoutByClientId();

    }
}