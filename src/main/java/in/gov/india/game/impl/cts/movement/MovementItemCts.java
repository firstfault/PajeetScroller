package in.gov.india.game.impl.cts.movement;

import in.gov.india.entities.movement.MovementController;
import in.gov.india.game.Game;
import in.gov.india.util.MathUtil;

public class MovementItemCts extends MovementController {
    private float position, positionY;
    private float mapSize = 1000.F;
    private float fallSpeed;
    private final Game game;

    public MovementItemCts(Game game) {
        this.game = game;
        this.positionY = 0.F;
        this.position = MathUtil.getRandom().nextFloat() * mapSize;
        this.fallSpeed = 5.F + (MathUtil.getRandom().nextFloat() * 0.2F);
    }

    @Override
    public float getX() {
        return (position / mapSize) * (game.getPajeetScroller().getWindow().getResolution().getWidth());
    }

    @Override
    public float getY() {
        return (positionY / mapSize) * game.getPajeetScroller().getWindow().getResolution().getHeight();
    }

    @Override
    public float getRotation() {
        return 0.F;
    }

    @Override
    public void tick() {
        this.positionY += fallSpeed;
        this.positionY *= 1.01F;
    }
}
