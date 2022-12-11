package org.softuni.exam.structures;

import org.softuni.exam.entities.Airline;
import org.softuni.exam.entities.Flight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AirlinesManagerImpl implements AirlinesManager {

    private Map<String, Airline> airlinesByName;
    private Map<String, Flight> flightsById;
    private Map<String, List<Flight>> flightByAirline;



    public AirlinesManagerImpl() {
        this.airlinesByName = new HashMap<>();
        this.flightByAirline = new HashMap<>();
        this.flightsById = new HashMap<>();
    }

    @Override
    public void addAirline(Airline airline) {
        airlinesByName.put(airline.getName(), airline);
        flightByAirline.put(airline.getName(), new ArrayList<>());

    }

    @Override
    public void addFlight(Airline airline, Flight flight) {
        if (!contains(airline)) {
            throw new IllegalArgumentException();
        }

        flightByAirline.get(airline.getName()).add(flight);
        flightsById.put(flight.getId(), flight);

    }

    @Override
    public boolean contains(Airline airline) {
        return airlinesByName.containsKey(airline.getName());
    }

    @Override
    public boolean contains(Flight flight) {
        return flightsById.containsKey(flight.getId());
    }

    @Override
    public void deleteAirline(Airline airline) throws IllegalArgumentException {
        if (!contains(airline)) {
            throw new IllegalArgumentException();
        }

        airlinesByName.remove(airline.getName());

        List<Flight> flightToRemove = flightByAirline.remove(airline.getName());
        for (Flight flight : flightToRemove) {
            flightsById.remove(flight.getId());
        }

        flightByAirline.remove(airline.getName());

    }

    @Override
    public Iterable<Flight> getAllFlights() {
        return flightsById.values();
    }

    @Override
    public Flight performFlight(Airline airline, Flight flight) throws IllegalArgumentException {
        if (!contains(airline) || !contains(flight)) {
            throw new IllegalArgumentException();
        }

        flightsById.get(flight.getId()).setCompleted(true);


        return flightsById.get(flight.getId());
    }

    @Override
    public Iterable<Flight> getCompletedFlights() {
        return flightsById.values()
                .stream()
                .filter(f -> f.isCompleted() == true)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Flight> getFlightsOrderedByNumberThenByCompletion() {
        return flightsById.values()
                .stream()
                .sorted((f1, f2) -> {
                    int result = Boolean.compare(f1.isCompleted(), f2.isCompleted());
                    if (result == 0) {
                        result = f1.getNumber().compareTo(f2.getNumber());
                    }
                    return result;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Airline> getAirlinesOrderedByRatingThenByCountOfFlightsThenByName() {
        return airlinesByName.values()
                .stream()
                .sorted((a1, a2) -> {
                    int result = Double.compare(a2.getRating(), a1.getRating());
                    if (result == 0) {
                        int a1FlightCount = flightByAirline.get(a1.getName()).size();
                        int a2FlightCount = flightByAirline.get(a2.getName()).size();

                        result = Integer.compare(a2FlightCount, a1FlightCount);

                        if (result == 0) {
                            result = a1.getName().compareTo(a2.getName());
                        }

                    }

                    return result;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Airline> getAirlinesWithFlightsFromOriginToDestination(String origin, String destination) {
        return null;
    }
}
