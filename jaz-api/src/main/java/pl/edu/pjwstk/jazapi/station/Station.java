package pl.edu.pjwstk.jazapi.station;

public class Station {
    private static int countId = 0;
    private int stationId;
    private String stationName;

    public Station(String stationName){
        setStationId(++countId);
        this.stationName = stationName;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
    
    public String toString(){
        return "Id: " + stationId + " Station name: " + stationName + "<br />";
    }
}
