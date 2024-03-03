import service.CarsTableManagementService;
import service.CarsTableManagementServiceImpl;


public class Main {
    public static void main(String[] args) {
        CarsTableManagementService carsTableManagementService = new CarsTableManagementServiceImpl();
        carsTableManagementService.manage();
    }
}