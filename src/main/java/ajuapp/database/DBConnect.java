package ajuapp.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBConnect {

    private static List<String> getDBProperties() {
        List<String> dbProperties = new ArrayList<>();

        try {
            FileInputStream configFile = new FileInputStream("src/main/resources/config.properties");
            Properties properties = new Properties();
            properties.load(configFile);

            dbProperties.add(properties.getProperty("db.host"));
            dbProperties.add(properties.getProperty("db.username"));
            dbProperties.add(properties.getProperty("db.password"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dbProperties;
    }

    public static Connection getConnection() {
        List<String> dbProperties = getDBProperties();
        Connection connection = null;

        try {
            assert dbProperties.size() == 3;
            assert dbProperties.get(0) != null;

            connection = DriverManager.getConnection(dbProperties.get(0), dbProperties.get(1), dbProperties.get(2));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  connection;
    }
}
