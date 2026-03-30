package controller;

import dispatch.DispatchStrategy;
import dispatch.SmartDispatchStrategy;
import elevator.ElevatorCar;
import enums.Direction;
import models.Floor;
import models.Passenger;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystemController {

    private static ElevatorSystemController instance;

    public static final int MAX_ELEVATORS = 3;
    public static final int MAX_FLOORS = 15;

    private final List<ElevatorCar> elevators;
    private final List<Floor> floors;
    private final DispatchStrategy dispatchStrategy;

    private ElevatorSystemController() {
        this.elevators = new ArrayList<>();
        this.floors = new ArrayList<>();
        this.dispatchStrategy = new SmartDispatchStrategy();
        initialize();
    }

    public static ElevatorSystemController getInstance() {
        if (instance == null) {
            synchronized (ElevatorSystemController.class) {
                if (instance == null) {
                    instance = new ElevatorSystemController();
                }
            }
        }
        return instance;
    }

    private void initialize() {
        for (int i = 0; i < MAX_ELEVATORS; i++) {
            elevators.add(new ElevatorCar(i + 1, MAX_FLOORS));
        }
        for (int i = 0; i < MAX_FLOORS; i++) {
            floors.add(new Floor(i));
        }
        System.out.println("[ElevatorSystem] Initialized with " + MAX_ELEVATORS + " elevators and " + MAX_FLOORS + " floors.");
    }

    public ElevatorCar requestElevator(int floor, Direction direction) {
        System.out.println("[ElevatorSystem] Elevator requested at floor " + floor + " going " + direction);
        ElevatorCar selected = dispatchStrategy.selectElevator(elevators, floor, direction);
        if (selected != null) {
            System.out.println("[ElevatorSystem] Elevator " + selected.getElevatorId() + " dispatched to floor " + floor);
            selected.addDestination(floor);
        }
        return selected;
    }

    public void requestFloorFromInside(int elevatorId, int destinationFloor) {
        ElevatorCar elevator = getElevatorById(elevatorId);
        if (elevator != null) {
            elevator.getInsidePanel().pressFloor(destinationFloor);
            elevator.addDestination(destinationFloor);
        }
    }

    public void openDoor(int elevatorId) {
        ElevatorCar elevator = getElevatorById(elevatorId);
        if (elevator != null) {
            elevator.getInsidePanel().pressOpenDoor();
            elevator.openDoor();
        }
    }

    public void closeDoor(int elevatorId) {
        ElevatorCar elevator = getElevatorById(elevatorId);
        if (elevator != null) {
            elevator.getInsidePanel().pressCloseDoor();
            elevator.closeDoor();
        }
    }

    public void boardPassenger(int elevatorId, Passenger passenger) {
        ElevatorCar elevator = getElevatorById(elevatorId);
        if (elevator != null) {
            elevator.boardPassenger(passenger);
        }
    }

    public void exitPassenger(int elevatorId, Passenger passenger) {
        ElevatorCar elevator = getElevatorById(elevatorId);
        if (elevator != null) {
            elevator.exitPassenger(passenger);
        }
    }

    public void processElevator(int elevatorId) {
        ElevatorCar elevator = getElevatorById(elevatorId);
        if (elevator != null) {
            elevator.processNextStop();
        }
    }

    public void printSystemStatus() {
        System.out.println("\n========== ELEVATOR SYSTEM STATUS ==========");
        for (ElevatorCar elevator : elevators) {
            System.out.println(elevator);
        }
        System.out.println("============================================\n");
    }

    private ElevatorCar getElevatorById(int elevatorId) {
        for (ElevatorCar elevator : elevators) {
            if (elevator.getElevatorId() == elevatorId) {
                return elevator;
            }
        }
        System.out.println("[ElevatorSystem] Elevator with ID " + elevatorId + " not found.");
        return null;
    }

    public List<ElevatorCar> getElevators() {
        return elevators;
    }

    public List<Floor> getFloors() {
        return floors;
    }
}
