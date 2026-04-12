package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GroundTimeFilter implements FlightFilter {
    private static final long MAX_GROUND_HOURS = 2;

    @Override
    public List<Flight> filter(List<Flight> flights) {

        return flights.stream().filter(flight -> calculateGroundTime(flight).toHours() < MAX_GROUND_HOURS)
                .collect(Collectors.toList());
    }

    private Duration calculateGroundTime(Flight flight) {
        Duration timeOnGround = Duration.ZERO;
        List<Segment> segments = flight.getSegments();
        for (int i = 0; i < segments.size() - 1; i++) {
            timeOnGround = timeOnGround.plus(Duration.between(segments.get(i).getArrivalDate(), segments.get(i + 1).getDepartureDate()));
        }
        return timeOnGround;
    }
}
