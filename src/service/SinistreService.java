package service;

import dao.SinistreDAO;
import enums.TypeContrat;
import enums.TypeSinistre;
import model.Contrat;
import model.Sinistre;

import java.util.Date;

public class SinistreService {
    SinistreDAO sinistreDAO = new SinistreDAO();

    public void addSinistre(TypeSinistre typeSinistre, Date dateTime, double cout, String description, Integer contratId){
        try{
            if (typeSinistre == null) {
                System.out.println("Error: Sinistre type must be specified");
                return;
            }

            if (dateTime == null) {
                System.out.println("Error: Date of sinistre must be provided");
                return;
            }

            java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
            if (dateTime.after(today)) {
                System.out.println("Error: Date cannot be in the future.");
                return;
            }

            if (cout <= 0) {
                System.out.println("Error: Cost must be greater than zero");
                return;
            }

            if (description == null || description.trim().isEmpty()) {
                System.out.println("Error: Description cannot be empty");
                return;
            }

            if (contratId == null || contratId <= 0){
                System.out.println("Error: Invalid contract id");
                return;
            }

            Sinistre sinistre = new Sinistre(typeSinistre,  dateTime,  cout,  description, contratId);
            sinistreDAO.addSinistre(sinistre);
        } catch (Exception e) {
            System.out.println("Eroor service: " + e.getMessage());
        }
    }
}
