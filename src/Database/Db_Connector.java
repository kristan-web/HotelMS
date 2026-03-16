package Database;
import java.sql.*;
import Session.Session;

public class Db_Connector {
    private static final String DB_HOST = "jdbc:mysql://localhost:3306/hoteldb";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "a.vill1110";
    public static Connection getCOnnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_HOST, DB_USERNAME, DB_PASSWORD);

            if (Session.getCurrentUser() != null) {
                int userId = Integer.parseInt(Session.getCurrentUser().getUser_id());
                try (PreparedStatement ps = conn.prepareStatement("SET @current_user_id = ?")) {
                    ps.setInt(1, userId);
                    ps.executeUpdate();
                }
            }
            
            return conn;
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
