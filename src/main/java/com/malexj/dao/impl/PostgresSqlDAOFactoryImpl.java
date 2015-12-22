package com.malexj.dao.impl;

import com.malexj.dao.CustomerDAO;
import com.malexj.dao.DAOFactory;

public class PostgresSqlDAOFactoryImpl implements DAOFactory {
    @Override
    public CustomerDAO getCustomerDAO() {
        return new PostgresSqlCustomerrDAO();
    }
}
