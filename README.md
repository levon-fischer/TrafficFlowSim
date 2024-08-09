# Traffic Flow Simulator

## Overview

The Traffic Flow Simulator is a Java-based application designed to simulate traffic flow at multiple intersections with dynamic traffic lights and cars. The simulator provides real-time updates of traffic lights and car positions, leveraging Java's multithreading capabilities to manage the concurrent actions of multiple components. This project demonstrates the effective use of object-oriented design principles, multithreading, and graphical programming in Java.

## Features

- **Dynamic Traffic Lights**: Simulate traffic lights at multiple intersections with configurable durations for green, yellow, and red lights. Each traffic light operates independently in its own thread.
- **Real-Time Car Movement**: Cars travel along a straight road, stopping at red lights and resuming movement when the lights turn green. Each car runs in its own thread, ensuring smooth and independent movement.
- **User Controls**: Start, pause, stop, and continue the simulation with intuitive buttons. Additional cars and traffic lights can be added dynamically during the simulation.
- **Graphical User Interface**: A clean and user-friendly GUI built using Java Swing, featuring real-time visual updates of the simulation.

## Installation

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/levon-fischer/TrafficFlowSimulator.git
    ```
2. **Navigate to the Project Directory**:
    ```bash
    cd TrafficFlowSimulator
    ```
3. **Open the Project in Your Preferred Java IDE**:
    - This project was developed and tested using IntelliJ IDEA. It is recommended to use IntelliJ IDEA or a similar IDE for the best experience.

4. **Build the Project**:
    - Ensure that you have JDK 22 or newer installed.
    - Build the project using your IDE's build tools.

## Usage

1. **Start the Simulation**:
    - Run the `Main` class to launch the Traffic Flow Simulator.
    - Use the control buttons to start, pause, stop, and continue the simulation.

2. **Add Dynamic Elements**:
    - Click the "Add Car" button to introduce additional cars into the simulation.
    - Click the "Add Light" button to add more traffic lights at random available positions.

3. **Observe the Simulation**:
    - Watch as cars move along the road, obeying traffic light signals.
    - Traffic lights will change color according to the configured durations, and cars will stop at red lights.

## Project Structure

- **Main**: The entry point of the application.
- **MainFrame**: The main GUI frame that houses all components.
- **TimePanel**: Displays the elapsed time of the simulation.
- **RoadPanel**: The central panel where traffic lights and cars are displayed.
- **TrafficLight**: Represents a traffic light, including its position and color.
- **Car**: Represents a car, including its position and speed.
- **TimeThread, TrafficLightThread, CarThread**: Manage the timing, traffic lights, and car movements, respectively.

## Lessons Learned

This project provided valuable insights into the complexities of multithreaded programming and dynamic user interface design. The experience of managing concurrent threads, ensuring smooth GUI updates, and implementing robust event handling mechanisms was instrumental in honing my software development skills.

## Future Enhancements

Potential enhancements to this project could include:
- **Advanced Traffic Patterns**: Implementing more complex traffic patterns, such as turn lanes and stop signs.
- **Realistic Physics**: Introducing acceleration and deceleration for cars to simulate more realistic driving behavior.
- **Expanded GUI**: Adding additional control features, such as adjusting the speed of cars or the duration of traffic lights in real-time.

## Contributing

Contributions to the Traffic Flow Simulator are welcome. Please fork the repository and submit a pull request with your proposed changes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

