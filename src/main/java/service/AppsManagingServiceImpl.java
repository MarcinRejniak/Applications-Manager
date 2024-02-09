package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class AppsManagingServiceImpl implements AppsManagingService{
    @Override
    public void manage(Connection conn) throws SQLException, InterruptedException {
        Scanner input = new Scanner(System.in);
        while (true) {
            int socials = 0;
            if (input.hasNextInt() && input.nextInt()==1) {
                break;
            }
            String applicationName = input.nextLine();

            System.out.println("Please enter the content of application");
            String applicationContent = input.nextLine();

//            connectionUtilInterface.insert_row(conn, "applications", applicationName, applicationContent);
            System.out.println();
            System.out.println("would you like to continue entering data");
            System.out.println("Yes(1)/No(2)");
            while (!input.hasNextInt()) {
                input.nextLine();
            }
            if (input.nextInt() == 2) {
//                connectionUtilInterface.display(conn, "applications");
                System.out.println();
                System.out.println("would you like to delete an entry Yes(1)/No(2)");
                while (input.nextInt() == 1) {
                    System.out.println("select a name of application");
                    String deleteName = input.next();
//                    connectionUtilInterface.deleteApplication(conn, "applications", deleteName);
                    Thread.sleep(3000);
//                    connectionUtilInterface.display(conn, "applications");
                    System.out.println();
                    System.out.println("would you like to delete an entry Yes(1)/No(2)");
                } break;
            } else {
                System.out.println("please enter the name of the application or 1 to exit program");
            }
        }
    }
}
