package in.gov.india.entities.impl;

import in.gov.india.entities.Entity;
import in.gov.india.entities.movement.MovementController;
import in.gov.india.game.impl.cts.items.Item;
import in.gov.india.gui.textures.Texture;

public final class EntityItem extends Entity {
    private final Item item;

    public EntityItem(MovementController movement, Item item) {
        super(item.getGame(), movement);
        this.setSize(item.getTexture().getWidth(), item.getTexture().getHeight());
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public Texture getTexture() {
        return getItem().getTexture();
    }
}
