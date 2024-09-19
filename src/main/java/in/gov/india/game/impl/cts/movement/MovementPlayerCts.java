package in.gov.india.game.impl.cts.movement;

import in.gov.india.entities.movement.MovementController;
import in.gov.india.game.Game;
import in.gov.india.keys.KeybindSystem;
import in.gov.india.util.MathUtil;

public class MovementPlayerCts extends MovementController {
    private float position;
    private float positionY;
    private float motion;
    private float motionY;
    private float rotation;
    private boolean onGround;
    private float mapSize = 1000.F;
    private final Game game;
    private final KeybindSystem keybinds;

    public MovementPlayerCts(Game game) {
        this.game = game;
        this.keybinds = game.getPajeetScroller().getKeybindSystem();
    }

    public float getPosition() {
        return position;
    }

    public float getPositionY() {
        return positionY;
    }

    @Override
    public float getY() {
        return game.getPajeetScroller().getWindow().getResolution().getHeight() - 115 - positionY;
    }

    @Override
    public float getX() {
        return (position / mapSize) * (game.getPajeetScroller().getWindow().getResolution().getWidth() - 95) - 20;
    }

    @Override
    public float getRotation() {
        return rotation;
    }

    @Override
    public void tick() {
        float speed = 2.F, rotationSpeed = 0.45F;

        this.position += this.motion;
        this.positionY += this.motionY;
        this.onGround = this.positionY <= 0.F;

        if (this.onGround) {
            this.positionY = 0.F;
        }

        if (keybinds.moveA.isPressed()) {
            this.motion -= speed;
        }

        if (keybinds.moveD.isPressed()) {
            this.motion += speed;
        }

        this.motion = this.motion - this.motion / 8.5F;

        this.motionY -= 0.98F;
        this.motionY *= 0.9F;

        if (keybinds.moveW.isPressed() && this.onGround) {
            this.motionY = 11.F;
            this.motion *= 1.3F;
        }

        this.rotation = MathUtil.clamp_float(this.rotation + this.motion * rotationSpeed, -10.F, 10.F);

        if (Math.abs(this.motion) < 0.005F) this.motion = 0.F;
        this.position = MathUtil.clamp_float(this.position, 0, this.mapSize);
    }
}
