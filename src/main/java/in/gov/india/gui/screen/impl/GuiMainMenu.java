package in.gov.india.gui.screen.impl;

import in.gov.india.gui.ScreenResolution;
import in.gov.india.gui.Window;
import in.gov.india.gui.screen.GuiScreen;
import in.gov.india.gui.screen.buttons.GuiButton;
import in.gov.india.gui.textures.Texture;
import in.gov.india.gui.textures.TextureManager;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;

public final class GuiMainMenu extends GuiScreen {
    @Override
    public void initialize(Window window) {
        ScreenResolution sr = window.getResolution();
        float btnWidth = 250.F;
        float btnHeight = 50.F;

        float y = 260.F;

        buttons.add(new GuiButton("Singleplayer", sr.getWidth() / 2.F - btnWidth / 2.F, y, btnWidth, btnHeight, this::singleplayer));
        buttons.add(new GuiButton("Settings", sr.getWidth() / 2.F - btnWidth / 2.F, y + (10.F + btnHeight), btnWidth, btnHeight, this::settings));
        buttons.add(new GuiButton("Tuc Tuc", sr.getWidth() / 2.F - btnWidth / 2.F, y + (10.F + btnHeight) * 2, btnWidth, btnHeight, () -> this.leave(window)));
    }

    private void leave(Window window) {
        glfwSetWindowShouldClose(window.getPointer(), true);
    }

    private void settings() {

    }

    private void singleplayer() {

    }

    @Override
    public void draw(Window window) {
        ScreenResolution sr = window.getResolution();
        TextureManager textureManager = window.getPajeetScroller().getTextureManager();
        textureManager.getTexture("menu_background.jpg").drawQuad(0.F, 0.F, sr.getWidth(), sr.getHeight());

        Texture flag = textureManager.getTexture("menu_flag.png");
        flag.drawQuad(100, 100, flag.getWidth(), sr.getHeight() - 180);

        GL11.glPushMatrix();
        GL11.glTranslatef(sr.getWidth() - 100, 100, 0);
        GL11.glRotatef(180.F, 0, 1, 0);
        flag.drawQuad(0, 0, flag.getWidth(), sr.getHeight() - 180);
        GL11.glPopMatrix();
    }
}
