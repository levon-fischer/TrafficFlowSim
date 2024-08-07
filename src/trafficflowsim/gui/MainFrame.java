package trafficflowsim.gui;

import trafficflowsim.threads.TimeThread;
import trafficflowsim.models.TrafficLight;
import trafficflowsim.threads.TrafficLightThread;
import trafficflowsim.threads.CarThread;
import trafficflowsim.models.Car;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private TimePanel timePanel;
    private RoadPanel roadPanel;
    private TimeThread timeThread;
    private final List<TrafficLightThread> trafficLightThreads;
    private final List<CarThread> carThreads;
    private JButton addLightButton;
    private boolean isRunning;
    private boolean isPaused;

    public MainFrame() {
        setTitle("Traffic Flow Simulator");
        setSize(1600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        trafficLightThreads = new ArrayList<>();
        carThreads = new ArrayList<>();

        initUI(); // Initialize components and add them to the frame
    }

    private void initUI() {
        // Set up Layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Title label
        JLabel titleLabel = new JLabel("Traffic Flow Simulator", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // Time panel
        timePanel = new TimePanel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        add(timePanel, gbc);

        // Name and class label
        JLabel nameLabel = new JLabel("<html>Levon Fischer<br>CMSC 335/6980<br>6 August, 2024</html>", JLabel.RIGHT);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 10, 10, 10);
        gbc.anchor = GridBagConstraints.EAST;
        add(nameLabel, gbc);

        // Road panel (for traffic lights and cars)
        roadPanel = new RoadPanel();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(roadPanel, gbc);

        // Control panel
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(controlPanel(), gbc);
    }

    private JPanel controlPanel() {
        // Add control buttons
        JPanel controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JButton startButton = new JButton("Start");
        JButton pauseButton = new JButton("Pause");
        JButton stopButton = new JButton("Stop");
        JButton continueButton = new JButton("Continue");
        JButton addCarButton = new JButton("Add Car");
        addLightButton = new JButton("Add Light");

        gbc.gridx = 0;
        gbc.gridy = 0;
        controlPanel.add(startButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        controlPanel.add(pauseButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        controlPanel.add(stopButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        controlPanel.add(continueButton, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        controlPanel.add(addCarButton, gbc);

        gbc.gridx = 5;
        gbc.gridy = 0;
        controlPanel.add(addLightButton, gbc);

        // Event handlers for buttons
        startButton.addActionListener(e -> startSimulation());
        pauseButton.addActionListener(e -> pauseSimulation());
        stopButton.addActionListener(e -> stopSimulation());
        continueButton.addActionListener(e -> continueSimulation());
        addCarButton.addActionListener(e -> addCarWithThread());
        addLightButton.addActionListener(e -> addTrafficLightWithThread());

        return controlPanel;
    }

    private void startSimulation() {
        if (!isRunning) {
            // Create the thread for the timer
            timeThread = new TimeThread(timePanel);
            timeThread.start();

            // Create the threads for the traffic lights
            for (TrafficLight trafficLight : roadPanel.getTrafficLights()) {
                TrafficLightThread trafficLightThread = new TrafficLightThread(trafficLight, roadPanel);
                trafficLightThreads.add(trafficLightThread);
                trafficLightThread.start();
            }

            // Create the threads for the cars
            for (Car car : roadPanel.getCars()) {
                CarThread carThread = new CarThread(car, roadPanel);
                carThreads.add(carThread);
                carThread.start();
            }

            isRunning = true;
            isPaused = false;
        }
    }

    private void pauseSimulation() {
        if (isRunning && !isPaused) {
            if (timeThread != null) timeThread.pauseRunning();
            for (TrafficLightThread trafficLightThread : trafficLightThreads) {
                trafficLightThread.pauseRunning();
            }
            for (CarThread carThread : carThreads) {
                carThread.pauseRunning();
            }
            isPaused = true;
        }
    }

    private void stopSimulation() {
        if (isRunning) {
            pauseSimulation();
            trafficLightThreads.clear();
            carThreads.clear();
            if (timeThread != null) timeThread.resetTime();
            isRunning = false;
            isPaused = false;
        }
    }

    private void continueSimulation() {
        if (isRunning && isPaused) {
           if (timeThread != null) timeThread.resumeRunning();
           for (TrafficLightThread trafficLightThread : trafficLightThreads) {
               trafficLightThread.resumeRunning();
           }
           for (CarThread carThread : carThreads) {
               carThread.resumeRunning();
           }
           isPaused = false;
        }
    }

    private void addTrafficLightWithThread() {
        roadPanel.addTrafficLight();
        if (!roadPanel.hasAvailableLightPositions()) {
            addLightButton.setEnabled(false); // Hide the button if there are no more available positions for lights
        }
        TrafficLight newLight = roadPanel.getTrafficLights().getLast();
        TrafficLightThread newLightThread = new TrafficLightThread(newLight, roadPanel);
        trafficLightThreads.add(newLightThread);
        newLightThread.start();
    }

    private void addCarWithThread() {
        roadPanel.addCar();
        Car newCar = roadPanel.getCars().getLast();
        CarThread newCarThread = new CarThread(newCar, roadPanel);
        carThreads.add(newCarThread);
        newCarThread.start();
    }
}
