package trafficflowsim.threads;

import trafficflowsim.gui.RoadPanel;
import trafficflowsim.models.Car;

public class CarThread extends Thread {
    private final Car car;
    private final RoadPanel roadPanel;
    private final Object pauseLock = new Object();
    private volatile boolean running = true;
    private volatile boolean paused = false;

    public CarThread(Car car, RoadPanel roadPanel) {
        this.car = car;
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

                car.updatePosition();
                if (car.shouldStopAtRedLight(roadPanel)) {
                    car.stop();
                } else {
                    car.resume();
                }
                roadPanel.updateRoad();
            }
             try {
                 Thread.sleep(1000); // Update every second
             } catch (InterruptedException e) {
                 Thread.currentThread().interrupt();
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
