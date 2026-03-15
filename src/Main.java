import DAO.UserDAO;
import Views.AccountManagement.AccountCreation.AdminRegistrationView;
import Views.AccountManagement.AccountCreation.AdminLoginView;

public class Main {
    private static UserDAO dao = new UserDAO();
    
    public static void main(String[] args) {
        if(dao.CheckIfDatabaseHasAdminQuery()){
            AdminLoginView dialog = new AdminLoginView();
            dialog.setVisible(true);
            return;
        }
        AdminRegistrationView dialog = new AdminRegistrationView();
        dialog.setVisible(true);
    }
}
