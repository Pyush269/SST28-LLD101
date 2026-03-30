package elevator;

import enums.DoorStatus;
import enums.ElevatorStatus;

public class Door {

    private DoorStatus status;
    private final int elevatorId;

    public Door(int elevatorId) {
        this.elevatorId = elevatorId;
        this.status = DoorStatus.CLOSED;
    }

    public void open(ElevatorStatus elevatorStatus) {
        if (elevatorStatus != ElevatorStatus.IDLE) {
            System.out.println("[Door - Elevator " + elevatorId + "] Cannot open door while elevator is in motion.");
            return;
        }
        this.status = DoorStatus.OPEN;
        System.out.println("[Door - Elevator " + elevatorId + "] Door OPENED.");
    }

    public void close() {
        this.status = DoorStatus.CLOSED;
        System.out.println("[Door - Elevator " + elevatorId + "] Door CLOSED.");
    }

    public DoorStatus getStatus() {
        return status;
    }
}
