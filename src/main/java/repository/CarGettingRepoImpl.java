package repository;

import model.CarEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class CarGettingRepoImpl {
    public List<CarEntity> findCar(Connection connection, String tableName, String brand, String model) {
        Statement statement;
        List<CarEntity> carEntityList = new LinkedList<>();
        try {
            String query = "SELECT * FROM " + tableName + " Where brand = '" + brand + "' and model = '" + model + "';";
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                int year = result.getInt("year");
                CarEntity carEntity = new CarEntity(brand, model, year);
                carEntityList.add(carEntity);
            }
            System.out.println("foundCarsWith:\n- Brand: " + brand + "\n- Model: " + model);
            System.out.println();

        } catch (Exception e) {
            System.out.println(e);
        }
        return carEntityList;
    }
}
