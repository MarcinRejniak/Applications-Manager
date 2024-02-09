package repository;

import java.sql.Connection;

public interface CarsCreationRepo {
    void createCarsTable(Connection conn, String tableName);
}
