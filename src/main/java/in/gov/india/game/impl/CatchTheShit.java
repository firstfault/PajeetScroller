package in.gov.india.game.impl;

import in.gov.india.PajeetScroller;
import in.gov.india.game.Game;
import in.gov.india.gui.textures.Texture;
import in.gov.india.keys.KeybindSystem;

public class CatchTheShit extends Game {
    private final Texture pajeet;
    private float position;
    private float motion;

    public CatchTheShit(PajeetScroller pajeetScroller) {
        super("Catch The Shit", pajeetScroller);
        this.pajeet = pajeetScroller.getTextureManager().getTexture("pajeet.png");
    }

    @Override
    public void draw() {
        getPajeetScroller().getTextureManager().getTexture("street.jpg").drawQuad(getPajeetScroller().getWindow().getResolution());
        this.drawPajeet((int) this.position);
    }

    @Override
    protected void tick() {
        KeybindSystem keybinds = getPajeetScroller().getKeybindSystem();

        this.position += this.motion;

        float speed = 3.F;

        if (keybinds.moveA.isPressed()) {
            this.motion -= speed;
        }

        if (keybinds.moveD.isPressed()) {
            this.motion += speed;
        }

        this.motion = this.motion - this.motion / 8.F;
    }

    private void drawPajeet(int x) {
        pajeet.drawQuad(x, getPajeetScroller().getWindow().getResolution().getHeight() - 115, 130, 130);
    }
}
