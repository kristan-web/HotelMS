package Database;
import java.sql.*;

public class Db_Connector {
    private static final String DB_HOST = "jdbc:mysql://localhost:3306/hoteldb";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "a.vill1110";
    
    public static Connection getCOnnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_HOST, DB_USERNAME, DB_PASSWORD);
        }
        catch(ClassNotFoundException | SQLException e){
            return null;
        }
    }
}
