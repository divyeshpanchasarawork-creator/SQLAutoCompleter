package com.divyesh.panchasara.SQLAutoCompleter.service;

import com.divyesh.panchasara.SQLAutoCompleter.model.ConnectionRequest;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class ConnectionService {

    public Connection getConnection(ConnectionRequest connectionRequest) {
        try {
            if (connectionRequest.getDbType().equalsIgnoreCase("MYSQL")) {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } else if (connectionRequest.getDbType().equalsIgnoreCase("POSTGRESQL")) {
                Class.forName("org.postgresql.Driver");
            } else {
                throw new IllegalArgumentException("Unsupported DB type: " + connectionRequest.getDbType());
            }

            return DriverManager.getConnection(
                    connectionRequest.getUrl(),
                    connectionRequest.getUsername(),
                    connectionRequest.getPassword());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Failed to connect: " + e.getMessage(), e);
        }
    }
}
