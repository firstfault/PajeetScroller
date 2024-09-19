package in.gov.india.entities;

import in.gov.india.entities.movement.MovementController;
import in.gov.india.game.Game;
import in.gov.india.gui.screen.ScreenPosition;
import in.gov.india.gui.textures.Texture;

import static org.lwjgl.opengl.GL11.*;

public abstract class Entity extends ScreenPosition {
    private final Game game;
    private final MovementController movement;

    protected Entity(Game game, MovementController movement) {
        this.game = game;
        this.movement = movement;
    }

    public final Game getGame() {
        return game;
    }

    public final MovementController getMovement() {
        return movement;
    }

    public final void tick() {
        this.movement.tick();

        this.setPosition(movement.getX(), movement.getY());
    }

    public final void draw() {
        final MovementController movement = this.getMovement();

        final float tilt = movement.getRotation();

        glPushMatrix();
        glTranslatef(x + 65, y + 65, 0);
        glRotatef(tilt, 0, 0, 1);
        getTexture().drawQuad(-65, -65, this.getWidth(), this.getHeight());
        glPopMatrix();
    }

    protected abstract Texture getTexture();
}
