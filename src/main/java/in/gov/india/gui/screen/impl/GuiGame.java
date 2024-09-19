package in.gov.india.gui.screen.impl;

import com.google.common.eventbus.Subscribe;
import in.gov.india.events.EventGamePaused;
import in.gov.india.events.EventKeybind;
import in.gov.india.game.Game;
import in.gov.india.gui.Window;
import in.gov.india.gui.render.FontRenderer;
import in.gov.india.gui.screen.GuiScreen;
import in.gov.india.gui.screen.buttons.GuiButton;
import in.gov.india.util.ColorUtil;

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
        if (game.isPaused()) {
            this.addPauseButtons(window);
        }
    }

    @Override
    protected void draw(Window window) {
        game.drawGame();

        if (game.isPaused()) {
            this.drawPauseMenu(window);
        }
    }

    @Subscribe
    public void onGamePause(EventGamePaused event) {
        if (event.isPaused()) {
            this.addPauseButtons(event.getGame().getPajeetScroller().getWindow());
        } else {
            this.buttons.clear();
        }
    }

    private void addPauseButtons(Window window) {
        this.addHorizontalMiddleButtons(window, 230.F,
                new GuiButton("Resume", () -> this.getGame().setPaused(false)),
                new GuiButton("Options", () -> this.options(window)),
                new GuiButton("Exit Game", () -> this.exit(window)));
    }

    private void options(Window window) {

    }

    private void exit(Window window) {
        window.setScreen(new GuiMainMenu());
    }

    private void drawPauseMenu(Window window) {
        window.getRenderer().drawRect(window.getResolution(), Integer.MIN_VALUE);

        final float y = buttons.getFirst().getY() - 75;
        final float x = window.getResolution().getX() + window.getResolution().getWidth() / 2.F;

        FontRenderer font = window.getRenderer().getSatisfyRegular().get(30.F);
        font.drawStringCentered(this.getGame().getName(), x, y, ColorUtil.generateWhiteColor(220));
        font.drawStringCentered("Game Paused", x, y + 33, ColorUtil.generateWhiteColor(170));
    }
}
