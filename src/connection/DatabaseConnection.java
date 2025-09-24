package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;

public class DatabaseConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/Assurance_db";
    private static final String username = "postgres";
    private static final String password = "barca1230/";

    private static Connection connection;

    public static Connection getConnection(){
        try {
            try{
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e){
                System.err.println("Class not found: " + e.getMessage());
            }

            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database.");
        } catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

//    public Connection getConnection(){
//        return connection;
//    }

    public void testQuery(){
        String query = "SELECT * FROM person";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while(rs.next()){
                System.out.println(rs.getString("nom"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
