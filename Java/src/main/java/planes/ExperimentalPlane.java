package planes;

import models.SecrecyLevel;

import java.util.Objects;

public class ExperimentalPlane extends Plane {

    private SecrecyLevel classificationLevel;

    public ExperimentalPlane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity, SecrecyLevel classificationLevel) {
        super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
        this.classificationLevel = classificationLevel;
    }

    public SecrecyLevel getClassificationLevel() {
        return classificationLevel;
    }

    public void setClassificationLevel(SecrecyLevel classificationLevel) {
        this.classificationLevel = classificationLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ExperimentalPlane that = (ExperimentalPlane) o;
        return classificationLevel == that.classificationLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), classificationLevel);
    }

    @Override
    public String toString() {
        return "ExperimentalPlane{" +
                "classificationLevel=" + classificationLevel +
                '}';
    }
}
