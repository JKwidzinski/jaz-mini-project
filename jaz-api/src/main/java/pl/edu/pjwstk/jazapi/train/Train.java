package pl.edu.pjwstk.jazapi.train;

public class Train {
    private static int countId = 0;
    private int trainId;
    private int segmentAmount;
    private int segmentCapacity;
    private int trainCapacity;
    private int passengersInside;
    private int currentStationId;
    private boolean goingForward;

    public Train(int segmentAmount, int segmentCapacity){
        setTrainId(++countId);
        this.segmentAmount = segmentAmount;
        this.segmentCapacity = segmentCapacity;
        this.trainCapacity = segmentAmount * segmentCapacity;
        this.passengersInside = 0;
        this.currentStationId = 1;
        this.goingForward = true;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getSegmentAmount() {
        return segmentAmount;
    }

    public void setSegmentAmount(int segmentAmount) {
        this.segmentAmount = segmentAmount;
    }

    public int getSegmentCapacity() {
        return segmentCapacity;
    }

    public void setSegmentCapacity(int segmentCapacity) {
        this.segmentCapacity = segmentCapacity;
    }

    public int getTrainCapacity() {
        return trainCapacity;
    }

    public int getPassengersInside() {
        return passengersInside;
    }

    public void setPassengersInside(int passengersInside) {
        this.passengersInside = passengersInside;
    }

    public int getCurrentStationId() {
        return currentStationId;
    }

    public void setCurrentStationId(int currentStationId) {
        this.currentStationId = currentStationId;
    }

    public boolean isGoingForward() { return goingForward; }

    public void setGoingForward(boolean goingForward) { this.goingForward = goingForward; }

    public String toString(){
        return "Id: " + trainId + " Segment Amount: " + segmentAmount + " Segment Capacity: " + segmentCapacity + " Train Capacity: " + trainCapacity + " Passengers inside: " + passengersInside + " Current station id:" + currentStationId + " <br />";
    }
}
