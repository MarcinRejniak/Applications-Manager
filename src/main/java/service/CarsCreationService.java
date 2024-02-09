package service;

import java.sql.Connection;

public interface CarsCreationService {
    void createCarsTable(Connection connection, String tableName);
}
