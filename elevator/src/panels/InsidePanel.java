package panels;

import buttons.DoorButton;
import buttons.ElevatorButton;

import java.util.HashMap;
import java.util.Map;

public class InsidePanel {

    private final int elevatorId;
    private final Map<Integer, ElevatorButton> floorButtons;
    private final DoorButton openDoorButton;
    private final DoorButton closeDoorButton;

    public InsidePanel(int elevatorId, int totalFloors) {
        this.elevatorId = elevatorId;
        this.floorButtons = new HashMap<>();
        for (int i = 0; i < totalFloors; i++) {
            floorButtons.put(i, new ElevatorButton(i, elevatorId));
        }
        this.openDoorButton = new DoorButton(DoorButton.DoorAction.OPEN, elevatorId);
        this.closeDoorButton = new DoorButton(DoorButton.DoorAction.CLOSE, elevatorId);
    }

    public ElevatorButton pressFloor(int floor) {
        if (!floorButtons.containsKey(floor)) {
            throw new IllegalArgumentException("Floor " + floor + " does not exist.");
        }
        ElevatorButton button = floorButtons.get(floor);
        button.press();
        return button;
    }

    public DoorButton pressOpenDoor() {
        openDoorButton.press();
        return openDoorButton;
    }

    public DoorButton pressCloseDoor() {
        closeDoorButton.press();
        return closeDoorButton;
    }

    public void resetFloorButton(int floor) {
        if (floorButtons.containsKey(floor)) {
            floorButtons.get(floor).reset();
        }
    }

    public Map<Integer, ElevatorButton> getFloorButtons() {
        return floorButtons;
    }

    public DoorButton getOpenDoorButton() {
        return openDoorButton;
    }

    public DoorButton getCloseDoorButton() {
        return closeDoorButton;
    }

    public int getElevatorId() {
        return elevatorId;
    }
}
