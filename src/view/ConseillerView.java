package view;

import service.ConseillerService;

import java.util.Scanner;

public class ConseillerView {
    ConseillerService conseillerService = new ConseillerService();
    Scanner scanner = new Scanner(System.in);

    public void addConseillerMenu(){
        System.out.println("=== Add New Conseiller ===");
        System.out.println("Enter conseiller name: ");
        String nom = scanner.nextLine();
        System.out.println("Enter conseiller prenom: ");
        String prenom = scanner.nextLine();
        System.out.println("Enter conseiller email: ");
        String email = scanner.nextLine();

        try {
            conseillerService.addConseiller(1, nom, prenom, email);
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteConseiller(){
        System.out.println("Enter the id of the conseiller: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try{
            conseillerService.deleteConseiller(id);
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
