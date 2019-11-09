package uk.co.devinity.unikorn;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class UnikornConfiguration {

    private final String jdbcUrl;
    private final String driverClass;
    private final String username;
    private final String password;

    public UnikornConfiguration(final String jdbcUrl,
                                final String driverClass,
                                final String username,
                                final String password) {
        this.jdbcUrl = jdbcUrl;
        this.driverClass = driverClass;
        this.username = username;
        this.password = password;

    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Connection getConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName(driverClass)
                                                       .getDeclaredConstructor()
                                                       .newInstance());

            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (final Exception ex) {
            throw new RuntimeException("Could not load datasource");
        }
    }
}
