package models;

import panels.OutsidePanel;

public class Floor {

    private final int floorNumber;
    private final OutsidePanel outsidePanel;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.outsidePanel = new OutsidePanel(floorNumber);
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public OutsidePanel getOutsidePanel() {
        return outsidePanel;
    }
}
