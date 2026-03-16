package Views.GuestManagement;
import java.util.List;
import Model.Guests;
import Controllers.GuestControllers;
import javax.swing.event.*;
import javax.swing.JOptionPane;

public class DeletedGuestsView extends javax.swing.JFrame {   
    public static DeletedGuestsView instance;
    public static GuestControllers control = new GuestControllers();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DeletedGuestsView.class.getName());
    
    public final void LoadCustomerDetails(){     
        List<Guests> customerList = control.ListOfAllDeletedGuests(); 
        
        
        javax.swing.table.DefaultTableModel model = 
        (javax.swing.table.DefaultTableModel) tblCustomers.getModel();
        model.setRowCount(0); // clear previous data

        for (Guests c : customerList) {
            model.addRow(new Object[]{
                c.getGuest_id(),
                c.getFirst_name(),
                c.getLast_name(),
                c.getPhone(),
                c.getEmail(),
                c.getAddress(),
                c.getStatus()
            });
        }
    }
    
    public final void LoadCustomerDetails(String searchfield){     
        List<Guests> customerList = control.ListOfAllDeletedGuests(searchfield); 
        
        
        javax.swing.table.DefaultTableModel model = 
        (javax.swing.table.DefaultTableModel) tblCustomers.getModel();
        model.setRowCount(0); // clear previous data

        for (Guests c : customerList) {
            model.addRow(new Object[]{
                c.getGuest_id(),
                c.getFirst_name(),
                c.getLast_name(),
                c.getPhone(),
                c.getEmail(),
                c.getStatus()
            });
        }
    }
    
    
    public static DeletedGuestsView getInstance(){
        return instance;
    }
    
    
    // =========================== CONSTRUCTOR ===========================
    public DeletedGuestsView() {
        initComponents();
        this.setLocationRelativeTo(null);
        instance = this;
        LoadCustomerDetails();
        
        CustomerSearchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e){
                /*
                    CALL CONTROLLER FUNCTION
                    PASS SEARCH FIELD TEXT AS ARGUMENT
                    CALL LOAD SERVICES
                */
                String searchfieldtext = CustomerSearchField.getText();
                DeletedGuestsView.getInstance().LoadCustomerDetails(searchfieldtext);
            }
            
            @Override
            public void changedUpdate(DocumentEvent e){
                /*
                    CALL CONTROLLER FUNCTION
                    PASS SEARCH FIELD TEXT AS ARGUMENT
                    CALL LOAD SERVICES

                */
                String searchfieldtext = CustomerSearchField.getText();
                DeletedGuestsView.getInstance().LoadCustomerDetails(searchfieldtext);
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                /*
                    CALL CONTROLLER FUNCTION
                    PASS SEARCH FIELD TEXT AS ARGUMENT
                    CALL LOAD SERVICES  
                */
                String searchfieldtext = CustomerSearchField.getText();
                DeletedGuestsView.getInstance().LoadCustomerDetails(searchfieldtext);
            }
        });
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        CustomerSearchField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCustomers = new javax.swing.JTable();
        RestoreCustomerButton = new javax.swing.JButton();
        BackToCustomerViewButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 224, 227));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 568));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(47, 32, 56));
        jLabel1.setText("Deleted Guests");

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(53, 42, 44));
        jLabel2.setText("List of Deleted Guests");

        jPanel2.setBackground(new java.awt.Color(243, 223, 220));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 194, 189), 2));
        jPanel2.setForeground(new java.awt.Color(255, 224, 227));

        CustomerSearchField.setBackground(new java.awt.Color(246, 238, 238));
        CustomerSearchField.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        CustomerSearchField.setSelectionColor(new java.awt.Color(210, 90, 119));
        CustomerSearchField.addActionListener(this::CustomerSearchFieldActionPerformed);

        tblCustomers.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        tblCustomers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "First Name", "Last Name", "Contact", "Email", "Address", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCustomers.setFillsViewportHeight(true);
        tblCustomers.setRowHeight(40);
        tblCustomers.setShowGrid(true);
        tblCustomers.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblCustomers);
        if (tblCustomers.getColumnModel().getColumnCount() > 0) {
            tblCustomers.getColumnModel().getColumn(0).setMinWidth(0);
            tblCustomers.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblCustomers.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CustomerSearchField)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CustomerSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        RestoreCustomerButton.setBackground(new java.awt.Color(47, 32, 56));
        RestoreCustomerButton.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        RestoreCustomerButton.setForeground(new java.awt.Color(255, 224, 227));
        RestoreCustomerButton.setText("Restore Guest");
        RestoreCustomerButton.setFocusPainted(false);
        RestoreCustomerButton.setFocusable(false);
        RestoreCustomerButton.addActionListener(this::RestoreCustomerButtonActionPerformed);

        BackToCustomerViewButton.setBackground(new java.awt.Color(190, 52, 85));
        BackToCustomerViewButton.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        BackToCustomerViewButton.setForeground(new java.awt.Color(255, 224, 227));
        BackToCustomerViewButton.setText("Back");
        BackToCustomerViewButton.setFocusPainted(false);
        BackToCustomerViewButton.setFocusable(false);
        BackToCustomerViewButton.addActionListener(this::BackToCustomerViewButtonActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BackToCustomerViewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(RestoreCustomerButton)))
                        .addGap(15, 15, 15))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(RestoreCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BackToCustomerViewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CustomerSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomerSearchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CustomerSearchFieldActionPerformed

    private void RestoreCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RestoreCustomerButtonActionPerformed
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to restore this guest?", 
        "Confirm Delete", 
        JOptionPane.YES_OPTION,
        JOptionPane.WARNING_MESSAGE
        );

        if(choice == JOptionPane.YES_OPTION){
            try{
               int selectedRow = tblCustomers.getSelectedRow();
                String customerID = tblCustomers.getValueAt(selectedRow, 0).toString();
                
                if(control.RestoreGuestByID(customerID)){
                    DeletedGuestsView.getInstance().LoadCustomerDetails(); 
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(this, "No guest is selected to be restored");
            }
        }
        
    }//GEN-LAST:event_RestoreCustomerButtonActionPerformed

    private void BackToCustomerViewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackToCustomerViewButtonActionPerformed
        GuestsView dialog = new GuestsView();
        dialog.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BackToCustomerViewButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        GuestsView dialog = new GuestsView();
        dialog.setVisible(true);
        this.setLocationRelativeTo(null);
        this. dispose();
    }//GEN-LAST:event_formWindowClosing

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
        java.awt.EventQueue.invokeLater(() -> new DeletedGuestsView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackToCustomerViewButton;
    private javax.swing.JTextField CustomerSearchField;
    private javax.swing.JButton RestoreCustomerButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblCustomers;
    // End of variables declaration//GEN-END:variables
}
