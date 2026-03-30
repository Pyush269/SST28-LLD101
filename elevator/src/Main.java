import controller.ElevatorSystemController;
import elevator.ElevatorCar;
import enums.Direction;
import models.Passenger;

public class Main {

    public static void main(String[] args) {

        ElevatorSystemController system = ElevatorSystemController.getInstance();

        system.printSystemStatus();

        Passenger alice = new Passenger("Alice", 70, 0, 7);
        Passenger bob = new Passenger("Bob", 80, 3, 12);
        Passenger carol = new Passenger("Carol", 65, 5, 1);

        System.out.println("=== Scenario 1: Alice calls elevator from floor 0 going UP ===");
        ElevatorCar assignedToAlice = system.requestElevator(0, Direction.UP);
        if (assignedToAlice != null) {
            system.processElevator(assignedToAlice.getElevatorId());
            system.openDoor(assignedToAlice.getElevatorId());
            system.boardPassenger(assignedToAlice.getElevatorId(), alice);
            system.requestFloorFromInside(assignedToAlice.getElevatorId(), alice.getDestinationFloor());
            system.closeDoor(assignedToAlice.getElevatorId());
            system.processElevator(assignedToAlice.getElevatorId());
            system.openDoor(assignedToAlice.getElevatorId());
            system.exitPassenger(assignedToAlice.getElevatorId(), alice);
            system.closeDoor(assignedToAlice.getElevatorId());
        }

        System.out.println("\n=== Scenario 2: Bob calls elevator from floor 3 going UP ===");
        ElevatorCar assignedToBob = system.requestElevator(3, Direction.UP);
        if (assignedToBob != null) {
            system.processElevator(assignedToBob.getElevatorId());
            system.openDoor(assignedToBob.getElevatorId());
            system.boardPassenger(assignedToBob.getElevatorId(), bob);
            system.requestFloorFromInside(assignedToBob.getElevatorId(), bob.getDestinationFloor());
            system.closeDoor(assignedToBob.getElevatorId());
            system.processElevator(assignedToBob.getElevatorId());
            system.openDoor(assignedToBob.getElevatorId());
            system.exitPassenger(assignedToBob.getElevatorId(), bob);
            system.closeDoor(assignedToBob.getElevatorId());
        }

        System.out.println("\n=== Scenario 3: Carol calls elevator from floor 5 going DOWN ===");
        ElevatorCar assignedToCarol = system.requestElevator(5, Direction.DOWN);
        if (assignedToCarol != null) {
            system.processElevator(assignedToCarol.getElevatorId());
            system.openDoor(assignedToCarol.getElevatorId());
            system.boardPassenger(assignedToCarol.getElevatorId(), carol);
            system.requestFloorFromInside(assignedToCarol.getElevatorId(), carol.getDestinationFloor());
            system.closeDoor(assignedToCarol.getElevatorId());
            system.processElevator(assignedToCarol.getElevatorId());
            system.openDoor(assignedToCarol.getElevatorId());
            system.exitPassenger(assignedToCarol.getElevatorId(), carol);
            system.closeDoor(assignedToCarol.getElevatorId());
        }

        System.out.println("\n=== Scenario 4: Multiple passengers simultaneously ===");
        Passenger dan = new Passenger("Dan", 75, 2, 9);
        Passenger emma = new Passenger("Emma", 60, 8, 0);

        ElevatorCar assignedToDan = system.requestElevator(2, Direction.UP);
        ElevatorCar assignedToEmma = system.requestElevator(8, Direction.DOWN);

        if (assignedToDan != null) {
            system.processElevator(assignedToDan.getElevatorId());
            system.openDoor(assignedToDan.getElevatorId());
            system.boardPassenger(assignedToDan.getElevatorId(), dan);
            system.requestFloorFromInside(assignedToDan.getElevatorId(), dan.getDestinationFloor());
            system.closeDoor(assignedToDan.getElevatorId());
            system.processElevator(assignedToDan.getElevatorId());
        }

        int danId = (assignedToDan != null) ? assignedToDan.getElevatorId() : -1;
        if (assignedToEmma != null && assignedToEmma.getElevatorId() != danId) {
            system.processElevator(assignedToEmma.getElevatorId());
            system.openDoor(assignedToEmma.getElevatorId());
            system.boardPassenger(assignedToEmma.getElevatorId(), emma);
            system.requestFloorFromInside(assignedToEmma.getElevatorId(), emma.getDestinationFloor());
            system.closeDoor(assignedToEmma.getElevatorId());
            system.processElevator(assignedToEmma.getElevatorId());
        }

        system.printSystemStatus();
    }
}
