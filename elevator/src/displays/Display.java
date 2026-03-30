package displays;

import enums.Direction;

public interface Display {
    void showFloor(int floor);
    void showDirection(Direction direction);
    void update(int floor, Direction direction);
}
