package BLL;


import  java.sql.Connection;
import  java.sql.DriverManager;
import  java.sql.SQLException;
public class  ConnectionWithDatabase {
    public static Connection connection() {
        try {
            String dbUrl = "jdbc:sqlserver://\\SQLEXPRESS:1433;databaseName=Movies";
            String username = "TestUser";
            String password = "test123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(dbUrl, username, password);
            return con;
        }   catch (ClassNotFoundException | SQLException ce) {
            System.out.println("Lidhja nuk ka qene e suksesshme");
        }
        return null;
    }
}