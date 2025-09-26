package dao;

import connection.DatabaseConnection;
import enums.TypeContrat;
import model.Contrat;

import java.sql.*;
import java.util.Optional;

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

    public Optional<Contrat> getContratById(Integer id){
        String query = "SELECT id, type_contrat, date_debut, date_fin, client_id FROM contrat WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    Contrat contrat = new Contrat(
                            TypeContrat.valueOf(resultSet.getString("type_contrat")),
                            resultSet.getDate("date_debut").toLocalDate(),
                            resultSet.getDate("date_fin").toLocalDate(),
                            resultSet.getInt("client_id")
                    );
                    return Optional.of(contrat);
                }
            }
        }catch (SQLException e){
            System.out.println("Error DAO: " + e.getMessage());
        }
        return Optional.empty();
    }

}
