package dao;

import connection.DatabaseConnection;
import model.Contrat;

import java.sql.*;

public class ContratDAO {
    Connection connection = DatabaseConnection.getConnection();

    public void addContrat(Contrat contrat){
        String query = "INSERT INTO contrat (type_contrat, date_debut, date_fin, client_id) VALUES (?, ?, ?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, contrat.getTypeContrat().name());
            statement.setDate(2, Date.valueOf(contrat.getDateDebut()));
            statement.setDate(3, Date.valueOf(contrat.getDateFin()));
            statement.setInt(4, contrat.getClient_id());

            statement.executeUpdate();
            System.out.println("Contrat added successfully");
        }catch(SQLException e){
            System.out.println("Error DAO: " + e.getMessage());
        }
    }

    public boolean isClient(Integer id) {
        String query = "SELECT id FROM person WHERE id = ? AND role = 2";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("Error DAO: " + e.getMessage());
            return false;
        }
    }

}
