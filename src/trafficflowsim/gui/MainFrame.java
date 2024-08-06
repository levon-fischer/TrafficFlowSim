package trafficflowsim.gui;

import trafficflowsim.threads.TimeThread;
import trafficflowsim.threads.TrafficLightThread;
import trafficflowsim.threads.CarThread;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private TimePanel timePanel;
    private TrafficLightPanel trafficLightPanel;
    private CarPanel carPanel;
    private TimeThread timeThread;
    private TrafficLightThread trafficLightThread;
    private CarThread carThread;

    public MainFrame() {
        setTitle("Traffic Flow Simulator");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Initialize components and add them to the frame
        initUI();
    }

    private void initUI() {
        // Set up Layout manager
        setLayout(new BorderLayout());

        // Add panels for different components
        timePanel = new TimePanel();
        trafficLightPanel = new TrafficLightPanel();
        carPanel = new CarPanel();

        // Add panels to the frame
        add(timePanel, BorderLayout.NORTH);
        add(trafficLightPanel, BorderLayout.CENTER);
        add(carPanel, BorderLayout.SOUTH);

        // Add control buttons
        JPanel controlPanel = new JPanel();
        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");
        JButton pauseButton = new JButton("Pause");
        JButton continueButton = new JButton("Continue");

        controlPanel.add(startButton);
        controlPanel.add(stopButton);
        controlPanel.add(pauseButton);
        controlPanel.add(continueButton);

        add(controlPanel, BorderLayout.SOUTH);

        // Event handlers for buttons
        startButton.addActionListener(e -> startSimulation());
        pauseButton.addActionListener(e -> pauseSimulation());
        stopButton.addActionListener(e -> stopSimulation());
        continueButton.addActionListener(e -> continueSimulation());
    }

    private void startSimulation() {
        timeThread = new TimeThread(timePanel);
        trafficLightThread = new TrafficLightThread(trafficLightPanel);
        carThread = new CarThread(carPanel);

        timeThread.start();
        trafficLightThread.start();
        carThread.start();
    }

    private void pauseSimulation() {
        if (timeThread != null) timeThread.stopRunning();
        if (trafficLightThread != null) trafficLightThread.stopRunning();
        if (carThread != null) carThread.stopRunning();
    }

    private void stopSimulation() {
        pauseSimulation();
        if (timeThread != null) timeThread.resetTime();
    }

    private void continueSimulation() {
        startSimulation();
    }
}
