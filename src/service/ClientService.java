package service;

import dao.ClientDAO;
import model.Client;
import model.Person;

public class ClientService {
    ClientDAO clientDAO =  new ClientDAO();

    public void addUser(Integer role, String nom, String prenom, String email, Integer conseiller_id){
        Person person;
        try {
            if (role != 2){
                throw new RuntimeException();
            }

            if (conseiller_id == null || conseiller_id <= 0) {
                System.out.println("Enter a valid conseiller_id.");
                return;
            }
            person = new Client(nom, prenom, email, conseiller_id);

            clientDAO.addClient(person);
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteClient(Integer id){
        try{
            if(id == null || id <= 0){
                System.out.println("Enter a valid number.");
                return;
            }
            int rows = clientDAO.deleteClient(id);
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
