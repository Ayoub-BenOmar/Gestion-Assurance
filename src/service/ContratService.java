package service;

import dao.ContratDAO;
import enums.TypeContrat;
import model.Contrat;

import java.time.LocalDate;
import java.util.Optional;

public class ContratService {
    ContratDAO contratDAO = new ContratDAO();

    public void addContrat(TypeContrat typeContrat, LocalDate date_debut, LocalDate date_fin, Integer client_id){
        try {
            if (date_fin.isBefore(date_debut)) {
                System.out.println("Error: End date cannot be before start date.");
                return;
            }

            if (client_id == null || client_id <= 0) {
                System.out.println("Error: Invalid client id.");
                return;
            }

            if (!contratDAO.isClient(client_id)){
                System.out.println("Error: id should be a client id.");
                return;
            }
            Contrat contrat = new Contrat(typeContrat, date_debut, date_fin, client_id);
            contratDAO.addContrat(contrat);
        }catch (Exception e) {
            System.out.println("Error service: " + e.getMessage());
        }
    }

    public void getClientById(Integer id){
        Optional<Contrat> contratOptional = contratDAO.getContratById(id);
        try{
            if(contratOptional.isPresent()){
                Contrat contrat = contratOptional.get();
                System.out.println("Contrat found: " + contrat.getTypeContrat() + ", " + contrat.getDateDebut() + ", " + contrat.getDateFin() + ", " + contrat.getClient_id());
            }else{
                System.out.println("No contrat found with this ID.");
            }
        }catch (Exception e) {
            System.out.println("Error service: " + e.getMessage());
        }
    }
}
