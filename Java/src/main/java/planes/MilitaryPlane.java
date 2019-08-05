package planes;

import models.MilitaryTypes;

import java.util.Objects;

public class MilitaryPlane extends Plane {

    private MilitaryTypes type;

    public MilitaryPlane(Plane plane, MilitaryTypes type) {
        super(plane.getModel(), plane.getMaxFlightDistance(), plane.getMaxSpeed(), plane.getMaxLoadCapacity());
        this.type = type;
    }

    public MilitaryTypes getType() {
        return type;
    }

    @Override
    public String toString() {
        return super.toString().replace("}",
                ", type=" + type +
                        '}');
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MilitaryPlane)) return false;
        if (!super.equals(o)) return false;
        MilitaryPlane that = (MilitaryPlane) o;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }
}
