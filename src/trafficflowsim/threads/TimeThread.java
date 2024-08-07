package trafficflowsim.threads;

import trafficflowsim.gui.TimePanel;

public class TimeThread extends Thread {
    private final TimePanel timePanel;
    private final Object pauseLock = new Object();
    private volatile boolean running = true;
    private volatile boolean paused = false;
    private int time;

    public TimeThread(TimePanel timePanel) {
        this.timePanel = timePanel;
        this.time = 0;
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

                time++;
                timePanel.updateTime(time);
            }
            try {
                Thread.sleep(1000); // Update every second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stopRunning() {
        running = false;
        resumeRunning(); // Ensure any pause threads are resumed so they can exit
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

    public void resetTime() {
        time = 0;
        timePanel.updateTime(time);
    }
}
