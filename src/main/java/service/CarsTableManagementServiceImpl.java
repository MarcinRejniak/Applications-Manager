package service;

import dto.CarDto;
import model.CarEntity;
import repository.CarsRepository;

import java.util.LinkedList;
import java.util.List;

public class CarsTableManagementServiceImpl implements CarsTableManagementService {
    CarsRepository carsRepository = CarsRepository.getInstance("postgres", "postgres", "12345678");

    @Override
    public void manage() {
        createCarsTable("cars");

        addCar("cars", "Toyota", "Yaris", 2020);
        addCar("cars", "Ford", "Mustang", 1969);
        addCar("cars", "Ford", "Mondeo", 2003);
        addCar("cars", "BMW", "X3", 2021);
        addCar("cars", "Volvo", "XC60", 2023);

        List<CarDto> cars = findAllCars("cars");
        System.out.println(cars);
        System.out.println();

        CarDto car = getCar("cars", 2);
        System.out.println(car);

        deleteCar("cars", 2);
    }

    @Override
    public void createCarsTable(String tableName) {
        carsRepository.createCarsTable(tableName);
    }

    @Override
    public void addCar(String tableName, String brand, String model, int year) {
        CarDto carDto = new CarDto(brand, model, year);

        carsRepository.addCar(tableName, carDto.getBrand(), carDto.getModel(), carDto.getYear());
    }

    @Override
    public void deleteCar(String tableName, int id) {
        carsRepository.deleteCar(tableName, id);
    }

    @Override
    public CarDto getCar(String tableName, int id) {
        CarEntity carEntity = carsRepository.findCar(tableName, id);
        return new CarDto(carEntity.getBrand(), carEntity.getModel(), carEntity.getYear());
    }

    @Override
    public List<CarDto> findAllCars(String tableName) {
        List<CarEntity> carEntityList = carsRepository.findAllCars(tableName);

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
    public void deleteAllCars(String tableName) {
        carsRepository.deleteAllCars(tableName);
    }
}
