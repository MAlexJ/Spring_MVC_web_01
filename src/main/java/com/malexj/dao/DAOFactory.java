package com.malexj.dao;

import com.malexj.dao.impl.PostgresSqlDAOFactoryImpl;

public interface DAOFactory {

    int POSTGRES = 1;
    int MYSQL = 2;

    CustomerDAO getCustomerDAO();

    static DAOFactory getDAOFactory(int whichFactory) {

        switch (whichFactory) {

            case POSTGRES:
                return new PostgresSqlDAOFactoryImpl();

            case MYSQL:
                return null;


            default:
                return null;
        }
    }
}
