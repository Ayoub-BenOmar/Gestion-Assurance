package dao;

import connection.DatabaseConnection;
import model.Person;
import model.Conseiller;

import java.sql.*;

public class ConseillerDAO {
    Connection connection = DatabaseConnection.getConnection();
    public void addConseiller(Person person){
        String query;
            query = "INSERT INTO person (role, nom, prenom, email) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                Conseiller conseiller = (Conseiller) person;
                statement.setInt(1, conseiller.getRole());
                statement.setString(2, conseiller.getName());
                statement.setString(3, conseiller.getPrenom());
                statement.setString(4, conseiller.getEmail());

                statement.executeUpdate();
                System.out.println("Conseiller added successfully.");
            }catch (SQLException e){
                System.out.println("Error: " + e.getMessage());
            }
    }

    public int deleteConseiller(Integer id){
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
