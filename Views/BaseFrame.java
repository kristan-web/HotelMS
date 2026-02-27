
package Views;

import java.util.prefs.Preferences;
import javax.swing.JFrame;

public class BaseFrame extends JFrame {

    private Preferences prefs;

    public BaseFrame() {
        prefs = Preferences.userNodeForPackage(BaseFrame.class);
        
        // Restore window state safely
        int state = prefs.getInt("windowState", JFrame.NORMAL);

        if (state == JFrame.ICONIFIED) {
            state = JFrame.NORMAL; // Don't reopen minimized
        }

        setExtendedState(state);

        if (state == JFrame.NORMAL) {
            int width = prefs.getInt("windowWidth", 800);
            int height = prefs.getInt("windowHeight", 600);
            setSize(width, height);
            setLocationRelativeTo(null);
        }

        // Save state changes (maximize/minimize/restore)
        addWindowStateListener(e -> {
            int newState = getExtendedState();
            prefs.putInt("windowState", newState);

            if (newState == JFrame.NORMAL) {
                setLocationRelativeTo(null); // Center when restored
            }
        });

        // Save size when resized (only when not maximized)
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                if (getExtendedState() == JFrame.NORMAL) {
                    prefs.putInt("windowWidth", getWidth());
                    prefs.putInt("windowHeight", getHeight());
                }
            }
        });
        
    }
}
