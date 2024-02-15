package service;

import repository.AllCarsDeletionRepoImpl;

import java.sql.Connection;

public class AllCarsDeletionServiceImpl {
    public void deleteAllCars(Connection connection, String tableName){
        AllCarsDeletionRepoImpl allCarsDeletionRepo = new AllCarsDeletionRepoImpl();
        allCarsDeletionRepo.deleteAllCars(connection,tableName);
    }
}
