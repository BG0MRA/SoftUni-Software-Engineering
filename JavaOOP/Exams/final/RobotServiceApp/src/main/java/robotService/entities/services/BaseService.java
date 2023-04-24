package robotService.entities.services;

import robotService.entities.robot.Robot;
import robotService.entities.supplements.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static robotService.common.ConstantMessages.NOT_ENOUGH_CAPACITY_FOR_ROBOT;
import static robotService.common.ExceptionMessages.*;

public abstract class BaseService implements Service {

    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Robot> robots;

    protected BaseService(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        supplements = new ArrayList<>();
        robots = new ArrayList<>();
    }



    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(SERVICE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int sumHardness() {
        return supplements
                .stream()
                .mapToInt(s -> s.getHardness())
                .sum();
    }

    @Override
    public void addRobot(Robot robot) {
        if (robots.size() >= this.capacity) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY_FOR_ROBOT);
        }
        robots.add(robot);
    }

    @Override
    public void removeRobot(Robot robot) {
        robots.remove(robot);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        supplements.add(supplement);
    }

    @Override
    public void feeding() {
        robots.forEach(Robot::eating);
    }

    @Override
    public Collection<Robot> getRobots() {
        return Collections.unmodifiableCollection(robots);
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return Collections.unmodifiableCollection(supplements);
    }

    @Override
    public String getStatistics() {
        //Note: I remind you that there are two service types – MainService and SecondaryService.

        //"{serviceName} {serviceType}:
        //Robots: {robotName1} {robotName2} {robotName3} ... / Robots: none
        //Supplements: {supplementsCount} Hardness: {sumHardness}"

        //ServiceRobotsWorld SecondaryService:
        //Robots: Scrap Sparkles
        //Supplements: 2 Hardness: 6

        //ServiceTechnicalsWorld MainService:
        //Robots: none
        //Supplements: 0 Hardness: 0

        String robotReport = robots.isEmpty()
                ? "none"
                : robots.stream().map(Robot::getName).collect(Collectors.joining(" "));

        return String.format("%s %s:" + System.lineSeparator() +
                            "Robots: %s" + System.lineSeparator() +
                            "Supplements: %d Hardness: %d"
                ,this.name,this.getClass().getSimpleName(),robotReport, supplements.size(), sumHardness());
    }
}
