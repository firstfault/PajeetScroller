package in.gov.india;

import com.google.common.eventbus.EventBus;
import in.gov.india.gui.textures.TextureManager;
import in.gov.india.gui.Window;

public class PajeetScroller {
    private final EventBus eventBus;
    private final Window window;
    private final TextureManager textureManager;

    public PajeetScroller() {
        this.eventBus = new EventBus();
        this.window = new Window(this);
        this.textureManager = new TextureManager(this);
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public Window getWindow() {
        return window;
    }

    public TextureManager getTextureManager() {
        return textureManager;
    }

    public static void main(String[] args) {
        new PajeetScroller().getWindow().begin();
    }
}
