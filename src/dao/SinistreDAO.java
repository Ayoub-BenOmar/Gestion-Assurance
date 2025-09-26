package dao;

import connection.DatabaseConnection;
import enums.TypeContrat;
import enums.TypeSinistre;
import model.Contrat;
import model.Sinistre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SinistreDAO {
    Connection connection = DatabaseConnection.getConnection();

    public void addSinistre(Sinistre sinistre){
        String query = "INSERT INTO sinistre (type_sinistre, date_time, cout, description, contrat_id) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, sinistre.getTypeSinistre().name());
            statement.setDate(2, Date.valueOf(sinistre.getDateTime()));
            statement.setDouble(3, sinistre.getCout());
            statement.setString(4, sinistre.getDescription());
            statement.setInt(5, sinistre.getContratId());

            statement.executeUpdate();
            System.out.println("Sinistre added successfully!");
        }catch (SQLException e){
            System.out.println("Error DAO: " + e.getMessage());
        }
    }

    public int deleteSinistre(Integer id){
        String query = "DELETE FROM sinistre WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, id);
            return statement.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error DAO: " + e.getMessage());
            return 0;
        }
    }

    public Optional<Sinistre> getSinistreById(Integer id){
        String query = "SELECT id, type_sinistre, date_time, cout, description, contrat_id FROM sinistre WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    Sinistre sinistre = new Sinistre(
                            TypeSinistre.valueOf(resultSet.getString("type_sinistre")),
                            resultSet.getDate("date_time").toLocalDate(),
                            resultSet.getDouble("cout"),
                            resultSet.getString("description"),
                            resultSet.getInt("contrat_id")
                    );
                    return Optional.of(sinistre);
                }
            }
        }catch (SQLException e){
            System.out.println("Error DAO: " + e.getMessage());
        }
        return Optional.empty();
    }

    public List<Sinistre> getAllSinistres() {
        List<Sinistre> sinistres = new ArrayList<>();
        String query = "SELECT id, type_sinistre, date_time, cout, description, contrat_id FROM sinistre";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                Sinistre sinistre = new Sinistre(
                        resultSet.getInt("id"),
                        TypeSinistre.valueOf(resultSet.getString("type_sinistre")),
                        resultSet.getDate("date_time").toLocalDate(),
                        resultSet.getDouble("cout"),
                        resultSet.getString("description"),
                        resultSet.getInt("contrat_id")
                );
                sinistres.add(sinistre);
            }
        } catch (SQLException e) {
            System.out.println("Error DAO: " + e.getMessage());
        }
        return sinistres;
    }

}
