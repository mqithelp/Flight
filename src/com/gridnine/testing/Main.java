package com.gridnine.testing;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.filter.DepartureInPastFilter;
import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.model.Flight;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // получить список перелётов
        List<Flight> flights = FlightBuilder.createFlights();

// создать фильтр
        FlightFilter filter = new DepartureInPastFilter();

// применить фильтр
        List<Flight> result = filter.filter(flights);

// вывести в консоль
        System.out.println(result);
    }
}