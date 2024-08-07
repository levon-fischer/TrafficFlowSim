package trafficflowsim.models;

import java.util.Random;

public class TrafficLight {
    private TrafficLightColor color;
    private int duration; // duration in milliseconds
    private final int position; // position in meters

    private static final int[] DURATIONS = {10000, 2000, 12000}; // durations for green, yellow, and red lights

    public TrafficLight(int position) {
        this.position = position;
        randomizeInitialState();
    }

    public TrafficLightColor getColor() {
        return color;
    }

    public int getDuration() {
        return duration;
    }

    public int getPosition() {
        return position;
    }

    public void changeColor() {
        switch (color) {
            case RED:
                color = TrafficLightColor.GREEN;
                duration = DURATIONS[0]; // Green for 10 seconds
                break;
            case GREEN:
                color = TrafficLightColor.YELLOW;
                duration = DURATIONS[1]; // Yellow for 2 seconds
                break;
            case YELLOW:
                color = TrafficLightColor.RED;
                duration = DURATIONS[2]; // Red for 12 seconds
                break;
        }
    }

    private void randomizeInitialState() {
        Random rand = new Random();
        int state = rand.nextInt(3);
        switch (state) {
            case 0:
                color = TrafficLightColor.GREEN;
                duration = DURATIONS[0];
                break;
            case 1:
                color = TrafficLightColor.YELLOW;
                duration = DURATIONS[1];
                break;
            case 2:
                color = TrafficLightColor.RED;
                duration = DURATIONS[2];
                break;
        }
    }
}
