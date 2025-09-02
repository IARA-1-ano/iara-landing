package com.dao;

import com.database.ConnectionFactory;

import java.sql.Connection;

public abstract class DAO {
    protected static final ConnectionFactory connF = new ConnectionFactory();
    protected Connection conn;
}
