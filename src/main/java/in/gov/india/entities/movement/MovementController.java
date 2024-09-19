package in.gov.india.entities.movement;

public abstract class MovementController {
    public abstract float getX();
    public abstract float getY();
    public abstract float getRotation();

    public abstract void tick();
}
