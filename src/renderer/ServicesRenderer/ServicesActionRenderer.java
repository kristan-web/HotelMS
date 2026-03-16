package renderer.ServicesRenderer;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.net.URL;

public class ServicesActionRenderer extends JPanel implements TableCellRenderer {

    private JButton updateButton = new JButton();
    private JButton deleteButton = new JButton();

    public ServicesActionRenderer() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        // Safe icon loading
        URL updateUrl = getClass().getResource("/resources/update.png");
        if(updateUrl != null) updateButton.setIcon(new ImageIcon(new ImageIcon(updateUrl).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        else System.out.println("update.png not found!");

        URL deleteUrl = getClass().getResource("/resources/delete.png");
        if(deleteUrl != null) deleteButton.setIcon(new ImageIcon(new ImageIcon(deleteUrl).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        else System.out.println("delete.png not found!");

        // Make buttons look flat
        updateButton.setBorderPainted(false);
        updateButton.setContentAreaFilled(false);
        deleteButton.setBorderPainted(false);
        deleteButton.setContentAreaFilled(false);

        add(updateButton);
        add(deleteButton);
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}