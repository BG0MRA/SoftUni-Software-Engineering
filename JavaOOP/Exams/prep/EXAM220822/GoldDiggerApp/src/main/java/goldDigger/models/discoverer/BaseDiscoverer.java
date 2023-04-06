package goldDigger.models.discoverer;

import goldDigger.models.museum.BaseMuseum;
import goldDigger.models.museum.Museum;

import javax.swing.*;

import static goldDigger.common.ExceptionMessages.*;

public abstract class BaseDiscoverer implements Discoverer {

    private String name;
    private double energy;
    private Museum museum;

    protected BaseDiscoverer(String name, double energy) {
        this.setName(name);
        this.setEnergy(energy);
        museum = new BaseMuseum();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(DISCOVERER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setEnergy(double energy) {
        if (energy < 0) {
            throw new IllegalArgumentException(DISCOVERER_ENERGY_LESS_THAN_ZERO);
        }
        this.energy = energy;
    }

    @Override
    public double getEnergy() {
        return this.energy;
    }

    @Override
    public boolean canDig() {
        return getEnergy() > 0;
    }

    @Override
    public Museum getMuseum() {
        return this.museum;
    }

    @Override
    public void dig() {
        double currentEnergy = getEnergy();
        currentEnergy -= 15;
        if (currentEnergy < 0) {
            currentEnergy = 0;
        }
        setEnergy(currentEnergy);

    }
}
