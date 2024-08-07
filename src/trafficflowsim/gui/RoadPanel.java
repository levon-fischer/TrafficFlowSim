package trafficflowsim.gui;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import trafficflowsim.models.Car;
import trafficflowsim.models.TrafficLight;

public class RoadPanel extends JPanel {
    private final List<TrafficLight> trafficLights;
    private final List<Car> cars;
    private final List<Integer> availableLightPositions;

    public RoadPanel() {
        this.trafficLights = new ArrayList<>();
        this.cars = new ArrayList<>();
        this.availableLightPositions = new ArrayList<>();

        for (int i = 200; i <= 1400; i += 200) {
            availableLightPositions.add(i);
        }

        setPreferredSize(new java.awt.Dimension(1600, 400));

        // Add initial traffic lights and cars
        addInitialTrafficLights();
        addInitialCars();
    }

    private void addInitialTrafficLights() {
        for (int i = 0; i < 3; i++) {
            addTrafficLight();
        }
    }

    private void addInitialCars() {
        for (int i = 0; i < 3; i++) {
            addCar();
        }
    }

    public List<TrafficLight> getTrafficLights() {
        return trafficLights;
    }

    public List<Car> getCars() {
        return cars;
    }

    public boolean hasAvailableLightPositions() {
        return !availableLightPositions.isEmpty();
    }

    public void addTrafficLight() {
        if (availableLightPositions.isEmpty()) return;

        Random rand = new Random();
        int index = rand.nextInt(availableLightPositions.size());
        int position = availableLightPositions.remove(index);

        TrafficLight trafficLight = new TrafficLight(position);
        trafficLights.add(trafficLight);

        revalidate();
        repaint();
    }

    public void addCar() {
        Car car = new Car();
        cars.add(car);
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int height = getHeight();
        int width = getWidth();
        int middle = height / 2;

        // Draw the road
        g.setColor(Color.BLACK);
        g.drawLine(0, middle, width, middle);

        // Draw traffic lights
        for (TrafficLight trafficLight : trafficLights) {
            int x = trafficLight.getPosition();
            int y = middle - 50; // Position the light above the road
            g.setColor(trafficLight.getColor().getColor()); // Get the current color from TrafficLight enum
            g.fillRect(x, y, 30, 30); // Draw the square representing the light

            // Draw the label
            g.setColor(Color.BLACK);
            g.drawString(trafficLight.getColor().toString(), x, y - 10);
        }

        // Draw cars
        for (Car car : cars) {
            int x = (int) car.getPosition();
            g.setColor(Color.BLUE);
            g.fillPolygon(new int[]{x + 15, x, x - 15}, new int[]{middle + 13, middle, middle + 13}, 3); // Draw the car as a triangle
        }
    }

    public void updateRoad() {
        repaint();
    }
}
