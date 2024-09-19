package in.gov.india.game.impl.cts;

import in.gov.india.PajeetScroller;
import in.gov.india.entities.impl.EntityPlayer;
import in.gov.india.game.Game;
import in.gov.india.game.impl.cts.items.ItemManager;
import in.gov.india.game.impl.cts.movement.MovementPlayerCts;
import in.gov.india.gui.ScreenResolution;
import in.gov.india.gui.render.Renderer;
import in.gov.india.util.ColorUtil;

import java.awt.*;

public class CatchTheShit extends Game {
    private final ItemManager itemManager = new ItemManager(this);
    private float smell;

    public CatchTheShit(PajeetScroller pajeetScroller) {
        super("Catch The Shit", pajeetScroller);
        this.setLocalPlayer(new EntityPlayer(this, new MovementPlayerCts(this)));
    }

    @Override
    public void draw() {
        this.getPajeetScroller().getTextureManager().getTexture("street.jpg").drawQuad(getPajeetScroller().getWindow().getResolution());

        this.drawStats();
    }

    @Override
    protected void tick() {
        this.itemManager.update();
    }

    private void drawStats() {
        ScreenResolution sr = this.getPajeetScroller().getWindow().getResolution();

        float x = sr.getX() + 10.F;
        float y = sr.getY() + 10.F;

        this.drawBar(x, y, this.getLocalPlayer().getSmell(), "Smell", new Color(164, 108, 74).getRGB());
    }

    private void drawBar(float x, float y, float progress, String text, int color) {
        final int width = 200;
        final Renderer renderer = this.getPajeetScroller().getWindow().getRenderer();

        renderer.drawRect(x, y, width, 26, ColorUtil.changeAlpha(color, 120));
        renderer.drawRect(x, y, (progress / 100.F) * width, 26, color);
        renderer.getSatisfyRegular().get(20.F).drawString(text + ": " + (int)progress + "%", x + 5, y + 1.F, ColorUtil.generateWhiteColor(240));
    }
}
