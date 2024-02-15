package service;

import dto.CarDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CarsTableManagementService {
    void manage(Connection connection) throws SQLException;
    void createCarsTable(Connection connection, String tableName);

    void addCar(Connection connection, String tableName, String brand, String model, int year);

    void deleteCar(Connection connection, String tableName, String brand, String model, int year);

    List<CarDto> getCar(Connection connection, String tableName, String brand, String model, int year);

    List<CarDto> findAllCars(Connection connection, String tableName) throws SQLException;

    void deleteAllCars(Connection connection, String tableName);
}
