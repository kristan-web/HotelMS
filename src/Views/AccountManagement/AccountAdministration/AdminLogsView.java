package Views.AccountManagement.AccountAdministration;

import Views.Dashboard.*;

import Session.Session;

import Controllers.AuditLogController;
import Model.AuditLog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;

/**
 * Admin Audit Logs View.
 * Displays all audit log entries with optional filtering by Action and Table.
 */
public class AdminLogsView extends JFrame {

    private static final Logger logger = Logger.getLogger(AdminLogsView.class.getName());

    // ── Palette (matches your existing purple/pink theme) ────────────────────
    private static final Color DARK_PURPLE  = new Color(47, 32, 56);
    private static final Color LIGHT_PINK   = new Color(255, 224, 227);
    private static final Color WHITE        = Color.WHITE;
    private static final Color ROW_ALT      = new Color(250, 240, 255);   // subtle lavender stripe
    private static final Color HEADER_FG    = Color.WHITE;
    private static final Color INSERT_COLOR = new Color(34, 139, 34);     // green
    private static final Color UPDATE_COLOR = new Color(30, 100, 200);    // blue
    private static final Color DELETE_COLOR = new Color(200, 40, 40);     // red

    // ── Controller ───────────────────────────────────────────────────────────
    private final AuditLogController controller = new AuditLogController();

    // ── Column names for the JTable ──────────────────────────────────────────
    private static final String[] COLUMNS = {
        "Log ID", "Table", "Record ID", "Action", "Changed By", "Timestamp", "Old Values", "New Values"
    };

    // ── Swing components ─────────────────────────────────────────────────────
    private JTable          logsTable;
    private DefaultTableModel tableModel;
    private JComboBox<String> actionFilterBox;
    private JComboBox<String> tableFilterBox;
    private JLabel          statusLabel;

    // ── Constructor ──────────────────────────────────────────────────────────
    public AdminLogsView() {
        super("Admin – Audit Logs");
        buildUI();
        loadTableFilterOptions();
        refreshTable("ALL", "ALL");
    }

    // ── UI Construction ──────────────────────────────────────────────────────

    private void buildUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(1100, 640));
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(DARK_PURPLE);

        root.add(buildHeaderPanel(),  BorderLayout.NORTH);
        root.add(buildCenterPanel(), BorderLayout.CENTER);

        setContentPane(root);
        pack();
    }

    /** Top banner with title, status label, and Back button */
    private JPanel buildHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(LIGHT_PINK);
        header.setBorder(new EmptyBorder(14, 22, 14, 22));

        JLabel title = new JLabel("Audit Logs");
        title.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 20));
        title.setForeground(DARK_PURPLE);

        // ── Right side: status label + back button ───────────────────────────
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        rightPanel.setOpaque(false);

        statusLabel = new JLabel("Loading…");
        statusLabel.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 13));
        statusLabel.setForeground(DARK_PURPLE);

        JButton backBtn = new JButton("← Back");
        backBtn.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 13));
        backBtn.setBackground(DARK_PURPLE);
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setBorderPainted(false);
        backBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backBtn.setPreferredSize(new Dimension(100, 32));
        backBtn.addActionListener(e -> goBackToDashboard());

        rightPanel.add(statusLabel);
        rightPanel.add(backBtn);

        header.add(title,      BorderLayout.WEST);
        header.add(rightPanel, BorderLayout.EAST);
        return header;
    }

    /** Closes this window and opens the AdminDashBoardView. */
    private void goBackToDashboard() {
        dispose();
        java.awt.EventQueue.invokeLater(() -> {
            try {
                if(Session.getUserRole().equals("Admin")){
                    AdminDashBoardView AdminDBView = new AdminDashBoardView();
                    AdminDBView.setVisible(true);
                    AdminDBView.setLocationRelativeTo(null);
                }else{
                    StaffDashBoardView StaffDBView = new StaffDashBoardView(); 
                    StaffDBView.setVisible(true);
                    StaffDBView.setLocationRelativeTo(null);
                }
                
            } catch (Exception ex) {
                logger.severe("Could not open AdminDashBoardView: " + ex.getMessage());
            }
        });
    }

    /** Filter bar + scrollable table */
    private JPanel buildCenterPanel() {
        JPanel center = new JPanel(new BorderLayout(0, 8));
        center.setBackground(DARK_PURPLE);
        center.setBorder(new EmptyBorder(14, 16, 16, 16));

        center.add(buildFilterPanel(), BorderLayout.NORTH);
        center.add(buildTablePanel(),  BorderLayout.CENTER);
        return center;
    }

    /** Action + Table filter combo boxes */
    private JPanel buildFilterPanel() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 4));
        bar.setBackground(DARK_PURPLE);

        // ── Action filter ────────────────────────────────────────────────────
        bar.add(styledLabel("Filter by Action:"));

        actionFilterBox = new JComboBox<>(new String[]{"ALL", "INSERT", "UPDATE", "DELETE"});
        styleComboBox(actionFilterBox);
        actionFilterBox.addActionListener((ActionEvent e) -> applyFilters());
        bar.add(actionFilterBox);

        bar.add(Box.createHorizontalStrut(18));

        // ── Table filter ─────────────────────────────────────────────────────
        bar.add(styledLabel("Filter by Table:"));

        tableFilterBox = new JComboBox<>();
        styleComboBox(tableFilterBox);
        tableFilterBox.addActionListener((ActionEvent e) -> applyFilters());
        bar.add(tableFilterBox);

        // ── Refresh button ────────────────────────────────────────────────────
        bar.add(Box.createHorizontalStrut(18));
        JButton refreshBtn = new JButton("↺  Refresh");
        refreshBtn.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 13));
        refreshBtn.setBackground(LIGHT_PINK);
        refreshBtn.setForeground(DARK_PURPLE);
        refreshBtn.setFocusPainted(false);
        refreshBtn.setBorderPainted(false);
        refreshBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        refreshBtn.addActionListener(e -> applyFilters());
        bar.add(refreshBtn);

        return bar;
    }

    /** Scrollable JTable wrapped in a white rounded panel */
    private JScrollPane buildTablePanel() {
        // ── Model: all cells non-editable ────────────────────────────────────
        tableModel = new DefaultTableModel(COLUMNS, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        logsTable = new JTable(tableModel);
        logsTable.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 13));
        logsTable.setRowHeight(28);
        logsTable.setShowGrid(false);
        logsTable.setIntercellSpacing(new Dimension(0, 0));
        logsTable.setSelectionBackground(new Color(200, 170, 220));
        logsTable.setSelectionForeground(DARK_PURPLE);
        logsTable.setFillsViewportHeight(true);

        // ── Header styling ───────────────────────────────────────────────────
        JTableHeader tableHeader = logsTable.getTableHeader();
        tableHeader.setBackground(DARK_PURPLE);
        tableHeader.setForeground(HEADER_FG);
        tableHeader.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 13));
        tableHeader.setReorderingAllowed(false);
        tableHeader.setResizingAllowed(true);

        // ── Column widths ────────────────────────────────────────────────────
        int[] widths = {60, 110, 80, 80, 140, 155, 185, 185};
        for (int i = 0; i < widths.length; i++) {
            logsTable.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
        }

        // ── Alternating row + action-colour renderer ──────────────────────────
        logsTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int col) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                setBorder(new EmptyBorder(0, 8, 0, 8));

                if (!isSelected) {
                    setBackground(row % 2 == 0 ? WHITE : ROW_ALT);
                    setForeground(Color.BLACK);

                    // Colour the Action column
                    if (col == 3) {
                        String action = value == null ? "" : value.toString();
                        switch (action) {
                            case "INSERT" -> { setForeground(INSERT_COLOR); setFont(getFont().deriveFont(Font.BOLD)); }
                            case "UPDATE" -> { setForeground(UPDATE_COLOR); setFont(getFont().deriveFont(Font.BOLD)); }
                            case "DELETE" -> { setForeground(DELETE_COLOR); setFont(getFont().deriveFont(Font.BOLD)); }
                            default       -> setFont(getFont().deriveFont(Font.PLAIN));
                        }
                    } else {
                        setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 13));
                    }
                }
                return this;
            }
        });

        JScrollPane scroll = new JScrollPane(logsTable);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(180, 160, 195)));
        scroll.getViewport().setBackground(WHITE);
        return scroll;
    }

    // ── Data loading ──────────────────────────────────────────────────────────

    private void loadTableFilterOptions() {
        tableFilterBox.removeActionListener(tableFilterBox.getActionListeners()[0]);
        tableFilterBox.removeAllItems();
        for (String opt : controller.getTableFilterOptions()) {
            tableFilterBox.addItem(opt);
        }
        tableFilterBox.addActionListener(e -> applyFilters());
    }

    private void applyFilters() {
        String action = (String) actionFilterBox.getSelectedItem();
        String table  = (String) tableFilterBox.getSelectedItem();
        if (action == null) action = "ALL";
        if (table  == null) table  = "ALL";
        refreshTable(action, table);
    }

    private void refreshTable(String actionFilter, String tableFilter) {
        tableModel.setRowCount(0);                      // clear existing rows

        List<AuditLog> logs = controller.getFilteredLogs(actionFilter, tableFilter);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (AuditLog log : logs) {
            tableModel.addRow(new Object[]{
                log.getLogId(),
                log.getTableName(),
                log.getRecordId(),
                log.getAction(),
                log.getChangedByName(),
                log.getChangedAt() != null ? sdf.format(log.getChangedAt()) : "—",
                trimJson(log.getOldValues()),
                trimJson(log.getNewValues())
            });
        }

        statusLabel.setText(logs.size() + " record" + (logs.size() == 1 ? "" : "s") + " found");
    }

    /**
     * Truncates long JSON strings so they fit in the cell without wrapping.
     * The full value is still in the model — a tooltip shows the rest.
     */
    private String trimJson(String json) {
        if (json == null) return "—";
        return json.length() > 60 ? json.substring(0, 57) + "…" : json;
    }

    // ── Helper factory methods ────────────────────────────────────────────────

    private JLabel styledLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 13));
        lbl.setForeground(Color.WHITE);
        return lbl;
    }

    private void styleComboBox(JComboBox<String> box) {
        box.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 13));
        box.setBackground(WHITE);
        box.setForeground(DARK_PURPLE);
        box.setPreferredSize(new Dimension(140, 28));
        box.setFocusable(false);
    }

    // ── Entry point ───────────────────────────────────────────────────────────

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | UnsupportedLookAndFeelException ex) {
            logger.severe(ex.getMessage());
        }

        EventQueue.invokeLater(() -> new AdminLogsView().setVisible(true));
    }
}