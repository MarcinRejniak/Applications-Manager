package test;

import dto.CarDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.InteractionWithDB;
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

    private final String tableName = "cars";
    private final String toyota = "Toyota";
    private final String ford = "Ford";
    private final String volvo = "Volvo";
    private final String bmw = "BMW";
    private final String yaris = "Yaris";
    private final String mustang = "Mustang";
    private final String mondeo = "Mondeo";
    private final String x3 = "X3";
    private final String xc60 = "XC60";

    @BeforeEach
    void connection(){
        InteractionWithDB interactionWithDB = new InteractionWithDB();
        connection = interactionWithDB.connect("postgres", "postgres", "12345678");

        carsTableManagementService = new CarsTableManagementServiceImpl();
    }

    @Test
    void testSizeOfCarsTable() throws SQLException {
//        Given
        List<CarDto> expectedCars = new LinkedList<>();

        expectedCars.add(new CarDto(toyota, yaris, 2020));
        expectedCars.add(new CarDto(ford, mustang, 1969));
        expectedCars.add(new CarDto(ford, mondeo, 2003));
        expectedCars.add(new CarDto(bmw, x3, 2021));
        expectedCars.add(new CarDto(volvo, xc60, 2023));

//        When
        addCars();

        List<CarDto> actualCars = carsTableManagementService.findAllCars(connection, tableName);

//        Then
        assertEquals(expectedCars.size(),actualCars.size());
    }

    @Test
    void testValuesOfOneCar() throws SQLException {
//        Given
        CarDto car = new CarDto(ford, mustang, 1969);

//        When
        addCars();

        List<CarDto> cars = carsTableManagementService
                .getCar(connection, this.tableName, ford, mustang, 1969);

//        Then
        assertEquals(car.getBrand(),cars.get(0).getBrand());
        assertEquals(car.getModel(),cars.get(0).getModel());
        assertEquals(car.getYear(),cars.get(0).getYear());
    }

    @AfterEach
    void deletionCars() throws SQLException {
        carsTableManagementService.deleteAllCars(connection, tableName);
   }

    private void addCars() throws SQLException {
        carsTableManagementService.addCar(connection, tableName, toyota, yaris, 2020);
        carsTableManagementService.addCar(connection, tableName, ford, mustang, 1969);
        carsTableManagementService.addCar(connection, tableName, ford, mondeo, 2003);
        carsTableManagementService.addCar(connection, tableName, bmw, x3, 2021);
        carsTableManagementService.addCar(connection, tableName, volvo, xc60, 2023);
    }
}
