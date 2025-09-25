package view;

import model.Client;
import model.Person;
import service.ClientService;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ClientView {
    ClientService clientService = new ClientService();
    Scanner scanner = new Scanner(System.in);

    public void deleteClientButton(){
        System.out.println("Enter the id of the user u wanna delete: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        try{
            clientService.deleteClient(id);
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void addClientMenu() {
        System.out.println("=== Add New Client ===");
        System.out.print("Nom: ");
        String nom = scanner.nextLine();
        System.out.print("Prenom: ");
        String prenom = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter conseiller_id: ");
        Integer conseillerId = scanner.nextInt();
        scanner.nextLine();

        try {
            clientService.addUser(2, nom, prenom, email, conseillerId);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void searchClient(){
        System.out.println("Enter the client name: ");
        String name = scanner.next();
        scanner.nextLine();

        List<Client> clients = clientService.searchClient(name);
        try {
            if (clients.isEmpty()){
                System.out.println("No clients with this name foud.");
                return;
            } else{
                clients.forEach(client -> System.out.println("Name: " + client.getName() +  ", " + "Prenom: " + client.getPrenom()));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void searchClientById(){
        System.out.println("Enter the client id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try{
            clientService.searchClientById(id);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
