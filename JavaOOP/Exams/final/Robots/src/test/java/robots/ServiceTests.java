package robots;

import org.junit.Test;

import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class ServiceTests {

    //        service.getName();
    //        service.getCapacity();
    @Test
    public void testCtorValid() {
        Service service = new Service("tesla", 10);

        assertEquals("tesla", service.getName());
        assertEquals(10, service.getCapacity());
    }


    @Test(expected = NullPointerException.class)
    public void testCtorNotValid_NameIsNull() {
        Service service = new Service(null, 10);

    }

    @Test(expected = NullPointerException.class)
    public void testCtorNotValid_NameIsWhiteSpace() {
        Service service = new Service("    ", 10);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCtorNotValid_Capacity() {
        Service service = new Service("tesla", -10);

    }


    //        service.add();

    @Test(expected = IllegalArgumentException.class)
    public void testAddRobotNotValidCapacity() {
        Service service = new Service("tesla", 0);
        Robot robot = new Robot("pesho");

        service.add(robot);
    }

    //        service.getCount()
    @Test
    public void testAddRobotTwice() {
        Service service = new Service("tesla", 10);
        Robot robot = new Robot("pesho");
        Robot robot2 = new Robot("dancho");

        service.add(robot);
        service.add(robot2);

        assertEquals(2, service.getCount());
    }
    @Test
    public void testAddRobotMultiple() {
        Service service = new Service("tesla", 10);
        Robot robot = new Robot("pesho");
        Robot robot2 = new Robot("dancho");

        service.add(robot);
        service.add(robot);
        service.add(robot2);
        service.add(robot2);

        assertEquals(4, service.getCount());
    }

    //        service.remove();
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNotValid() {
        Service service = new Service("tesla", 10);

        service.remove("pesho");
    }


    @Test
    public void testRemoveRobotShouldWork() {
        Service service = new Service("tesla", 10);
        Robot robot = new Robot("pesho");
        Robot robot2 = new Robot("dancho");
        service.add(robot);
        service.add(robot2);

        assertEquals(2, service.getCount());

        service.remove("pesho");

        assertEquals(1, service.getCount());
    }

    @Test
    public void testRemoveRobotShouldWorkTwice() {
        Service service = new Service("tesla", 10);
        Robot robot = new Robot("pesho");
        Robot robot2 = new Robot("dancho");
        service.add(robot);
        service.add(robot2);

        assertEquals(2, service.getCount());

        service.remove("pesho");

        assertEquals(1, service.getCount());

        service.remove("dancho");

        assertEquals(0, service.getCount());
    }

//        service.forSale();

    @Test(expected = IllegalArgumentException.class)
    public void testRobotForSaleNotValid() {
        Service service = new Service("tesla", 10);

        service.forSale("pesho");
    }

    @Test
    public void testRobotForSaleValid() {
        Service service = new Service("tesla", 10);
        Robot robot = new Robot("pesho");
        Robot robot2 = new Robot("dancho");
        service.add(robot);

        service.forSale("pesho");

        boolean readyForSale = robot.isReadyForSale();
        boolean readyForSale2 = robot2.isReadyForSale();

        assertEquals(false, readyForSale);
        assertEquals(true, readyForSale2);

    }

    //      service.report
    @Test
    public void testRobotReport() {
        Service service = new Service("tesla", 10);
        Robot robot = new Robot("pesho");
        service.add(robot);

        String names = "pesho";
        String serviceName = "tesla";

        assertEquals(String.format("The robot %s is in the service %s!",names ,serviceName), service.report());

    }

    @Test
    public void testRobotReportTwice() {
        Service service = new Service("tesla", 10);
        Robot robot = new Robot("pesho");
        Robot robot2 = new Robot("dancho");
        service.add(robot);
        service.add(robot2);

        String names = "pesho, dancho";
        String serviceName = "tesla";

        assertEquals(String.format("The robot %s is in the service %s!",names ,serviceName), service.report());

    }

}