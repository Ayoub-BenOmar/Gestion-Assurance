package dao;

import connection.DatabaseConnection;
import model.Client;
import model.Conseiller;
import model.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientDAO {
    Connection connection = DatabaseConnection.getConnection();
    public void addUser(Person person){
        String query;

        try {
            if (person instanceof Client){
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
            } else if(person instanceof Conseiller){
                query = "INSERT INTO person (role, nom, prenom, email) VALUES (?, ?, ?, ?)";
                try(PreparedStatement statement = connection.prepareStatement(query)){
                    Conseiller conseiller = (Conseiller) person;
                    statement.setInt(1, conseiller.getRole());
                    statement.setString(2, conseiller.getName());
                    statement.setString(3, conseiller.getPrenom());
                    statement.setString(4, conseiller.getEmail());

                    statement.executeUpdate();
                    System.out.println("Conseiller added successfully.");
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteUser(int id){
        String query = "DELETE FROM person WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("User deleted successfully.");
        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
