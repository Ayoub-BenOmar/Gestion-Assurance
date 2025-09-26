import connection.DatabaseConnection;
import view.MainMenu;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection connection = new DatabaseConnection();
        connection.getConnection();
        MainMenu mainMenu = new MainMenu();

        mainMenu.mainMenu();
    }
}