package Controllers;
import DAO.DashboardDAO;
import Debugger.Debugger;

abstract class DashboardControllersTemplate {
    abstract int getAvailableServices();
    
  //  abstract int getOccupiedServices();
}

public class DashboardControllers extends DashboardControllersTemplate{
    private static DashboardDAO dao = new DashboardDAO();
    
    @Override
    public int getAvailableServices(){
        Debugger.Debugger("I AM INSIDE GET AVAILABLE SERVICES");
        Debugger.Debugger(dao.getAvailableServicesQuery());
        return dao.getAvailableServicesQuery();
    }
    /*
    public int getOccupiedServices(){
        return dao.getOccupiedServicesQuery();
    }*/
}
