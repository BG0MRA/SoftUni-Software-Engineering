package barbershopjava;

import java.util.*;
import java.util.stream.Collectors;

public class BarberShopImpl implements BarberShop {

    //Map <BarbersName , Barbers objects>
    private Map<String, Barber> barbersByNames;
    private Map<String, List<Client>> barbersWithClients;
    private Map<String, Client> clientsByNames;


    public BarberShopImpl() {
        this.barbersByNames = new HashMap<>();
        this.barbersWithClients = new HashMap<>();
        this.clientsByNames = new HashMap<>();
    }

    @Override
    public void addBarber(Barber b) {
        if (barbersByNames.containsKey(b.name)) {
            throw new IllegalArgumentException();
        }
        //adding barber
        barbersByNames.put(b.name, b);
        //for assignClient method: when we add new barber we add it also in barbersClients;
        barbersWithClients.put(b.name, new ArrayList<>());


    }

    @Override
    public void addClient(Client c) {
        if (clientsByNames.containsKey(c.name)) {
            throw new IllegalArgumentException();
        }
        clientsByNames.put(c.name, c);

    }

    @Override
    public boolean exist(Barber b) {
        return barbersByNames.containsKey(b.name);
    }

    @Override
    public boolean exist(Client c) {
        return clientsByNames.containsKey(c.name);
    }

    @Override
    public Collection<Barber> getBarbers() {
        return barbersByNames.values();
    }

    @Override
    public Collection<Client> getClients() {
        return clientsByNames.values();
    }

    @Override
    public void assignClient(Barber b, Client c) {
        if (!exist(b) || !exist(c)) {
            throw new IllegalArgumentException();
        }

        //adding clients barber | client has Barber in his class
        c.barber = b;
        barbersWithClients.get(b.name).add(c);
    }

    @Override
    public void deleteAllClientsFrom(Barber b) {
        if (!exist(b)) {
            throw new IllegalArgumentException();
        }

        List<Client> clientsToRemove = barbersWithClients.get(b.name);
        clientsToRemove.clear();


    }

    @Override
    public Collection<Client> getClientsWithNoBarber() {
        return getClients()
                .stream()
                .filter(c -> c.barber == null)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Barber> getAllBarbersSortedWithClientsCountDesc() {
        //TODO try Iterating the collection barbersWithClients.get(b.name) (this is List<Client>)
        return getBarbers()
                .stream()
                .sorted((b1, b2) -> {
                    int firstClients = barbersWithClients.get(b1.name).size();
                    int secondClients = barbersWithClients.get(b2.name).size();

                    return Integer.compare(secondClients, firstClients);

                })
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Barber> getAllBarbersSortedWithStarsDescendingAndHaircutPriceAsc() {
        return getBarbers()
                .stream()
                .sorted((b1, b2) -> {
                    int result = Integer.compare(b2.stars, b1.stars);

                    if (result == 0) {
                        result = Integer.compare(b1.haircutPrice, b2.haircutPrice);
                    }

                    return result;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Client> getClientsSortedByAgeDescAndBarbersStarsDesc() {
        return barbersWithClients.values()
                .stream()
                //FlatMap >>>> Stream<List<Clients>> transferring to Collection of Clients
                .flatMap(List::stream)
                .sorted((c1, c2) -> {
                    int result = Integer.compare(c2.age, c1.age);

                    if (result == 0) {
                        result = Integer.compare(c2.barber.stars, c1.barber.stars);
                    }

                    return result;
                })
                .collect(Collectors.toList());
    }
}
