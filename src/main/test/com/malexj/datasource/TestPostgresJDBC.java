package com.malexj.datasource;

import com.malexj.datasource.impl.PostgresJDBCImpl;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;


public class TestPostgresJDBC {

    @Test
    public void testConnect() {
        PostgresJDBC postgresJDBC = new PostgresJDBCImpl();
        postgresJDBC.connect();
        assertTrue(postgresJDBC.isConnected());
        postgresJDBC.close();
        assertFalse(postgresJDBC.isConnected());
    }
}
