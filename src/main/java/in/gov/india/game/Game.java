package in.gov.india.game;

import com.google.common.eventbus.Subscribe;
import in.gov.india.PajeetScroller;
import in.gov.india.entities.Entity;
import in.gov.india.entities.impl.EntityPlayer;
import in.gov.india.events.EventGamePaused;
import in.gov.india.events.EventGameQuit;
import in.gov.india.events.EventGameTick;
import in.gov.india.events.EventWindowFocus;
import in.gov.india.gui.screen.impl.gameselect.GameType;

import java.util.ArrayList;
import java.util.List;

public abstract class Game {
    private final GameType type;
    private final PajeetScroller pajeetScroller;
    private final List<Entity> entityList = new ArrayList<>();
    private EntityPlayer localPlayer;
    private boolean paused;

    protected Game(GameType type, PajeetScroller pajeetScroller) {
        this.type = type;
        this.pajeetScroller = pajeetScroller;

        pajeetScroller.getEventBus().register(this);
    }

    protected final void setLocalPlayer(EntityPlayer localPlayer) {
        if (this.localPlayer != null) {
            throw new RuntimeException("Added local player twice");
        }

        this.localPlayer = localPlayer;
        this.addEntity(localPlayer);
    }

    public final void addEntity(Entity entity) {
        this.getEntityList().add(entity);
    }

    public final void removeEntity(Entity entity) {
        this.getEntityList().remove(entity);
    }

    public final void setPaused(boolean paused) {
        if (this.paused == paused) {
            return;
        }
        this.paused = paused;
        pajeetScroller.getEventBus().post(new EventGamePaused(this, this.isPaused()));
    }

    public final boolean isPaused() {
        return paused;
    }

    public final String getName() {
        return type.getName();
    }

    public final PajeetScroller getPajeetScroller() {
        return pajeetScroller;
    }

    public final List<Entity> getEntityList() {
        return entityList;
    }

    public final void stop() {
        pajeetScroller.getEventBus().unregister(this);
        pajeetScroller.getEventBus().post(new EventGameQuit(this));
    }

    @Subscribe
    private void onGameTick(EventGameTick event) {
        if (!this.isPaused()) {
            this.tick();

            Entity[] entities = getEntityList().toArray(Entity[]::new);

            for (Entity entity : entities) {
                entity.tick();
            }

            for (Entity e2 : entities) {
                for (Entity e1 : entities) {
                    if (e1 == e2) continue;

                    if (e1.intersects(e2)) {
//                        if (e1.getTexture().getCollision().isColliding(e2.getTexture().getCollision(), e1.x, e1.y, e2.x, e2.y)) {
                            e1.collide(e2);
//                        }
                    }
                }
            }
        }
    }

    @Subscribe
    public void onWindowFocus(EventWindowFocus event) {
        if (!event.isFocused()) {
            this.setPaused(true);
        }
    }

    public EntityPlayer getLocalPlayer() {
        return localPlayer;
    }

    public final void drawGame() {
        draw();

        for (Entity entity : getEntityList()) {
            entity.draw();
        }
    }

    protected abstract void draw();
    protected abstract void tick();
}
