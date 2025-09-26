package view;

import model.Client;
import service.ClientService;
import java.util.List;
import java.util.Scanner;

public class ClientView {
    ClientService clientService = new ClientService();
    Scanner scanner = new Scanner(System.in);

    public void menuClient() {
        int choice = -1;
        do {
            System.out.println("\n=== Gérer les Clients ===");
            System.out.println("1. Ajouter un client");
            System.out.println("2. Supprimer un client par ID");
            System.out.println("3. Rechercher un client par nom de famille");
            System.out.println("4. Rechercher un client par ID");
            System.out.println("0. Retour au menu principal");
            System.out.print("Entrez votre choix: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addClientMenu();
                    break;
                case 2:
                    deleteClient();
                    break;
                case 3:
                    searchClient();
                    break;
                case 4:
                    searchClientById();
                    break;
                case 0:
                    System.out.println("Retour au menu principal");
                    break;
                default:
                    System.out.println("Choix invalide !");
                    break;
            }
        } while (choice != 0);
    }

    public void deleteClient(){
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
