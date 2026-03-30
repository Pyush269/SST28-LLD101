package models;

public class Passenger {

    private static int idCounter = 1;

    private final int passengerId;
    private final String name;
    private final int weightKg;
    private int currentFloor;
    private int destinationFloor;

    public Passenger(String name, int weightKg, int currentFloor, int destinationFloor) {
        this.passengerId = idCounter++;
        this.name = name;
        this.weightKg = weightKg;
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public String getName() {
        return name;
    }

    public int getWeightKg() {
        return weightKg;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    @Override
    public String toString() {
        return "Passenger{id=" + passengerId + ", name='" + name + "', weight=" + weightKg
                + "kg, from=" + currentFloor + ", to=" + destinationFloor + "}";
    }
}
