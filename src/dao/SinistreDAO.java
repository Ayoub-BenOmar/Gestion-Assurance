package dao;

import connection.DatabaseConnection;
import model.Sinistre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SinistreDAO {
    Connection connection = DatabaseConnection.getConnection();

    public void addSinistre(Sinistre sinistre){
        String query = "INSERT INTO sinistre (type_sinistre, date_time, cout, description, contrat_id) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, sinistre.getTypeSinistre().name());
            statement.setDate(2, new java.sql.Date(sinistre.getDateTime().getTime()));
            statement.setDouble(3, sinistre.getCout());
            statement.setString(4, sinistre.getDescription());
            statement.setInt(5, sinistre.getContratId());

            statement.executeUpdate();
            System.out.println("Sinistre added successfully!");
        }catch (SQLException e){
            System.out.println("Error DAO: " + e.getMessage());
        }
    }
}
