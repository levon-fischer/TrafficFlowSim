package trafficflowsim.gui;

import javax.swing.*;
import java.awt.*;

public class TrafficLightPanel extends JPanel {
    private JLabel[] trafficLights;

    public TrafficLightPanel() {
        setLayout(new GridLayout(1, 3));
        trafficLights = new JLabel[3];
        for (int i = 0; i < 3; i++) {
            trafficLights[i] = new JLabel("Green");
            trafficLights[i].setHorizontalAlignment(SwingConstants.CENTER);
            add(trafficLights[i]);
        }
    }

    public void updateTrafficLight(int index, String color) {
        if (index >= 0 && index < trafficLights.length) {
            trafficLights[index].setText(color);
        }
    }
}
