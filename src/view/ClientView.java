package view;

import model.Person;
import service.ClientService;

import java.sql.SQLException;
import java.util.Scanner;

public class ClientView {
    ClientService clientService = new ClientService();
    Scanner scanner = new Scanner(System.in);

    public void deleteUserButton(){
        System.out.println("Enter the id of the user u wanna delete: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        try{
            clientService.deleteUser(id);
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void searchConseillerById(){
        System.out.println("Enter the conseiller id: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        try {
            clientService.searchConseillerById(id);
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void addUserMenu() {
        System.out.println("=== Add New Person ===");
        System.out.print("Nom: ");
        String nom = scanner.nextLine();
        System.out.print("Prenom: ");
        String prenom = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.println("Choose role: 1-Conseiller | 2-Client");
        Integer role = scanner.nextInt();
        scanner.nextLine();

        Integer conseillerId = null;
        if (role == 2) {
            System.out.print("Enter conseiller_id: ");
            conseillerId = scanner.nextInt();
            scanner.nextLine();
        }

        try {
            clientService.addUser(role, nom, prenom, email, conseillerId);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
