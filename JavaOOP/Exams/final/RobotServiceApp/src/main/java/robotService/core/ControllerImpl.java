package robotService.core;

import robotService.entities.robot.FemaleRobot;
import robotService.entities.robot.MaleRobot;
import robotService.entities.robot.Robot;
import robotService.entities.services.MainService;
import robotService.entities.services.SecondaryService;
import robotService.entities.services.Service;
import robotService.entities.supplements.MetalArmor;
import robotService.entities.supplements.PlasticArmor;
import robotService.entities.supplements.Supplement;
import robotService.repositories.SupplementRepository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import static robotService.common.ConstantMessages.*;
import static robotService.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private SupplementRepository supplements;
    private Map<String, Service> services;

    public ControllerImpl() {
        this.supplements = new SupplementRepository();
        this.services = new LinkedHashMap<>();
    }

    @Override
    public String addService(String type, String name) {
        Service service;
        if (type.equals("MainService")) {
            service = new MainService(name);

        } else if (type.equals("SecondaryService")) {
            service = new SecondaryService(name);

        } else {
            throw new NullPointerException(INVALID_SERVICE_TYPE);
        }

        services.put(service.getName(), service);

        return String.format(SUCCESSFULLY_ADDED_SERVICE_TYPE, type);
    }

    @Override
    public String addSupplement(String type) {
        Supplement supplement;
        if (type.equals("PlasticArmor")) {
            supplement = new PlasticArmor();

        } else if (type.equals("MetalArmor")) {
            supplement = new MetalArmor();
        } else {
            throw new IllegalArgumentException(INVALID_SUPPLEMENT_TYPE);
        }

        supplements.addSupplement(supplement);

        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForService(String serviceName, String supplementType) {

        Service currentService = services.get(serviceName);
        Supplement currentSupplement = supplements.findFirst(supplementType);

        if (currentSupplement == null) {
            throw new IllegalArgumentException(String.format(NO_SUPPLEMENT_FOUND, supplementType));
        }

        currentService.addSupplement(currentSupplement);
        supplements.removeSupplement(currentSupplement);

        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_IN_SERVICE, supplementType, serviceName);
    }

    @Override
    public String addRobot(String serviceName, String robotType, String robotName, String robotKind, double price) {

        if (robotType.equals("MaleRobot")) {
            //Can only live in MainService!
            Service currentService = services.get(serviceName);
            String currentServiceType = currentService.getClass().getSimpleName();
            if (currentServiceType.equals("SecondaryService")) {
                return String.format(UNSUITABLE_SERVICE);
            }
            MaleRobot robot = new MaleRobot(robotName, robotKind, price);
            currentService.addRobot(robot);


        } else if (robotType.equals("FemaleRobot")) {
            //Can only live in SecondaryService!
            Service currentService = services.get(serviceName);
            String currentServiceType = currentService.getClass().getSimpleName();
            if (currentServiceType.equals("MainService")) {
                return String.format(UNSUITABLE_SERVICE);
            }
            FemaleRobot robot = new FemaleRobot(robotName, robotKind, price);
            currentService.addRobot(robot);

        } else {
            throw new IllegalArgumentException(INVALID_ROBOT_TYPE);
        }

        return String.format(SUCCESSFULLY_ADDED_ROBOT_IN_SERVICE, robotType, serviceName);
    }

    @Override
    public String feedingRobot(String serviceName) {
        Service currentService = services.get(serviceName);

        currentService.feeding();

        return String.format(FEEDING_ROBOT, currentService.getRobots().size());
    }

    @Override
    public String sumOfAll(String serviceName) {
        Service currentService = services.get(serviceName);

        Collection<Robot> currentRobots = currentService.getRobots();
        Collection<Supplement> currentSupplements = currentService.getSupplements();

        double result = 0;
        for (Robot robot : currentRobots) {
            result += robot.getPrice();
        }

        for (Supplement supplement : currentSupplements) {
            result += supplement.getPrice();
        }


        return String.format(VALUE_SERVICE, serviceName, result);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        for (Service service : services.values()) {
            sb.append(service.getStatistics());
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
