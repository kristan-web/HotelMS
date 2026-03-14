package Views.ServiceManagement;
import java.util.List;
import Model.*;
import Controllers.ServiceControllers;
import javax.swing.JOptionPane;
import javax.swing.event.*;

public class DeletedServicesView extends javax.swing.JFrame {
    public static DeletedServicesView instance;
    private static final ServiceControllers control = new ServiceControllers();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DeletedServicesView.class.getName());
    
    public final void loadServicesToTable() {
    List<Services> services = control.ListOfAllDeletedServices();
    
    javax.swing.table.DefaultTableModel model = 
        (javax.swing.table.DefaultTableModel) tblServices.getModel();
    model.setRowCount(0); // clear previous data

    for (Services s : services) {
        model.addRow(new Object[]{
            s.getServiceID(),
            s.getServiceName(),
            s.getPrice(),
            s.getDurationMinutes(),
            s.getStatus()
        });
    }
    
    
}
    public final void loadServicesToTable(String text) {
        List<Services> services = control.ListOfAllDeletedServices(text);

        javax.swing.table.DefaultTableModel model = 
            (javax.swing.table.DefaultTableModel) tblServices.getModel();
        model.setRowCount(0); // clear previous data

        for (Services s : services) {
            model.addRow(new Object[]{
                s.getServiceID(),
                s.getServiceName(),
                s.getPrice(),
                s.getDurationMinutes(),
                s.getStatus(),
            });
        }
    }
    public static DeletedServicesView getInstance(){
        return instance;
    }
    
    
    public DeletedServicesView() {
        initComponents();
        this.setLocationRelativeTo(null);
        instance = this;
        //Displays Values From the Database
        loadServicesToTable();
        
       serviceSearchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e){
                String searchfieldtext = serviceSearchField.getText();
                DeletedServicesView.getInstance().loadServicesToTable(searchfieldtext);
            }
            
            @Override
            public void changedUpdate(DocumentEvent e){
                String searchfieldtext = serviceSearchField.getText();
                DeletedServicesView.getInstance().loadServicesToTable(searchfieldtext);
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                String searchfieldtext = serviceSearchField.getText();
                DeletedServicesView.getInstance().loadServicesToTable(searchfieldtext);
            }
        });
      
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        RestoreServiceButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        serviceSearchField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblServices = new javax.swing.JTable();
        ServiceViewButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 224, 227));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 24)); // NOI18N
        jLabel1.setText("Deleted Services");

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel2.setText("List of deleted services");

        RestoreServiceButton.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        RestoreServiceButton.setText("Restore Service");
        RestoreServiceButton.addActionListener(this::RestoreServiceButtonActionPerformed);

        jPanel2.setBackground(new java.awt.Color(243, 223, 220));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 194, 189), 2));
        jPanel2.setForeground(new java.awt.Color(255, 224, 227));
        jPanel2.setPreferredSize(new java.awt.Dimension(468, 315));

        serviceSearchField.setBackground(new java.awt.Color(246, 238, 238));
        serviceSearchField.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        serviceSearchField.setSelectionColor(new java.awt.Color(210, 90, 119));
        serviceSearchField.addActionListener(this::serviceSearchFieldActionPerformed);
        serviceSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                serviceSearchFieldKeyTyped(evt);
            }
        });

        tblServices.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        tblServices.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Service ID", "Service Name", "Service Price", "Service Duration (in mins.)", "Service Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblServices.setFillsViewportHeight(true);
        tblServices.setRowHeight(40);
        tblServices.setShowGrid(true);
        tblServices.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblServices);
        if (tblServices.getColumnModel().getColumnCount() > 0) {
            tblServices.getColumnModel().getColumn(0).setMinWidth(0);
            tblServices.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblServices.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
                    .addComponent(serviceSearchField))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(serviceSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ServiceViewButton.setBackground(new java.awt.Color(204, 204, 204));
        ServiceViewButton.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        ServiceViewButton.setText("Go Back");
        ServiceViewButton.addActionListener(this::ServiceViewButtonActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ServiceViewButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(RestoreServiceButton)))))
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(RestoreServiceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ServiceViewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RestoreServiceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RestoreServiceButtonActionPerformed
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this service?", 
            "Confirm Delete", 
            JOptionPane.YES_OPTION,
            JOptionPane.WARNING_MESSAGE
            );
            
            if(choice == JOptionPane.YES_OPTION){
                try{
                    int selectedRow = tblServices.getSelectedRow();
                    String serviceID = tblServices.getValueAt(selectedRow, 0).toString();
                    String message = control.RestoreServiceByID(serviceID);
                    JOptionPane.showMessageDialog(this, message);
                    DeletedServicesView.getInstance().loadServicesToTable();
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(this, "No service is selected to be restored");
                }
            }
    }//GEN-LAST:event_RestoreServiceButtonActionPerformed

    private void serviceSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceSearchFieldActionPerformed
        
    }//GEN-LAST:event_serviceSearchFieldActionPerformed

    private void serviceSearchFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_serviceSearchFieldKeyTyped
        
    }//GEN-LAST:event_serviceSearchFieldKeyTyped

    private void ServiceViewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ServiceViewButtonActionPerformed
        ServiceView dialog = new ServiceView();
        dialog.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ServiceViewButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new DeletedServicesView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton RestoreServiceButton;
    private javax.swing.JButton ServiceViewButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField serviceSearchField;
    private javax.swing.JTable tblServices;
    // End of variables declaration//GEN-END:variables
}
