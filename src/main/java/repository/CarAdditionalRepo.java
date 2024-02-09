package repository;

import java.sql.Connection;

public interface CarAdditionalRepo {
    void addCar(Connection conn, String tableName, String model, String brand, int year);
}
