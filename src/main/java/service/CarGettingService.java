package service;

import dto.CarDto;

import java.sql.Connection;
import java.util.List;

public interface CarGettingService {
    List<CarDto> getCar(Connection connection, String tableName, String brand, String model);
}
