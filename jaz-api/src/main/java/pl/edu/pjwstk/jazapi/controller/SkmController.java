package pl.edu.pjwstk.jazapi.controller;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jazapi.skm.Skm;

@RestController
public class SkmController {

    private Skm skm;

    public SkmController(){
        skm = new Skm(4,10,50);
    }

    @GetMapping("/stations")
    private String displayStationsList(){
        return skm.displayStationList();
    }

    @GetMapping("/trains")
    private String displayTrainsList(){
        return skm.displayTrainList();
    }

    @GetMapping("/trains/{trainId}")
    private String displayTrainById(@PathVariable int trainId){
        return skm.displayTrainById(trainId);
    }

    @GetMapping("/passengers/inside/{trainId}")
    private String displayPassengersByTrainId(@PathVariable int trainId) {return skm.displayPassengerByTrainId(trainId);}

    @PostMapping("/forward")
    private void forward(){ skm.forward(); }
}
