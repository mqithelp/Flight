package com.gridnine.testing;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.filter.ArrivalBeforeDepartureFilter;
import com.gridnine.testing.filter.DepartureInPastFilter;
import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.filter.GroundTimeFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.FlightFilterService;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // получить список перелётов
        List<Flight> flights = FlightBuilder.createFlights();
        printFilterResult("ℹ️ Вылеты без фильтрации ℹ️ ",flights);

        // фильтр
        FlightFilterService service = new FlightFilterService();

        List<Flight> result = service.filter(flights, List.of(new ArrivalBeforeDepartureFilter()));
        printFilterResult("🧑‍✈️Результат применения фильтра \"прилёт раньше вылета\" ArrivalBeforeDepartureFilter🧑‍✈️", result);

        result = service.filter(flights, List.of(new DepartureInPastFilter()));
        printFilterResult("🧑‍✈️Результат применения фильтра \"вылет в прошлом\" DepartureInPastFilter🧑‍✈️", result);

        result = service.filter(flights, List.of(new GroundTimeFilter()));
        printFilterResult("🧑‍✈️Результат применения фильтра \">2ч на земле\" GroundTimeFilter🧑‍✈️", result);

        List<FlightFilter> filters = new ArrayList<>();
        filters.add(new GroundTimeFilter());
        filters.add(new ArrivalBeforeDepartureFilter());
        List<Flight> results = service.filter(flights, filters);
        printFilterResult("🧑‍✈️Результат применения цепочки фильтров🧑‍✈️", results);

    }

    private static void printFilterResult(String title, List<Flight> result) {
        System.out.println(title);
        result.forEach(r -> System.out.println("\t Перелет " + r));
        System.out.println("-".repeat(80));
    }
}