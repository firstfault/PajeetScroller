package in.gov.india.gui.screen.impl;

import com.google.common.eventbus.Subscribe;
import in.gov.india.events.EventKeybind;
import in.gov.india.game.Game;
import in.gov.india.gui.Window;
import in.gov.india.gui.screen.GuiScreen;

public class GuiGame extends GuiScreen {
    private final Game game;

    public GuiGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    @Override
    public void close(Window window) {
        game.stop();
    }

    @Subscribe
    private void onKeybind(EventKeybind event) {
        if (event.isPressed() && event.getKeybindSystem().pauseGame == event.getKeybind()) {
            this.game.setPaused(!this.game.isPaused());
        }
    }

    @Override
    protected void initialize(Window window) {
    }

    @Override
    protected void draw(Window window) {
        game.draw();
    }
}
