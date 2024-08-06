package trafficflowsim.threads;

import trafficflowsim.gui.TrafficLightPanel;

public class TrafficLightThread extends Thread {
    private TrafficLightPanel trafficLightPanel;
    private boolean running;

    public TrafficLightThread(TrafficLightPanel trafficLightPanel) {
        this.trafficLightPanel = trafficLightPanel;
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(5000); // Change light every 5 seconds
                updateTrafficLights();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateTrafficLights() {
        trafficLightPanel.updateTrafficLight(0, "Green");
        trafficLightPanel.updateTrafficLight(1, "Red");
        trafficLightPanel.updateTrafficLight(2, "Yellow");
    }

    public void stopRunning() {
        running = false;
    }
}
