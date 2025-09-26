package service;

import dao.ContratDAO;
import dao.SinistreDAO;
import enums.TypeContrat;
import enums.TypeSinistre;
import model.Contrat;
import model.Sinistre;

import java.time.LocalDate;
import java.util.stream.Collectors;

import java.util.*;

public class SinistreService {
    SinistreDAO sinistreDAO = new SinistreDAO();

    public void addSinistre(TypeSinistre typeSinistre, LocalDate dateTime, double cout, String description, Integer contratId){
        try{
            if (typeSinistre == null) {
                System.out.println("Error: Sinistre type must be specified");
                return;
            }

            if (dateTime == null) {
                System.out.println("Error: Date of sinistre must be provided");
                return;
            }

//            java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
//            if (dateTime.after(today)) {
//                System.out.println("Error: Date cannot be in the future.");
//                return;
//            }

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

    public List<Sinistre> getSinistresByClientId(Integer id) {
        if (id == null || id <= 0) {
            return Collections.emptyList();
        }
        List<Sinistre> sinistres = sinistreDAO.getAllSinistres();
        ContratDAO contratDAO = new ContratDAO();
        return sinistres.stream()
                .filter(sinistre -> {
                    Optional<Contrat> contratOpt = contratDAO.getContratById(sinistre.getContratId());
                    return contratOpt.map(contrat -> Objects.equals(contrat.getClient_id(), id))
                            .orElse(false);
                })
                .collect(Collectors.toList());
    }

    public List<Sinistre> getSinistresByContratId(Integer id) {
        if (id == null || id <= 0) {
            System.out.println("Enter a valid contrat id");
            return Collections.emptyList();
        }
        return sinistreDAO.getAllSinistres().stream()
                .filter(sinistre -> Objects.equals(sinistre.getContratId(), id))
                .collect(Collectors.toList());
    }

    public List<Sinistre> getSinistresByCoutDesc() {
        return sinistreDAO.getAllSinistres().stream()
                .sorted((sinistre1, sinistre2) -> Double.compare(sinistre2.getCout(), sinistre1.getCout()))
                .collect(Collectors.toList());
    }

    public List<Sinistre> getSinistresBeforeDate(LocalDate date) {
        if (date == null) {
            System.out.println("Enter a valid date");
            return Collections.emptyList();
        }
        return sinistreDAO.getAllSinistres().stream()
                .filter(sinistre -> sinistre.getDateTime().isBefore(date))
                .collect(Collectors.toList());
    }

    public List<Sinistre> getSinistresByCoutMoreThan(double montant) {
        if (montant <= 0) {
            System.out.println("Montant should be positive.");
            return Collections.emptyList();
        }
        return sinistreDAO.getAllSinistres().stream()
                .filter(sinistre -> sinistre.getCout() > montant)
                .collect(Collectors.toList());
    }

    public double getTotalCoutByClientId(Integer id) {
        if (id == null || id <= 0) {
            System.out.println("Enter a valid client ID");
            return 0;
        }
        ContratService contratService = new ContratService();
        List<Contrat> contrats = contratService.getContractsByClientId(id);
        List<Sinistre> sinistres = sinistreDAO.getAllSinistres().stream()
                .filter(sinistre -> contrats.stream()
                        .anyMatch(c -> c.getId().equals(sinistre.getContratId()))
                )
                .collect(Collectors.toList());
        return sinistres.stream()
                .mapToDouble(Sinistre::getCout)
                .sum();
    }

}
