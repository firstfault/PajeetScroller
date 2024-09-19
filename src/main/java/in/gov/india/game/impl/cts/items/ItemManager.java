package in.gov.india.game.impl.cts.items;

import in.gov.india.entities.impl.EntityItem;
import in.gov.india.game.Game;
import in.gov.india.game.impl.cts.items.impl.ItemIndianShit;
import in.gov.india.game.impl.cts.movement.MovementItemCts;
import in.gov.india.util.MathUtil;

import java.util.List;

public class ItemManager {
    private final List<Item> itemRegistry;
    private int spawnSpeed = 45;
    private int spawnTicks = spawnSpeed - 10;
    private final Game game;

    public ItemManager(Game game) {
        this.game = game;
        this.itemRegistry = List.of(
                new ItemIndianShit(game)
        );
    }

    public void update() {
        if (++this.spawnTicks > spawnSpeed) {
            this.game.addEntity(new EntityItem(new MovementItemCts(), MathUtil.getRandomElement(this.itemRegistry)));
            this.spawnTicks = 0;
        }
    }
}
