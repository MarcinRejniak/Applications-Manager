package service;

import dto.CarDto;
import model.CarEntity;
import repository.CarGettingRepo;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

public class CarGettingServiceImpl implements CarGettingService{
    private final CarGettingRepo carGettingRepo;

    public CarGettingServiceImpl(CarGettingRepo carGettingRepo) {
        this.carGettingRepo = carGettingRepo;
    }

    @Override
    public List<CarDto> getCar(Connection connection, String tableName, String brand, String model) {
        List<CarEntity> carEntity = carGettingRepo.findCar(connection, tableName, brand, model);
        List<CarDto> carDtoList = new LinkedList<>();
        for (CarEntity car : carEntity) {
            int year = car.getYear();
            carDtoList.add(new CarDto(brand, model, year));
        }
        return carDtoList;
    }
}
