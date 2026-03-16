package Views.ServiceManagement;
import java.util.List;
import Model.*;
import Controllers.ServiceControllers;
import renderer.ServicesRenderer.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import Views.Dashboard.*;

public class ServiceView extends javax.swing.JFrame {
    public static ServiceView instance;
    private static final ServiceControllers control = new ServiceControllers();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ServiceView.class.getName());
    
    public final void loadServicesToTable() {
        List<Services> services = control.ListOfAllServices();
    
        DefaultTableModel model = (DefaultTableModel) tblServices.getModel();
        model.setRowCount(0); // clear previous data

        for (Services s : services) {
            model.addRow(new Object[]{
                s.getServiceID(),
                s.getServiceName(),
                s.getPrice(),
                s.getDurationMinutes(),
                s.getStatus(),
                "Edit/Delete" // placeholder for actions
            });
        }
    
    
}
    public final void loadServicesToTable(String text) {
        List<Services> services = control.ListOfAllServices(text);

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
                "Edit/Delete" // placeholder for actions
            });
        }
    }
    public static ServiceView getInstance(){
        return instance;
    }
    
    
    public ServiceView() {
        initComponents();
        this.setLocationRelativeTo(null);
        instance = this;
        //Displays Values From the Database
        loadServicesToTable();
        tblServices.getColumn("Actions").setCellRenderer(new ServicesActionRenderer());
        tblServices.getColumn("Actions").setCellEditor(new ServicesActionEditor(tblServices));
        
       serviceSearchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e){
                String searchfieldtext = serviceSearchField.getText();
                ServiceView.getInstance().loadServicesToTable(searchfieldtext);
            }
            
            @Override
            public void changedUpdate(DocumentEvent e){
                String searchfieldtext = serviceSearchField.getText();
                ServiceView.getInstance().loadServicesToTable(searchfieldtext);
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                String searchfieldtext = serviceSearchField.getText();
                ServiceView.getInstance().loadServicesToTable(searchfieldtext);
            }
        });
      
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        AddServiceButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        serviceSearchField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblServices = new javax.swing.JTable();
        DeletedServicesButton = new javax.swing.JButton();
        ServiceViewButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 224, 227));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 24)); // NOI18N
        jLabel1.setText("Service Management");

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel2.setText("Manage your services here");

        AddServiceButton.setBackground(new java.awt.Color(190, 52, 85));
        AddServiceButton.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        AddServiceButton.setForeground(new java.awt.Color(255, 224, 227));
        AddServiceButton.setText("+   Add");
        AddServiceButton.setFocusPainted(false);
        AddServiceButton.setFocusable(false);
        AddServiceButton.addActionListener(this::AddServiceButtonActionPerformed);

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
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Service ID", "Service Name", "Service Price", "Service Duration (in mins.)", "Service Status", "Actions"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
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

        DeletedServicesButton.setBackground(new java.awt.Color(140, 38, 62));
        DeletedServicesButton.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        DeletedServicesButton.setForeground(new java.awt.Color(255, 224, 227));
        DeletedServicesButton.setText("View Deleted Services");
        DeletedServicesButton.setFocusPainted(false);
        DeletedServicesButton.setFocusable(false);
        DeletedServicesButton.addActionListener(this::DeletedServicesButtonActionPerformed);

        ServiceViewButton.setBackground(new java.awt.Color(219, 158, 154));
        ServiceViewButton.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        ServiceViewButton.setForeground(new java.awt.Color(255, 224, 227));
        ServiceViewButton.setText("Go Back");
        ServiceViewButton.setFocusPainted(false);
        ServiceViewButton.setFocusable(false);
        ServiceViewButton.addActionListener(this::ServiceViewButtonActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ServiceViewButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DeletedServicesButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AddServiceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddServiceButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DeletedServicesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ServiceViewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
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

    private void AddServiceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddServiceButtonActionPerformed
        AddServiceView dialog = new AddServiceView();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }//GEN-LAST:event_AddServiceButtonActionPerformed

    private void serviceSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceSearchFieldActionPerformed
        
    }//GEN-LAST:event_serviceSearchFieldActionPerformed

    private void serviceSearchFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_serviceSearchFieldKeyTyped
        
    }//GEN-LAST:event_serviceSearchFieldKeyTyped

    private void DeletedServicesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeletedServicesButtonActionPerformed
        DeletedServicesView dialog = new DeletedServicesView();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_DeletedServicesButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        AdminDashBoardView dialog = new AdminDashBoardView();
        dialog.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

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
        java.awt.EventQueue.invokeLater(() -> new ServiceView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddServiceButton;
    private javax.swing.JButton DeletedServicesButton;
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
