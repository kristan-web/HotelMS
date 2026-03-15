package Views.ReservationManagement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A reusable modal date-picker dialog.
 * Usage:  LocalDate d = DatePicker.showDialog(parentFrame, "Pick Date", LocalDate.now());
 */
public class DatePicker extends JDialog {

    private LocalDate selectedDate  = null;
    private LocalDate displayedMonth;
    private JLabel    monthYearLabel;
    private JPanel    calendarGrid;

    private static final Color COL_TODAY    = new Color(52, 101, 164);
    private static final Color COL_SELECTED = new Color(38, 139, 38);
    private static final Color COL_HOVER    = new Color(220, 230, 245);

    public DatePicker(Frame parent, String title, LocalDate initial) {
        super(parent, title, true);
        displayedMonth = (initial != null ? initial : LocalDate.now()).withDayOfMonth(1);
        initUI();
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    private void initUI() {
        JPanel root = new JPanel(new BorderLayout(6, 6));
        root.setBorder(new EmptyBorder(12, 12, 12, 12));
        root.setBackground(Color.WHITE);
        setContentPane(root);

        // ── Header ─────────────────────────────────────────────────────────────
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(52, 101, 164));
        header.setBorder(new EmptyBorder(6, 8, 6, 8));

        JButton prev = navButton("◀");
        JButton next = navButton("▶");
        monthYearLabel = new JLabel("", SwingConstants.CENTER);
        monthYearLabel.setForeground(Color.WHITE);
        monthYearLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

        prev.addActionListener(e -> { displayedMonth = displayedMonth.minusMonths(1); refresh(); });
        next.addActionListener(e -> { displayedMonth = displayedMonth.plusMonths(1);  refresh(); });

        header.add(prev, BorderLayout.WEST);
        header.add(monthYearLabel, BorderLayout.CENTER);
        header.add(next, BorderLayout.EAST);

        // ── Day-of-week row ────────────────────────────────────────────────────
        JPanel dowRow = new JPanel(new GridLayout(1, 7, 2, 0));
        dowRow.setBackground(Color.WHITE);
        for (String d : new String[]{"Su","Mo","Tu","We","Th","Fr","Sa"}) {
            JLabel l = new JLabel(d, SwingConstants.CENTER);
            l.setFont(new Font("SansSerif", Font.BOLD, 11));
            l.setForeground(new Color(100, 100, 100));
            dowRow.add(l);
        }

        // ── Calendar grid ──────────────────────────────────────────────────────
        calendarGrid = new JPanel(new GridLayout(6, 7, 2, 2));
        calendarGrid.setBackground(Color.WHITE);

        JPanel center = new JPanel(new BorderLayout(0, 4));
        center.setBackground(Color.WHITE);
        center.add(dowRow, BorderLayout.NORTH);
        center.add(calendarGrid, BorderLayout.CENTER);

        // ── Footer ─────────────────────────────────────────────────────────────
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(e -> dispose());
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        footer.setBackground(Color.WHITE);
        footer.add(cancel);

        root.add(header, BorderLayout.NORTH);
        root.add(center, BorderLayout.CENTER);
        root.add(footer, BorderLayout.SOUTH);

        refresh();
    }

    private JButton navButton(String text) {
        JButton b = new JButton(text);
        b.setForeground(Color.WHITE);
        b.setBackground(new Color(52, 101, 164));
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setFont(new Font("SansSerif", Font.BOLD, 12));
        return b;
    }

    private void refresh() {
        monthYearLabel.setText(displayedMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy")));
        calendarGrid.removeAll();

        int firstDow   = displayedMonth.getDayOfWeek().getValue() % 7; // Sun=0
        int daysInMonth = displayedMonth.lengthOfMonth();
        LocalDate today = LocalDate.now();

        for (int i = 0; i < firstDow; i++) calendarGrid.add(new JLabel(""));

        for (int day = 1; day <= daysInMonth; day++) {
            final LocalDate date = displayedMonth.withDayOfMonth(day);
            JButton btn = new JButton(String.valueOf(day));
            btn.setMargin(new Insets(3, 3, 3, 3));
            btn.setFont(new Font("SansSerif", Font.PLAIN, 12));
            btn.setFocusPainted(false);
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btn.setOpaque(true);
            btn.setBorderPainted(false);

            if (date.equals(selectedDate)) {
                btn.setBackground(COL_SELECTED);
                btn.setForeground(Color.WHITE);
            } else if (date.equals(today)) {
                btn.setBackground(COL_TODAY);
                btn.setForeground(Color.WHITE);
            } else {
                btn.setBackground(Color.WHITE);
                btn.setForeground(Color.DARK_GRAY);
                btn.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent e) { btn.setBackground(COL_HOVER); }
                    public void mouseExited(java.awt.event.MouseEvent e)  { btn.setBackground(Color.WHITE); }
                });
            }

            btn.addActionListener(e -> { selectedDate = date; dispose(); });
            calendarGrid.add(btn);
        }

        int filled = firstDow + daysInMonth;
        int rem    = filled % 7 == 0 ? 0 : 7 - (filled % 7);
        for (int i = 0; i < rem; i++) calendarGrid.add(new JLabel(""));

        calendarGrid.revalidate();
        calendarGrid.repaint();
    }

    /** Show the dialog and return the chosen date, or null if cancelled. */
    public static LocalDate showDialog(Frame parent, String title, LocalDate initial) {
        DatePicker dp = new DatePicker(parent, title, initial);
        dp.setVisible(true);
        return dp.selectedDate;
    }
}
