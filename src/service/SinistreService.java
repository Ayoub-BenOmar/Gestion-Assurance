package service;

import dao.SinistreDAO;
import enums.TypeContrat;
import enums.TypeSinistre;
import model.Contrat;
import model.Sinistre;

import java.util.Date;
import java.util.Optional;

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

    public void deleteSinistre(Integer id){
        try{
            if (id == null || id <= 0){
                System.out.println("Error: Enter a valid id number");
                return;
            }
            int rows = sinistreDAO.deleteSinistre(id);
            if (rows > 0){
                System.out.println("Sinistre deleted successfully");
                return;
            }else{
                System.out.println("No sinistre found with this id");
            }
        }catch (Exception e){
            System.out.println("Error service: " + e.getMessage());
        }
    }

    public void getSinistreById(Integer id){
        Optional<Sinistre> sinistreOptional = sinistreDAO.getSinistreById(id);
        try{
            if(sinistreOptional.isPresent()){
                Sinistre sinistre = sinistreOptional.get();
                System.out.println("Sinistre found: " + sinistre.getTypeSinistre() + ", " + sinistre.getDateTime() + ", " + sinistre.getCout() + ", " + sinistre.getDescription() + ", " + sinistre.getContratId());
            }else{
                System.out.println("No sinistre found with this ID.");
            }
        }catch (Exception e) {
            System.out.println("Error service: " + e.getMessage());
        }
    }
}
