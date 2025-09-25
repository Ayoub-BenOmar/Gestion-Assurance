package view;

import model.Person;
import service.ClientService;
import java.sql.SQLException;
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
}
