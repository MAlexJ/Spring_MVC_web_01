package com.malexj.dao.impl;

import com.malexj.dao.CustomerDAO;
import com.malexj.datasource.PostgresJDBC;
import com.malexj.datasource.impl.PostgresJDBCImpl;

public class PostgresSqlCustomerrDAO implements CustomerDAO {
    private PostgresJDBC postgresJDBC = new PostgresJDBCImpl();

    @Override
    public void connect() {
        postgresJDBC.connect();
    }

    @Override
    public boolean close() {
        return postgresJDBC.close();
    }

    @Override
    public boolean isConnected() {
        return postgresJDBC.isConnected();
    }
}
