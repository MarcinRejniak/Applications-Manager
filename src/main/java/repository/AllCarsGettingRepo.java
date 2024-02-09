package repository;

import model.CarEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AllCarsGettingRepo {
    List<CarEntity> findAllCars(Connection conn, String tableName) throws SQLException;
}
