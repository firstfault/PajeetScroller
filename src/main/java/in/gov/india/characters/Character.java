package in.gov.india.characters;

import in.gov.india.PajeetScroller;
import in.gov.india.gui.textures.Texture;

public final class Character {
    private final String name;
    private final String asset;
    private final PajeetScroller pajeetScroller;
    private Texture texture;

    public Character(String name, PajeetScroller pajeetScroller, String asset) {
        this.name = name;
        this.asset = asset;
        this.pajeetScroller = pajeetScroller;
    }

    public String getName() {
        return name;
    }

    public Texture getTexture() {
        if (this.texture == null) {
            this.texture = pajeetScroller.getTextureManager().getTexture(asset);
        }
        return texture;
    }
}
