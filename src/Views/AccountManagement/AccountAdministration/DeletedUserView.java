package Views.AccountManagement.AccountAdministration;
import java.util.List;
import Model.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import Views.Dashboard.*;
import javax.swing.JOptionPane;
import Controllers.UserControllers;

public class DeletedUserView extends javax.swing.JFrame {
    public static DeletedUserView instance;
    public static final UserControllers control = new UserControllers();
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DeletedUserView.class.getName());
    
    public final void LoadUsersToTable() {
        List<Users> user = control.ListOfAllDeletedUsers();
    
        DefaultTableModel model = (DefaultTableModel) tblUsers.getModel();
        model.setRowCount(0); // clear previous data

        for (Users u : user) {
            model.addRow(new Object[]{
                u.getUser_id(),
                u.getFirst_name(),
                u.getLast_name(),
                u.getPhone(),
                u.getEmail(),
                u.getRole()
            });
        }
    
    
}
    public final void LoadUsersToTable(String text) {
        List<Users> user = control.ListOfAllDeletedUsers(text);

        javax.swing.table.DefaultTableModel model = 
            (javax.swing.table.DefaultTableModel) tblUsers.getModel();
        model.setRowCount(0); // clear previous data

        for (Users u : user) {
            model.addRow(new Object[]{
                u.getUser_id(),
                u.getFirst_name(),
                u.getLast_name(),
                u.getPhone(),
                u.getEmail()
            });
        }
    }
    public static DeletedUserView getInstance(){
        return instance;
    }
    
    
    public DeletedUserView() {
        initComponents();
        this.setLocationRelativeTo(null);
        instance = this;
        //Displays Values From the Database
        DeletedUserView.this.LoadUsersToTable();
        
       serviceSearchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e){
                String searchfieldtext = serviceSearchField.getText();
                DeletedUserView.getInstance().LoadUsersToTable(searchfieldtext);
            }
            
            @Override
            public void changedUpdate(DocumentEvent e){
                String searchfieldtext = serviceSearchField.getText();
                DeletedUserView.getInstance().LoadUsersToTable(searchfieldtext);
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                String searchfieldtext = serviceSearchField.getText();
                DeletedUserView.getInstance().LoadUsersToTable(searchfieldtext);
            }
        });
      
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        BackToMainUserView = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        serviceSearchField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();
        RestoreButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 224, 227));

        BackToMainUserView.setBackground(new java.awt.Color(204, 204, 204));
        BackToMainUserView.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        BackToMainUserView.setText("Go Back");
        BackToMainUserView.setFocusPainted(false);
        BackToMainUserView.setFocusable(false);
        BackToMainUserView.addActionListener(this::BackToMainUserViewActionPerformed);

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 24)); // NOI18N
        jLabel1.setText("Deleted Users");

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel2.setText("Manage deleted account here");

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

        tblUsers.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "User ID", "First Name", "Last Name", "Contact No.", "Email", "Role"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsers.setFillsViewportHeight(true);
        tblUsers.setRowHeight(40);
        tblUsers.setShowGrid(true);
        tblUsers.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblUsers);
        if (tblUsers.getColumnModel().getColumnCount() > 0) {
            tblUsers.getColumnModel().getColumn(0).setMinWidth(0);
            tblUsers.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblUsers.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(serviceSearchField, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(serviceSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        RestoreButton.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        RestoreButton.setText("Restore User");
        RestoreButton.setFocusPainted(false);
        RestoreButton.setFocusable(false);
        RestoreButton.setMinimumSize(new java.awt.Dimension(118, 27));
        RestoreButton.setPreferredSize(new java.awt.Dimension(118, 27));
        RestoreButton.addActionListener(this::RestoreButtonActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BackToMainUserView)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(RestoreButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addComponent(RestoreButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BackToMainUserView, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
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

    private void serviceSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceSearchFieldActionPerformed
        
    }//GEN-LAST:event_serviceSearchFieldActionPerformed

    private void serviceSearchFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_serviceSearchFieldKeyTyped
        
    }//GEN-LAST:event_serviceSearchFieldKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        StaffAndAdminAccountView dialog = new StaffAndAdminAccountView();
        dialog.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void BackToMainUserViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackToMainUserViewActionPerformed
        StaffAndAdminAccountView dialog = new StaffAndAdminAccountView();
        dialog.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BackToMainUserViewActionPerformed

    private void RestoreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RestoreButtonActionPerformed
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to restore this user?", 
        "Confirm Delete", 
        JOptionPane.YES_OPTION,
        JOptionPane.WARNING_MESSAGE
        );

        if(choice == JOptionPane.YES_OPTION){
            try{
               int selectedRow = tblUsers.getSelectedRow();
                String userID = tblUsers.getValueAt(selectedRow, 0).toString();
                
                if(control.RestoreUserByID(userID)){
                    DeletedUserView.getInstance().LoadUsersToTable(); 
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(this, "No user is selected to be restored");
            }
        }
    }//GEN-LAST:event_RestoreButtonActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new DeletedUserView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackToMainUserView;
    private javax.swing.JButton RestoreButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField serviceSearchField;
    private javax.swing.JTable tblUsers;
    // End of variables declaration//GEN-END:variables
}
