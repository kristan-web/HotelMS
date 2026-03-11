package Views.CustomerManagement;
import renderer.CutomerRenderer.*;
import java.util.List;
import Model.Customers;
import Controllers.CustomerControllers;
import javax.swing.event.*;
//import javax.swing.event.*;

public class CustomersView extends javax.swing.JFrame {   
    public static CustomersView instance;
    public static CustomerControllers control = new CustomerControllers();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CustomersView.class.getName());
    
    public final void LoadCustomerDetails(){     
        List<Customers> customerList = control.ListOfAllCustomers(); 
        
        
        javax.swing.table.DefaultTableModel model = 
        (javax.swing.table.DefaultTableModel) tblCustomers.getModel();
        model.setRowCount(0); // clear previous data

        for (Customers c : customerList) {
            model.addRow(new Object[]{
                c.getCustomer_id(),
                c.getFirst_name(),
                c.getLast_name(),
                c.getPhone_number(),
                c.getEmail(),
                c.getStatus(), 
                "Edit/Delete" // placeholder for actions
            });
        }
    }
    
    public final void LoadCustomerDetails(String searchfield){     
        List<Customers> customerList = control.ListOfAllCustomers(searchfield); 
        
        
        javax.swing.table.DefaultTableModel model = 
        (javax.swing.table.DefaultTableModel) tblCustomers.getModel();
        model.setRowCount(0); // clear previous data

        for (Customers c : customerList) {
            model.addRow(new Object[]{
                c.getCustomer_id(),
                c.getFirst_name(),
                c.getLast_name(),
                c.getPhone_number(),
                c.getEmail(),
                c.getStatus(), 
                "Edit/Delete" // placeholder for actions
            });
        }
    }
    
    
    public static CustomersView getInstance(){
        return instance;
    }
    
    
    // =========================== CONSTRUCTOR ===========================
    public CustomersView() {
        initComponents();
        instance = this;
        LoadCustomerDetails();
        tblCustomers.getColumn("Actions").setCellRenderer(new CustomerActionRenderer());
        tblCustomers.getColumn("Actions").setCellEditor(new CustomerActionEditor(tblCustomers));
        
        CustomerSearchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e){
                /*
                    CALL CONTROLLER FUNCTION
                    PASS SEARCH FIELD TEXT AS ARGUMENT
                    CALL LOAD SERVICES
                */
                String searchfieldtext = CustomerSearchField.getText();
                CustomersView.getInstance().LoadCustomerDetails(searchfieldtext);
            }
            
            @Override
            public void changedUpdate(DocumentEvent e){
                /*
                    CALL CONTROLLER FUNCTION
                    PASS SEARCH FIELD TEXT AS ARGUMENT
                    CALL LOAD SERVICES

                */
                String searchfieldtext = CustomerSearchField.getText();
                CustomersView.getInstance().LoadCustomerDetails(searchfieldtext);
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                /*
                    CALL CONTROLLER FUNCTION
                    PASS SEARCH FIELD TEXT AS ARGUMENT
                    CALL LOAD SERVICES  
                */
                String searchfieldtext = CustomerSearchField.getText();
                CustomersView.getInstance().LoadCustomerDetails(searchfieldtext);
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
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 224, 227));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 568));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(47, 32, 56));
        jLabel1.setText("Customer Management");

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(53, 42, 44));
        jLabel2.setText("Manage your customer database");

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
                "Id", "First Name", "Last Name", "Contact", "Email", "Status", "Actions"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, true
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
                    .addComponent(jScrollPane2))
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

        jButton1.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        jButton1.setText("+   Add");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
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
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AddCustomerView dialog = new AddCustomerView(this, true);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new CustomersView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CustomerSearchField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblCustomers;
    // End of variables declaration//GEN-END:variables
}
