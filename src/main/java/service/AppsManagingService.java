package service;

import java.sql.Connection;
import java.sql.SQLException;

public interface AppsManagingService {

    void manage(Connection conn) throws SQLException, InterruptedException;

}
