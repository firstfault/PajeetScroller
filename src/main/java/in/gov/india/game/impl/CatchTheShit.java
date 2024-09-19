package in.gov.india.game.impl;

import in.gov.india.PajeetScroller;
import in.gov.india.game.Game;
import in.gov.india.gui.ScreenResolution;
import in.gov.india.gui.textures.Texture;
import in.gov.india.keys.KeybindSystem;
import in.gov.india.util.ColorUtil;
import in.gov.india.util.MathUtil;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class CatchTheShit extends Game {
    private final Texture pajeet;
    private float position;
    private float positionY;
    private float motion;
    private float motionY;
    private float rotation;
    private boolean onGround;

    public CatchTheShit(PajeetScroller pajeetScroller) {
        super("Catch The Shit", pajeetScroller);
        this.pajeet = pajeetScroller.getTextureManager().getTexture("pajeet.png");
    }

    @Override
    public void draw() {
        getPajeetScroller().getTextureManager().getTexture("street.jpg").drawQuad(getPajeetScroller().getWindow().getResolution());

        this.drawStats();
        this.drawPajeet();
    }

    private void drawStats() {
        ScreenResolution sr = this.getPajeetScroller().getWindow().getResolution();

        float x = sr.getX() + 10.F;
        float y = sr.getY() + 10.F;

        this.getPajeetScroller().getWindow().getRenderer().drawRect(x, y, 200, 26, new Color(152, 93, 70, 130).getRGB());
        this.getPajeetScroller().getWindow().getRenderer().drawRect(x, y, 100, 26, new Color(152, 93, 70).getRGB());
        this.getPajeetScroller().getWindow().getRenderer().getSatisfyRegular().get(20.F).drawString("Smell: 50%", x + 5, y + 1.F, ColorUtil.generateWhiteColor(240));
    }

    @Override
    protected void tick() {
        KeybindSystem keybinds = getPajeetScroller().getKeybindSystem();

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
    }

    private void drawPajeet() {
        final float y = getPajeetScroller().getWindow().getResolution().getHeight() - 115 - this.positionY;
        final float x = this.position;

        final float tilt = this.rotation;

        glPushMatrix();
        glTranslatef(x + 65, y + 65, 0);
        glRotatef(tilt, 0, 0, 1);
        pajeet.drawQuad(-65, -65, 130, 130);
        glPopMatrix();
    }
}
