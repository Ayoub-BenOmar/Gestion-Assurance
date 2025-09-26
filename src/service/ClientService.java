package service;

import dao.ClientDAO;
import model.Client;
import model.Person;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Client> searchClient(String nom){
        return clientDAO.searchClient().stream()
                .filter(person -> person instanceof Client)
                .map(person -> (Client) person)
                .filter(client -> client.getName().equalsIgnoreCase(nom))
                .sorted(Comparator.comparing(Client::getPrenom))
                .collect(Collectors.toList());
    }

    public void searchClientById(Integer id){
        Optional<Client> clientOptional = clientDAO.searchClientById(id);
        try {
            if (clientOptional.isPresent()){
                Client client = clientOptional.get() ;
                System.out.println("Client found: " + client.getName() + " " + client.getPrenom());
            }else{
                System.out.println("No client found with this ID.");
            }
        } catch (Exception e) {
            System.out.println("Error service: " + e.getMessage());
        }
    }
}
