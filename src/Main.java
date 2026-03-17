import DAO.UserDAO;
import Views.AccountManagement.AccountCreation.AdminRegistrationView;
import Views.AccountManagement.AccountCreation.AdminLoginView;
import Debugger.Debugger;

public class Main {
    private static UserDAO dao = new UserDAO();
    
    public static void main(String[] args) {
        Debugger.Debugger("PROGRAM START. WILL CHECK IF THERES AN ADMIN ACCOUNT");
        if(dao.CheckIfDatabaseHasAdminQuery()){
            Debugger.Debugger("DATABASE HAS AN ADMIN ACCOUNT. GOING TO ADMIN LOGIN NOW");
            AdminLoginView dialog = new AdminLoginView("setup");
            dialog.setVisible(true);
            return;
        }
        Debugger.Debugger("NO ADMIN ACCOUNT ON DATABASE. GOING TO ADMIN REGISTRATION");
        AdminRegistrationView dialog = new AdminRegistrationView("setup");
        dialog.setVisible(true);
    }
}
