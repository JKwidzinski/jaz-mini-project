package pl.edu.pjwstk.jazapi.passenger;

import pl.edu.pjwstk.jazapi.station.Station;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Passenger {
    private List<String> nameList = Arrays.asList("Jakub", "Daniel", "Adam", "Filip", "Janusz", "Jarosław");
    private List<String> surnameList = Arrays.asList("Kowalski", "Adamczyk", "Kaczyński", "Nowak", "Wiśniewski", "Wójcik");
    private static int countId=0;
    private int passengerId;
    private String name;
    private String surname;
    private int insideTrainId;
    private Station targetStation;


    public Passenger(int insideTrainId, Station targetStation){
        setPassengerId(++countId);
        this.name = randomName();
        this.surname = randomSurname();
        this.insideTrainId = insideTrainId;
        this.targetStation = targetStation;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getInsideTrainId() {
        return insideTrainId;
    }

    public void setInsideTrainId(int insideTrainId) {
        this.insideTrainId = insideTrainId;
    }

    public Station getTargetStation() {
        return targetStation;
    }

    public void setTargetStation(Station targetStation) {
        this.targetStation = targetStation;
    }

    public String randomName(){
        Random rand = new Random();
        name = nameList.get(rand.nextInt(nameList.size()));
        return name;
    }

    public String randomSurname(){
        Random rand = new Random();
        surname = surnameList.get(rand.nextInt(surnameList.size()));
        return surname;
    }

    public String toString(){
        return "Id: " + passengerId + " Name: " + name + " Surname: " + surname + "Inside train with id: " + insideTrainId + " Target station: " + targetStation + "<br />";
    }
}
