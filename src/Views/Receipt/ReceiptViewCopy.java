package Views.Receipt;

public class ReceiptViewCopy extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ReceiptViewCopy.class.getName());
    
    public ReceiptViewCopy() {
        initComponents();
    }

    public ReceiptViewCopy(int reservationId, String guestName, String guestAddress,
                           String roomNumber, String checkIn, String checkOut,
                           int nights, double roomPrice, double totalAmount, String notes) {
        initComponents();
        populateReceipt(reservationId, guestName, guestAddress,
                        roomNumber, checkIn, checkOut,
                        nights, roomPrice, totalAmount, notes);
    }
    
    public void populateReceipt(int reservationId, String guestName, String guestAddress,
                                String roomNumber, String checkIn, String checkOut,
                                int nights, double roomPrice, double totalAmount, String notes) { // ── Header labels ────────────────────────────────────────────────────
        // jLabel9  = customer name
        jLabel9.setText(guestName != null && !guestName.isBlank() ? guestName : "—");
 
        // jLabel10 / jLabel11 = guest address lines
        if (guestAddress != null && !guestAddress.isBlank()) {
            jLabel10.setText(guestAddress);
            jLabel11.setText("");          // clear second address line
        } else {
            jLabel10.setText("—");
            jLabel11.setText("");
        }
 
        // jLabel13 = Receipt # value
        jLabel13.setText(String.format("%07d", reservationId));
 
        // jLabel15 = Receipt date value
        jLabel15.setText(java.time.LocalDate.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("MM-dd-yy")));
 
        // ── Items table ──────────────────────────────────────────────────────
        javax.swing.table.DefaultTableModel model =
                new javax.swing.table.DefaultTableModel(
                        new String[]{"Item", "Description", "Qty", "Price (₱)", "Total (₱)"}, 0) {
                    @Override public boolean isCellEditable(int r, int c) { return false; }
                };
 
        // Row 1: Room charge
        model.addRow(new Object[]{
            "Room " + roomNumber,
            checkIn + " → " + checkOut,
            nights,
            String.format("%.2f", roomPrice),
            String.format("%.2f", nights * roomPrice)
        });
 
        jTable1.setModel(model);
 
        // ── Totals ───────────────────────────────────────────────────────────
        double subtotal = nights * roomPrice;
        double tax      = subtotal * 0.05;          // 5 % sales tax
        double grand    = subtotal + tax;
 
        jLabel17.setText(String.format("₱%.2f", subtotal));   // Subtotal value
        jLabel19.setText(String.format("₱%.2f", tax));        // Tax value
        jLabel21.setText(String.format("₱%.2f", grand));      // Total value
 
        // ── Notes ────────────────────────────────────────────────────────────
        if (notes != null && !notes.isBlank()) {
            jLabel2.setText("Notes: " + notes);
        } else {
            jLabel2.setText("Notes: —");
        }
 
        pack();
    }
    
        public void printReceipt() {
        java.awt.print.PrinterJob job = java.awt.print.PrinterJob.getPrinterJob();
        job.setJobName("Hotel Receipt");
        job.setPrintable((graphics, pageFormat, pageIndex) -> {
            if (pageIndex > 0) return java.awt.print.Printable.NO_SUCH_PAGE;
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) graphics;
            g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            double scaleX = pageFormat.getImageableWidth()  / jPanel1.getWidth();
            double scaleY = pageFormat.getImageableHeight() / jPanel1.getHeight();
            double scale  = Math.min(scaleX, scaleY);
            g2.scale(scale, scale);
            jPanel1.printAll(g2);
            return java.awt.print.Printable.PAGE_EXISTS;
        });
        if (job.printDialog()) {
            try {
                job.print();
            } catch (java.awt.print.PrinterException ex) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Printing failed: " + ex.getMessage(),
                        "Print Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }
        
         public void saveAsPDF() {
        javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
        fc.setDialogTitle("Save Receipt as PDF");
        fc.setSelectedFile(new java.io.File("receipt_" + jLabel13.getText() + ".pdf"));
        fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PDF files", "pdf"));
        if (fc.showSaveDialog(this) != javax.swing.JFileChooser.APPROVE_OPTION) return;
 
        java.io.File file = fc.getSelectedFile();
        if (!file.getName().toLowerCase().endsWith(".pdf")) {
            file = new java.io.File(file.getAbsolutePath() + ".pdf");
        }
 
        // Use javax.print with DocFlavor.SERVICE_FORMATTED to print to PDF writer
        // (Works on any JDK with a PDF print service, e.g. "Microsoft Print to PDF"
        //  on Windows or CUPS-PDF on Linux. If unavailable, fall back to a screenshot PNG.)
        java.awt.print.PrinterJob job = java.awt.print.PrinterJob.getPrinterJob();
        javax.print.PrintService[] services =
                javax.print.PrintServiceLookup.lookupPrintServices(null, null);
 
        javax.print.PrintService pdfService = null;
        for (javax.print.PrintService ps : services) {
            String name = ps.getName().toLowerCase();
            if (name.contains("pdf") || name.contains("print to pdf")) {
                pdfService = ps;
                break;
            }
        }
 
        if (pdfService != null) {
            // Route through the OS PDF printer
            try {
                job.setPrintService(pdfService);
            } catch (java.awt.print.PrinterException ignored) { pdfService = null; }
        }
 
        if (pdfService == null) {
            // Fallback: render to a PNG image saved alongside the receipt
            saveAsImage(file);
            return;
        }
 
        final java.io.File finalFile = file;
        job.setJobName("Receipt_" + jLabel13.getText());
        job.setPrintable((graphics, pageFormat, pageIndex) -> {
            if (pageIndex > 0) return java.awt.print.Printable.NO_SUCH_PAGE;
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) graphics;
            g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            double scale = Math.min(
                    pageFormat.getImageableWidth()  / jPanel1.getWidth(),
                    pageFormat.getImageableHeight() / jPanel1.getHeight());
            g2.scale(scale, scale);
            jPanel1.printAll(g2);
            return java.awt.print.Printable.PAGE_EXISTS;
        });
        try {
            job.print();
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Receipt saved as PDF successfully.",
                    "Save PDF", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } catch (java.awt.print.PrinterException ex) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Could not save PDF: " + ex.getMessage(),
                    "PDF Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
         
         
         private void saveAsImage(java.io.File pdfFile) {
        java.io.File imgFile = new java.io.File(
                pdfFile.getAbsolutePath().replaceAll("\\.pdf$", ".png"));
        java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(
                jPanel1.getWidth(), jPanel1.getHeight(),
                java.awt.image.BufferedImage.TYPE_INT_RGB);
        java.awt.Graphics2D g2 = img.createGraphics();
        jPanel1.printAll(g2);
        g2.dispose();
        try {
            javax.imageio.ImageIO.write(img, "PNG", imgFile);
            javax.swing.JOptionPane.showMessageDialog(this,
                    "No PDF printer found.\nReceipt saved as image:\n" + imgFile.getAbsolutePath(),
                    "Saved as Image", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } catch (java.io.IOException ex) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Failed to save image: " + ex.getMessage(),
                    "Save Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 224, 227));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel1.setText("Kobu Hotel and Reservations");

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 10)); // NOI18N
        jLabel2.setText("Notes:");

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 10)); // NOI18N
        jLabel3.setText("1234 Kobu St. ");

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 10)); // NOI18N
        jLabel4.setText("Dasmariñas Cavite, ST 1234  ");

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 10)); // NOI18N
        jLabel5.setText("Thank you for your purchase! All sales are final after 30 days.");

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 10)); // NOI18N
        jLabel6.setText("For questions or support, contact us at kobu@hotel.com");

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("R E C E I P T");

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 10)); // NOI18N
        jLabel8.setText("Billed To ");

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel9.setText("Customer Name");

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 10)); // NOI18N
        jLabel10.setText("1234 Kobu St. ");

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 10)); // NOI18N
        jLabel11.setText("Dasmariñas Cavite, ST 1234  ");

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 10)); // NOI18N
        jLabel12.setText("Receipt #");

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 10)); // NOI18N
        jLabel13.setText("0000456");

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 10)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Receipt Date");

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 10)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("11-04-23");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Item", "Description", "Qty", "Price", "Total"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 10)); // NOI18N
        jLabel16.setText("Subtotal");

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 10)); // NOI18N
        jLabel17.setText("₱350.00");

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 10)); // NOI18N
        jLabel18.setText("Sales Tax (5%) ");

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 10)); // NOI18N
        jLabel19.setText("₱30.00");

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 10)); // NOI18N
        jLabel20.setText("Total(PHP) ");

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 10)); // NOI18N
        jLabel21.setText("₱380.00");

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 10)); // NOI18N
        jLabel22.setText("Please retain this receipt for warranty or exchange purposes.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel13))
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 105, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel21))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(80, 80, 80)
                                .addComponent(jLabel17))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel19))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(2, 2, 2)
                .addComponent(jLabel4)
                .addGap(28, 28, 28)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(32, 32, 32))
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
        java.awt.EventQueue.invokeLater(() -> new ReceiptViewCopy().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
