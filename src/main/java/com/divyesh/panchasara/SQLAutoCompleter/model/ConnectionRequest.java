package com.divyesh.panchasara.SQLAutoCompleter.model;

public class ConnectionRequest {
    private String url;
    private String username;
    private String password;
    private String dbType;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ConnectionRequest() {}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
