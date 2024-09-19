package in.gov.india.entities.impl;

import in.gov.india.entities.Entity;
import in.gov.india.entities.movement.MovementController;
import in.gov.india.game.Game;
import in.gov.india.game.impl.cts.items.Item;
import in.gov.india.gui.textures.Texture;

public final class EntityPlayer extends Entity {
    private final Texture pajeet;
    private float smell;

    public EntityPlayer(Game game, MovementController movement) {
        super(game, movement);
        this.pajeet = game.getPajeetScroller().getTextureManager().getTexture("pajeet.png");
        this.setSize(130.F, 130.F);
    }

    @Override
    public Texture getTexture() {
        return pajeet;
    }

    public float getSmell() {
        return smell;
    }

    @Override
    public void collide(Entity entity) {
        if (entity instanceof EntityItem) {
            EntityItem entityItem = (EntityItem) entity;
            Item item = entityItem.getItem();

            this.smell += item.getSmell();

            getGame().removeEntity(entity);
        }
    }
}
