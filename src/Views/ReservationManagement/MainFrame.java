package Views.ReservationManagement;
import Model.Users;
import Session.Session;
import java.awt.Image;
import javax.swing.ImageIcon;
import Views.Dashboard.*;
import javax.swing.JOptionPane;

public class MainFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainFrame.class.getName());
    
    public MainFrame() {
        initComponents();
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/resources/staff_logo.jpg"));
        Image scaled1 = icon1.getImage().getScaledInstance(lblIcon1.getWidth(), lblIcon1.getHeight(), Image.SCALE_SMOOTH);
        lblIcon1.setIcon(new ImageIcon(scaled1));

        mainTabs.addTab("Reservations", new ReservationPanel());
        mainTabs.addTab("Guests", new GuestPanel());
        mainTabs.addTab("Services", new ServicesPanel());
        System.out.println(Session.getCurrentUser());
        if(Session.getUserRole().equals("Admin")){
            mainTabs.addTab("Rooms", new RoomPanel());
        } 
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainPanel = new javax.swing.JPanel();
        Body = new javax.swing.JPanel();
        mainTabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        Header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblIcon1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1400, 800));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        MainPanel.setLayout(new java.awt.BorderLayout());

        Body.setBackground(new java.awt.Color(255, 224, 227));

        mainTabs.setBackground(new java.awt.Color(255, 204, 204));
        mainTabs.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 194, 189)));
        mainTabs.setMinimumSize(new java.awt.Dimension(100, 100));

        jPanel1.setBackground(new java.awt.Color(190, 52, 85));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 96, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout BodyLayout = new javax.swing.GroupLayout(Body);
        Body.setLayout(BodyLayout);
        BodyLayout.setHorizontalGroup(
            BodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(BodyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainTabs, javax.swing.GroupLayout.DEFAULT_SIZE, 1388, Short.MAX_VALUE)
                .addContainerGap())
        );
        BodyLayout.setVerticalGroup(
            BodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BodyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainTabs, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MainPanel.add(Body, java.awt.BorderLayout.CENTER);

        Header.setBackground(new java.awt.Color(190, 52, 85));
        Header.setMaximumSize(new java.awt.Dimension(32767, 100));
        Header.setMinimumSize(new java.awt.Dimension(1300, 75));
        Header.setPreferredSize(new java.awt.Dimension(1300, 75));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 224, 227));
        jLabel1.setText("Reservation");

        jButton1.setBackground(new java.awt.Color(255, 204, 204));
        jButton1.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(190, 52, 85));
        jButton1.setText("Back");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(HeaderLayout);
        HeaderLayout.setHorizontalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1080, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderLayout.createSequentialGroup()
                .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HeaderLayout.createSequentialGroup()
                        .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(HeaderLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(HeaderLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblIcon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        MainPanel.add(Header, java.awt.BorderLayout.NORTH);

        getContentPane().add(MainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(Session.getUserRole().equals("Admin")){
            AdminDashBoardView dialog = new AdminDashBoardView();
            dialog.setVisible(true);
            this.dispose();
        }else{
            StaffDashBoardView dialog = new StaffDashBoardView();
            dialog.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        StaffDashBoardView dialog = new StaffDashBoardView();
        dialog.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    public static void main(String args[]) {
        MainFrame dialog = new MainFrame();
        dialog.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Body;
    private javax.swing.JPanel Header;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblIcon1;
    private javax.swing.JTabbedPane mainTabs;
    // End of variables declaration//GEN-END:variables
}
