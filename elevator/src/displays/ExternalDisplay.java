package displays;

import enums.Direction;

public class ExternalDisplay implements Display {

    private final int elevatorId;
    private int currentFloor;
    private Direction direction;

    public ExternalDisplay(int elevatorId) {
        this.elevatorId = elevatorId;
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
    }

    @Override
    public void showFloor(int floor) {
        this.currentFloor = floor;
        System.out.println("[External Display - Elevator " + elevatorId + "] Floor: " + currentFloor);
    }

    @Override
    public void showDirection(Direction direction) {
        this.direction = direction;
        System.out.println("[External Display - Elevator " + elevatorId + "] Direction: " + direction);
    }

    @Override
    public void update(int floor, Direction direction) {
        this.currentFloor = floor;
        this.direction = direction;
        System.out.println("[External Display - Elevator " + elevatorId + "] Floor: " + currentFloor + " | Direction: " + direction);
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }
}
