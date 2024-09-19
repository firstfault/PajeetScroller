package in.gov.india.game;

import com.google.common.eventbus.Subscribe;
import in.gov.india.PajeetScroller;
import in.gov.india.entities.Entity;
import in.gov.india.entities.impl.EntityPlayer;
import in.gov.india.events.EventGamePaused;
import in.gov.india.events.EventGameTick;

import java.util.ArrayList;
import java.util.List;

public abstract class Game {
    private final String name;
    private final PajeetScroller pajeetScroller;
    private final List<Entity> entityList = new ArrayList<>();
    private EntityPlayer localPlayer;
    private boolean paused;

    protected Game(String name, PajeetScroller pajeetScroller) {
        this.name = name;
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
        return name;
    }

    public final PajeetScroller getPajeetScroller() {
        return pajeetScroller;
    }

    public final List<Entity> getEntityList() {
        return entityList;
    }

    public final void stop() {
        pajeetScroller.getEventBus().unregister(this);
    }

    @Subscribe
    private void onGameTick(EventGameTick event) {
        if (!this.isPaused()) {
            this.tick();
            for (Entity entity : getEntityList()) {
                entity.tick();
            }
        }
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
