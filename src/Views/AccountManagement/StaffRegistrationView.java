package Views.AccountManagement;
import java.awt.*;
import Controllers.UserControllers;
import javax.swing.JOptionPane;
import Model.Users;
 
public class StaffRegistrationView extends javax.swing.JFrame {
    private static final UserControllers control = new UserControllers();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(StaffRegistrationView.class.getName());

    public StaffRegistrationView() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        FirstNameField = new javax.swing.JTextField();
        LastNameField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        StaffRegistrationButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        PhoneField = new javax.swing.JTextField();
        EmailField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        PasswordField = new javax.swing.JPasswordField();
        ConfPassField = new javax.swing.JPasswordField();
        LoginFormButton = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(190, 52, 85));

        jPanel3.setBackground(new java.awt.Color(255, 224, 227));
        jPanel3.setMinimumSize(new java.awt.Dimension(490, 524));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(48, 24, 29));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Staff Registration");

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(48, 24, 29));
        jLabel3.setText("First Name:");

        FirstNameField.setBackground(new java.awt.Color(255, 239, 241));
        FirstNameField.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        FirstNameField.setForeground(new java.awt.Color(48, 24, 29));
        FirstNameField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        FirstNameField.setMinimumSize(new java.awt.Dimension(160, 30));
        FirstNameField.setPreferredSize(new java.awt.Dimension(160, 30));
        FirstNameField.setSelectionColor(new java.awt.Color(210, 90, 119));
        FirstNameField.addActionListener(this::FirstNameFieldActionPerformed);

        LastNameField.setBackground(new java.awt.Color(255, 239, 241));
        LastNameField.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        LastNameField.setForeground(new java.awt.Color(48, 24, 29));
        LastNameField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        LastNameField.setSelectionColor(new java.awt.Color(210, 90, 119));
        LastNameField.addActionListener(this::LastNameFieldActionPerformed);

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(48, 24, 29));
        jLabel5.setText("Contact Number:");

        StaffRegistrationButton.setBackground(new java.awt.Color(190, 52, 85));
        StaffRegistrationButton.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        StaffRegistrationButton.setForeground(new java.awt.Color(255, 224, 227));
        StaffRegistrationButton.setText("Register");
        StaffRegistrationButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        StaffRegistrationButton.setMinimumSize(new java.awt.Dimension(190, 42));
        StaffRegistrationButton.setPreferredSize(new java.awt.Dimension(190, 42));
        StaffRegistrationButton.addActionListener(this::StaffRegistrationButtonActionPerformed);

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(48, 24, 29));
        jLabel9.setText("Last Name:");

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(48, 24, 29));
        jLabel7.setText("Email:");

        PhoneField.setBackground(new java.awt.Color(255, 239, 241));
        PhoneField.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        PhoneField.setForeground(new java.awt.Color(48, 24, 29));
        PhoneField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        PhoneField.setSelectionColor(new java.awt.Color(210, 90, 119));
        PhoneField.addActionListener(this::PhoneFieldActionPerformed);

        EmailField.setBackground(new java.awt.Color(255, 239, 241));
        EmailField.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        EmailField.setForeground(new java.awt.Color(48, 24, 29));
        EmailField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        EmailField.setMinimumSize(new java.awt.Dimension(380, 26));
        EmailField.setPreferredSize(new java.awt.Dimension(380, 26));
        EmailField.setSelectionColor(new java.awt.Color(210, 90, 119));
        EmailField.addActionListener(this::EmailFieldActionPerformed);

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(48, 24, 29));
        jLabel4.setText("Password:");

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(48, 24, 29));
        jLabel8.setText("Confirm Password:");

        PasswordField.setMinimumSize(new java.awt.Dimension(160, 30));
        PasswordField.setPreferredSize(new java.awt.Dimension(160, 30));

        ConfPassField.setMinimumSize(new java.awt.Dimension(160, 30));
        ConfPassField.setPreferredSize(new java.awt.Dimension(160, 30));

        LoginFormButton.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        LoginFormButton.setForeground(new java.awt.Color(48, 24, 29));
        LoginFormButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LoginFormButton.setText("Login Here");
        LoginFormButton.setAlignmentY(0.0F);
        LoginFormButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LoginFormButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        LoginFormButton.setMinimumSize(new java.awt.Dimension(90, 20));
        LoginFormButton.setPreferredSize(new java.awt.Dimension(90, 20));
        LoginFormButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoginFormButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LoginFormButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LoginFormButtonMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(StaffRegistrationButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(150, 150, 150))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(FirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(LastNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(60, 60, 60)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(EmailField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PhoneField, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ConfPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(LoginFormButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {FirstNameField, LastNameField});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {EmailField, PhoneField});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EmailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PhoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ConfPassField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(StaffRegistrationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LoginFormButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {EmailField, FirstNameField, LastNameField, PhoneField});

        jLabel2.setBackground(new java.awt.Color(255, 224, 227));
        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 224, 227));
        jLabel2.setText("Hotel MS");

        jSeparator1.setForeground(new java.awt.Color(255, 224, 227));

        jLabel6.setBackground(new java.awt.Color(255, 224, 227));
        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 224, 227));
        jLabel6.setText("Registration Form for Staffs.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void StaffRegistrationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StaffRegistrationButtonActionPerformed
        Users user = new Users();
        
        user.setFirst_name(FirstNameField.getText().trim());
        user.setLast_name(LastNameField.getText().trim());
        user.setEmail(EmailField.getText().trim());
        user.setPhone(PhoneField.getText().trim());
        user.setPassword(new String(PasswordField.getPassword()));
        user.setConfpass(new String(ConfPassField.getPassword()));
        
        if(control.ValidateAndRegisterStaffAccount(user)){
            StaffLoginView dialog = new StaffLoginView();
            dialog.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_StaffRegistrationButtonActionPerformed

    private void LastNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LastNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LastNameFieldActionPerformed

    private void FirstNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FirstNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FirstNameFieldActionPerformed

    private void PhoneFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhoneFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PhoneFieldActionPerformed

    private void EmailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailFieldActionPerformed

    private void LoginFormButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginFormButtonMouseClicked
        StaffLoginView dialog = new StaffLoginView();
        dialog.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_LoginFormButtonMouseClicked

    private void LoginFormButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginFormButtonMouseEntered
        Font f = LoginFormButton.getFont();
        LoginFormButton.setFont(new Font(f.getName(), Font.BOLD, f.getSize()));
    }//GEN-LAST:event_LoginFormButtonMouseEntered

    private void LoginFormButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginFormButtonMouseExited
        Font f = LoginFormButton.getFont();
        LoginFormButton.setFont(new Font(f.getName(), Font.PLAIN, f.getSize()));
    }//GEN-LAST:event_LoginFormButtonMouseExited

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
        java.awt.EventQueue.invokeLater(() -> new StaffRegistrationView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField ConfPassField;
    private javax.swing.JTextField EmailField;
    private javax.swing.JTextField FirstNameField;
    private javax.swing.JTextField LastNameField;
    private javax.swing.JLabel LoginFormButton;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JTextField PhoneField;
    private javax.swing.JButton StaffRegistrationButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
