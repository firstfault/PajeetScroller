package in.gov.india.gui.screen.impl;

import com.google.common.eventbus.Subscribe;
import in.gov.india.events.EventGameTick;
import in.gov.india.game.impl.fp.FlappyPajeet;
import in.gov.india.gui.ScreenResolution;
import in.gov.india.gui.Window;
import in.gov.india.gui.audio.Sound;
import in.gov.india.gui.screen.GuiScreen;
import in.gov.india.gui.screen.buttons.GuiButton;
import in.gov.india.gui.textures.Texture;
import in.gov.india.gui.textures.TextureManager;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;

public final class GuiMainMenu extends GuiScreen {
    private Sound soundtrack;

    @Override
    public void initialize(Window window) {
        this.soundtrack = window.getAudioManager().getSound("menu_soundtrack.ogg");
        this.addHorizontalMiddleButtons(window, 250.F,
                new GuiButton("Singleplayer", () -> this.singleplayer(window)),
                new GuiButton("Options", this::settings),
                new GuiButton("Tuk-Tuk", () -> this.leave(window))
        );
    }

    @Override
    public void close(Window window) {
        soundtrack.stop();
    }

    @Override
    public void draw(Window window) {
        ScreenResolution sr = window.getResolution();
        TextureManager textureManager = window.getPajeetScroller().getTextureManager();

        textureManager.getTexture("menu_background.jpg").drawQuad(sr);

        Texture flag = textureManager.getTexture("menu_flag.png");
        flag.drawQuad(100, 100, flag.getWidth(), sr.getHeight() - 180);

        GL11.glPushMatrix();
        GL11.glTranslatef(sr.getWidth() - 100, 100, 0);
        GL11.glRotatef(180.F, 0, 1, 0);
        flag.drawQuad(0, 0, flag.getWidth(), sr.getHeight() - 180);
        GL11.glPopMatrix();

        window.getRenderer().getSatisfyRegular().get(20.F).drawString("30.0 rupees", 3, 3, new Color(40, 170, 80).getRGB());
    }

    @Subscribe
    public void onGameTick(EventGameTick event) {
        if (!this.soundtrack.isPlaying()) {
            this.soundtrack.replay();
        }
    }

    private void leave(Window window) {
        glfwSetWindowShouldClose(window.getPointer(), true);
    }

    private void settings() {

    }

    private void singleplayer(Window window) {
        window.setScreen(new GuiGame(new FlappyPajeet(window.getPajeetScroller())));
    }
}
