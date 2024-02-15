package service;

import dto.CarDto;
import model.CarEntity;
import repository.AllCarsGettingRepo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AllCarsGettingServiceImpl implements AllCarsGettingService{
    private final AllCarsGettingRepo allCarsGettingRepo;

    public AllCarsGettingServiceImpl(AllCarsGettingRepo allCarsGettingRepo) {
        this.allCarsGettingRepo = allCarsGettingRepo;
    }

    @Override
    public List<CarDto> findAllCars(Connection connection, String tableName) throws SQLException {
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
}
