package Bai5.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/hospital_db";
        String user = "root";
        String password = "123456";

        return DriverManager.getConnection(url, user, password);
    }
}