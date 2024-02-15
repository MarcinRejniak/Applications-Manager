package repository;

import model.CarEntity;

import java.sql.Connection;
import java.util.List;

public interface CarGettingRepo {
    List<CarEntity> findCar(Connection connection, String tableName, String brand, String model);
}
