package Views.GuestManagement;
import renderer.GuestRenderer.GuestActionRenderer;
import renderer.GuestRenderer.GuestActionEditor;
import java.util.List;
import Model.Guests;
import Controllers.GuestControllers;
import Views.Dashboard.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import Model.Users;

public class GuestsView extends javax.swing.JFrame {  
    public static GuestsView instance;
    public static GuestControllers control = new GuestControllers();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GuestsView.class.getName());
    
    public final void LoadGuestDetails(){     
        List<Guests> guestList = control.ListOfAllGuests(); 
        
        
        DefaultTableModel model = (DefaultTableModel) tblCustomers.getModel();
        model.setRowCount(0); // clear previous data

        for (Guests c : guestList) {
            model.addRow(new Object[]{
                c.getGuest_id(),
                c.getFirst_name(),
                c.getLast_name(),
                c.getPhone(),
                c.getEmail(),
                c.getAddress(),
                c.getStatus(), 
                "Edit/Delete" // placeholder for actions
            });
        }
    }
    
    public final void LoadGuestDetails(String searchfield){     
        List<Guests> guestList = control.ListOfAllGuests(searchfield); 
        
        
        DefaultTableModel model = (DefaultTableModel) tblCustomers.getModel();
        model.setRowCount(0); // clear previous data

        for (Guests g : guestList) {
            model.addRow(new Object[]{
                g.getGuest_id(),
                g.getFirst_name(),
                g.getLast_name(),
                g.getPhone(),
                g.getEmail(),
                g.getAddress(),
                g.getStatus(), 
                "Edit/Delete" // placeholder for actions
            });
        }
    }
    
    
    public static GuestsView getInstance(){
        return instance;
    }
    
    
    // =========================== CONSTRUCTOR ===========================
    public GuestsView() {
        initComponents();
        instance = this;
        this.setLocationRelativeTo(null);
        GuestsView.this.LoadGuestDetails();
        tblCustomers.getColumn("Actions").setCellRenderer(new GuestActionRenderer());
        tblCustomers.getColumn("Actions").setCellEditor(new GuestActionEditor(tblCustomers));
        
        CustomerSearchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e){
                String searchfieldtext = CustomerSearchField.getText();
                GuestsView.getInstance().LoadGuestDetails(searchfieldtext);
            }
            
            @Override
            public void changedUpdate(DocumentEvent e){
                String searchfieldtext = CustomerSearchField.getText();
                GuestsView.getInstance().LoadGuestDetails(searchfieldtext);
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                String searchfieldtext = CustomerSearchField.getText();
                GuestsView.getInstance().LoadGuestDetails(searchfieldtext);
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
        DeletedServicesButton = new javax.swing.JButton();
        BackToCustomerViewButton = new javax.swing.JButton();
        BackToCustomerViewButton1 = new javax.swing.JButton();
        BackToCustomerViewButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 224, 227));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 568));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(47, 32, 56));
        jLabel1.setText("Guest Management");

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(53, 42, 44));
        jLabel2.setText("Manage your guest database");

        jPanel2.setBackground(new java.awt.Color(243, 223, 220));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 194, 189), 2));
        jPanel2.setForeground(new java.awt.Color(255, 224, 227));

        CustomerSearchField.setBackground(new java.awt.Color(246, 238, 238));
        CustomerSearchField.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        CustomerSearchField.setSelectionColor(new java.awt.Color(210, 90, 119));
        CustomerSearchField.addActionListener(this::CustomerSearchFieldActionPerformed);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tblCustomers.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        tblCustomers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "First Name", "Last Name", "Contact", "Email", "Address", "Status", "Actions"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
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
            tblCustomers.getColumnModel().getColumn(1).setPreferredWidth(120);
            tblCustomers.getColumnModel().getColumn(2).setPreferredWidth(120);
            tblCustomers.getColumnModel().getColumn(3).setPreferredWidth(120);
            tblCustomers.getColumnModel().getColumn(4).setPreferredWidth(120);
            tblCustomers.getColumnModel().getColumn(5).setPreferredWidth(120);
            tblCustomers.getColumnModel().getColumn(6).setPreferredWidth(120);
            tblCustomers.getColumnModel().getColumn(7).setMinWidth(120);
            tblCustomers.getColumnModel().getColumn(7).setPreferredWidth(120);
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

        jButton1.setBackground(new java.awt.Color(47, 32, 56));
        jButton1.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 224, 227));
        jButton1.setText("+   Add");
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.addActionListener(this::jButton1ActionPerformed);

        DeletedServicesButton.setBackground(new java.awt.Color(219, 158, 154));
        DeletedServicesButton.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        DeletedServicesButton.setForeground(new java.awt.Color(255, 224, 227));
        DeletedServicesButton.setText("View Deleted Guests");
        DeletedServicesButton.setFocusPainted(false);
        DeletedServicesButton.setFocusable(false);
        DeletedServicesButton.addActionListener(this::DeletedServicesButtonActionPerformed);

        BackToCustomerViewButton.setBackground(new java.awt.Color(47, 32, 56));
        BackToCustomerViewButton.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        BackToCustomerViewButton.setForeground(new java.awt.Color(255, 224, 227));
        BackToCustomerViewButton.setText("Back");
        BackToCustomerViewButton.setFocusPainted(false);
        BackToCustomerViewButton.setFocusable(false);
        BackToCustomerViewButton.addActionListener(this::BackToCustomerViewButtonActionPerformed);

        BackToCustomerViewButton1.setBackground(new java.awt.Color(47, 32, 56));
        BackToCustomerViewButton1.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        BackToCustomerViewButton1.setForeground(new java.awt.Color(255, 224, 227));
        BackToCustomerViewButton1.setText("Back");
        BackToCustomerViewButton1.setFocusPainted(false);
        BackToCustomerViewButton1.setFocusable(false);

        BackToCustomerViewButton2.setBackground(new java.awt.Color(190, 52, 85));
        BackToCustomerViewButton2.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        BackToCustomerViewButton2.setForeground(new java.awt.Color(255, 224, 227));
        BackToCustomerViewButton2.setText("Back");
        BackToCustomerViewButton2.setFocusPainted(false);
        BackToCustomerViewButton2.setFocusable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BackToCustomerViewButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DeletedServicesButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(364, 364, 364)
                    .addComponent(BackToCustomerViewButton)
                    .addContainerGap(364, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(BackToCustomerViewButton1)
                    .addGap(0, 0, Short.MAX_VALUE)))
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
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DeletedServicesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BackToCustomerViewButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(229, 229, 229)
                    .addComponent(BackToCustomerViewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(229, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(BackToCustomerViewButton1)
                    .addGap(0, 0, Short.MAX_VALUE)))
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
        AddGuestView dialog = new AddGuestView();
        dialog.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void DeletedServicesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeletedServicesButtonActionPerformed
        DeletedGuestsView dialog = new DeletedGuestsView();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_DeletedServicesButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        AdminDashBoardView dialog = new AdminDashBoardView();
        dialog.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

    private void BackToCustomerViewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackToCustomerViewButtonActionPerformed
        GuestsView dialog = new GuestsView();
        dialog.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BackToCustomerViewButtonActionPerformed

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
    private javax.swing.JButton BackToCustomerViewButton;
    private javax.swing.JButton BackToCustomerViewButton1;
    private javax.swing.JButton BackToCustomerViewButton2;
    private javax.swing.JTextField CustomerSearchField;
    private javax.swing.JButton DeletedServicesButton;
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
