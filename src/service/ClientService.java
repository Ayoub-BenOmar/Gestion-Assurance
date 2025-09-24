package service;

import dao.ClientDAO;
import model.Client;
import model.Conseiller;
import model.Person;

import java.sql.SQLException;

public class ClientService {
    private ClientDAO clientDAO =  new ClientDAO();

    public void addUser(Integer role, String nom, String prenom, String email, Integer conseiller_id){
        Person person;
        try {
            if (role != 1 && role != 2){
                System.out.println("Role should be: 1- Conseiller or 2- Client.");
                return;
            }

            if (role == 2) {
                if (conseiller_id == null || conseiller_id <= 0) {
                    System.out.println("Enter a valid conseiller_id.");
                    return;
                }
                person = new Client(nom, prenom, email, conseiller_id);
            } else {
                person = new Conseiller(nom, prenom, email);
            }

            clientDAO.addUser(person);
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteUser(Integer id){
        try{
            if(id == null || id <= 0){
                System.out.println("Enter a valid number.");
                return;
            }
            int rows = clientDAO.deleteUser(id);
            if (rows > 0){
                System.out.println("User deleted successfully");
            }else{
                System.out.println("No user found");
            }
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void searchConseillerById(Integer id){
        try{
            if(id == null || id <= 0){
                System.out.println("Enter a valid number.");
                return;
            }
            int rows = clientDAO.searchConseillerById(id);
            if (rows <= 0){
                System.out.println("No user found");
            }
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
