
package Views.ReservationManagement;
import Controllers.RoomController;


public class RoomPanel extends javax.swing.JPanel {
    private final RoomController ctrl = new RoomController();
    private javax.swing.table.DefaultTableModel tableModel;
    
    public RoomPanel() {
        initComponents();
        setupTable();
        loadRooms();
        roomTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) fillFormFromTable();
        });
    }
    private void setupTable() {
    tableModel = new javax.swing.table.DefaultTableModel(
        new String[]{"ID","Room No","Type","Price (₱)","Capacity","Status","Description"}, 0) {
        public boolean isCellEditable(int r, int c) { return false; }
    };
    roomTable.setModel(tableModel);
    roomTable.setEnabled(true);
    roomTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    roomTable.setRowHeight(24);
    roomTable.getColumnModel().getColumn(0).setMinWidth(0);
    roomTable.getColumnModel().getColumn(0).setMaxWidth(0);
    roomTable.getColumnModel().getColumn(0).setWidth(0);
    }

    private void loadRooms() {
        tableModel.setRowCount(0);
        ctrl.getAllRooms().forEach(r -> tableModel.addRow(new Object[]{
            r.getRoomId(), r.getRoomNumber(), r.getRoomType(),
            String.format("%.2f", r.getPrice()), r.getCapacity(), r.getStatus(), r.getDescription()}));
    }

    private void fillFormFromTable() {
        int row = roomTable.getSelectedRow();
        if (row < 0) return;
        roomNumberField.setText((String)  tableModel.getValueAt(row, 1));
        roomTypeCombo.setSelectedItem(    tableModel.getValueAt(row, 2));
        priceField.setText(               tableModel.getValueAt(row, 3).toString());
        capacityField.setText(            tableModel.getValueAt(row, 4).toString());
        descriptionField.setText((String) tableModel.getValueAt(row, 6));
        roomStatusCombo.setSelectedItem(  tableModel.getValueAt(row, 5));
    }

    private void clearForm() {
        roomNumberField.setText(""); priceField.setText("");
        capacityField.setText(""); descriptionField.setText("");
        roomTypeCombo.setSelectedIndex(0); roomTable.clearSelection();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        roomNumberField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        capacityField = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        roomTypeCombo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        descriptionField = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        priceField = new javax.swing.JTextField();
        btnAddRoom = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        roomTable = new javax.swing.JTable();
        btnSetStatus = new javax.swing.JButton();
        btnDeleteRoom = new javax.swing.JButton();
        Statuss = new javax.swing.JLabel();
        roomStatusCombo = new javax.swing.JComboBox<>();
        btnCancel = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(100, 100));
        setPreferredSize(new java.awt.Dimension(1000, 600));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1000, 600));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 600));

        jPanel2.setBackground(new java.awt.Color(255, 204, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(1300, 200));
        jPanel2.setMinimumSize(new java.awt.Dimension(100, 100));
        jPanel2.setPreferredSize(new java.awt.Dimension(1113, 200));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel3.setMinimumSize(new java.awt.Dimension(150, 100));
        jPanel3.setPreferredSize(new java.awt.Dimension(350, 175));
        jPanel3.setRequestFocusEnabled(false);
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Room No:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jLabel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(roomNumberField, gridBagConstraints);

        jLabel2.setText("Capacity:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jLabel2, gridBagConstraints);

        capacityField.setPreferredSize(new java.awt.Dimension(225, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(capacityField, gridBagConstraints);

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

        jLabel3.setText("Type:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel6.add(jLabel3, gridBagConstraints);

        roomTypeCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SINGLE", "DOUBLE", "DELUXE", "SUITE" }));
        roomTypeCombo.setPreferredSize(new java.awt.Dimension(225, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel6.add(roomTypeCombo, gridBagConstraints);

        jLabel5.setText("Description:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel6.add(jLabel5, gridBagConstraints);

        descriptionField.setAutoscrolls(false);
        descriptionField.setPreferredSize(new java.awt.Dimension(225, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel6.add(descriptionField, gridBagConstraints);

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

        jLabel6.setText("Price:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel7.add(jLabel6, gridBagConstraints);

        priceField.setPreferredSize(new java.awt.Dimension(225, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel7.add(priceField, gridBagConstraints);

        btnAddRoom.setText("Add Room");
        btnAddRoom.setPreferredSize(new java.awt.Dimension(150, 22));
        btnAddRoom.addActionListener(this::btnAddRoomActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel7.add(btnAddRoom, gridBagConstraints);

        btnClear.setText("Clear");
        btnClear.setPreferredSize(new java.awt.Dimension(150, 22));
        btnClear.addActionListener(this::btnClearActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel7.add(btnClear, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel2.add(jPanel7, gridBagConstraints);

        jPanel4.setBackground(new java.awt.Color(255, 153, 153));
        jPanel4.setMaximumSize(new java.awt.Dimension(1127, 410));
        jPanel4.setName(""); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(731, 376));

        roomTable.setModel(new javax.swing.table.DefaultTableModel(
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
        roomTable.setColumnSelectionAllowed(true);
        roomTable.setEnabled(false);
        roomTable.setOpaque(false);
        roomTable.setRowHeight(40);
        roomTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(roomTable);

        btnSetStatus.setText("Set Status");
        btnSetStatus.addActionListener(this::btnSetStatusActionPerformed);

        btnDeleteRoom.setText("Delete");
        btnDeleteRoom.addActionListener(this::btnDeleteRoomActionPerformed);

        Statuss.setText("Change Status:");

        roomStatusCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AVAILABLE", "OCCUPIED", "MAINTENANCE" }));
        roomStatusCombo.addActionListener(this::roomStatusComboActionPerformed);

        btnCancel.setText("Refresh");
        btnCancel.setMaximumSize(new java.awt.Dimension(81, 23));
        btnCancel.setMinimumSize(new java.awt.Dimension(81, 23));
        btnCancel.setPreferredSize(new java.awt.Dimension(81, 23));
        btnCancel.addActionListener(this::btnCancelActionPerformed);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(Statuss)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roomStatusCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSetStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDeleteRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSetStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Statuss)
                        .addComponent(roomStatusCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1288, Short.MAX_VALUE))
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

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSetStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetStatusActionPerformed
        int row = roomTable.getSelectedRow();
        if (row < 0) { javax.swing.JOptionPane.showMessageDialog(this, "Select a room first."); return; }
        String result = ctrl.updateRoomStatus((int) tableModel.getValueAt(row, 0),
            (String) roomStatusCombo.getSelectedItem());
        javax.swing.JOptionPane.showMessageDialog(this, result);
        loadRooms();
    }//GEN-LAST:event_btnSetStatusActionPerformed

    private void btnDeleteRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteRoomActionPerformed
        int row = roomTable.getSelectedRow();
        if (row < 0) { javax.swing.JOptionPane.showMessageDialog(this, "Select a room first."); return; }
        if (javax.swing.JOptionPane.showConfirmDialog(this, "Delete this room?",
            "Confirm", javax.swing.JOptionPane.YES_NO_OPTION) == javax.swing.JOptionPane.YES_OPTION) {
            javax.swing.JOptionPane.showMessageDialog(this, ctrl.deleteRoom((int) tableModel.getValueAt(row, 0)));
            loadRooms(); clearForm();
        }
    }//GEN-LAST:event_btnDeleteRoomActionPerformed

    private void roomStatusComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomStatusComboActionPerformed
        
    }//GEN-LAST:event_roomStatusComboActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        loadRooms();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnAddRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRoomActionPerformed
        String r = ctrl.addRoom(roomNumberField.getText(), (String) roomTypeCombo.getSelectedItem(),
        priceField.getText(), capacityField.getText(), descriptionField.getText());
        javax.swing.JOptionPane.showMessageDialog(this, r);
        loadRooms(); if (r.startsWith("SUCCESS")) clearForm();
    }//GEN-LAST:event_btnAddRoomActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
         clearForm();
    }//GEN-LAST:event_btnClearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Statuss;
    private javax.swing.JButton btnAddRoom;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDeleteRoom;
    private javax.swing.JButton btnSetStatus;
    private javax.swing.JTextField capacityField;
    private javax.swing.JTextField descriptionField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField priceField;
    private javax.swing.JTextField roomNumberField;
    private javax.swing.JComboBox<String> roomStatusCombo;
    private javax.swing.JTable roomTable;
    private javax.swing.JComboBox<String> roomTypeCombo;
    // End of variables declaration//GEN-END:variables
}
