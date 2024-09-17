package in.gov.india.gui.screen.impl;

import in.gov.india.PajeetScroller;
import in.gov.india.gui.ScreenResolution;
import in.gov.india.gui.screen.GuiScreen;
import in.gov.india.gui.screen.buttons.GuiButton;
import in.gov.india.gui.textures.Texture;
import org.lwjgl.opengl.GL11;

public final class GuiMainMenu extends GuiScreen {
    public GuiMainMenu(PajeetScroller pajeetScroller) {
        super(pajeetScroller);
    }

    @Override
    public void initialize(ScreenResolution sr) {
        float btnWidth = 250.F;
        float btnHeight = 50.F;

        float y = 260.F;

        buttons.add(new GuiButton("Singleplayer", sr.getWidth() / 2.F - btnWidth / 2.F, y, btnWidth, btnHeight));
        buttons.add(new GuiButton("Settings", sr.getWidth() / 2.F - btnWidth / 2.F, y + (10.F + btnHeight), btnWidth, btnHeight));
        buttons.add(new GuiButton("Tuc Tuc", sr.getWidth() / 2.F - btnWidth / 2.F, y + (10.F + btnHeight) * 2, btnWidth, btnHeight));
    }

    @Override
    public void draw(ScreenResolution sr) {
        ps.getTextureManager().getTexture("menu_background.jpg").drawQuad(0.F, 0.F, sr.getWidth(), sr.getHeight());

        Texture title = ps.getTextureManager().getTexture("title.png");
        title.drawQuad(sr.getWidth() / 2.F - title.getWidth() / 2.F, 180.F);

        Texture flag = ps.getTextureManager().getTexture("menu_flag.png");
        flag.drawQuad(100, 100, flag.getWidth(), sr.getHeight() - 180);

        GL11.glPushMatrix();
        GL11.glTranslatef(sr.getWidth() - 100, 100, 0);
        GL11.glRotatef(180.F, 0, 1, 0);
        flag.drawQuad(0, 0, flag.getWidth(), sr.getHeight() - 180);
        GL11.glPopMatrix();
    }
}
