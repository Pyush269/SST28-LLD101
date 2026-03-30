package buttons;

import enums.Direction;

public class FloorButton extends Button {

    private final int floor;
    private final Direction direction;

    public FloorButton(int floor, Direction direction) {
        super();
        this.floor = floor;
        this.direction = direction;
    }

    @Override
    protected void onPress() {
        System.out.println("[Floor Button] Pressed at Floor " + floor + " | Direction: " + direction);
    }

    public int getFloor() {
        return floor;
    }

    public Direction getDirection() {
        return direction;
    }
}
