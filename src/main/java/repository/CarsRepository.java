package repository;

import model.CarEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public final class CarsRepository {
    private static CarsRepository instance;
    private final String dataName;
    private final String user;
    private final String password;

    private final Connection connection;

    private CarsRepository(String dataName, String user, String password) {
        this.dataName = dataName;
        this.user = user;
        this.password = password;
        this.connection = connect();
    }

    public static CarsRepository getInstance(String dataName, String user, String password) {
        if (instance == null) {
            instance = new CarsRepository(dataName, user, password);
        }
        return instance;
    }

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dataName, user, password);
            System.out.println("Connection Established");
            System.out.println();

        } catch (Exception e) {
            System.out.println("Connection Failed");
            System.out.println(e);
        }

        return conn;
    }

    public void createCarsTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String query = "create table if not exists " + tableName + " ( " +
                    "carid SERIAL PRIMARY KEY, " +
                    "brand VARCHAR(255), " +
                    "model VARCHAR(255), " +
                    "year int);";

            statement.executeUpdate(query);
            System.out.println("createdCarsTable");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addCar(String tableName, String brand, String model, int year) {
        CarEntity carEntity = new CarEntity(brand, model, year);
        List<CarEntity> carsEntity = new LinkedList<>();
        carsEntity.add(carEntity);

        try (Statement statement = connection.createStatement()) {
            String query = "INSERT INTO " + tableName + " (brand, model, year) " +
                    "VALUES ('" +
//                    id + " ,'" +
                    carEntity.getBrand() + "','" +
                    carEntity.getModel() + "'," +
                    carEntity.getYear() + ");";

            statement.executeUpdate(query);
            System.out.println("car inserted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteCar(String tableName, String brand, String model, int year) {
        try (Statement statement = connection.createStatement()) {
            String query = "DELETE FROM " + tableName + " Where brand = '" + brand + "' and model = '" + model +
                    "' and year = " + year + ";";
            statement.executeUpdate(query);
            System.out.println("Cars with brand '" + brand + "', model '" + model + "' and year " + year +
                    " have been deleted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteAllCars(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String query = "DELETE FROM " + tableName + ";";
            statement.executeUpdate(query);
            System.out.println("All cars have been deleted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public CarEntity findCar(String tableName, int id) {
        CarEntity carEntity = null;
        try (Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM " + tableName + " Where carid = '" + id + "';";
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                String brand = result.getString("brand");
                String model = result.getString("model");
                int year = result.getInt("year");
                carEntity = new CarEntity(brand, model, year);
                System.out.println("foundCarWith:\n- ID: " + id);
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return carEntity;
    }

    public List<CarEntity> findAllCars(String tableName) {
        List<CarEntity> carEntityList = new LinkedList<>();
        try (Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM " + tableName + ";";

            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                String brand = result.getString("brand");
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
