package com.logic;

import com.logic.service.AppsManagingService;
import com.logic.service.AppsManagingServiceImpl;
import util.ConnectionUtil;
import util.ConnectionUtilInterface;

import java.sql.SQLException;



public class Main {
    public static void main(String[] args) throws SQLException, InterruptedException {
        ConnectionUtilInterface connectionUtilInterface = new ConnectionUtil();

        AppsManagingService appsManagingService = new AppsManagingServiceImpl(connectionUtilInterface);
        appsManagingService.create();
    }
}