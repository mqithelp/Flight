package com.gridnine.testing.service;

import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.model.Flight;

import java.util.ArrayList;
import java.util.List;

public class FlightFilterService {
    public List<Flight> filter(List<Flight> flights, List<FlightFilter> filters) {
        if (flights == null) {
            throw new IllegalArgumentException("Flights is null");
        }
        if (filters == null) {
            throw new IllegalArgumentException("Filters is null");
        }
        List<Flight> results = new ArrayList<>(flights);
        for (FlightFilter filter : filters) {
            results = (filter.filter(results));
        }
        return results;
    }
}

