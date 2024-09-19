package in.gov.india.game.impl.cts.items.impl;

import in.gov.india.game.Game;
import in.gov.india.game.impl.cts.items.Item;

public class ItemIndianShit extends Item {
    public ItemIndianShit(Game game) {
        super(game, "Indian Shit", game.getPajeetScroller().getTextureManager().getTexture("indian-shit.png"));
    }


}
