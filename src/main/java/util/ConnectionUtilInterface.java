package util;

import java.sql.*;

public interface ConnectionUtilInterface {
    Connection connect_to_db(String dbname, String user, String pass);

    void createApplicationsTable(Connection conn, String tableName);

    void insert_row(Connection conn, String tableName, String applicationName, String applicationContent);

    void display(Connection conn, String tableName) throws SQLException;

    void deleteApplication(Connection conn, String tableName ,String deleteName);
}
