package dao;

import connection.DatabaseConnection;
import model.Client;
import model.Conseiller;
import model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    Connection connection = DatabaseConnection.getConnection();
    public void addClient(Person person){
        String query;
            query = "INSERT INTO person (role, nom, prenom, email, conseiller_id) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                Client client = (Client) person;
                statement.setInt(1, client.getRole());
                statement.setString(2, client.getName());
                statement.setString(3, client.getPrenom());
                statement.setString(4, client.getEmail());
                statement.setInt(5, client.getConseiller_id());

                statement.executeUpdate();
                System.out.println("Client added successfully.");
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
    }

    public int deleteClient(Integer id){
        String query = "DELETE FROM person WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, id);
            return statement.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
            return 0;
        }
    }

    public List<Person> searchClient(){
        String query = "SELECT role, nom, prenom, email, conseiller_id FROM person";
        List<Person> persons = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery()){
            while(resultSet.next()){
                int role = resultSet.getInt("role");
                if(role == 1){
                    Conseiller conseiller = new Conseiller(
                            resultSet.getString("nom"),
                            resultSet.getString("prenom"),
                            resultSet.getString("email")
                    );
                    persons.add(conseiller);
                } else if (role == 2) {
                    Client client = new Client(
                            resultSet.getString("nom"),
                            resultSet.getString("prenom"),
                            resultSet.getString("email"),
                            resultSet.getInt("conseiller_id")
                    );
                    persons.add(client);
                }
            }
        }catch(SQLException e){
            System.out.println("Error DAO: " + e.getMessage());
        }
        return persons;
    }
}
