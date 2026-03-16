package Controllers;
import DAO.DashboardDAO;
import Debugger.Debugger;

abstract class DashboardControllersTemplate {
    abstract int getAvailableServices();
    
    abstract int getInactiveServices();
    
    abstract double getTotalRevenue();
}

public class DashboardControllers extends DashboardControllersTemplate{
    private static DashboardDAO dao = new DashboardDAO();
    
    @Override
    public int getAvailableServices(){
        return dao.getAvailableServicesQuery();
    }
    
    public int getInactiveServices(){
        return dao.getInactiveServicesQuery();
    }
    
    public double getTotalRevenue(){
        return dao.getTotalRevenueQuery();
    }
    
    public int getTotalReservations(){
        return dao.getTotalReservationsQuery();
    }
}
