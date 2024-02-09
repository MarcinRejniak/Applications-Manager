import repository.*;
import service.CarAdditionalService;
import service.CarAdditionalServiceImpl;
import service.CarsCreationService;
import service.CarsCreationServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;



public class Main {
    public static void main(String[] args) throws SQLException, InterruptedException {
        ConnectionToDb connectionToDb = new ConnectionToDb();
        Connection connection = connectionToDb.connect("postgres", "postgres", "12345678");

        CarsCreationRepo carsCreationRepo = new CarsCreationRepoImpl();
        CarAdditionalRepo carAdditionalRepo = new CarAdditionalRepoImpl();

        CarsCreationService carsCreationService = new CarsCreationServiceImpl(carsCreationRepo);
        CarAdditionalService carAdditionalService = new CarAdditionalServiceImpl(carAdditionalRepo);

        carsCreationService.createCarsTable(connection, "cars");
        carAdditionalService.addCar(connection, "cars", "Ford", "Mustang", 1969);


    }
}