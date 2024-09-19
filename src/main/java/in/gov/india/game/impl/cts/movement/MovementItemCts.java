package in.gov.india.game.impl.cts.movement;

import in.gov.india.entities.movement.MovementController;
import in.gov.india.util.MathUtil;

public class MovementItemCts extends MovementController {
    private float position, positionY;
    private float mapSize = 1000.F;

    public MovementItemCts() {
        this.positionY = 0.F;
        this.position = MathUtil.getRandom().nextFloat() * mapSize;
    }

    @Override
    public float getX() {
        return position;
    }

    @Override
    public float getY() {
        return positionY;
    }

    @Override
    public float getRotation() {
        return 0.F;
    }

    @Override
    public void tick() {
        this.positionY += 5.F;
        this.positionY *= 1.01F;
    }
}
