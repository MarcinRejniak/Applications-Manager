package repository;

import java.sql.Connection;
import java.sql.Statement;

public class AllCarsDeletionRepoImpl {
    public void deleteAllCars(Connection conn, String tableName) {
        Statement statement;
        try {
            String query = "DELETE FROM " + tableName + ";";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("All cars have benn deleted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
