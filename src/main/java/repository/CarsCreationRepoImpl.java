package repository;

import java.sql.Connection;
import java.sql.Statement;

public class CarsCreationRepoImpl implements CarsCreationRepo{
    @Override
    public void createCarsTable(Connection conn, String tableName) {
        Statement statement;
        try {
            String query = "create table if not exists " + tableName + "(" +
                    "brand VARCHAR(255), " +
                    "model VARCHAR(255), " +
                    "year int);";

            statement = conn.createStatement();
            statement.executeUpdate(query);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
