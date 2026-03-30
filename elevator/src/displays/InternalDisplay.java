package displays;

import enums.Direction;

public class InternalDisplay implements Display {

    private final int elevatorId;
    private int currentFloor;
    private Direction direction;
    private int currentLoad;
    private final int maxCapacityKg;

    public InternalDisplay(int elevatorId, int maxCapacityKg) {
        this.elevatorId = elevatorId;
        this.maxCapacityKg = maxCapacityKg;
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
        this.currentLoad = 0;
    }

    @Override
    public void showFloor(int floor) {
        this.currentFloor = floor;
        System.out.println("[Internal Display - Elevator " + elevatorId + "] Floor: " + currentFloor);
    }

    @Override
    public void showDirection(Direction direction) {
        this.direction = direction;
        System.out.println("[Internal Display - Elevator " + elevatorId + "] Direction: " + direction);
    }

    @Override
    public void update(int floor, Direction direction) {
        this.currentFloor = floor;
        this.direction = direction;
        System.out.println("[Internal Display - Elevator " + elevatorId + "] Floor: " + currentFloor
                + " | Direction: " + direction + " | Load: " + currentLoad + "/" + maxCapacityKg + " kg");
    }

    public void updateLoad(int loadKg) {
        this.currentLoad = loadKg;
        System.out.println("[Internal Display - Elevator " + elevatorId + "] Current Load: " + currentLoad + "/" + maxCapacityKg + " kg");
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getCurrentLoad() {
        return currentLoad;
    }
}
