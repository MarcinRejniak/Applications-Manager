package repository;

import java.sql.Connection;
import java.sql.Statement;

public class CarDeletionRepoImpl {
    public void deleteCar(Connection connection, String tableName, String brand, String model, int year) {
        Statement statement;
        try {
            String query = "DELETE FROM " + tableName + " Where brand = '" + brand + "' and model = '" + model +
                    "' and year = " + year + ";";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Cars with brand '" + brand + "', model '" + model + "' and year " + year +
                    " have been deleted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
