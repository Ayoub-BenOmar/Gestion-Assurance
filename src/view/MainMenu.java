package view;

import java.util.Scanner;

public class MainMenu {
    private Scanner scanner = new Scanner(System.in);
    private ConseillerView conseillerView = new ConseillerView();
    private ClientView clientView = new ClientView();
    private ContratView contratView = new ContratView();
    private SinistreView sinistreView = new SinistreView();

    public void mainMenu() {
        int choice = -1;
        do {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Gérer les Conseillers");
            System.out.println("2. Gérer les Clients");
            System.out.println("3. Gérer les Contrats");
            System.out.println("4. Gérer les Sinistres");
            System.out.println("0. Quitter");
            System.out.print("Entrez votre choix: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    conseillerView.menuConseiller();
                    break;
                case 2: clientView.menuClient();
                    break;
                case 3: contratView.menuContrat();
                    break;
                case 4: sinistreView.menuSinistre();
                    break;
                case 0: System.out.println("Au revoir !");
                    break;
                default: System.out.println("Choix invalide !");
                    break;
            }
        } while (choice != 0);
    }
}
