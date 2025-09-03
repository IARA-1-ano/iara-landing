package com.dao;

import java.sql.Connection;

import com.database.ConnectionFactory;

// Classe abstrata para facilitar o gerenciamento de conexões dos DAOs
public abstract class DAO {
    protected static final ConnectionFactory connF = new ConnectionFactory();
    protected Connection conn;
}
