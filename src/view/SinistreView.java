package view;

import enums.TypeSinistre;
import model.Sinistre;
import service.SinistreService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class SinistreView {
    private SinistreService sinistreService = new SinistreService();
    private Scanner scanner = new Scanner(System.in);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public void menuSinistre() {
        int choice = -1;
        do {
            System.out.println("\n=== Gérer les Sinistres ===");
            System.out.println("1. Ajouter un sinistre");
            System.out.println("2. Supprimer un sinistre par ID");
            System.out.println("3. Calcul total des couts des sinistres d’un client");
            System.out.println("4. Rechercher un sinistre par ID");
            System.out.println("5. Afficher les sinistres d’un contrat par ID");
            System.out.println("6. Afficher les sinistres triés par montant décroissant");
            System.out.println("7. Afficher les sinistres par client ID");
            System.out.println("8. Afficher les sinistres avant une date donnée");
            System.out.println("9. Afficher les sinistres dont le cout est supérieur à un montant donné");
            System.out.println("0. Retour au menu principal");
            System.out.print("Entrez votre choix: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addSinistre();
                    break;
                case 2:
                    deleteSinistre();
                    break;
                case 3:
                    getTotalCoutByClientId();
                    break;
                case 4:
                    getSinistreById();
                    break;
                case 5:
                    getSinistresByContratId();
                    break;
                case 6:
                    getSinistresByCoutDesc();
                    break;
                case 7:
                    getSinistresByClientId();
                    break;
                case 8:
                    getSinistresBeforeDate();
                    break;
                case 9:
                    getSinistresByCoutMoreThan();
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

    public void addSinistre(){
        try {
            System.out.println("Enter sinistre type (ACCIDENT_VOITURE, ACCIDENT_MAISON, MALADIE): ");
            String typeSinistre = scanner.nextLine().toUpperCase();
            TypeSinistre type_sinistre;
            try {
                type_sinistre = TypeSinistre.valueOf(typeSinistre);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid type, must be ACCIDENT_VOITURE, ACCIDENT_MAISON, MALADIE");
                return;
            }

            System.out.println("Enter date of sinistre (YYYY-MM-DD): ");
            String dateTime = scanner.nextLine();
            LocalDate date_time;
            try {
                date_time = LocalDate.parse(dateTime);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format");
                return;
            }

            System.out.println("Enter cost: ");
            double cout = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Enter description: ");
            String description = scanner.nextLine();
            System.out.println("Enter contract ID: ");
            Integer contratId = scanner.nextInt();
            scanner.nextLine();

            sinistreService.addSinistre(type_sinistre, date_time, cout, description, contratId);

        } catch (Exception e) {
            System.out.println("Error view: " + e.getMessage());
        }
    }

    public void deleteSinistre(){
        System.out.println("Enter the sinistre id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try{
            sinistreService.deleteSinistre(id);
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void getSinistreById(){
        System.out.println("Enter the sinistre id: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        try{
            sinistreService.getSinistreById(id);
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void getSinistresByClientId() {
        System.out.print("Enter client ID: ");
        Integer id = null;
        try {
            id = Integer.valueOf(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
            return;
        }

        List<Sinistre> sinistres = sinistreService.getSinistresByClientId(id);

        if (sinistres.isEmpty()) {
            System.out.println("No sinistres found for this client ");
            return;
        }

        System.out.println("Sinistres for client " + id + " found:");
        sinistres.forEach(sinistre -> { System.out.println("ID: " + sinistre.getId() + ", Type: " + sinistre.getTypeSinistre() + ", Date: " + sinistre.getDateTime() + ", Cout: " + sinistre.getCout() + ", Desc: " + sinistre.getDescription() + ", ContratId: " + sinistre.getContratId());
        });
    }

    public void getSinistresByContratId() {
        System.out.print("Enter contrat ID: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        List<Sinistre> sinistres = sinistreService.getSinistresByContratId(id);

        if (sinistres.isEmpty()) {
            System.out.println("No sinistres found for this contrat");
        } else {
            sinistres.forEach(sinistre -> System.out.println("ID: " + sinistre.getId() + ", Type: " + sinistre.getTypeSinistre() + ", Date: " + sinistre.getDateTime() + ", Cout: " + sinistre.getCout() + ", Desc: " + sinistre.getDescription()));
        }
    }

    public void getSinistresByCoutDesc() {
        List<Sinistre> sinistres = sinistreService.getSinistresByCoutDesc();
        if (sinistres.isEmpty()) {
            System.out.println("No sinistres found.");
        } else {
            System.out.println("Sinistres sorted by amount (descending):");
            sinistres.forEach(s -> System.out.println("ID: " + s.getId() + ", Type: " + s.getTypeSinistre() + ", Date: " + s.getDateTime() + ", Cout: " + s.getCout() + ", Desc: " + s.getDescription()));
        }
    }

    public void getSinistresBeforeDate() {
        System.out.print("Enter a date (YYYY-MM-DD): ");
        String dateInput = scanner.nextLine();
        LocalDate date;
        try {
            date = LocalDate.parse(dateInput);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format.");
            return;
        }
        List<Sinistre> sinistres = sinistreService.getSinistresBeforeDate(date);
        if (sinistres.isEmpty()) {
            System.out.println("No sinistres found before this date.");
        } else {
            System.out.println("Sinistres before this date:");
            sinistres.forEach(sinistre -> System.out.println("ID: " + sinistre.getId() + ", Type: " + sinistre.getTypeSinistre() + ", Date: " + sinistre.getDateTime() + ", Cout: " + sinistre.getCout() + ", Desc: " + sinistre.getDescription()));
        }
    }

    public void getSinistresByCoutMoreThan() {
        System.out.print("Enter montant: ");
        double montant = scanner.nextDouble();
        scanner.nextLine();
        List<Sinistre> sinistres = sinistreService.getSinistresByCoutMoreThan(montant);
        if (sinistres.isEmpty()) {
            System.out.println("No sinistres found with cout more than this amount");
        } else {
            System.out.println("Sinistres with cout more than this amount: ");
            sinistres.forEach(sinistre -> System.out.println("ID: " + sinistre.getId() +", Type: " + sinistre.getTypeSinistre() +", Date: " + sinistre.getDateTime() + ", Cout: " + sinistre.getCout() + ", Desc: " + sinistre.getDescription()));
        }
    }

    public void getTotalCoutByClientId() {
        System.out.print("Enter client ID: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        double totalCout = sinistreService.getTotalCoutByClientId(id);
        System.out.println("Total cost of all sinistres for this client is: " + totalCout);
    }
}
