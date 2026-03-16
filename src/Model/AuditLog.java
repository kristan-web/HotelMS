package Model;

import java.sql.Timestamp;

/**
 * Model representing a single row in the audit_logs table.
 */
public class AuditLog {

    private int    logId;
    private String tableName;
    private int    recordId;
    private String action;       // INSERT | UPDATE | DELETE
    private int    changedBy;
    private String changedByName; // joined from users table
    private Timestamp changedAt;
    private String oldValues;    // raw JSON string
    private String newValues;    // raw JSON string

    public AuditLog() {}

    public AuditLog(int logId, String tableName, int recordId, String action,
                    int changedBy, String changedByName, Timestamp changedAt,
                    String oldValues, String newValues) {
        this.logId        = logId;
        this.tableName    = tableName;
        this.recordId     = recordId;
        this.action       = action;
        this.changedBy    = changedBy;
        this.changedByName = changedByName;
        this.changedAt    = changedAt;
        this.oldValues    = oldValues;
        this.newValues    = newValues;
    }

    // ── Getters ──────────────────────────────────────────────────────────────

    public int       getLogId()        { return logId; }
    public String    getTableName()    { return tableName; }
    public int       getRecordId()     { return recordId; }
    public String    getAction()       { return action; }
    public int       getChangedBy()    { return changedBy; }
    public String    getChangedByName(){ return changedByName; }
    public Timestamp getChangedAt()    { return changedAt; }
    public String    getOldValues()    { return oldValues; }
    public String    getNewValues()    { return newValues; }

    // ── Setters ──────────────────────────────────────────────────────────────

    public void setLogId(int logId)               { this.logId = logId; }
    public void setTableName(String tableName)     { this.tableName = tableName; }
    public void setRecordId(int recordId)          { this.recordId = recordId; }
    public void setAction(String action)           { this.action = action; }
    public void setChangedBy(int changedBy)        { this.changedBy = changedBy; }
    public void setChangedByName(String name)      { this.changedByName = name; }
    public void setChangedAt(Timestamp changedAt)  { this.changedAt = changedAt; }
    public void setOldValues(String oldValues)     { this.oldValues = oldValues; }
    public void setNewValues(String newValues)     { this.newValues = newValues; }
}
