import models.ClassificationLevel;
import models.MilitaryTypes;
import org.testng.Assert;
import org.testng.annotations.Test;
import planes.ExperimentalPlane;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;

import java.util.Arrays;
import java.util.List;

public class AirportTest {
    private static List<Plane> planes = Arrays.asList(
            new PassengerPlane(new Plane("Boeing-737", 900, 12000, 60500), 164),
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
            new MilitaryPlane(new Plane("C-130 Hercules", 650, 5000, 110000), MilitaryTypes.TRANSPORT),
            new ExperimentalPlane(new Plane("Bell X-14", 277, 482, 500), ClassificationLevel.SECRET),
            new ExperimentalPlane(new Plane("Ryan X-13 Vertijet", 560, 307, 500), ClassificationLevel.TOP_SECRET)
    );

    private static PassengerPlane planeWithMaxPassengerCapacity = new PassengerPlane(new Plane("Boeing-747", 980, 16100, 70500), 242);

    @Test
    public void verifyTransportMilitaryPlanes() {
        Airport airport = new Airport(planes);
        List<MilitaryPlane> transportMilitaryPlanes = airport.getTransportMilitaryPlanes();
        boolean isMilitaryPlaneOfTransportType = false;
        for (MilitaryPlane militaryPlane : transportMilitaryPlanes) {
            if ((militaryPlane.getType() == MilitaryTypes.TRANSPORT)) {
                isMilitaryPlaneOfTransportType = true;
                break;
            }
        }
        Assert.assertTrue(isMilitaryPlaneOfTransportType);
    }

    @Test
    public void verifyPassengerPlaneWithMaxCapacity() {
        Airport airport = new Airport(planes);
        PassengerPlane expectedPlaneWithMaxPassengersCapacity = airport.getPassengerPlaneWithMaxPassengersCapacity();
        Assert.assertEquals(expectedPlaneWithMaxPassengersCapacity, planeWithMaxPassengerCapacity);
    }

    @Test
    public void verifyPlanesSortedByMaxLoadCapacity() {
        Airport airport = new Airport(planes);
        airport.sortByMaxLoadCapacity();
        List<? extends Plane> planesSortedByMaxLoadCapacity = airport.getPlanes();

        boolean nextPlaneMaxLoadCapacityIsHigherThanCurrent = true;
        for (int i = 0; i < planesSortedByMaxLoadCapacity.size() - 1; i++) {
            Plane currentPlane = planesSortedByMaxLoadCapacity.get(i);
            Plane nextPlane = planesSortedByMaxLoadCapacity.get(i + 1);
            if (currentPlane.getMaxLoadCapacity() > nextPlane.getMaxLoadCapacity()) {
                nextPlaneMaxLoadCapacityIsHigherThanCurrent = false;
                break;
            }
        }
        Assert.assertTrue(nextPlaneMaxLoadCapacityIsHigherThanCurrent);
    }

    @Test
    public void verifyAtLeastOneBomberInMilitaryPlanes() {
        Airport airport = new Airport(planes);
        List<MilitaryPlane> bomberMilitaryPlanes = airport.getBomberMilitaryPlanes();
        boolean isBomberType = false;
        for (MilitaryPlane militaryPlane : bomberMilitaryPlanes) {
            if ((militaryPlane.getType() == MilitaryTypes.BOMBER)) {
                isBomberType = true;
                break;
            }
        }
        Assert.assertTrue(isBomberType);
    }

    @Test
    public void verifyExperimentalPlanesHasClassificationLevelHigherThanUnclassified() {
        Airport airport = new Airport(planes);
        List<ExperimentalPlane> experimentalPlanes = airport.getExperimentalPlanes();
        boolean hasUnclassifiedPlanes = false;
        for (ExperimentalPlane experimentalPlane : experimentalPlanes) {
            if (experimentalPlane.getClassificationLevel() == ClassificationLevel.UNCLASSIFIED) {
                hasUnclassifiedPlanes = true;
                break;
            }
        }
        Assert.assertFalse(hasUnclassifiedPlanes);
    }
}
