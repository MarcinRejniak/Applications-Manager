package service;

import dto.CarDto;
import repository.CarAdditionalRepo;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

public class CarAdditionalServiceImpl implements CarAdditionalService{
    private final CarAdditionalRepo carAdditionalRepo;

    public CarAdditionalServiceImpl(CarAdditionalRepo carAdditionalRepo) {
        this.carAdditionalRepo = carAdditionalRepo;
    }

    @Override
    public void addCar(Connection connection, String tableName, String brand, String model, int year) {
        CarDto carDto = new CarDto(brand,model,year);
        List<CarDto> carsDto = new LinkedList<>();
        carsDto.add(carDto);

        carAdditionalRepo.addCar(connection, tableName, carDto.getBrand(), carDto.getModel(), carDto.getYear());
    }
}
