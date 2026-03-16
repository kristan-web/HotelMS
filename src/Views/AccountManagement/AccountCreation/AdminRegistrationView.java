package Views.AccountManagement.AccountCreation;
import Controllers.UserControllers;
import Views.AccountManagement.AccountAdministration.*;
import javax.swing.JOptionPane;
import java.awt.*;
import Model.Users;
import javax.swing.ImageIcon;
        
public class AdminRegistrationView extends javax.swing.JFrame {
    private String source;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdminRegistrationView.class.getName());
    private static final UserControllers control = new UserControllers();
    
    public AdminRegistrationView(String source) {
        initComponents();
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/resources/admin_logo.jpg"));
        Image scaled1 = icon1.getImage().getScaledInstance(lblIcon1.getWidth(), lblIcon1.getHeight(), Image.SCALE_SMOOTH);
        lblIcon1.setIcon(new ImageIcon(scaled1));
        
        this.source = source;
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        LastNameField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        FirstNameField = new javax.swing.JTextField();
        EmailField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        PhoneField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        RegisterAdminButton = new javax.swing.JButton();
        LoginFormButton = new javax.swing.JLabel();
        PasswordField = new javax.swing.JPasswordField();
        ConfPassField = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        lblIcon1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(47, 32, 56));
        jPanel1.setMinimumSize(new java.awt.Dimension(500, 500));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 500));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 224, 227));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Admin Registration");
        jLabel2.setAlignmentY(0.0F);
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        LastNameField.setBackground(new java.awt.Color(255, 239, 241));
        LastNameField.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        LastNameField.setForeground(new java.awt.Color(48, 24, 29));
        LastNameField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        LastNameField.setMinimumSize(new java.awt.Dimension(180, 27));
        LastNameField.setPreferredSize(new java.awt.Dimension(180, 27));
        LastNameField.setSelectionColor(new java.awt.Color(210, 90, 119));
        LastNameField.addActionListener(this::LastNameFieldActionPerformed);

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 224, 227));
        jLabel4.setText("First Name:");

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 224, 227));
        jLabel5.setText("Last Name: ");

        FirstNameField.setBackground(new java.awt.Color(255, 239, 241));
        FirstNameField.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        FirstNameField.setForeground(new java.awt.Color(48, 24, 29));
        FirstNameField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        FirstNameField.setMinimumSize(new java.awt.Dimension(180, 30));
        FirstNameField.setPreferredSize(new java.awt.Dimension(180, 30));
        FirstNameField.setSelectionColor(new java.awt.Color(210, 90, 119));
        FirstNameField.addActionListener(this::FirstNameFieldActionPerformed);

        EmailField.setBackground(new java.awt.Color(255, 239, 241));
        EmailField.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        EmailField.setForeground(new java.awt.Color(48, 24, 29));
        EmailField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        EmailField.setMinimumSize(new java.awt.Dimension(440, 27));
        EmailField.setPreferredSize(new java.awt.Dimension(440, 27));
        EmailField.setSelectionColor(new java.awt.Color(210, 90, 119));
        EmailField.addActionListener(this::EmailFieldActionPerformed);

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 224, 227));
        jLabel6.setText("Email Address:");

        PhoneField.setBackground(new java.awt.Color(255, 239, 241));
        PhoneField.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        PhoneField.setForeground(new java.awt.Color(48, 24, 29));
        PhoneField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        PhoneField.setMinimumSize(new java.awt.Dimension(440, 27));
        PhoneField.setPreferredSize(new java.awt.Dimension(440, 27));
        PhoneField.setSelectionColor(new java.awt.Color(210, 90, 119));
        PhoneField.addActionListener(this::PhoneFieldActionPerformed);

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 224, 227));
        jLabel7.setText("Contact Number:");

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 224, 227));
        jLabel8.setText("Password:");

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 224, 227));
        jLabel9.setText("Confirm Password:");

        RegisterAdminButton.setBackground(new java.awt.Color(255, 224, 227));
        RegisterAdminButton.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        RegisterAdminButton.setForeground(new java.awt.Color(47, 32, 56));
        RegisterAdminButton.setText("Register");
        RegisterAdminButton.setAlignmentY(0.0F);
        RegisterAdminButton.setBorderPainted(false);
        RegisterAdminButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RegisterAdminButton.setFocusPainted(false);
        RegisterAdminButton.setFocusable(false);
        RegisterAdminButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        RegisterAdminButton.setMinimumSize(new java.awt.Dimension(160, 35));
        RegisterAdminButton.setPreferredSize(new java.awt.Dimension(160, 35));
        RegisterAdminButton.addActionListener(this::RegisterAdminButtonActionPerformed);

        LoginFormButton.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        LoginFormButton.setForeground(new java.awt.Color(255, 224, 227));
        LoginFormButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LoginFormButton.setText("Login Here");
        LoginFormButton.setAlignmentY(0.0F);
        LoginFormButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LoginFormButton.setFocusable(false);
        LoginFormButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        LoginFormButton.setMinimumSize(new java.awt.Dimension(80, 20));
        LoginFormButton.setPreferredSize(new java.awt.Dimension(80, 20));
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

        PasswordField.setBackground(new java.awt.Color(255, 239, 241));
        PasswordField.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        PasswordField.setForeground(new java.awt.Color(48, 24, 29));
        PasswordField.setMinimumSize(new java.awt.Dimension(180, 27));
        PasswordField.setPreferredSize(new java.awt.Dimension(180, 27));
        PasswordField.setSelectionColor(new java.awt.Color(210, 90, 119));

        ConfPassField.setBackground(new java.awt.Color(255, 239, 241));
        ConfPassField.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        ConfPassField.setForeground(new java.awt.Color(48, 24, 29));
        ConfPassField.setMinimumSize(new java.awt.Dimension(180, 27));
        ConfPassField.setPreferredSize(new java.awt.Dimension(180, 27));
        ConfPassField.setSelectionColor(new java.awt.Color(210, 90, 119));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(140, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(140, 140, 140))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LoginFormButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(180, 180, 180))
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(FirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(EmailField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(80, 80, 80)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ConfPassField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(PhoneField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RegisterAdminButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {ConfPassField, LastNameField});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel2)
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(5, 5, 5)
                .addComponent(EmailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel7)
                .addGap(5, 5, 5)
                .addComponent(PhoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ConfPassField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(RegisterAdminButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LoginFormButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {ConfPassField, EmailField, FirstNameField, LastNameField, PasswordField, PhoneField});

        jPanel2.setBackground(new java.awt.Color(255, 224, 227));

        jLabel3.setBackground(new java.awt.Color(255, 224, 227));
        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(47, 32, 56));
        jLabel3.setText("Hotel MS");

        jSeparator3.setForeground(new java.awt.Color(47, 32, 56));

        jLabel10.setBackground(new java.awt.Color(255, 224, 227));
        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(47, 32, 56));
        jLabel10.setText("Registration Form for Admin.");

        jButton1.setBackground(new java.awt.Color(190, 52, 85));
        jButton1.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 224, 227));
        jButton1.setText("Go Back");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblIcon1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(106, 106, 106))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(lblIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LastNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LastNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LastNameFieldActionPerformed

    private void FirstNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FirstNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FirstNameFieldActionPerformed

    private void EmailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailFieldActionPerformed

    private void PhoneFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhoneFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PhoneFieldActionPerformed

    private void RegisterAdminButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterAdminButtonActionPerformed
        Users user = new Users();
        
        user.setFirst_name(FirstNameField.getText().trim());
        user.setLast_name(LastNameField.getText().trim());
        user.setEmail(EmailField.getText().trim());
        user.setPhone(PhoneField.getText().trim());
        user.setPassword(new String(PasswordField.getPassword()));
        user.setConfpass(new String(ConfPassField.getPassword()));
        
        if(control.ValidateAndRegisterAdminAccount(user)){
            if(source.equals("setup")){
                AdminLoginView dialog = new AdminLoginView(source);
                dialog.setVisible(true);
                this.dispose();
            }
            else if(source.equals("dashboard")){
                StaffAndAdminAccountView dialog = new StaffAndAdminAccountView();
                dialog.setVisible(true);
                this.dispose();
            }
        }
    }//GEN-LAST:event_RegisterAdminButtonActionPerformed

    private void LoginFormButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginFormButtonMouseEntered
        Font f = LoginFormButton.getFont();
        LoginFormButton.setFont(new Font(f.getName(), Font.BOLD, f.getSize()));
    }//GEN-LAST:event_LoginFormButtonMouseEntered

    private void LoginFormButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginFormButtonMouseExited
        Font f = LoginFormButton.getFont();
        LoginFormButton.setFont(new Font(f.getName(), Font.PLAIN, f.getSize()));
    }//GEN-LAST:event_LoginFormButtonMouseExited

    private void LoginFormButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginFormButtonMouseClicked
        if(source.equals("setup")){
            AdminLoginView dialog = new AdminLoginView(source);
            dialog.setVisible(true);
            this.dispose();
        }
        else if(source.equals("dashboard")){
            JOptionPane.showMessageDialog(null, "You can't login from dashboard.");
            StaffAndAdminAccountView dialog = new StaffAndAdminAccountView();
            dialog.setVisible(true);
            this.dispose();
        }
        
    }//GEN-LAST:event_LoginFormButtonMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if(source.equals("setup")){
            this.dispose();
        }
        else if(source.equals("dashboard")){
            StaffAndAdminAccountView dialog = new StaffAndAdminAccountView();
            dialog.setVisible(true);
            this.dispose();
        }
        
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField ConfPassField;
    private javax.swing.JTextField EmailField;
    private javax.swing.JTextField FirstNameField;
    private javax.swing.JTextField LastNameField;
    private javax.swing.JLabel LoginFormButton;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JTextField PhoneField;
    private javax.swing.JButton RegisterAdminButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblIcon1;
    // End of variables declaration//GEN-END:variables
}
