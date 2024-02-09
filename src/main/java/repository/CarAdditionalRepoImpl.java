package repository;

import model.CarEntity;

import java.sql.Connection;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class CarAdditionalRepoImpl implements CarAdditionalRepo{
    @Override
    public void addCar(Connection conn, String tableName, String model, String brand, int year) {
        CarEntity carEntity = new CarEntity(model, brand, year);
        List<CarEntity> carsEntity = new LinkedList<>();
        carsEntity.add(carEntity);

        Statement statement;
        try {
            String query = "INSERT INTO " + tableName + "(brand, model, year) " +
                    "VALUES ('" + carEntity.getModel() + "','" +
                    carEntity.getBrand() + "'," +
                    carEntity.getYear() + ");";

            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("car inserted");

        } catch (Exception e) {
            System.out.println("incorrect data");
            System.out.println(e);
        }
    }
}
