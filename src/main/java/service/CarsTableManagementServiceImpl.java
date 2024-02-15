package service;

import dto.CarDto;
import model.CarEntity;
import repository.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CarsTableManagementServiceImpl implements CarsTableManagementService {
    @Override
    public void manage(Connection connection) throws SQLException {
        createCarsTable(connection, "cars");

        addCar(connection, "cars", "Toyota", "Yaris", 2020);
        addCar(connection, "cars", "Ford", "Mustang", 1969);
        addCar(connection, "cars", "Ford", "Mondeo", 2003);
        addCar(connection, "cars", "BMW", "X3", 2021);
        addCar(connection, "cars", "Volvo", "XC60", 2023);

        List<CarDto> cars = findAllCars(connection, "cars");
        System.out.println(cars);
        System.out.println();

        List<CarDto> car = getCar(connection, "cars", "Ford", "Mustang", 1969);
        System.out.println(car);

//        deleteCar(connection, "cars", "Toyota", "Yaris", 2020);
//        deleteAllCars(connection, "cars");
    }

    @Override
    public void createCarsTable(Connection connection, String tableName) {
        CarsCreationRepoImpl carsCreationRepo = new CarsCreationRepoImpl();
        carsCreationRepo.createCarsTable(connection, tableName);
    }

    @Override
    public void addCar(Connection connection, String tableName, String brand, String model, int year) {
        CarDto carDto = new CarDto(brand, model, year);
//        List<CarDto> carsDto = new LinkedList<>();
//        carsDto.add(carDto);

        CarAdditionalRepoImpl carAdditionalRepo = new CarAdditionalRepoImpl();
        carAdditionalRepo.addCar(connection, tableName, carDto.getBrand(), carDto.getModel(), carDto.getYear());
    }

    @Override
    public void deleteCar(Connection connection, String tableName, String brand, String model, int year) {
        CarDeletionRepoImpl carDeletionRepo = new CarDeletionRepoImpl();
        carDeletionRepo.deleteCar(connection, tableName, brand, model, year);
    }

    @Override
    public List<CarDto> getCar(Connection connection, String tableName, String brand, String model, int year) {
        CarGettingRepoImpl carGettingRepo = new CarGettingRepoImpl();
        List<CarEntity> carEntity = carGettingRepo.findCar(connection, tableName, brand, model);
        List<CarDto> carDtoList = new LinkedList<>();
        for (int i = 0; i < carEntity.size(); i++) {
            carDtoList.add(new CarDto(brand, model, year));
        }
        return carDtoList;
    }

    @Override
    public List<CarDto> findAllCars(Connection connection, String tableName) throws SQLException {
        AllCarsGettingRepoImpl allCarsGettingRepo = new AllCarsGettingRepoImpl();
        List<CarEntity> carEntityList = allCarsGettingRepo.findAllCars(connection, tableName);

        List<CarDto> carDtoList = new LinkedList<>();

        for (CarEntity car : carEntityList) {
            String brand = car.getBrand();
            String model = car.getModel();
            int year = car.getYear();
            carDtoList.add(new CarDto(brand, model, year));
        }

        return carDtoList;
    }

    @Override
    public void deleteAllCars(Connection connection, String tableName) {
        AllCarsDeletionRepoImpl allCarsDeletionRepo = new AllCarsDeletionRepoImpl();
        allCarsDeletionRepo.deleteAllCars(connection, tableName);
    }
}
