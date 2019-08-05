import models.MilitaryTypes;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class Runner {
    private static final Logger LOG = Logger.getAnonymousLogger();
    static List<Plane> planes = Arrays.asList(
            new PassengerPlane(new Plane("Boeing-737", 900, 12000, 6000), 164),
            new PassengerPlane(new Plane("Boeing-737-800", 940, 12300, 63870), 192),
            new PassengerPlane(new Plane("Boeing-747", 980, 16100, 70500), 242),
            new PassengerPlane(new Plane("Airbus A320", 930, 11800, 65500), 188),
            new PassengerPlane(new Plane("Airbus A330", 990, 14800, 80500), 222),
            new PassengerPlane(new Plane("Embraer 190", 870, 8100, 30800), 64),
            new PassengerPlane(new Plane("Sukhoi Superjet 100", 870, 11500, 50500), 140),
            new PassengerPlane(new Plane("Bombardier CS300", 920, 11000, 60700), 196),
            new MilitaryPlane(new Plane("B-1B Lancer", 1050, 21000, 80000), MilitaryTypes.BOMBER),
            new MilitaryPlane(new Plane("B-2 Spirit", 1030, 22000, 70000), MilitaryTypes.BOMBER),
            new MilitaryPlane(new Plane("B-52 Stratofortress", 1000, 20000, 80000), MilitaryTypes.BOMBER),
            new MilitaryPlane(new Plane("F-15", 1500, 12000, 10000), MilitaryTypes.FIGHTER),
            new MilitaryPlane(new Plane("F-22", 1550, 13000, 11000), MilitaryTypes.FIGHTER),
            new MilitaryPlane(new Plane("C-130 Hercules", 650, 5000, 110000), MilitaryTypes.TRANSPORT)
    );

    public static void main(String[] args) {
        Airport airport = new Airport(planes);
        Airport militaryAirport = new Airport(airport.getMilitaryPlanes());
        Airport passengerAirport = new Airport(airport.getPassengerPlanes());

        String militaryAirportSortMessage = "Military airport sorted by max distance: " + militaryAirport
                .sortByMaxDistance()
                .toString();
        LOG.info(militaryAirportSortMessage);

        String passengerAirportSortMessage = "Passenger airport sorted by max speed: " + passengerAirport
                .sortByMaxSpeed()
                .toString();
        LOG.info(passengerAirportSortMessage);

        LOG.info("Plane with max passenger capacity: " + passengerAirport.getPassengerPlaneWithMaxPassengersCapacity());
    }
}
