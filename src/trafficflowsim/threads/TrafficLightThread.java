package trafficflowsim.threads;

import trafficflowsim.gui.RoadPanel;
import trafficflowsim.models.TrafficLight;

public class TrafficLightThread extends Thread {
    private final TrafficLight trafficLight;
    private final RoadPanel roadPanel;
    private final Object pauseLock = new Object();
    private volatile boolean running = true;
    private volatile boolean paused = false;

    public TrafficLightThread(TrafficLight trafficLight, RoadPanel roadPanel) {
        this.trafficLight = trafficLight;
        this.roadPanel = roadPanel;
    }

    @Override
    public void run() {
        while (running) {
            synchronized (pauseLock) {
                while (paused) {
                    try {
                        pauseLock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                try {
                    Thread.sleep(trafficLight.getDuration()); // Wait for the duration of the traffic light
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                if (running) {
                    trafficLight.changeColor();
                    roadPanel.updateRoad();
                }
            }
        }
    }

    public void pauseRunning() {
        paused = true;
    }

    public void resumeRunning() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll();
        }
    }
}
