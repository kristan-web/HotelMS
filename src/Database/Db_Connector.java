package Database;
import java.sql.*;

public class Db_Connector {
    private static final String DB_HOST = "jdbc:mysql://localhost:3306/hoteldb";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
    
    public static Connection getCOnnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_HOST, DB_USERNAME, DB_PASSWORD);
        }
        catch(ClassNotFoundException | SQLException e){
            return null;
        }
    }
    public static void setCurrentUser(Connection conn, int userId) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("SET @current_user_id = ?")) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        }
    }
}
