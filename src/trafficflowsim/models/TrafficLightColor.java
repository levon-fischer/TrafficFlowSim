package trafficflowsim.models;

import java.awt.Color;

public enum TrafficLightColor {
    RED(Color.RED), GREEN(Color.GREEN), YELLOW(Color.YELLOW);

    private final Color color;

    TrafficLightColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
