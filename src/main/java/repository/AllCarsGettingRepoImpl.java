package repository;

import model.CarEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AllCarsGettingRepoImpl implements AllCarsGettingRepo{
    @Override
    public List<CarEntity> findAllCars(Connection conn, String tableName) throws SQLException {
        Statement statement;
        try {
            String query = "SELECT * FROM " + tableName + ";";
            statement = conn.createStatement();

            ResultSet result = statement.executeQuery(query);

//            while (result.next()) {
//                String name  = result.getString("application_name");
//                String content = result.getString("application_content");
//                String state = result.getString("application_state");
//
//                System.out.printf("Name: %10s Content: %10s State: %6s\n", name, content, state);
//            }
            System.out.println();

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
