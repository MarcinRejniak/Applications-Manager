package service;

import dto.CarDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AllCarsGettingService {
    List<CarDto> findAllCars(Connection connection, String tableName) throws SQLException;
}
