package t1.gorchanyuk.timetracking.example;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import t1.gorchanyuk.timetracking.example.model.Plant;
import t1.gorchanyuk.timetracking.example.service.PlantService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Example {

    private final PlantService plantService;

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        plantService.addPlant(new Plant("Роза", "Цветок"));
        plantService.addPlant(new Plant("Тюльпан", "Цветок"));

        System.out.println(plantService.getPlantsByType("Цветок"));
        System.out.println(plantService.getPlantByName("Роза"));

        plantService.addPlants(List.of(new Plant("Кукуруза", "трава"), new Plant("Дуб", "дерево")));
    }
}
