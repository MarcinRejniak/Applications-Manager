package util;

import com.logic.model.State;

import java.sql.*;

public class ConnectionUtil implements ConnectionUtilInterface {
    public Connection connect_to_db(String dbname, String user, String pass) {

        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);
            if (conn != null) {
                System.out.println("Connection Established");
            } else {
                System.out.println("Connection Failed");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

    public void createApplicationsTable(Connection conn, String tableName) {

        Statement statement;
        try {
            String query = "create table " + tableName + "(" +
                    "application_name VARCHAR(255), " +
                    "application_content VARCHAR(255), " +
                    "application_state VARCHAR(255));";

            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("please enter the name of the application");

        } catch (Exception e) {
            System.out.println("the applications table already exists");
            System.out.println("please enter the name of the application or 1 to exit program");
        }
    }

    public void insert_row(Connection conn, String tableName, String applicationName, String applicationContent) {
        Statement statement;
        try {
            State application_state = State.CREATED;
            String query = "INSERT INTO " + tableName + "(application_name, application_content, application_state) " +
                    "VALUES ('" + applicationName + "','" + applicationContent + "','" + application_state + "');";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("row inserted");

        } catch (Exception e) {
            System.out.println("incorrect data");
            System.out.println(e);
        }
    }

    public void display(Connection conn, String tableName) throws SQLException {
        Statement statement;
        try {
            String query = "SELECT * FROM " + tableName + ";";
            statement = conn.createStatement();



            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                String name  = result.getString("application_name");
                String content = result.getString("application_content");
                String state = result.getString("application_state");

                System.out.printf("Name: %10s Content: %10s State: %6s\n", name, content, state);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void deleteApplication(Connection conn, String tableName ,String deleteName) {
        Statement statement;
        try {
            String query = "DELETE FROM " + tableName + " WHERE application_name='"+deleteName+"';";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("account with name of application: " +deleteName+" deleted" + "\n");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
