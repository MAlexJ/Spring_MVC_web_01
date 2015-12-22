package com.malexj.datasource;

public interface PostgresJDBC {
    void connect();

    boolean close();

    boolean isConnected();
}
