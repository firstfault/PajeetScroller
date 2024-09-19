package in.gov.india.game.impl.cts.items;

import in.gov.india.game.Game;
import in.gov.india.gui.textures.Texture;

public abstract class Item {
    private final Game game;
    private final String name;
    private final Texture texture;

    protected Item(Game game, String name, Texture texture) {
        this.game = game;
        this.name = name;
        this.texture = texture;
    }

    public final String getName() {
        return name;
    }

    public final Game getGame() {
        return game;
    }

    public final Texture getTexture() {
        return texture;
    }
}
