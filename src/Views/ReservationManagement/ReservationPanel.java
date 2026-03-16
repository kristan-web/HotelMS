
package Views.ReservationManagement;
import Controllers.*;
import Model.*;
public class ReservationPanel extends javax.swing.JPanel {

    private final ReservationController resCtrl   = new ReservationController();
    private final GuestControllers       guestCtrl = new GuestControllers();
    private final RoomController        roomCtrl  = new RoomController();
    private java.time.LocalDate checkInDate, checkOutDate;
    private javax.swing.table.DefaultTableModel tableModel;
    private static final java.time.format.DateTimeFormatter FMT =
    java.time.format.DateTimeFormatter.ofPattern("MMM dd, yyyy");

    public ReservationPanel() {
        initComponents();
        setupTable();
        loadGuestsAndRooms();
        loadReservations();
        btnRefresh.addActionListener(e -> loadReservations());
    }

    private void setupTable() {
        tableModel = new javax.swing.table.DefaultTableModel(
            new String[]{"ID","Guests","Room","Check-In","Check-Out","Nights","Total (₱)","Status"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        reservationTable.setModel(tableModel);
        reservationTable.setEnabled(true);
        reservationTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        reservationTable.setRowHeight(24);
        reservationTable.getColumnModel().getColumn(0).setMinWidth(0);
        reservationTable.getColumnModel().getColumn(0).setMaxWidth(0);
        reservationTable.getColumnModel().getColumn(0).setWidth(0);
    }

    private void loadGuestsAndRooms() {
        javax.swing.DefaultComboBoxModel guestModel = new javax.swing.DefaultComboBoxModel();
        guestCtrl.getAllGuests().forEach(guestModel::addElement);
        guestCombo.setModel(guestModel);

        javax.swing.DefaultComboBoxModel roomModel = new javax.swing.DefaultComboBoxModel();
        roomCtrl.getAllRooms().forEach(roomModel::addElement);
        roomCombo.setModel(roomModel);
    }

    private void loadReservations() {
        tableModel.setRowCount(0);
        String filter = (String) statusFilter.getSelectedItem();
        java.util.List<Reservation> list = "ALL".equals(filter)
            ? resCtrl.getAllReservations() : resCtrl.getByStatus(filter);
        for (Reservation r : list) {
            tableModel.addRow(new Object[]{
                r.getReservationId(), r.getGuest().getFullName(),
                "Room " + r.getRoom().getRoomNumber(),
                r.getCheckIn().format(FMT), r.getCheckOut().format(FMT),
                r.getNights(), String.format("%.2f", r.getTotalAmount()), r.getStatus()
            });
        }
    }

    private void updateTotal() {
        Object selected = roomCombo.getSelectedItem();
        if (!(selected instanceof Room)) {
            lblTotal.setText("Total: ₱0.00");
            return;
        }
        Room room = (Room) selected;
        if (checkInDate != null && checkOutDate != null && checkOutDate.isAfter(checkInDate)) {
            long nights = java.time.temporal.ChronoUnit.DAYS.between(checkInDate, checkOutDate);
            lblTotal.setText(String.format("Total: ₱%.2f (%d nights)", nights * room.getPrice(), nights));
        } else {
            lblTotal.setText("Total: ₱0.00");
        }
    }
    
    private String getSelectedStatus() {
        int row = reservationTable.getSelectedRow();
        if (row < 0) return null;
        Object val = tableModel.getValueAt(row, 7);
        return val != null ? val.toString() : null;
    }
    
     private Views.Receipt.ReceiptViewCopy buildReceiptForSelectedRow() {
        int row = reservationTable.getSelectedRow();
        if (row < 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Please select a reservation first.",
                "No Selection", javax.swing.JOptionPane.WARNING_MESSAGE);
            return null;
        }
        String status = getSelectedStatus();
        if (!"CHECKED_OUT".equals(status)) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Receipt is only available for CHECKED_OUT reservations.\n"
                + "Selected reservation status: " + status,
                "Invalid Status", javax.swing.JOptionPane.WARNING_MESSAGE);
            return null;
        }
 
        int    reservationId = ((Number) tableModel.getValueAt(row, 0)).intValue();
        String guestName     = (String) tableModel.getValueAt(row, 1);
        String roomNumber    = tableModel.getValueAt(row, 2).toString().replace("Room ", "");
        String checkIn       = (String) tableModel.getValueAt(row, 3);
        String checkOut      = (String) tableModel.getValueAt(row, 4);
        int    nights        = ((Number) tableModel.getValueAt(row, 5)).intValue();
        String totalStr      = tableModel.getValueAt(row, 6).toString();
        double totalAmount   = Double.parseDouble(totalStr);
        double roomPrice     = nights > 0 ? totalAmount / nights : 0;
 
        // Fetch notes from DB for the selected reservation
        String notes = "";
        try (java.sql.Connection conn = Database.Db_Connector.getCOnnection();
             java.sql.PreparedStatement ps = conn.prepareStatement(
                     "SELECT notes FROM reservations WHERE reservation_id = ?")) {
            ps.setInt(1, reservationId);
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) notes = rs.getString("notes");
            }
        } catch (Exception ex) {
            // notes stays empty — not a blocking error
        }
 
        // Fetch guest address from DB
        String guestAddress = "";
        try (java.sql.Connection conn = Database.Db_Connector.getCOnnection();
             java.sql.PreparedStatement ps = conn.prepareStatement(
                     "SELECT g.address FROM guests g "
                   + "JOIN reservations r ON r.guest_id = g.guest_id "
                   + "WHERE r.reservation_id = ?")) {
            ps.setInt(1, reservationId);
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) guestAddress = rs.getString("address");
            }
        } catch (Exception ex) {
            // address stays empty
        }
 
        Views.Receipt.ReceiptViewCopy receipt = new Views.Receipt.ReceiptViewCopy(
            reservationId, guestName, guestAddress,
            roomNumber, checkIn, checkOut,
            nights, roomPrice, totalAmount, notes);
 
        receipt.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        receipt.setTitle("Receipt – Reservation #" + reservationId);
        return receipt;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        guestCombo = new javax.swing.JComboBox<>();
        roomCombo = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        notesArea = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        checkInField = new javax.swing.JTextField();
        checkOutField = new javax.swing.JTextField();
        btnPickCheckIn = new javax.swing.JButton();
        btnPickCheckOut = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        btnFindRooms = new javax.swing.JButton();
        btnConfirm = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        reservationTable = new javax.swing.JTable();
        btnCheckIn = new javax.swing.JButton();
        btnCheckOut = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        statusFilter = new javax.swing.JComboBox<>();
        btnCancel = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        SavePDF = new javax.swing.JButton();
        ViewReceipt = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 255));
        setMinimumSize(new java.awt.Dimension(100, 100));
        setPreferredSize(new java.awt.Dimension(1000, 600));
        setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(255, 224, 227));
        jPanel1.setMinimumSize(new java.awt.Dimension(900, 500));

        jPanel2.setBackground(new java.awt.Color(190, 52, 85));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(232, 194, 189), 1, true));
        jPanel2.setForeground(new java.awt.Color(190, 52, 85));
        jPanel2.setMaximumSize(new java.awt.Dimension(1300, 200));
        jPanel2.setMinimumSize(new java.awt.Dimension(100, 100));
        jPanel2.setPreferredSize(new java.awt.Dimension(1113, 200));
        jPanel2.setRequestFocusEnabled(false);
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBackground(new java.awt.Color(255, 224, 227));
        jPanel3.setMinimumSize(new java.awt.Dimension(150, 100));
        jPanel3.setPreferredSize(new java.awt.Dimension(350, 175));
        jPanel3.setRequestFocusEnabled(false);

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(47, 32, 56));
        jLabel6.setText("Guest:");

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(47, 32, 56));
        jLabel7.setText("Rooms:");

        guestCombo.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N
        guestCombo.setForeground(new java.awt.Color(47, 32, 56));
        guestCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        guestCombo.setPreferredSize(new java.awt.Dimension(250, 25));

        roomCombo.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N
        roomCombo.setForeground(new java.awt.Color(47, 32, 56));
        roomCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        roomCombo.setPreferredSize(new java.awt.Dimension(250, 25));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(47, 32, 56));
        jLabel8.setText("Notes:");

        jScrollPane2.setMaximumSize(new java.awt.Dimension(32767, 100));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(224, 70));

        notesArea.setColumns(20);
        notesArea.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N
        notesArea.setForeground(new java.awt.Color(47, 32, 56));
        notesArea.setLineWrap(true);
        notesArea.setRows(3);
        notesArea.setTabSize(10);
        notesArea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        notesArea.setMinimumSize(new java.awt.Dimension(250, 52));
        notesArea.setRequestFocusEnabled(false);
        jScrollPane2.setViewportView(notesArea);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(125, 125, 125)
                        .addComponent(jLabel7))
                    .addComponent(jLabel8)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(guestCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(roomCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guestCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roomCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel2.add(jPanel3, gridBagConstraints);

        jPanel6.setBackground(new java.awt.Color(255, 224, 227));
        jPanel6.setMinimumSize(new java.awt.Dimension(150, 100));
        jPanel6.setPreferredSize(new java.awt.Dimension(350, 175));
        jPanel6.setRequestFocusEnabled(false);

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(47, 32, 56));
        jLabel1.setText("Check-In:");

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(47, 32, 56));
        jLabel2.setText("Check-Out:");

        checkInField.setEditable(false);
        checkInField.setBackground(new java.awt.Color(255, 255, 255));
        checkInField.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N
        checkInField.setForeground(new java.awt.Color(47, 32, 56));
        checkInField.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        checkInField.setPreferredSize(new java.awt.Dimension(225, 30));
        checkInField.addActionListener(this::checkInFieldActionPerformed);

        checkOutField.setEditable(false);
        checkOutField.setBackground(new java.awt.Color(255, 255, 255));
        checkOutField.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N
        checkOutField.setForeground(new java.awt.Color(47, 32, 56));
        checkOutField.setPreferredSize(new java.awt.Dimension(225, 30));

        btnPickCheckIn.setBackground(new java.awt.Color(255, 239, 241));
        btnPickCheckIn.setForeground(new java.awt.Color(190, 52, 85));
        btnPickCheckIn.setText("📅");
        btnPickCheckIn.addActionListener(this::btnPickCheckInActionPerformed);

        btnPickCheckOut.setBackground(new java.awt.Color(255, 239, 241));
        btnPickCheckOut.setForeground(new java.awt.Color(190, 52, 85));
        btnPickCheckOut.setText("📅");
        btnPickCheckOut.addActionListener(this::btnPickCheckOutActionPerformed);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(checkInField, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPickCheckIn))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(checkOutField, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPickCheckOut))
                    .addComponent(jLabel2))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkInField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPickCheckIn))
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addGap(1, 1, 1)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkOutField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPickCheckOut))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel2.add(jPanel6, gridBagConstraints);

        jPanel7.setBackground(new java.awt.Color(255, 224, 227));
        jPanel7.setMinimumSize(new java.awt.Dimension(150, 100));
        jPanel7.setPreferredSize(new java.awt.Dimension(350, 175));
        jPanel7.setRequestFocusEnabled(false);
        jPanel7.setLayout(new java.awt.GridBagLayout());

        btnFindRooms.setBackground(new java.awt.Color(156, 175, 214));
        btnFindRooms.setForeground(new java.awt.Color(255, 255, 255));
        btnFindRooms.setText("🔎 Find Available Rooms");
        btnFindRooms.setMaximumSize(new java.awt.Dimension(275, 40));
        btnFindRooms.setMinimumSize(new java.awt.Dimension(275, 40));
        btnFindRooms.setPreferredSize(new java.awt.Dimension(275, 40));
        btnFindRooms.addActionListener(this::btnFindRoomsActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel7.add(btnFindRooms, gridBagConstraints);

        btnConfirm.setBackground(new java.awt.Color(184, 223, 187));
        btnConfirm.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirm.setText("✅ Confirm Reservation");
        btnConfirm.setMaximumSize(new java.awt.Dimension(275, 40));
        btnConfirm.setMinimumSize(new java.awt.Dimension(159, 40));
        btnConfirm.setPreferredSize(new java.awt.Dimension(275, 40));
        btnConfirm.addActionListener(this::btnConfirmActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel7.add(btnConfirm, gridBagConstraints);

        lblTotal.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 24)); // NOI18N
        lblTotal.setText("Total: ₱0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        jPanel7.add(lblTotal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel2.add(jPanel7, gridBagConstraints);

        jPanel4.setBackground(new java.awt.Color(190, 52, 85));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(232, 194, 189), 1, true));
        jPanel4.setMaximumSize(new java.awt.Dimension(1127, 410));

        reservationTable.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N
        reservationTable.setForeground(new java.awt.Color(47, 32, 56));
        reservationTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        reservationTable.setColumnSelectionAllowed(true);
        reservationTable.setEnabled(false);
        reservationTable.setOpaque(false);
        reservationTable.setRowHeight(40);
        reservationTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(reservationTable);
        reservationTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        btnCheckIn.setBackground(new java.awt.Color(255, 224, 227));
        btnCheckIn.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N
        btnCheckIn.setForeground(new java.awt.Color(47, 32, 56));
        btnCheckIn.setText("Check In");
        btnCheckIn.addActionListener(this::btnCheckInActionPerformed);

        btnCheckOut.setBackground(new java.awt.Color(255, 224, 227));
        btnCheckOut.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N
        btnCheckOut.setForeground(new java.awt.Color(47, 32, 56));
        btnCheckOut.setText("Check Out");
        btnCheckOut.addActionListener(this::btnCheckOutActionPerformed);

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 224, 227));
        jLabel4.setText("Filter:");

        statusFilter.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N
        statusFilter.setForeground(new java.awt.Color(47, 32, 56));
        statusFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALL", "CONFIRMED", "CHECKED_IN", "CHECKED_OUT", "CANCELLED" }));
        statusFilter.addActionListener(this::statusFilterActionPerformed);

        btnCancel.setBackground(new java.awt.Color(255, 224, 227));
        btnCancel.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(47, 32, 56));
        btnCancel.setText("Cancel Reservation");
        btnCancel.addActionListener(this::btnCancelActionPerformed);

        btnRefresh.setBackground(new java.awt.Color(255, 224, 227));
        btnRefresh.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(47, 32, 56));
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(this::btnRefreshActionPerformed);

        SavePDF.setBackground(new java.awt.Color(255, 224, 227));
        SavePDF.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N
        SavePDF.setForeground(new java.awt.Color(47, 32, 56));
        SavePDF.setText("Save PDF");
        SavePDF.addActionListener(this::SavePDFActionPerformed);

        ViewReceipt.setBackground(new java.awt.Color(255, 224, 227));
        ViewReceipt.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 12)); // NOI18N
        ViewReceipt.setForeground(new java.awt.Color(47, 32, 56));
        ViewReceipt.setText("View Receipt");
        ViewReceipt.addActionListener(this::ViewReceiptActionPerformed);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ViewReceipt)
                        .addGap(18, 18, 18)
                        .addComponent(SavePDF)
                        .addGap(29, 29, 29))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCheckIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addComponent(statusFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCheckOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SavePDF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ViewReceipt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1115, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void checkInFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkInFieldActionPerformed
    }//GEN-LAST:event_checkInFieldActionPerformed

    private void statusFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusFilterActionPerformed
        loadReservations();
    }//GEN-LAST:event_statusFilterActionPerformed

    private void btnCheckInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckInActionPerformed
        int row = reservationTable.getSelectedRow();
        if (row < 0) { javax.swing.JOptionPane.showMessageDialog(this, "Select a reservation first."); return; }
        javax.swing.JOptionPane.showMessageDialog(this, resCtrl.checkIn((int) tableModel.getValueAt(row, 0)));
        loadReservations();
    }//GEN-LAST:event_btnCheckInActionPerformed

    private void btnCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckOutActionPerformed
        int row = reservationTable.getSelectedRow();
        if (row < 0) { javax.swing.JOptionPane.showMessageDialog(this, "Select a reservation first."); return; }
        javax.swing.JOptionPane.showMessageDialog(this, resCtrl.checkOut((int) tableModel.getValueAt(row, 0)));
        loadReservations();
    }//GEN-LAST:event_btnCheckOutActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        int row = reservationTable.getSelectedRow();
        if (row < 0) { javax.swing.JOptionPane.showMessageDialog(this, "Select a reservation first."); return; }
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
            "Cancel this reservation?", "Confirm", javax.swing.JOptionPane.YES_NO_OPTION);
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            javax.swing.JOptionPane.showMessageDialog(this, resCtrl.cancelReservation((int) tableModel.getValueAt(row, 0)));
            loadReservations();
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnPickCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPickCheckOutActionPerformed
        java.awt.Frame f = (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this);
        java.time.LocalDate d = DatePicker.showDialog(f, "Check-Out Date", checkOutDate);
        if (d != null) { checkOutDate = d; checkOutField.setText(d.format(FMT)); updateTotal(); }
    }//GEN-LAST:event_btnPickCheckOutActionPerformed

    private void btnPickCheckInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPickCheckInActionPerformed
        java.awt.Frame f = (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this);
        java.time.LocalDate d = DatePicker.showDialog(f, "Check-In Date", checkInDate);
        if (d != null) { checkInDate = d; checkInField.setText(d.format(FMT)); updateTotal(); }
    }//GEN-LAST:event_btnPickCheckInActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        Object guestObj = guestCombo.getSelectedItem();
        Object roomObj  = roomCombo.getSelectedItem();
        if (!(guestObj instanceof Guests) || !(roomObj instanceof Room)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a valid Guests and room.");
            return;
        }
        String result = resCtrl.makeReservation(
            (Guests) guestObj, (Room) roomObj,
            checkInDate, checkOutDate, notesArea.getText());
        javax.swing.JOptionPane.showMessageDialog(this, result);
        if (result.startsWith("SUCCESS")) {
            loadReservations();
            loadGuestsAndRooms();
            checkInDate = null; checkOutDate = null;
            checkInField.setText(""); checkOutField.setText("");
            notesArea.setText("");
            lblTotal.setText("Total: ₱0.00");
        }
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void btnFindRoomsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindRoomsActionPerformed
        if (checkInDate == null || checkOutDate == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Select both dates first.");
            return;
        }
        javax.swing.DefaultComboBoxModel roomModel = new javax.swing.DefaultComboBoxModel();
        roomCtrl.getAvailableRooms(checkInDate, checkOutDate).forEach(roomModel::addElement);
        if (roomModel.getSize() == 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "No rooms available for selected dates.");
        } else {
            roomCombo.setModel(roomModel);
        }
        updateTotal();
    }//GEN-LAST:event_btnFindRoomsActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        loadGuestsAndRooms();  // ← add this
        loadReservations();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void SavePDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SavePDFActionPerformed
        Views.Receipt.ReceiptViewCopy receipt = buildReceiptForSelectedRow();
        if (receipt != null) {
            // Must be visible so Swing has valid component sizes for rendering
            receipt.setLocationRelativeTo(this);
            receipt.setVisible(true);
            receipt.saveAsPDF();
        }
    }//GEN-LAST:event_SavePDFActionPerformed

    private void ViewReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewReceiptActionPerformed
        Views.Receipt.ReceiptViewCopy receipt = buildReceiptForSelectedRow();
        if (receipt != null) {
            receipt.setLocationRelativeTo(this);
            receipt.setVisible(true);
        }
    }//GEN-LAST:event_ViewReceiptActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SavePDF;
    private javax.swing.JButton ViewReceipt;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCheckIn;
    private javax.swing.JButton btnCheckOut;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnFindRooms;
    private javax.swing.JButton btnPickCheckIn;
    private javax.swing.JButton btnPickCheckOut;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JTextField checkInField;
    private javax.swing.JTextField checkOutField;
    private javax.swing.JComboBox<String> guestCombo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTextArea notesArea;
    private javax.swing.JTable reservationTable;
    private javax.swing.JComboBox<String> roomCombo;
    private javax.swing.JComboBox<String> statusFilter;
    // End of variables declaration//GEN-END:variables
}
