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

    public int searchConseillerById(Integer id){
        String query = "SELECT nom, prenom FROM person WHERE id = ? AND role = 1";

        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                System.out.println("Conseiller found: " + nom + prenom );
                return 1;
            }else{
                System.out.println("No conseiller found.");
                return 0;
            }
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
            return 0;
        }
    }
}
