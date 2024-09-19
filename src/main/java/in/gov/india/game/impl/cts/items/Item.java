package in.gov.india.game.impl.cts.items;

import in.gov.india.game.Game;
import in.gov.india.gui.textures.Texture;
import in.gov.india.util.MathUtil;

import java.util.List;

public class Item {
    private final Game game;
    private final String name;
    private final Texture texture;
    private final int smell;

    protected Item(Game game, String name, int smell, String... assets) {
        this.game = game;
        this.name = name;
        this.smell = smell;
        if (assets.length == 0) {
            throw new ArrayIndexOutOfBoundsException("No assets specified for item '" + name + "'");
        }
        this.texture = game.getPajeetScroller().getTextureManager().getTexture(MathUtil.getRandomElement(List.of(assets)));
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

    public int getSmell() {
        return smell;
    }
}
