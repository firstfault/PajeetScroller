package in.gov.india.events;

import in.gov.india.game.Game;

public class EventGameQuit {
    private final Game game;

    public EventGameQuit(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
