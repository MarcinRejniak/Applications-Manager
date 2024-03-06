package oldwithoutspring;

import oldwithoutspring.service.CarsTableManagementService;
import oldwithoutspring.service.CarsTableManagementServiceImpl;


public class Main {
    public static void main(String[] args) {
        CarsTableManagementService carsTableManagementService = new CarsTableManagementServiceImpl();
        carsTableManagementService.manage();
    }
}