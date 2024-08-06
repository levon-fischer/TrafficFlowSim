package trafficflowsim.threads;

import trafficflowsim.gui.CarPanel;

public class CarThread extends Thread {
    private CarPanel carPanel;
    private boolean running;
    private double[] speeds;
    private double[] positions;

    public CarThread(CarPanel carPanel) {
        this.carPanel = carPanel;
        this.running = true;
        this.speeds = new double[]{30, 40, 50}; // Initial speeds for cars
        this.positions = new double[3]; // Initial positions for cars
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1000); // Update every second
                updateCarPositions();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateCarPositions() {
        for (int i = 0; i < positions.length; i++) {
            positions[i] += speeds[i] / 3.6; // Convert speed from km/h to m/s
            carPanel.updateCar(i, speeds[i], positions[i]);
        }
    }

    public void stopRunning() {
        running = false;
    }
}
