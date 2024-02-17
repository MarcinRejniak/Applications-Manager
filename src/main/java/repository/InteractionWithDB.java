package repository;

import model.CarEntity;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class InteractionWithDB {
    Logger logger = Logger.getLogger(getClass().getName());
    public Connection connect(String dataName, String user, String pass){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dataName, user, pass);
            System.out.println("Connection Established");
            System.out.println();

        } catch (Exception e) {
            System.out.println("Connection Failed");
            System.out.println(e);
        }
        return conn;
    }
    public void createCarsTable(Connection conn, String tableName) throws SQLException {
        Statement statement = null;
        try {
            String query = "create table if not exists " + tableName + "(" +
                    "brand VARCHAR(255), " +
                    "model VARCHAR(255), " +
                    "year int);";

            statement = conn.createStatement();
            statement.executeUpdate(query);

        } catch (Exception e) {
            logger.info(e.toString());
        } finally {
            if(statement!= null){
                statement.close();
            }
        }
    }

    public void addCar(Connection conn, String tableName, String model, String brand, int year) throws SQLException {
        CarEntity carEntity = new CarEntity(model, brand, year);
        List<CarEntity> carsEntity = new LinkedList<>();
        carsEntity.add(carEntity);

        try(Statement statement = conn.createStatement()){
            String query = "INSERT INTO " + tableName + "(brand, model, year) " +
                    "VALUES ('" + carEntity.getBrand() + "','" +
                    carEntity.getModel() + "'," +
                    carEntity.getYear() + ");";

            statement.executeUpdate(query);
            System.out.println("car inserted");
        }
    }

    public void deleteCar(Connection connection, String tableName, String brand, String model, int year) throws SQLException {
        try (Statement statement = connection.createStatement()){
            String query = "DELETE FROM " + tableName + " Where brand = '" + brand + "' and model = '" + model +
                    "' and year = " + year + ";";
            statement.executeUpdate(query);
            System.out.println("Cars with brand '" + brand + "', model '" + model + "' and year " + year +
                    " have been deleted");
        }
    }

    public void deleteAllCars(Connection connection, String tableName) throws SQLException {
        try(Statement statement = connection.createStatement()){
            String query = "DELETE FROM " + tableName + ";";
            statement.executeUpdate(query);
            System.out.println("All cars have been deleted");
        }
    }

    public List<CarEntity> findCar(Connection connection, String tableName, String brand, String model) throws SQLException {
        List<CarEntity> carEntityList = new LinkedList<>();

        try(Statement statement = connection.createStatement()){
            String query = "SELECT * FROM " + tableName + " Where brand = '" + brand + "' and model = '" + model + "';";
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                int year = result.getInt("year");
                CarEntity carEntity = new CarEntity(brand, model, year);
                carEntityList.add(carEntity);
            }
            System.out.println("foundCarsWith:\n- Brand: " + brand + "\n- Model: " + model);
            System.out.println();
        }
        return carEntityList;
    }

    public List<CarEntity> findAllCars(Connection conn, String tableName) throws SQLException {
        List<CarEntity> carEntityList = new LinkedList<>();
        try(Statement statement = conn.createStatement()){
            String query = "SELECT * FROM " + tableName + ";";

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
        }
        return carEntityList;
    }
}
