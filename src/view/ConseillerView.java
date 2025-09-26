package view;

import model.Person;
import service.ConseillerService;
import java.util.List;
import java.util.Scanner;

public class ConseillerView {
    ConseillerService conseillerService = new ConseillerService();
    Scanner scanner = new Scanner(System.in);

    public void menuConseiller() {
        int choice = -1;
        do {
            System.out.println("\n=== Gérer les Conseillers ===");
            System.out.println("1. Ajouter un conseiller");
            System.out.println("2. Supprimer un conseiller par ID");
            System.out.println("3. Rechercher un conseiller par ID");
            System.out.println("4. Afficher les clients d'un conseiller par ID");
            System.out.println("0. Retour au menu principal");
            System.out.print("Entrez votre choix: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addConseillerMenu();
                    break;
                case 2:
                    deleteConseiller();
                    break;
                case 3:
                    searchConseillerById();
                    break;
                case 4:
                    getClientsByConseillerId();
                    break;
                case 0:
                    System.out.println("Retour au menu principal");
                    break;
                default : System.out.println("Choix invalide !");
                    break;
            }
        } while (choice != 0);
    }

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

    public void searchConseillerById(){
        System.out.println("Enter the conseiller id: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        try {
            conseillerService.searchConseillerById(id);
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void getClientsByConseillerId(){
        System.out.println("Enter the conseiller id: ");
        int id = scanner.nextInt();

        List<Person> clients = conseillerService.getClientsByConseillerId(id);
        try {
            if(clients.isEmpty()){
                System.out.println("No clients found for this conseiller id ");
                return;
            }else {
                clients.forEach(client -> System.out.println("name: " + client.getName()));
            }
        } catch (Exception e) {
            System.out.println("Error view: " + e.getMessage());
        }
    }
}
