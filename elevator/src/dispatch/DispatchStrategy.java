package dispatch;

import elevator.ElevatorCar;
import enums.Direction;

import java.util.List;

public interface DispatchStrategy {
    ElevatorCar selectElevator(List<ElevatorCar> elevators, int requestedFloor, Direction requestedDirection);
}
