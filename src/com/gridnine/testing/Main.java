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
import java.util.logging.Filter;

public class Main {
    public static void main(String[] args) {
        // получить список перелётов
        List<Flight> flights = FlightBuilder.createFlights();
     for (Flight flight: flights) {
            System.out.println(flight);
        }
        System.out.println("--------------------------------------------");
// создать фильтр
        FlightFilterService service = new FlightFilterService();
        List<FlightFilter> filters = new ArrayList<>();
        filters.add(new GroundTimeFilter());
        filters.add(new ArrivalBeforeDepartureFilter());
        List<Flight> results = service.filter(flights,filters);


// вывести в консоль
        for (Flight result: results) {
            System.out.println(result);
        }
    }
}