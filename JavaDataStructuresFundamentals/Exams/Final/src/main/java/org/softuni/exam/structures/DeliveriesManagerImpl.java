package org.softuni.exam.structures;

import org.softuni.exam.entities.Deliverer;
import org.softuni.exam.entities.Package;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DeliveriesManagerImpl implements DeliveriesManager {

    private Map<String, Deliverer> delivererByName;
    private Map<String, List<Package>> delivererWithPackages;
    private Map<String, Package> packagesByIds;

    private Map<String, Package> packagesWithoutDeliverer;

    public DeliveriesManagerImpl() {
        this.delivererByName = new HashMap<>();
        this.packagesByIds = new HashMap<>();
        this.delivererWithPackages = new HashMap<>();
        this.packagesWithoutDeliverer = new HashMap<>();
    }

    @Override
    public void addDeliverer(Deliverer deliverer) {
        delivererByName.put(deliverer.getName(), deliverer);
        delivererWithPackages.put(deliverer.getName(), new ArrayList<>());

    }

    @Override
    public void addPackage(Package _package) {
        packagesByIds.put(_package.getId(), _package);
        packagesWithoutDeliverer.put(_package.getId(),_package);

    }

    @Override
    public boolean contains(Deliverer deliverer) {
        return delivererByName.containsKey(deliverer.getName());
    }

    @Override
    public boolean contains(Package _package) {
        return packagesByIds.containsKey(_package.getId());
    }

    @Override
    public Iterable<Deliverer> getDeliverers() {
        return delivererByName.values();
    }

    @Override
    public Iterable<Package> getPackages() {
        return packagesByIds.values();
    }

    @Override
    public void assignPackage(Deliverer deliverer, Package _package) throws IllegalArgumentException {

        if (!contains(deliverer) || !contains(_package)) {
            throw new IllegalArgumentException();
        }

        delivererWithPackages.get(deliverer.getName()).add(_package);

        packagesWithoutDeliverer.remove(_package.getId());


    }

    @Override
    public Iterable<Package> getUnassignedPackages() {
        return packagesWithoutDeliverer.values();
    }

    @Override
    public Iterable<Package> getPackagesOrderedByWeightThenByReceiver() {
        return packagesByIds.values()
                .stream()
                .sorted((p1, p2) -> {
                    int result = Double.compare(p2.getWeight(), p1.getWeight());
                    if (result == 0) {
                        result = p1.getReceiver().compareTo(p2.getReceiver());
                    }
                    return result;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Deliverer> getDeliverersOrderedByCountOfPackagesThenByName() {
        return null;
    }
}
