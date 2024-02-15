package repository;

import java.sql.Connection;
import java.sql.Statement;

public class AllCarsDeletionRepoImpl {
    public void deleteAllCars(Connection connection, String tableName) {
        Statement statement;
        try {
            String query = "DELETE FROM " + tableName + ";";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("All cars have been deleted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
