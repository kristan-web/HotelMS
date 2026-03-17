package Controllers;

import DAO.AuditLogDAO;
import Model.AuditLog;

import java.util.List;

/**
 * Controller for Admin Audit Logs.
 * Sits between the View and the DAO — contains no SQL.
 */
public class AuditLogController {

    private final AuditLogDAO auditLogDAO = new AuditLogDAO();

    /**
     * Returns all logs with no filtering applied.
     */
    public List<AuditLog> getAllLogs() {
        return auditLogDAO.getLogs("ALL", "ALL");
    }

    /**
     * Returns logs filtered by action type and/or table name.
     *
     * @param actionFilter "ALL", "INSERT", "UPDATE", or "DELETE"
     * @param tableFilter  "ALL" or a specific table name (e.g. "Guests")
     */
    public List<AuditLog> getFilteredLogs(String actionFilter, String tableFilter) {
        return auditLogDAO.getLogs(actionFilter, tableFilter);
    }

    /**
     * Returns distinct table names for the filter combo box.
     * Prepends "ALL" as the default option.
     */
    public List<String> getTableFilterOptions() {
        List<String> options = auditLogDAO.getDistinctTableNames();
        options.add(0, "ALL");
        return options;
    }
}
