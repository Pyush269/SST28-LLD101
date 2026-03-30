package dispatch;

import elevator.ElevatorCar;
import enums.Direction;
import enums.ElevatorStatus;

import java.util.List;

public class SmartDispatchStrategy implements DispatchStrategy {

    @Override
    public ElevatorCar selectElevator(List<ElevatorCar> elevators, int requestedFloor, Direction requestedDirection) {
        ElevatorCar bestElevator = null;
        int bestScore = Integer.MAX_VALUE;

        for (ElevatorCar elevator : elevators) {
            if (elevator.isAtCapacity()) {
                continue;
            }

            int score = computeScore(elevator, requestedFloor, requestedDirection);

            if (score < bestScore) {
                bestScore = score;
                bestElevator = elevator;
            }
        }

        if (bestElevator == null) {
            System.out.println("[SmartDispatch] All elevators at capacity. Request queued.");
        }

        return bestElevator;
    }

    private int computeScore(ElevatorCar elevator, int requestedFloor, Direction requestedDirection) {
        int distance = Math.abs(elevator.getCurrentFloor() - requestedFloor);

        if (elevator.getStatus() == ElevatorStatus.IDLE) {
            return distance;
        }

        boolean movingTowardRequest = isMovingToward(elevator, requestedFloor);
        boolean sameDirectionAsRequest = elevator.getDirection() == requestedDirection;

        if (movingTowardRequest && sameDirectionAsRequest) {
            return distance;
        }

        if (movingTowardRequest) {
            return distance + 5;
        }

        return distance + 20;
    }

    private boolean isMovingToward(ElevatorCar elevator, int requestedFloor) {
        if (elevator.getDirection() == Direction.UP) {
            return requestedFloor > elevator.getCurrentFloor();
        }
        if (elevator.getDirection() == Direction.DOWN) {
            return requestedFloor < elevator.getCurrentFloor();
        }
        return false;
    }
}
