package Views.ReservationManagement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import Controllers.GuestControllers;
import Controllers.ServiceControllers;
import Controllers.ServiceBookingControllers;
import Model.Guests;
import Model.ServiceBooking;
import Model.Services;

public class ServicesPanel extends javax.swing.JPanel {

    private final GuestControllers          guestCtrl   = new GuestControllers();
    private final ServiceControllers        serviceCtrl = new ServiceControllers();
    private final ServiceBookingControllers bookingCtrl = new ServiceBookingControllers();

    private javax.swing.table.DefaultTableModel tableModel;

    private static final DateTimeFormatter FMT =
        DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm a");

    // ── Constructor ───────────────────────────────────────────────────────────
    public ServicesPanel() {
        initComponents();
        setupTable();
        loadGuestAndServices();
        loadBookings();
        serviceCombo.addActionListener(e -> updateInfo());
        jButton5.addActionListener(e -> handleBook());
    }

    // ── Load combos ───────────────────────────────────────────────────────────
    public void loadGuestAndServices() {
        javax.swing.DefaultComboBoxModel<Guests> guestModel = new javax.swing.DefaultComboBoxModel<>();
        List<Guests> guests = guestCtrl.getAllGuests();
        if (guests != null) guests.forEach(guestModel::addElement);
        guestCombo.setModel(guestModel);

        javax.swing.DefaultComboBoxModel<Services> servicesModel = new javax.swing.DefaultComboBoxModel<>();
        List<Services> services = serviceCtrl.ListOfAllServices();
        if (services != null) services.forEach(servicesModel::addElement);
        serviceCombo.setModel(servicesModel);

        updateInfo();
    }

    // ── Load bookings into table ──────────────────────────────────────────────
    private void loadBookings() {
        tableModel.setRowCount(0);
        List<ServiceBooking> list = bookingCtrl.ListAllBookings();
        if (list == null) return;
        for (ServiceBooking b : list) {
            tableModel.addRow(new Object[]{
                b.getBookingId(),
                b.getGuestName(),
                b.getServiceName(),
                b.getScheduledAt().format(FMT),
                b.getDuration() + " min",
                String.format("%.2f", b.getTotal()),
                b.getStatus()
            });
        }
    }

    // ── Auto-update Duration + Total when service changes ─────────────────────
    private void updateInfo() {
        Object sel = serviceCombo.getSelectedItem();
        if (!(sel instanceof Services)) {
            jTextField6.setText("");
            jLabel8.setText("Total: ");
            return;
        }
        Services s = (Services) sel;
        jTextField6.setText(s.getDurationMinutes() + " min");
        jLabel8.setText("Total: ₱" + String.format("%.2f", s.getPrice()));
    }

    // ── Book button handler ───────────────────────────────────────────────────
    private void handleBook() {
        Object guestObj   = guestCombo.getSelectedItem();
        Object serviceObj = serviceCombo.getSelectedItem();

        if (!(guestObj instanceof Guests)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a Guests.");
            return;
        }
        if (!(serviceObj instanceof Services)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a service.");
            return;
        }

        Guests    g = (Guests)    guestObj;
        Services s = (Services) serviceObj;
        LocalDateTime now = LocalDateTime.now();

        // ── Save to database ──────────────────────────────────────────────────
        String result = bookingCtrl.AddBookingProcess(
            Integer.parseInt(g.getGuest_id()),
            Integer.parseInt(s.getServiceID()),
            now,
            Integer.parseInt(s.getDurationMinutes()),
            Double.parseDouble(s.getPrice())
        );

        javax.swing.JOptionPane.showMessageDialog(this, result);

        // ── Only update table if save succeeded ───────────────────────────────
        if (result.startsWith("Service booked")) {
            loadBookings(); // reload from DB so table shows real booking_id + status
        }
    }

    // ── Table setup ───────────────────────────────────────────────────────────
    private void setupTable() {
        tableModel = new javax.swing.table.DefaultTableModel(
            new String[]{"ID", "Guests", "Service", "Scheduled", "Duration", "Total (₱)", "Status"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        ServicesTable.setModel(tableModel);
        ServicesTable.setEnabled(true);
        ServicesTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ServicesTable.getTableHeader().setReorderingAllowed(false);
        // Hide ID column
        ServicesTable.getColumnModel().getColumn(0).setMinWidth(0);
        ServicesTable.getColumnModel().getColumn(0).setMaxWidth(0);
        ServicesTable.getColumnModel().getColumn(0).setWidth(0);
    }
    private void btnRefreshMouseClicked(java.awt.event.MouseEvent evt) {
        loadGuestAndServices();
        loadBookings();
    }

    // ── initComponents (unchanged from your file) ─────────────────────────────
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        guestCombo = new javax.swing.JComboBox<Guests>();
        jLabel7 = new javax.swing.JLabel();
        serviceCombo = new javax.swing.JComboBox<Services>();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ServicesTable = new javax.swing.JTable();
        btnRefresh = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(100, 100));

        jPanel2.setBackground(new java.awt.Color(255, 204, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(1113, 200));
        jPanel2.setMinimumSize(new java.awt.Dimension(100, 100));
        jPanel2.setPreferredSize(new java.awt.Dimension(1115, 163));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel3.setMinimumSize(new java.awt.Dimension(150, 100));
        jPanel3.setPreferredSize(new java.awt.Dimension(350, 175));
        jPanel3.setRequestFocusEnabled(false);
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel6.setText("Guest");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jLabel6, gridBagConstraints);

        guestCombo.setModel(new javax.swing.DefaultComboBoxModel<>());
        guestCombo.setPreferredSize(new java.awt.Dimension(250, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(guestCombo, gridBagConstraints);

        jLabel7.setText("Service");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jLabel7, gridBagConstraints);

        serviceCombo.setModel(new javax.swing.DefaultComboBoxModel<>());
        serviceCombo.setPreferredSize(new java.awt.Dimension(250, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(serviceCombo, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel2.add(jPanel3, gridBagConstraints);

        jPanel6.setMinimumSize(new java.awt.Dimension(150, 100));
        jPanel6.setPreferredSize(new java.awt.Dimension(350, 175));
        jPanel6.setRequestFocusEnabled(false);
        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabel9.setText("Time Duration");
        jPanel6.add(jLabel9, new java.awt.GridBagConstraints());

        jTextField6.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel6.add(jTextField6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel2.add(jPanel6, gridBagConstraints);

        jPanel7.setMinimumSize(new java.awt.Dimension(150, 100));
        jPanel7.setPreferredSize(new java.awt.Dimension(350, 175));
        jPanel7.setRequestFocusEnabled(false);
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Total: ");
        jPanel7.add(jLabel8, new java.awt.GridBagConstraints());

        jButton5.setText("Book");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel7.add(jButton5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel2.add(jPanel7, gridBagConstraints);

        jPanel4.setBackground(new java.awt.Color(255, 153, 153));
        jPanel4.setMinimumSize(new java.awt.Dimension(100, 100));

        ServicesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Guest", "Service", "Scheduled", "Duration"
            }
        ));
        ServicesTable.setColumnSelectionAllowed(true);
        ServicesTable.setEnabled(false);
        ServicesTable.setOpaque(false);
        ServicesTable.setRowHeight(40);
        ServicesTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(ServicesTable);
        ServicesTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        btnRefresh.setText("Refresh");
        

        
        btnRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefreshMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1127, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1127, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ServicesTable;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox<Guests> guestCombo;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JComboBox<Services> serviceCombo;
    // End of variables declaration//GEN-END:variables
}
