package goldDigger.repositories;

import goldDigger.models.spot.Spot;

import java.util.*;

public class SpotRepository implements Repository<Spot> {
    private Map<String, Spot> spots;

    public SpotRepository() {
        this.spots = new LinkedHashMap<>();
    }

    @Override
    public Collection<Spot> getCollection() {
        return Collections.unmodifiableCollection(spots.values());
    }

    @Override
    public void add(Spot entity) {
        spots.put(entity.getName(), entity);
    }

    @Override
    public boolean remove(Spot entity) {
        boolean isRemoved = spots.values().remove(entity);
        return isRemoved;
    }

    @Override
    public Spot byName(String name) {
        return spots.values()
                .stream()
                .filter(spot -> spot.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
