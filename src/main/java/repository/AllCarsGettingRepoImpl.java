package repository;

import model.CarEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class AllCarsGettingRepoImpl {
    public List<CarEntity> findAllCars(Connection conn, String tableName) throws SQLException {
        Statement statement;
        List<CarEntity> carEntityList = new LinkedList<>();
        try {
            String query = "SELECT * FROM " + tableName + ";";
            statement = conn.createStatement();

            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                String brand  = result.getString("brand");
                String model = result.getString("model");
                int year = result.getInt("year");
                CarEntity carEntity = new CarEntity(brand, model, year);
                carEntityList.add(carEntity);
            }
            System.out.println();
            System.out.println("foundAllCars");
            System.out.println();

        } catch (Exception e) {
            System.out.println(e);
        }
        return carEntityList;
    }
}
