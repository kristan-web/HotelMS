package Views.CustomerManagement;
import Model.Customers;
import Controllers.CustomerControllers;
import javax.swing.JOptionPane;

public class EditCustomerView extends javax.swing.JDialog {
    public static CustomerControllers control = new CustomerControllers();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(EditCustomerView.class.getName());
    
    public void loadCustomerData(Customers c) {
        CustomerIDField.setText(String.valueOf(c.getCustomer_id()));
        FirstNameField.setText(c.getFirst_name().trim());
        LastNameField.setText(c.getLast_name().trim());
        EmailField.setText(c.getEmail().trim());
        PhoneField.setText(c.getPhone_number().trim());
        StatusField.setSelectedItem(String.valueOf(c.getStatus()));
    }
    public EditCustomerView() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CustomerIDField = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        CancelCustomerUpdate = new javax.swing.JButton();
        UpdateCustomerButton = new javax.swing.JButton();
        StatusField = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        PhoneField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        EmailField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        FirstNameField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        LastNameField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(47, 32, 56));

        jPanel2.setBackground(new java.awt.Color(255, 224, 227));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(47, 32, 56));
        jLabel1.setText("Edit Customer");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        CancelCustomerUpdate.setBackground(new java.awt.Color(190, 52, 85));
        CancelCustomerUpdate.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        CancelCustomerUpdate.setForeground(new java.awt.Color(255, 224, 227));
        CancelCustomerUpdate.setText("Cancel");
        CancelCustomerUpdate.addActionListener(this::CancelCustomerUpdateActionPerformed);

        UpdateCustomerButton.setBackground(new java.awt.Color(255, 224, 227));
        UpdateCustomerButton.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        UpdateCustomerButton.setForeground(new java.awt.Color(47, 32, 56));
        UpdateCustomerButton.setText("Update");
        UpdateCustomerButton.addActionListener(this::UpdateCustomerButtonActionPerformed);

        StatusField.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        StatusField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 224, 227));
        jLabel6.setText("Status: *");

        PhoneField.setBackground(new java.awt.Color(255, 239, 241));
        PhoneField.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        PhoneField.setForeground(new java.awt.Color(47, 32, 56));
        PhoneField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        PhoneField.setSelectionColor(new java.awt.Color(241, 206, 215));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 224, 227));
        jLabel5.setText("Email Address: *");

        EmailField.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        EmailField.setForeground(new java.awt.Color(47, 32, 56));
        EmailField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        EmailField.setSelectionColor(new java.awt.Color(241, 206, 215));
        EmailField.addActionListener(this::EmailFieldActionPerformed);

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 224, 227));
        jLabel4.setText("Contact Number: *");

        FirstNameField.setBackground(new java.awt.Color(255, 239, 241));
        FirstNameField.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        FirstNameField.setForeground(new java.awt.Color(47, 32, 56));
        FirstNameField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        FirstNameField.setDisabledTextColor(new java.awt.Color(255, 224, 227));
        FirstNameField.setSelectionColor(new java.awt.Color(241, 206, 215));
        FirstNameField.addActionListener(this::FirstNameFieldActionPerformed);

        jLabel3.setBackground(new java.awt.Color(255, 224, 227));
        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 224, 227));
        jLabel3.setText("First Name: *");

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 224, 227));
        jLabel7.setText("Last Name: *");

        LastNameField.setBackground(new java.awt.Color(255, 239, 241));
        LastNameField.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        LastNameField.setForeground(new java.awt.Color(47, 32, 56));
        LastNameField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        LastNameField.setDisabledTextColor(new java.awt.Color(255, 224, 227));
        LastNameField.setSelectionColor(new java.awt.Color(241, 206, 215));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 26, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(StatusField, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(UpdateCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(CancelCustomerUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(PhoneField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(EmailField, javax.swing.GroupLayout.Alignment.LEADING))
                                .addComponent(jLabel6)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EmailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PhoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(StatusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UpdateCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CancelCustomerUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CancelCustomerUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelCustomerUpdateActionPerformed
        this.dispose();
    }//GEN-LAST:event_CancelCustomerUpdateActionPerformed

    private void FirstNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FirstNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FirstNameFieldActionPerformed

    private void UpdateCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateCustomerButtonActionPerformed
        String cstID = CustomerIDField.getText().trim();
        String cstfName = FirstNameField.getText().trim();
        String cstlName = LastNameField.getText().trim();
        String cstEmail = EmailField.getText().trim();
        String cstPhone = PhoneField.getText().trim();
        String cstStatus = StatusField.getSelectedItem().toString().trim();
        
        String message = control.UpdateCustomerDetails(cstID, cstfName, cstlName, cstEmail, cstPhone, cstStatus);
        
        JOptionPane.showMessageDialog(this, message);
        CustomersView.getInstance().LoadCustomerDetails();
        this.dispose();
    }//GEN-LAST:event_UpdateCustomerButtonActionPerformed

    private void EmailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailFieldActionPerformed

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

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                EditCustomerView dialog = new EditCustomerView();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelCustomerUpdate;
    private javax.swing.JTextField CustomerIDField;
    private javax.swing.JTextField EmailField;
    private javax.swing.JTextField FirstNameField;
    private javax.swing.JTextField LastNameField;
    private javax.swing.JTextField PhoneField;
    private javax.swing.JComboBox<String> StatusField;
    private javax.swing.JButton UpdateCustomerButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
