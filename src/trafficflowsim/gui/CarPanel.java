package trafficflowsim.gui;

import javax.swing.*;
import java.awt.*;

public class CarPanel extends JPanel {
    private JLabel[] carLabels;

    public CarPanel() {
        setLayout(new GridLayout(3, 1)); // 3 rows for 3 cars
        carLabels = new JLabel[3];
        for (int i = 0; i < 3; i++) {
            carLabels[i] = new JLabel("Car " + (i + 1) + ": Speed = 0 km/h, Position = 0 m");
            add(carLabels[i]);
        }
    }

    public void updateCar(int index, double speed, double position) {
        if (index >= 0 && index < carLabels.length) {
            carLabels[index].setText("Car " + (index + 1) + ": Speed = " + speed + " km/h, Position = " + position + " m");
        }
    }
}
