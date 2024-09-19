package in.gov.india.gui.screen.impl.mainmenu;

import com.google.common.eventbus.Subscribe;
import in.gov.india.events.EventGameTick;
import in.gov.india.gui.ScreenResolution;
import in.gov.india.gui.Window;
import in.gov.india.gui.audio.Sound;
import in.gov.india.gui.screen.GuiScreen;
import in.gov.india.gui.textures.TextureManager;

public abstract class GuiAbstractMainMenu extends GuiScreen {
    private Sound soundtrack;

    @Override
    protected void initialize(Window window) {
        this.soundtrack = window.getAudioManager().getSound("menu_soundtrack.ogg");
    }

    @Override
    public void close(Window window, GuiScreen nextScreen) {
        if (nextScreen instanceof GuiAbstractMainMenu) {
            return;
        }
        this.soundtrack.stop();
    }

    @Subscribe
    public void onGameTick(EventGameTick event) {
        if (!this.soundtrack.isPlaying()) {
            this.soundtrack.replay();
        }
    }

    @Override
    protected void draw(Window window) {
        ScreenResolution sr = window.getResolution();
        TextureManager textureManager = window.getPajeetScroller().getTextureManager();

        textureManager.getTexture("menu_background.jpg").drawQuad(sr);
    }
}
