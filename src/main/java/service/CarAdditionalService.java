package service;

import dto.CarDto;

import java.sql.Connection;

public interface CarAdditionalService {
    void addCar(Connection connection, String tableName, String brand, String model, int year);
}
