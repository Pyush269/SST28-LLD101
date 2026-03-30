package buttons;

public class DoorButton extends Button {

    public enum DoorAction {
        OPEN,
        CLOSE
    }

    private final DoorAction action;
    private final int elevatorId;

    public DoorButton(DoorAction action, int elevatorId) {
        super();
        this.action = action;
        this.elevatorId = elevatorId;
    }

    @Override
    protected void onPress() {
        System.out.println("[Door Button - Elevator " + elevatorId + "] Door " + action + " requested.");
    }

    public DoorAction getAction() {
        return action;
    }

    public int getElevatorId() {
        return elevatorId;
    }
}
