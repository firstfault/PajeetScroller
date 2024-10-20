package in.gov.india.game.impl.fp.entity;

import in.gov.india.entities.movement.MovementController;
import in.gov.india.game.Game;
import in.gov.india.gui.textures.Texture;
import in.gov.india.keys.KeybindSystem;

import static org.lwjgl.opengl.GL11.*;

public class Pajeet extends MovementController {
    private final Game game;
    public static final int PAJEET_SIZE = 50;
    private static final int PAJEET_JUMP_HEIGHT = 16;
    private static final int GRAVITY = 2;

    private final int startHeight;
    private final Texture pajeetTexture;
    private float pajeetYpos;
    private float pajeetVelocity;

    public Pajeet(Game game) {
        this.pajeetTexture = game.getPajeetScroller().getCharacterManager().getSelected().getTexture();
        this.startHeight = game.getPajeetScroller().getWindow().getResolution().getHeightI() / 2;
        this.game = game;
    }

    @Override
    public float getX() {
        return 0;
    }

    @Override
    public float getY() {
        return pajeetYpos;
    }

    @Override
    public float getRotation() {
        return pajeetVelocity * 2;
    }

    @Override
    public void tick() {
        KeybindSystem keybinds = game.getPajeetScroller().getKeybindSystem();
        pajeetVelocity += GRAVITY;
        pajeetYpos += pajeetVelocity;

        if (keybinds.space.isPressed()) {
            pajeetVelocity = -PAJEET_JUMP_HEIGHT;
        }
    }

    public void draw() {
        glPushMatrix();
        glTranslatef(PAJEET_SIZE * 2, pajeetYpos, 0);
        glRotatef(getRotation(), 0, 0, 1);
        pajeetTexture.drawQuad((float) -PAJEET_SIZE / 2, (float) -PAJEET_SIZE / 2, PAJEET_SIZE, PAJEET_SIZE);
        glPopMatrix();
    }

    public void reset() {
        pajeetYpos = startHeight;
        pajeetVelocity = 0;
    }
}