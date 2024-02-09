package service;

import repository.CarsCreationRepo;
import repository.CarsCreationRepoImpl;

import java.sql.Connection;

public class CarsCreationServiceImpl implements CarsCreationService{
    private final CarsCreationRepo carsCreationRepo;

    public CarsCreationServiceImpl(CarsCreationRepo carsCreationRepo) {
        this.carsCreationRepo = carsCreationRepo;
    }

    @Override
    public void createCarsTable(Connection connection, String tableName) {
        carsCreationRepo.createCarsTable(connection, tableName);
    }
}
