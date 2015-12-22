package com.malexj.dao;


import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class TestDAOFactory {
    @Test
    public void testConnect() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRES);
        CustomerDAO customerDAO = factory.getCustomerDAO();
        customerDAO.connect();
        assertTrue(customerDAO.isConnected());
        customerDAO.close();
        assertFalse(customerDAO.isConnected());

    }
}
