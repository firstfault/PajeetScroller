package in.gov.india.game;

import com.google.common.eventbus.Subscribe;
import in.gov.india.PajeetScroller;
import in.gov.india.events.EventGamePaused;
import in.gov.india.events.EventGameTick;

public abstract class Game {
    private final String name;
    private final PajeetScroller pajeetScroller;
    private boolean paused;

    protected Game(String name, PajeetScroller pajeetScroller) {
        this.name = name;
        this.pajeetScroller = pajeetScroller;

        pajeetScroller.getEventBus().register(this);
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

    public final void stop() {
        pajeetScroller.getEventBus().unregister(this);
    }

    @Subscribe
    private void onGameTick(EventGameTick event) {
        if (!this.isPaused()) {
            this.tick();
        }
    }

    public abstract void draw();
    protected abstract void tick();
}
