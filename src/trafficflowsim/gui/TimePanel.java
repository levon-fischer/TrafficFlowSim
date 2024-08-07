package trafficflowsim.gui;

import javax.swing.*;
import java.awt.*;

public class TimePanel extends JPanel {
    private final JLabel timeLabel;

    public TimePanel() {
        timeLabel = new JLabel("Time: 0 seconds");
        setLayout(new FlowLayout());
        add(timeLabel);
    }

    public void updateTime(int seconds) {
        timeLabel.setText("Time: " + seconds + " seconds");
    }
}
