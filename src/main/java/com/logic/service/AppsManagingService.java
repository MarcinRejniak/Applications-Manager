package com.logic.service;

import java.sql.Connection;
import java.sql.SQLException;

public interface AppsManagingService {
    void create() throws SQLException, InterruptedException;

    void manage(Connection conn) throws SQLException, InterruptedException;

}
