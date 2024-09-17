package in.gov.india.gui.textures;

import com.google.common.eventbus.Subscribe;
import in.gov.india.PajeetScroller;
import in.gov.india.events.EventGameClosed;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;

public class TextureManager {
    private final PajeetScroller pajeetScroller;
    private final Map<String, Texture> textures = new HashMap<>();

    public TextureManager(PajeetScroller pajeetScroller) {
        this.pajeetScroller = pajeetScroller;
        pajeetScroller.getEventBus().register(this);
    }

    @Subscribe
    public void onGameClosed(EventGameClosed event) {
        this.cleanup();
    }

    public Texture getTexture(String path) {
        return this.textures.computeIfAbsent(path, Texture::new);
    }

    public void cleanup() {
        for (Texture texture : textures.values()) {
            glDeleteTextures(texture.getTextureId());
        }
        textures.clear();
    }
}