package service;

import dao.ConseillerDAO;
import model.Conseiller;
import model.Person;

public class ConseillerService {
    ConseillerDAO conseillerDAO = new ConseillerDAO();

    public void addConseiller(Integer role, String nom, String prenom, String email){
        Person person;
        if (role != 1){
            throw new RuntimeException();
        }

        person = new Conseiller(nom, prenom, email);
        conseillerDAO.addConseiller(person);
    }

    public void deleteConseiller(Integer id){
        try {
            if (id <= 0){
                System.out.println("Enter a valid id.");
                return;
            }

            int rows = conseillerDAO.deleteConseiller(id);
            if(rows > 0){
                System.out.println("Conseiller deleted successfully.");
                return;
            }else {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
