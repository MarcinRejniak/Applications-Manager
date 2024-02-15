package test;

import dto.CarDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.ConnectionToDb;
import service.CarsTableManagementService;
import service.CarsTableManagementServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CarsTableTest {
    private Connection connection;
    private CarsTableManagementService carsTableManagementService;
    @BeforeEach
    void connection(){
        ConnectionToDb connectionToDb = new ConnectionToDb();
        connection = connectionToDb.connect("postgres", "postgres", "12345678");

        carsTableManagementService = new CarsTableManagementServiceImpl();
    } 

    @Test
    void testSizeOfCarsTable() throws SQLException {
//        Given
        List<CarDto> expectedCars = new LinkedList<>();

        expectedCars.add(new CarDto("Toyota", "Yaris", 2020));
        expectedCars.add(new CarDto("Ford", "Mustang", 1969));
        expectedCars.add(new CarDto("Ford", "Mondeo", 2003));
        expectedCars.add(new CarDto("BMW", "X3", 2021));
        expectedCars.add(new CarDto("Volvo", "XC60", 2023));

//        When
        carsTableManagementService.addCar(connection, "cars", "Toyota", "Yaris", 2020);
        carsTableManagementService.addCar(connection,"cars" , "Ford", "Mustang", 1969);
        carsTableManagementService.addCar(connection,"cars", "Ford", "Mondeo", 2003);
        carsTableManagementService.addCar(connection,"cars", "BMW", "X3", 2021);
        carsTableManagementService.addCar(connection,"cars", "Volvo", "XC60", 2023);

        List<CarDto> actualCars = carsTableManagementService.findAllCars(connection, "cars");

//        Then
        assertEquals(expectedCars.size(),actualCars.size());
    }
    
    @Test
    void testValuesOfOneCar() {
//        Given
        CarDto car = new CarDto("Ford", "Mustang", 1969);

//        When
        carsTableManagementService.addCar(connection, "cars", "Toyota", "Yaris", 2020);
        carsTableManagementService.addCar(connection,"cars" , "Ford", "Mustang", 1969);
        carsTableManagementService.addCar(connection,"cars", "Ford", "Mondeo", 2003);
        carsTableManagementService.addCar(connection,"cars", "BMW", "X3", 2021);
        carsTableManagementService.addCar(connection,"cars", "Volvo", "XC60", 2023);

        List<CarDto> cars = carsTableManagementService
                .getCar(connection, "cars", "Ford", "Mustang", 1969);

//        Then
        assertEquals(car.getBrand(),cars.get(0).getBrand());
        assertEquals(car.getModel(),cars.get(0).getModel());
        assertEquals(car.getYear(),cars.get(0).getYear());
    }
    
    @AfterEach
    void deletionCars(){
        carsTableManagementService.deleteAllCars(connection,"cars");
   }
}
