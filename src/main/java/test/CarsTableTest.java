package test;

import Utils.AddingAndGettingUtils;
import dto.CarDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.*;
import service.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CarsTableTest {
    private Connection connection;
    
    @BeforeEach
    void connection(){
        ConnectionToDb connectionToDb = new ConnectionToDb();
        connection = connectionToDb.connect("postgres", "postgres", "12345678");
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
        AddingAndGettingUtils.addAllCars(connection, "cars");
        List<CarDto> actualCars = AddingAndGettingUtils.getAllCars(connection, "cars");

//        Then
        assertEquals(expectedCars.size(),actualCars.size());
    }
    
    @Test
    void testValuesOfOneCar() throws SQLException {
//        Given
        CarDto car = new CarDto("Ford", "Mustang", 1969);

        CarGettingRepo carGettingRepo = new CarGettingRepoImpl();
        CarGettingService carGettingService = new CarGettingServiceImpl(carGettingRepo);

//        When
        AddingAndGettingUtils.addAllCars(connection,"cars");
        List<CarDto> cars = carGettingService.getCar(connection, "cars", "Ford", "Mustang");
        
//        Then
        assertEquals(car.getBrand(),cars.get(0).getBrand());
        assertEquals(car.getModel(),cars.get(0).getModel());
        assertEquals(car.getYear(),cars.get(0).getYear());
    }
    
    @AfterEach
    void deletionCars(){
        AllCarsDeletionServiceImpl allCarsDeletionService = new AllCarsDeletionServiceImpl();
        allCarsDeletionService.deleteAllCars(connection, "cars");
   }
}
