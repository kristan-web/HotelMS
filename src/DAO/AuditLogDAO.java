package DAO;

import Model.AuditLog;
import Database.Db_Connector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DAO for the audit_logs table.
 * All SQL queries related to audit logs live here.
 */
public class AuditLogDAO {

    private static final Logger logger =
            Logger.getLogger(AuditLogDAO.class.getName());

    /**
     * Fetches audit logs with optional filtering by action and table name.
     *
     * @param actionFilter "ALL", "INSERT", "UPDATE", or "DELETE"
     * @param tableFilter  "ALL" or a specific table name (e.g. "Guests")
     */
    public List<AuditLog> getLogs(String actionFilter, String tableFilter) {
        List<AuditLog> logs = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
            "SELECT al.log_id, al.table_name, al.record_id, al.action, " +
            "       al.changed_by, " +
            "       COALESCE(CONCAT(u.first_name, ' ', u.last_name), 'Unknown') AS changed_by_name, " +
            "       al.changed_at, al.old_values, al.new_values " +
            "FROM audit_logs al " +
            "LEFT JOIN users u ON al.changed_by = u.user_id " +
            "WHERE 1=1 "
        );

        boolean filterAction = actionFilter != null && !actionFilter.equalsIgnoreCase("ALL");
        boolean filterTable  = tableFilter  != null && !tableFilter.equalsIgnoreCase("ALL");

        if (filterAction) sql.append("AND al.action = ? ");
        if (filterTable)  sql.append("AND al.table_name = ? ");

        sql.append("ORDER BY al.changed_at DESC");

        try (Connection conn = Db_Connector.getCOnnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int i = 1;
            if (filterAction) ps.setString(i++, actionFilter.toUpperCase());
            if (filterTable)  ps.setString(i,   tableFilter);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    logs.add(mapRow(rs));
                }
            }

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Failed to fetch audit logs", ex);
        }

        return logs;
    }

    /**
     * Returns the distinct table names present in audit_logs.
     * Used to populate the Table filter combo box in the View.
     */
    public List<String> getDistinctTableNames() {
        List<String> tables = new ArrayList<>();

        String sql = "SELECT DISTINCT table_name FROM audit_logs ORDER BY table_name";

        try (Connection conn = Db_Connector.getCOnnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                tables.add(rs.getString("table_name"));
            }

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Failed to fetch distinct table names", ex);
        }

        return tables;
    }

    // ── Private helpers ──────────────────────────────────────────────────────

    private AuditLog mapRow(ResultSet rs) throws SQLException {
        return new AuditLog(
            rs.getInt("log_id"),
            rs.getString("table_name"),
            rs.getInt("record_id"),
            rs.getString("action"),
            rs.getInt("changed_by"),
            rs.getString("changed_by_name"),
            rs.getTimestamp("changed_at"),
            rs.getString("old_values"),
            rs.getString("new_values")
        );
    }
}
