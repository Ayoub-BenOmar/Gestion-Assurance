package dao;

import connection.DatabaseConnection;
import model.Client;
import model.Person;

import java.sql.*;

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
}
