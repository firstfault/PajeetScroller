package in.gov.india.gui.screen.impl.gameselect;

import in.gov.india.PajeetScroller;
import in.gov.india.game.Game;
import in.gov.india.game.impl.cts.CatchTheShit;
import in.gov.india.game.impl.fp.FlappyPajeet;

import java.util.function.Function;

public enum GameType {
    FLAPPYPAJEET("FlappyPajeet", "india.jpg", FlappyPajeet::new),
    CATCH_THE_SHIT("Catch The Shit", "street.jpg", CatchTheShit::new),
    ;

    private final String name;
    private final String assetBackground;
    private final Function<PajeetScroller, Game> gameInstantiator;

    GameType(String name, String assetBackground, Function<PajeetScroller, Game> gameInstantiator) {
        this.name = name;
        this.assetBackground = assetBackground;
        this.gameInstantiator = gameInstantiator;
    }

    public String getName() {
        return name;
    }

    public String getAssetBackground() {
        return assetBackground;
    }

    public Game createGame(PajeetScroller pajeetScroller) {
        return gameInstantiator.apply(pajeetScroller);
    }
}
