package in.gov.india.events;

import in.gov.india.game.Game;

public class EventGamePaused {
    private final Game game;
    private final boolean paused;

    public EventGamePaused(Game game, boolean paused) {
        this.game = game;
        this.paused = paused;
    }

    public Game getGame() {
        return game;
    }

    public boolean isPaused() {
        return paused;
    }
}
