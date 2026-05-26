package com.divyesh.panchasara.SQLAutoCompleter.service;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SchemaExtractorService {
    public List<String> extractSchema(Connection connection) {
        List<String> schema = new ArrayList<>();
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet set = metaData.getTables(null,
                    null,
                    "%",
                    new String[]{"TABLE"});

            while (set.next()) {
                String tableName = set.getString("TABLE_NAME");
                schema.add(tableName);
                ResultSet columns = metaData.getColumns(null, null, tableName, null);
                while (columns.next()) {
                    schema.add(columns.getString("COLUMN_NAME"));
                }
            }
            return schema;
        } catch (SQLException e) {
            throw new RuntimeException("Cannot fetch metadata: " + e.getMessage(), e);
        }
    }
}
