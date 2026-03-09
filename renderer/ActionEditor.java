package renderer;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import Views.CustomerManagement.EditCustomerView;

public class ActionEditor extends AbstractCellEditor implements TableCellEditor {

    private JPanel panel = new JPanel();
    private JButton updateButton = new JButton();
    private JButton deleteButton = new JButton();

    public ActionEditor(JTable table) {
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        // Safe icon loading with scaling
        URL updateUrl = getClass().getResource("/resources/update.png");
        if(updateUrl != null) updateButton.setIcon(new ImageIcon(new ImageIcon(updateUrl).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        else System.out.println("update.png not found!");

        URL deleteUrl = getClass().getResource("/resources/delete.png");
        if(deleteUrl != null) deleteButton.setIcon(new ImageIcon(new ImageIcon(deleteUrl).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        else System.out.println("delete.png not found!");

        updateButton.setBorderPainted(false);
        updateButton.setContentAreaFilled(false);
        deleteButton.setBorderPainted(false);
        deleteButton.setContentAreaFilled(false);

        panel.add(updateButton);
        panel.add(deleteButton);

        // Click actions
        updateButton.addActionListener(e -> {
        int row = table.getEditingRow();
        fireEditingStopped();

    // Get parent frame so dialog is modal
        java.awt.Frame parentFrame = (java.awt.Frame) SwingUtilities.getWindowAncestor(table);

    // Open your existing dialog
        EditCustomerView editDialog = new EditCustomerView(parentFrame, true);
        editDialog.setLocationRelativeTo(parentFrame);
        editDialog.setVisible(true);
    });

        deleteButton.addActionListener(e -> {
            int row = table.getEditingRow();
            fireEditingStopped();
            // Open JDialog for delete
            JDialog deleteDialog = new JDialog();
            deleteDialog.setTitle("Delete Row " + row);
            deleteDialog.setSize(300, 150);
            deleteDialog.setLocationRelativeTo(null);
            deleteDialog.setModal(true);
            deleteDialog.setVisible(true);
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }
}