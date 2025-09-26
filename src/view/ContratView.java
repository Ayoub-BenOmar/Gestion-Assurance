package view;

import enums.TypeContrat;
import model.Contrat;
import service.ContratService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ContratView {
    ContratService contratService = new ContratService();
    Scanner scanner = new Scanner(System.in);

    public void menuContrat() {
        int choice = -1;
        do {
            System.out.println("\n=== Gérer les Contrats ===");
            System.out.println("1. Ajouter un contrat");
            System.out.println("2. Affichage des informations d’un contrat par ID");
            System.out.println("3. Supprimer un contrat par ID");
            System.out.println("4. Afficher les contrats d’un client par ID");
            System.out.println("0. Retour au menu principal");
            System.out.print("Entrez votre choix: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addContrat();
                    break;
                case 2:
                    getContratById();
                    break;
                case 3:
                    deleteContrat();
                    break;
                case 4:
                    getContractsByClientId();
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

    public void addContrat(){
        System.out.println("Enter contrat type (AUTOMOBILE, MAISON, MALADIE): ");
        String typeContrat = scanner.nextLine().toUpperCase();
        System.out.println("Enter debut date (YYYY-MM-DD): ");
        String date_debut = scanner.nextLine();
        System.out.println("Enter end date (YYYY-MM-DD): ");
        String date_fin = scanner.nextLine();
        System.out.println("Enter the client id: ");
        Integer client_id = scanner.nextInt();
        scanner.nextLine();

        try{
            TypeContrat typeContrat1 = TypeContrat.valueOf(typeContrat);
            LocalDate dateDebut = LocalDate.parse(date_debut);
            LocalDate dateFin = LocalDate.parse(date_fin);
            contratService.addContrat(typeContrat1, dateDebut, dateFin, client_id);
        }catch (IllegalArgumentException e){
            System.out.println("Error: Invalid contract type Please use AUTOMOBILE, MAISON, or MALADIE.");
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void getContratById(){
        System.out.println("Enter the contrat id: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        try{
            contratService.getClientById(id);
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteContrat(){
        System.out.println("Enter the contrat id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try{
            contratService.deleteContrat(id);
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void getContractsByClientId(){
        System.out.println("Enter the client id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try{
            List<Contrat> contrats = contratService.getContractsByClientId(id);
            contrats.forEach(contrat -> System.out.println("ID: " + contrat.getId() + ", Type of contrat: " + contrat.getTypeContrat() + ", Start: " + contrat.getDateDebut() + ", End: " + contrat.getDateFin()));
        } catch (Exception e) {
            System.out.println("Error: " +e.getMessage());
        }
    }
}
