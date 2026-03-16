package renderer.AccountRenderer;
import Views.GuestManagement.GuestsView;
import Views.AccountManagement.AccountAdministration.*;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.net.URL;
import Controllers.UserControllers;
import Model.Users;

public class AccountActionEditor extends AbstractCellEditor implements TableCellEditor {
    private static UserControllers control = new UserControllers();
    private JPanel panel = new JPanel();
    private JButton updateButton = new JButton();
    private JButton deleteButton = new JButton();

    public AccountActionEditor(JTable table) {
        // FETCH ICONS FROM THE LIBRARY
        
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

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

        // UPDATE BUTTON EVENT LISTENER
        updateButton.addActionListener(e -> {
            int row = table.getEditingRow();
            fireEditingStopped();
            
            int userID = Integer.parseInt(table.getValueAt(row, 0).toString()); 
            Users user = control.GetUserDetailsByID(userID);
            
            EditUserView dialog = new EditUserView();
            dialog.loadUserData(user);
            dialog.setVisible(true);
        });

        deleteButton.addActionListener(e -> {
            int row = table.getEditingRow();
            fireEditingStopped();
            
            int userID = Integer.parseInt(table.getValueAt(row, 0).toString());
            
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this user?", 
            "Confirm Delete", 
            JOptionPane.YES_OPTION,
            JOptionPane.WARNING_MESSAGE
            );
            
            if(choice == JOptionPane.YES_OPTION){
                if(control.DeleteUserByID(userID)){
                    StaffAndAdminAccountView.getInstance().LoadUsersToTable();
                }
            }
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

