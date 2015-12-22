package com.malexj.dao;

public interface CustomerDAO {
    void connect();

    boolean close();

    boolean isConnected();
}
