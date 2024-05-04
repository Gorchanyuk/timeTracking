package t1.gorchanyuk.homework.example.service;

import org.springframework.stereotype.Service;
import t1.gorchanyuk.homework.annotation.TrackAsyncTime;
import t1.gorchanyuk.homework.annotation.TrackTime;
import t1.gorchanyuk.homework.example.model.Plant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PlantService {

    private final Map<String, Plant> plants = new HashMap<>();

    @TrackTime
    public void addPlant(Plant plant){
        plants.put(plant.getName(), plant);
    }

    @TrackAsyncTime
    public void addPlants(List<Plant> plantList){
        plants.putAll(plantList.stream().collect(Collectors.toMap(Plant::getName, Function.identity())));
    }

    @TrackTime
    public Plant getPlantByName (String name){
        return plants.get(name);
    }

    @TrackTime
    public  List<Plant> getPlantsByType (String type){
        return plants.values().stream().filter(plant-> plant.getType().equals(type)).collect(Collectors.toList());
    }
}