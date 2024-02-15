import Utils.AddingAndGettingUtils;
import dto.CarDto;
import repository.*;
import service.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLException, InterruptedException {
        ConnectionToDb connectionToDb = new ConnectionToDb();
        Connection connection = connectionToDb.connect("postgres", "postgres", "12345678");

        CarsCreationRepo carsCreationRepo = new CarsCreationRepoImpl();
        CarsCreationService carsCreationService = new CarsCreationServiceImpl(carsCreationRepo);

        CarGettingRepo carGettingRepo = new CarGettingRepoImpl();
        CarGettingService carGettingService = new CarGettingServiceImpl(carGettingRepo);

        carsCreationService.createCarsTable(connection, "cars");

        AddingAndGettingUtils.addAllCars(connection,"cars");

        List<CarDto> cars = AddingAndGettingUtils.getAllCars(connection, "cars");
        System.out.println(cars);
        System.out.println();

        List<CarDto> carDto = carGettingService.getCar(connection, "cars", "Ford", "Mustang");
        System.out.println(carDto);

//        AllCarsDeletionServiceImpl allCarsDeletionService = new AllCarsDeletionServiceImpl();
//        allCarsDeletionService.deleteAllCars(connection,"cars");
    }
}