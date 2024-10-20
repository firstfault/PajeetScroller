package in.gov.india;

import com.google.common.eventbus.EventBus;
import in.gov.india.characters.CharacterManager;
import in.gov.india.gui.Window;
import in.gov.india.gui.textures.TextureManager;
import in.gov.india.keys.KeybindSystem;

public class PajeetScroller {
    private final EventBus eventBus;
    private final KeybindSystem keybindSystem;
    private final Window window;
    private final TextureManager textureManager;
    private final CharacterManager characterManager;

    public PajeetScroller() {
        this.eventBus = new EventBus();
        this.keybindSystem = new KeybindSystem(this);
        this.window = new Window(this);
        this.textureManager = new TextureManager(this);
        this.characterManager = new CharacterManager(this);
    }

    public KeybindSystem getKeybindSystem() {
        return keybindSystem;
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

    public CharacterManager getCharacterManager() {
        return characterManager;
    }

    public static void main(String[] args) {
        new PajeetScroller().getWindow().begin();
    }
}
