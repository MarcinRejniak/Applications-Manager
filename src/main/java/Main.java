import repository.InteractionWithDB;
import service.CarsTableManagementService;
import service.CarsTableManagementServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        InteractionWithDB interactionWithDB = new InteractionWithDB();
        Connection connection = interactionWithDB.connect("postgres", "postgres", "12345678");

        CarsTableManagementService carsTableManagementService = new CarsTableManagementServiceImpl();
        carsTableManagementService.manage(connection);
    }
}