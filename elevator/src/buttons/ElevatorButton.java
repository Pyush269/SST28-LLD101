package buttons;

public class ElevatorButton extends Button {

    private final int destinationFloor;
    private final int elevatorId;

    public ElevatorButton(int destinationFloor, int elevatorId) {
        super();
        this.destinationFloor = destinationFloor;
        this.elevatorId = elevatorId;
    }

    @Override
    protected void onPress() {
        System.out.println("[Elevator Button - Elevator " + elevatorId + "] Destination Floor " + destinationFloor + " selected.");
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public int getElevatorId() {
        return elevatorId;
    }
}
