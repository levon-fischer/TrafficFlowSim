package trafficflowsim.threads;

import trafficflowsim.gui.TimePanel;

public class TimeThread extends Thread {
    private TimePanel timePanel;
    private boolean running;
    private int time;

    public TimeThread(TimePanel timePanel) {
        this.timePanel = timePanel;
        this.running = true;
        this.time = 0;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1000); // Sleep for 1 second
                time++;
                timePanel.updateTime(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopRunning() {
        running = false;
    }

    public void resetTime() {
        time = 0;
        timePanel.updateTime(time);
    }
}
