package com.malexj.datasource.impl;

import com.malexj.constants.Constant;
import com.malexj.datasource.PostgresJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PostgresJDBCImpl implements PostgresJDBC {

    private Connection connection;
    private ResultSet resultSet;

    @Override
    public void connect() {
        try {
            Class.forName(Constant.DRIVER_POSTGRES);
            connection = DriverManager.getConnection(Constant.CONNECTING_URL, Constant.USER_NAME, Constant.USER_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(">>>> Exception - Class.forName(Constant.DRIVER_POSTGRES) ", e);
        } catch (SQLException e) {
            throw new RuntimeException(">>>> Exception - DriverManager.getConnection(Constant.CONNECTING_URL,Constant.USER_NAME,Constant.USER_PASSWORD);", e);
        }
    }

    @Override
    public boolean close() {
        boolean flag = false;
        try {
            if (connection != null) {
                connection.close();
                flag = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(">>>> Exception - connection.close();", e);
        }
        return flag;
    }

    @Override
    public boolean isConnected() {
        boolean flag = false;
        try {
            flag = !connection.isClosed();
        } catch (SQLException e) {
            throw new RuntimeException(">>>> !connection.isClosed();", e);
        }
        return flag;
    }


}
