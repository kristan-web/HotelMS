package Controllers;
import DAO.DashboardDAO;
import Debugger.Debugger;

abstract class DashboardControllersTemplate {
    abstract int getAvailableServices();
    
    abstract int getTotalStaff();
    
    abstract double getTotalRevenue();
    
    abstract int getTotalReservations();

    abstract double getTodaysRevenue();

    abstract int getTodaysCheckins();

    abstract int getTodaysCheckouts();
}

public class DashboardControllers extends DashboardControllersTemplate{
    private static DashboardDAO dao = new DashboardDAO();
    
    @Override
    public int getAvailableServices(){
        return dao.getAvailableServicesQuery();
    }
    
    public int getTotalStaff(){
        return dao.getTotalStaffQuery();
    }
    
    public double getTotalRevenue(){
        return dao.getTotalRevenueQuery();
    }
    
    public int getTotalReservations(){
        return dao.getTotalReservationsQuery();
    }
    
    public int getTodaysReservations(){
        return dao.getTodaysReservationsQuery();
    }

    public double getTodaysRevenue(){
        return dao.getTodaysRevenueQuery();
    }

    public int getTodaysCheckins(){
        return dao.getTodaysCheckinsQuery();
    }

    public int getTodaysCheckouts(){
        return dao.getTodaysCheckoutsQuery();
    }
}
