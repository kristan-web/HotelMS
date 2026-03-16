package Controllers;
import DAO.DashboardDAO;

abstract class DashboardControllersTemplate {
    abstract int getAvailableServices();
    
  //  abstract int getOccupiedServices();
}

public class DashboardControllers extends DashboardControllersTemplate{
    private static DashboardDAO dao = new DashboardDAO();
    
    @Override
    public int getAvailableServices(){
        return dao.getAvailableServicesQuery();
    }
    /*
    public int getOccupiedServices(){
        return dao.getOccupiedServicesQuery();
    }*/
}
