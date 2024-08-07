package trafficflowsim.models;

import java.util.Random;

import trafficflowsim.gui.RoadPanel;

public class Car {
    private final double speed;
    private double position; // position in meters
    private boolean stopped;

    public Car() {
        Random rand = new Random();
        this.speed = 85 + rand.nextInt(11); // Speed between 85 and 95 km/h
        this.position = rand.nextDouble() * 1600; // Random position between 0 and 1600 meters
        this.stopped = false;
    }

    public double getPosition() {
        return position;
    }

    public void updatePosition() {
        if (!stopped) {
            this.position += (speed / 3.6); // Convert speed from km/h to m/s and update position
            if (this.position >= 1600) {
                this.position = 0; // Reset position if it reaches the end of the road
            }
        }
    }

    public void stop() {
        this.stopped = true; // Stop the car
    }

    public void resume() {
        this.stopped = false; // Resume the car
    }

    public boolean shouldStopAtRedLight(RoadPanel roadPanel) {
        for (TrafficLight trafficLight : roadPanel.getTrafficLights()) {
            int lightPosition = trafficLight.getPosition();
            if (trafficLight.getColor() == TrafficLightColor.RED && this.position >= lightPosition - 27 && this.position <= lightPosition) {
                return true;
            }
        }
        return false;
    }
}
