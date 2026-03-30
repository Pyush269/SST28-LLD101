package elevator;

import displays.ExternalDisplay;
import displays.InternalDisplay;
import enums.Direction;
import enums.DoorStatus;
import enums.ElevatorStatus;
import models.Passenger;
import panels.InsidePanel;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class ElevatorCar {

    public static final int MAX_PASSENGERS = 8;
    public static final int MAX_CAPACITY_KG = 680;

    private final int elevatorId;
    private int currentFloor;
    private Direction direction;
    private ElevatorStatus status;

    private final Door door;
    private final InsidePanel insidePanel;
    private final InternalDisplay internalDisplay;
    private final ExternalDisplay externalDisplay;

    private final List<Passenger> passengers;
    private int currentLoadKg;

    private final TreeSet<Integer> upQueue;
    private final TreeSet<Integer> downQueue;

    public ElevatorCar(int elevatorId, int totalFloors) {
        this.elevatorId = elevatorId;
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
        this.status = ElevatorStatus.IDLE;

        this.door = new Door(elevatorId);
        this.insidePanel = new InsidePanel(elevatorId, totalFloors);
        this.internalDisplay = new InternalDisplay(elevatorId, MAX_CAPACITY_KG);
        this.externalDisplay = new ExternalDisplay(elevatorId);

        this.passengers = new ArrayList<>();
        this.currentLoadKg = 0;

        this.upQueue = new TreeSet<>();
        this.downQueue = new TreeSet<>();
    }

    public void addDestination(int floor) {
        if (floor == currentFloor) {
            return;
        }
        if (floor > currentFloor) {
            upQueue.add(floor);
        } else {
            downQueue.add(floor);
        }
        System.out.println("[Elevator " + elevatorId + "] Destination floor " + floor + " queued.");
    }

    public void boardPassenger(Passenger passenger) {
        if (passengers.size() >= MAX_PASSENGERS) {
            System.out.println("[Elevator " + elevatorId + "] At passenger capacity. Cannot board " + passenger.getName() + ".");
            return;
        }
        if (currentLoadKg + passenger.getWeightKg() > MAX_CAPACITY_KG) {
            System.out.println("[Elevator " + elevatorId + "] Weight limit exceeded. Cannot board " + passenger.getName() + ".");
            return;
        }
        if (door.getStatus() != DoorStatus.OPEN) {
            System.out.println("[Elevator " + elevatorId + "] Door is closed. Cannot board " + passenger.getName() + ".");
            return;
        }
        passengers.add(passenger);
        currentLoadKg += passenger.getWeightKg();
        internalDisplay.updateLoad(currentLoadKg);
        System.out.println("[Elevator " + elevatorId + "] Passenger " + passenger.getName() + " boarded. Total passengers: " + passengers.size());
    }

    public void exitPassenger(Passenger passenger) {
        if (passengers.remove(passenger)) {
            currentLoadKg -= passenger.getWeightKg();
            internalDisplay.updateLoad(currentLoadKg);
            System.out.println("[Elevator " + elevatorId + "] Passenger " + passenger.getName() + " exited. Total passengers: " + passengers.size());
        }
    }

    public void openDoor() {
        door.open(status);
    }

    public void closeDoor() {
        door.close();
    }

    public void moveUp() {
        if (door.getStatus() == DoorStatus.OPEN) {
            System.out.println("[Elevator " + elevatorId + "] Cannot move. Door is open.");
            return;
        }
        status = ElevatorStatus.MOVING;
        direction = Direction.UP;
        currentFloor++;
        updateDisplays();
        System.out.println("[Elevator " + elevatorId + "] Moving UP to floor " + currentFloor);
    }

    public void moveDown() {
        if (door.getStatus() == DoorStatus.OPEN) {
            System.out.println("[Elevator " + elevatorId + "] Cannot move. Door is open.");
            return;
        }
        if (currentFloor == 0) {
            System.out.println("[Elevator " + elevatorId + "] Already at ground floor.");
            return;
        }
        status = ElevatorStatus.MOVING;
        direction = Direction.DOWN;
        currentFloor--;
        updateDisplays();
        System.out.println("[Elevator " + elevatorId + "] Moving DOWN to floor " + currentFloor);
    }

    public void processNextStop() {
        if (!upQueue.isEmpty() && (direction == Direction.UP || direction == Direction.IDLE)) {
            int nextStop = upQueue.first();
            while (currentFloor < nextStop) {
                moveUp();
            }
            upQueue.remove(nextStop);
            arriveAtFloor(nextStop);
        } else if (!downQueue.isEmpty() && (direction == Direction.DOWN || direction == Direction.IDLE)) {
            int nextStop = downQueue.last();
            while (currentFloor > nextStop) {
                moveDown();
            }
            downQueue.remove(nextStop);
            arriveAtFloor(nextStop);
        } else if (!upQueue.isEmpty()) {
            direction = Direction.UP;
            processNextStop();
        } else if (!downQueue.isEmpty()) {
            direction = Direction.DOWN;
            processNextStop();
        } else {
            setIdle();
        }
    }

    private void arriveAtFloor(int floor) {
        status = ElevatorStatus.IDLE;
        direction = (upQueue.isEmpty() && downQueue.isEmpty()) ? Direction.IDLE : direction;
        System.out.println("[Elevator " + elevatorId + "] Arrived at floor " + floor);
        openDoor();
        updateDisplays();
        insidePanel.resetFloorButton(floor);
    }

    private void setIdle() {
        status = ElevatorStatus.IDLE;
        direction = Direction.IDLE;
        updateDisplays();
        System.out.println("[Elevator " + elevatorId + "] Now IDLE at floor " + currentFloor);
    }

    private void updateDisplays() {
        internalDisplay.update(currentFloor, direction);
        externalDisplay.update(currentFloor, direction);
    }

    public int getElevatorId() {
        return elevatorId;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public ElevatorStatus getStatus() {
        return status;
    }

    public Door getDoor() {
        return door;
    }

    public InsidePanel getInsidePanel() {
        return insidePanel;
    }

    public InternalDisplay getInternalDisplay() {
        return internalDisplay;
    }

    public ExternalDisplay getExternalDisplay() {
        return externalDisplay;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public int getCurrentLoadKg() {
        return currentLoadKg;
    }

    public TreeSet<Integer> getUpQueue() {
        return upQueue;
    }

    public TreeSet<Integer> getDownQueue() {
        return downQueue;
    }

    public boolean isAtCapacity() {
        return passengers.size() >= MAX_PASSENGERS || currentLoadKg >= MAX_CAPACITY_KG;
    }

    @Override
    public String toString() {
        return "ElevatorCar{id=" + elevatorId + ", floor=" + currentFloor + ", direction=" + direction
                + ", status=" + status + ", passengers=" + passengers.size() + "/" + MAX_PASSENGERS
                + ", load=" + currentLoadKg + "/" + MAX_CAPACITY_KG + "kg}";
    }
}
