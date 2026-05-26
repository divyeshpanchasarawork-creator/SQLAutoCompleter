package com.divyesh.panchasara.SQLAutoCompleter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

@Configuration
public class SqlKeywordConfig {

    @Bean
    public List<String> sqlKeywords() {
        return Collections.unmodifiableList(List.of(
                "SELECT", "INSERT", "UPDATE", "DELETE", "MERGE",
                "CREATE", "DROP", "ALTER", "TRUNCATE",
                "WHERE", "FROM", "JOIN", "GROUP", "ORDER", "HAVING", "LIMIT", "OFFSET",
                "INNER", "LEFT", "RIGHT", "FULL", "CROSS", "ON", "USING",
                "INT", "BIGINT", "VARCHAR", "CHAR", "BOOLEAN", "DATE", "TIMESTAMP", "TEXT", "DECIMAL",
                "COUNT", "SUM", "AVG", "MAX", "MIN", "COALESCE", "IFNULL", "CONCAT", "SUBSTRING",
                "AND", "OR", "IN", "LIKE", "BETWEEN", "IS", "NULL", "NOT", "EXISTS", "CASE", "WHEN", "THEN", "ELSE", "END",
                "AS", "DISTINCT", "UNION", "ALL", "BY", "WITH", "OVER"
        ));
    }
}
