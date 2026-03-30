package panels;

import buttons.FloorButton;
import enums.Direction;

public class OutsidePanel {

    private final int floorNumber;
    private final FloorButton upButton;
    private final FloorButton downButton;

    public OutsidePanel(int floorNumber) {
        this.floorNumber = floorNumber;
        this.upButton = new FloorButton(floorNumber, Direction.UP);
        this.downButton = new FloorButton(floorNumber, Direction.DOWN);
    }

    public FloorButton pressUp() {
        upButton.press();
        return upButton;
    }

    public FloorButton pressDown() {
        downButton.press();
        return downButton;
    }

    public void resetUp() {
        upButton.reset();
    }

    public void resetDown() {
        downButton.reset();
    }

    public FloorButton getUpButton() {
        return upButton;
    }

    public FloorButton getDownButton() {
        return downButton;
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}
