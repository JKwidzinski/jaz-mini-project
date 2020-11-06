package pl.edu.pjwstk.jazapi.skm;

import pl.edu.pjwstk.jazapi.passenger.Passenger;
import pl.edu.pjwstk.jazapi.station.Station;
import pl.edu.pjwstk.jazapi.train.Train;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Skm {
    private int numberOfTrains;
    private int segmentAmount;
    private int segmentCapacity;
    private List<Station> stationList = getStationList();
    private List<Train> trainList = getTrainList();
    private List<Passenger> passengerList = new ArrayList<>();
    private List<Integer> idle = new ArrayList<>();
    private static int turn = 1;

    public Skm(int numberOfTrains, int segmentAmount, int segmentCapacity){
        this.numberOfTrains = numberOfTrains;
        this.segmentAmount = segmentAmount;
        this.segmentCapacity = segmentCapacity;
    }

    public List<Station> getStationList(){
        List<Station> stationList = new ArrayList<>();
        stationList.add(new Station ("Gdynia Główna"));
        stationList.add(new Station ("Gdynia Wzgórze św.Maksym."));
        stationList.add(new Station ("Gdynia Redłowo"));
        stationList.add(new Station ("Gdynia Orłowo"));
        stationList.add(new Station ("Sopot Kamienny Potok"));
        stationList.add(new Station ("Sopot"));
        stationList.add(new Station ("Sopot Wyścigi"));
        stationList.add(new Station ("Gdańsk Żabianka-AWFiS"));
        stationList.add(new Station ("Gdańsk Oliwa"));
        stationList.add(new Station ("Gdańsk Przymorze-Uniwer."));
        stationList.add(new Station ("Gdańsk Zaspa"));
        stationList.add(new Station ("Gdańsk Wrzeszcz"));
        stationList.add(new Station ("Gdańsk Politechnika"));
        stationList.add(new Station ("Gdańsk Stocznia"));
        stationList.add(new Station ("Gdańsk Główny"));
        return stationList;
    }

    public String displayStationList(){
        String stations = "";
        for(Station station : stationList){
             stations += station.toString();
        }
        return stations;
    }

    public List<Train> getTrainList(){
        List<Train> trainList = new ArrayList<>();
        for(int i=0;i<numberOfTrains;i++){
            Train train = new Train(segmentAmount, segmentCapacity);
            train.setCurrentStationId(i+1);
            trainList.add(train);
            createPassengers(train.getTrainId());
        }
        return trainList;
    }

    public String displayTrainList(){
        String trains = "";
        for(Train train : trainList){
            trains += train.toString();
        }
        return trains;
    }
    
    public Train getTrainById(int trainId){
        for(Train train : trainList){
            if(trainId == train.getTrainId()){
                return train;
            }
        }
        Train newTrain;
        return newTrain = new Train(segmentAmount, segmentCapacity);
    }

    public String displayTrainById(int trainId){
        for(Train train : trainList){
            if(trainId == train.getTrainId()){
                return train.toString();
            }
        }
        return "There is no train with such ID";
    }

    public Station getStationById(int stationId){
        for(Station station : stationList){
            if(stationId == station.getStationId()){
                return station;
            }
        }
        return stationList.get(0);
    }

    public void createPassengers(int trainId){
        List<Passenger> passengers = new ArrayList<>();
        Random rand = new Random();
        int numberOfPassengers = rand.nextInt() * 10 + 1;
        for(int i = 0;i<numberOfPassengers;i++){
            int currentStation = getTrainById(trainId).getCurrentStationId();
            int targetStationId;
            do{
                targetStationId = rand.nextInt() * 15 + 1;
            }while(targetStationId == currentStation);
            passengers.add(new Passenger(currentStation, getStationById(targetStationId)));
        }
        passengerList.addAll(passengers);
    }

    public String displayPassengerByTrainId(int trainId){
        List<Passenger> listOfPassengers = new ArrayList<>();
        listOfPassengers = passengerList.stream().filter(passenger -> passenger.getInsideTrainId() == trainId).collect(Collectors.toList());
        String passengers = "";
        for(Passenger passenger : listOfPassengers){
            passengers += passenger.toString();
        }
        return "There is no one in this train";
    }

    public List<Passenger> getPassengerByTrainId(int trainId){
        List<Passenger> listOfPassengers = new ArrayList<>();
        listOfPassengers = passengerList.stream().filter(passenger -> passenger.getInsideTrainId() == trainId).collect(Collectors.toList());
        return listOfPassengers;
    }

    public void setIdleAndChangeDirection(Train train){
        if((train.getCurrentStationId() == 1) && (idle.get(train.getTrainId() - 1) != 2)){
            idle.set(train.getTrainId() - 1, 0);
            train.setGoingForward(true);
        }else if ((train.getCurrentStationId() == 15) && (idle.get(train.getTrainId() - 1) != 2)){
            idle.set(train.getTrainId() - 1, 0);
            train.setGoingForward(false);
        }
    }

    public void moveTrain(Train train){
        int current = train.getCurrentStationId();
        int idleTurn = idle.get(train.getTrainId());
        if((current == 1 && idleTurn == 0) || (current == 1 && idleTurn == 1) || (current == 15 && idleTurn == 0) || (current == 15 && idleTurn == 1) ){
            idleTurn += 1;
            idle.set(train.getTrainId() - 1, idleTurn);
        }
        if((train.isGoingForward() == true) && ((idleTurn - 1) == 0)){
            train.setCurrentStationId(current + 1);
        }else if ((train.isGoingForward() == false) && ((idleTurn - 1) == 0)){
            train.setCurrentStationId(current - 1);
        }
    }

    public void process(){
        for (Train train : trainList) {
            setIdleAndChangeDirection(train);
            moveTrain(train);
            List<Passenger> listOfPassengers = getPassengerByTrainId(train.getTrainId());
            for(Passenger passenger : listOfPassengers){
                if(passenger.getTargetStation() == getStationById(train.getCurrentStationId())){
                    passengerList.remove(passenger);
                }
            }
            createPassengers(train.getTrainId());
        }
    }

    public void forward(){
        if (turn == 1) {
            turn++;
            for (int i = 0; i < numberOfTrains; i++) {
                idle.add(0);
            }
        }
        process();
    }
}
