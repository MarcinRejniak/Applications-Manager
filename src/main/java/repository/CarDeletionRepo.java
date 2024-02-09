package repository;

import java.sql.Connection;

public interface CarDeletionRepo {
    void deleteApplication(Connection conn, String tableName , String deleteName);
}
