package goldDigger.core;

import goldDigger.models.discoverer.Anthropologist;
import goldDigger.models.discoverer.Archaeologist;
import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.discoverer.Geologist;
import goldDigger.models.operation.Operation;
import goldDigger.models.operation.OperationImpl;
import goldDigger.models.spot.Spot;
import goldDigger.models.spot.SpotImpl;
import goldDigger.repositories.DiscovererRepository;
import goldDigger.repositories.SpotRepository;

import java.util.List;
import java.util.stream.Collectors;

import static goldDigger.common.ConstantMessages.*;
import static goldDigger.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private DiscovererRepository discovererRepository;
    private SpotRepository spotRepository;
    private int spotCount;

    public ControllerImpl() {
        discovererRepository = new DiscovererRepository();
        spotRepository = new SpotRepository();
    }

    @Override
    public String addDiscoverer(String kind, String discovererName) {
        Discoverer discoverer;
        if (kind.equals("Anthropologist")) {
            discoverer = new Anthropologist(discovererName);

        } else if (kind.equals("Archaeologist")) {
            discoverer = new Archaeologist(discovererName);

        } else if (kind.equals("Geologist")) {
            discoverer = new Geologist(discovererName);

        } else {
            throw new IllegalArgumentException(DISCOVERER_INVALID_KIND);
        }

        discovererRepository.add(discoverer);

        return String.format(DISCOVERER_ADDED, kind, discovererName);
    }

    @Override
    public String addSpot(String spotName, String... exhibits) {
        Spot spot = new SpotImpl(spotName);

        for (String exhibit : exhibits) {
            spot.getExhibits().add(exhibit);
        }

        spotRepository.add(spot);
        return String.format(SPOT_ADDED, spotName);
    }

    @Override
    public String excludeDiscoverer(String discovererName) {
        Discoverer discoverer = discovererRepository.byName(discovererName);

        if (discoverer == null) {
            throw new IllegalArgumentException(String.format(DISCOVERER_DOES_NOT_EXIST, discovererName));
        }
        discovererRepository.remove(discoverer);

        return String.format(DISCOVERER_EXCLUDE, discovererName);
    }

    @Override
    public String inspectSpot(String spotName) {
        List<Discoverer> currentDiscoverers = discovererRepository.getCollection()
                .stream()
                .filter(discoverer -> discoverer.getEnergy() > 45)
                .collect(Collectors.toList());

        if (currentDiscoverers.isEmpty()) {
            throw new IllegalArgumentException(SPOT_DISCOVERERS_DOES_NOT_EXISTS);
        }
        Spot spot = spotRepository.byName(spotName);
        Operation operation = new OperationImpl();
        operation.startOperation(spot, currentDiscoverers);
        long excluded = currentDiscoverers.stream().filter(discoverer -> discoverer.getEnergy() == 0).count();
        this.spotCount++; // for getStatistics()

        return String.format(INSPECT_SPOT, spotName, excluded);
    }

    @Override
    public String getStatistics() {
        //"{inspectedSpotCount} spots were inspected.
        //Information for the discoverers:
        //Name: {discovererName}
        //Energy: {discovererName}
        //Museum exhibits: {museumExhibits1, museumExhibits2, museumExhibits3, …, museumExhibits n}"
        //…
        //Name: {discovererName}
        //Energy: {discovererEnergy}
        //Museum exhibits: museumExhibits1, museumExhibits2, museumExhibits3, …, museumExhibits n}"

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(FINAL_SPOT_INSPECT, spotCount));
        sb.append(System.lineSeparator());
        sb.append(FINAL_DISCOVERER_INFO);
        for (Discoverer discoverer : discovererRepository.getCollection()) {
            sb.append(System.lineSeparator());
            sb.append(String.format(FINAL_DISCOVERER_NAME, discoverer.getName()));
            sb.append(System.lineSeparator());
            sb.append(String.format(FINAL_DISCOVERER_ENERGY, discoverer.getEnergy()));
            sb.append(System.lineSeparator());

            if (discoverer.getMuseum().getExhibits().isEmpty()) {
                sb.append(String.format(FINAL_DISCOVERER_MUSEUM_EXHIBITS, "None"));
            } else {
                String discovererExhibits = discoverer.getMuseum().getExhibits().stream().collect(Collectors.joining(FINAL_DISCOVERER_MUSEUM_EXHIBITS_DELIMITER));

                sb.append(String.format(FINAL_DISCOVERER_MUSEUM_EXHIBITS, discovererExhibits));
            }
        }
        return sb.toString().trim();

    }
}
