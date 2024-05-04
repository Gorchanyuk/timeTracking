package t1.gorchanyuk.homework;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import t1.gorchanyuk.homework.example.model.Plant;
import t1.gorchanyuk.homework.example.service.PlantService;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class HomeWorkApplication {


    private final PlantService plantService;

    public static void main(String[] args) {
        SpringApplication.run(HomeWorkApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady(){
        plantService.addPlant(new Plant("Роза", "Цветок"));
        plantService.addPlant(new Plant("Тюльпан", "Цветок"));

        System.out.println(plantService.getPlantsByType("Цветок"));
        System.out.println(plantService.getPlantByName("Роза"));

        plantService.addPlants(List.of(new Plant("Кукуруза", "трава"), new Plant("Дуб", "дерево")));
    }

}
