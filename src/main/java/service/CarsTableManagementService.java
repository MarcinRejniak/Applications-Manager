package service;

import dto.CarDto;

import java.util.List;

public interface CarsTableManagementService {
    void manage();
    void createCarsTable(String tableName);

    void addCar(String tableName, String brand, String model, int year);

    void deleteCar(String tableName, String brand, String model, int year);

    CarDto getCar(String tableName, int id);

    List<CarDto> findAllCars(String tableName);

    void deleteAllCars(String tableName);
}
