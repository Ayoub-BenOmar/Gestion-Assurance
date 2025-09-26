package view;

import enums.TypeSinistre;
import service.SinistreService;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class SinistreView {
    private SinistreService sinistreService = new SinistreService();
    private Scanner scanner = new Scanner(System.in);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

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
            Date date_time;
            try {
                java.util.Date utilDate = dateFormat.parse(dateTime);
                date_time = new Date(utilDate.getTime());
            } catch (ParseException e) {
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
}
