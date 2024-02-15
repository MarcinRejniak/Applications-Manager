package Utils;

import dto.CarDto;
import repository.AllCarsGettingRepo;
import repository.AllCarsGettingRepoImpl;
import repository.CarAdditionalRepo;
import repository.CarAdditionalRepoImpl;
import service.AllCarsGettingService;
import service.AllCarsGettingServiceImpl;
import service.CarAdditionalService;
import service.CarAdditionalServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AddingAndGettingUtils {
    public static void addAllCars(Connection connection, String tableName) throws SQLException {
        CarAdditionalRepo carAdditionalRepo = new CarAdditionalRepoImpl();
        CarAdditionalService carAdditionalService = new CarAdditionalServiceImpl(carAdditionalRepo);

        carAdditionalService.addCar(connection, tableName, "Toyota", "Yaris", 2020);
        carAdditionalService.addCar(connection, tableName, "Ford", "Mustang", 1969);
        carAdditionalService.addCar(connection, tableName, "Ford", "Mondeo", 2003);
        carAdditionalService.addCar(connection, tableName, "BMW", "X3", 2021);
        carAdditionalService.addCar(connection, tableName, "Volvo", "XC60", 2023);
    }

    public static List<CarDto> getAllCars(Connection connection, String tableName) throws SQLException{
        AllCarsGettingRepo allCarsGettingRepo = new AllCarsGettingRepoImpl();
        AllCarsGettingService allCarsGettingService = new AllCarsGettingServiceImpl(allCarsGettingRepo);

        return allCarsGettingService.findAllCars(connection, "cars");
    }
}
