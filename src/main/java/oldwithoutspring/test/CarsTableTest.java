package oldwithoutspring.test;

import oldwithoutspring.dto.CarDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import oldwithoutspring.service.CarsTableManagementService;
import oldwithoutspring.service.CarsTableManagementServiceImpl;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CarsTableTest {
    private CarsTableManagementService carsTableManagementService;

    private static final String CARS_TABLE_NAME = "cars";

    @BeforeEach
    void connection() {
        carsTableManagementService = new CarsTableManagementServiceImpl();
    }

    @Test
    void testSizeOfCarsTable() {
//        Given
        List<CarDto> expectedCars = new LinkedList<>();

        expectedCars.add(new CarDto("Ford", "Mustang", 1969));
        expectedCars.add(new CarDto("Ford", "Mondeo", 2003));
        expectedCars.add(new CarDto("Toyota", "Yaris", 2020));
        expectedCars.add(new CarDto("BMW", "X3", 2021));
        expectedCars.add(new CarDto("Volvo", "XC60", 2023));

//        When
        addCars();

        List<CarDto> actualCars = carsTableManagementService.findAllCars(CARS_TABLE_NAME);

//        Then
        assertEquals(expectedCars.size(), actualCars.size());
    }

    @Test
    void testValuesOfOneCar() {
//        Given
        CarDto carExpected = new CarDto("Ford", "Mustang", 1969);

//        When
        addCars();

        CarDto carActual = carsTableManagementService
                .getCar(CarsTableTest.CARS_TABLE_NAME, 2);

//        Then
        assertEquals(carExpected.getBrand(), carActual.getBrand());
        assertEquals(carExpected.getModel(), carActual.getModel());
        assertEquals(carExpected.getYear(), carActual.getYear());
    }

    @AfterEach
    void deletionCars() {
        carsTableManagementService.deleteAllCars(CARS_TABLE_NAME);
    }

    private void addCars() {
        carsTableManagementService.addCar(CARS_TABLE_NAME, "Toyota", "Yaris", 2020);
        carsTableManagementService.addCar(CARS_TABLE_NAME, "Ford", "Mustang", 1969);
        carsTableManagementService.addCar(CARS_TABLE_NAME, "Ford", "Mondeo", 2003);
        carsTableManagementService.addCar(CARS_TABLE_NAME, "BMW", "X3", 2021);
        carsTableManagementService.addCar(CARS_TABLE_NAME, "Volvo", "XC60", 2023);
    }
}
