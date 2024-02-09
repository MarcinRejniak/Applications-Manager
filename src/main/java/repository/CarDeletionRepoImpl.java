package repository;

import service.CarsCreationService;

import java.sql.Connection;
import java.sql.Statement;

public class CarDeletionRepoImpl implements CarDeletionRepo {
    @Override
    public void deleteApplication(Connection conn, String tableName, String deleteName) {
        Statement statement;
        try {
            String query = "DELETE FROM " + tableName + " WHERE application_name='"+deleteName+"';";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("account with name of application: " +deleteName+" deleted" + "\n");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
