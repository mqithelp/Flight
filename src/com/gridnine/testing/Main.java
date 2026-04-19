package com.gridnine.testing;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.filter.ArrivalBeforeDepartureFilter;
import com.gridnine.testing.filter.DepartureInPastFilter;
import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.filter.GroundTimeFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.FlightFilterService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Filter;

/*
Вывод 1 — после применения правила "вылет в прошлом"
Вывод 2 — после применения правила "прилёт раньше вылета"
Вывод 3 — после применения правила ">2ч на земле"
 */

public class Main {
    public static void main(String[] args) {
        // получить список перелётов
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println("ℹ️ Вылеты без фильтрации ℹ️ ");
        for (Flight flight : flights) {
            System.out.println("🛫 " + flight);
        }
        System.out.println("=".repeat(120));
// создать фильтр
        FlightFilterService service = new FlightFilterService();
        List<Flight> result = service.filter(flights, List.of(new ArrivalBeforeDepartureFilter()));
        printFilterResult("🧑‍✈️Результат применения фильтра \"прилёт раньше вылета\" ArrivalBeforeDepartureFilter🧑‍✈️", result);
//        System.out.println(result);
        System.out.println("🧑‍✈️Результат применения фильтра \"вылет в прошлом\" DepartureInPastFilter🧑‍✈️");
        result = service.filter(flights, List.of(new DepartureInPastFilter()));
        System.out.println(result);
        System.out.println("🧑‍✈️Результат применения фильтра \">2ч на земле\" GroundTimeFilter🧑‍✈️");
        result = service.filter(flights, List.of(new GroundTimeFilter()));
        System.out.println(result);

        System.out.println("🧑‍✈️Результат применения цепочки фильтров🧑‍✈️");
        List<FlightFilter> filters = new ArrayList<>();
        filters.add(new GroundTimeFilter());
        filters.add(new ArrivalBeforeDepartureFilter());
        List<Flight> results = service.filter(flights, filters);
// вывести в консоль
        for (Flight flight : results) {
            System.out.println(flight);
        }
    }
    private static void printFilterResult(String title, List<Flight> result){
        System.out.println(title);
    }
}