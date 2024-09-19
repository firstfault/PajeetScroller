package in.gov.india.gui.screen.impl;

import in.gov.india.gui.ScreenResolution;
import in.gov.india.gui.Window;
import in.gov.india.gui.screen.actions.GuiAction;
import in.gov.india.gui.screen.buttons.GuiButton;
import in.gov.india.gui.screen.impl.gameselect.GuiGameSelect;
import in.gov.india.gui.screen.impl.mainmenu.GuiAbstractMainMenu;
import in.gov.india.gui.textures.Texture;
import in.gov.india.gui.textures.TextureManager;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;

public final class GuiMainMenu extends GuiAbstractMainMenu {
    @Override
    public void initialize(Window window) {
        super.initialize(window);

        this.addVerticalMiddleButtons(window, 250.F,
                new GuiButton("Singleplayer", () -> this.singleplayer(window)),
                new GuiButton("Options", this::settings),
                new GuiButton("Tuk-Tuk", () -> this.leave(window))
        );
    }

    @Override
    public void draw(Window window) {
        super.draw(window);

        ScreenResolution sr = window.getResolution();
        TextureManager textureManager = window.getPajeetScroller().getTextureManager();

        Texture flag = textureManager.getTexture("menu_flag.png");
        flag.drawQuad(100, 100, flag.getWidth(), sr.getHeight() - 180);

        GL11.glPushMatrix();
        GL11.glTranslatef(sr.getWidth() - 100, 100, 0);
        GL11.glRotatef(180.F, 0, 1, 0);
        flag.drawQuad(0, 0, flag.getWidth(), sr.getHeight() - 180);
        GL11.glPopMatrix();
    }

    @Override
    protected void fireAction(Window window, GuiAction action) {
    }

    private void leave(Window window) {
        glfwSetWindowShouldClose(window.getPointer(), true);
    }

    private void settings() {
    }

    private void singleplayer(Window window) {
        window.setScreen(new GuiGameSelect());
    }
}
