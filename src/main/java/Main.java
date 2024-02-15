import repository.ConnectionToDb;
import service.CarsTableManagementService;
import service.CarsTableManagementServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLException, InterruptedException {
        ConnectionToDb connectionToDb = new ConnectionToDb();
        Connection connection = connectionToDb.connect("postgres", "postgres", "12345678");

        CarsTableManagementService carsTableManagementService = new CarsTableManagementServiceImpl();
        carsTableManagementService.manage(connection);
    }
}