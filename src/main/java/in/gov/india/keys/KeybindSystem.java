package in.gov.india.keys;

import com.google.common.eventbus.Subscribe;
import in.gov.india.PajeetScroller;
import in.gov.india.events.EventKey;
import in.gov.india.events.EventWindowFocus;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class KeybindSystem {
    private final PajeetScroller pajeetScroller;
    private final List<Keybind> keybindList = new ArrayList<>();

    // Movement
    public final Keybind moveW, moveA, moveS, moveD;
    // Misc
    public final Keybind pauseGame;

    public KeybindSystem(PajeetScroller pajeetScroller) {
        this.pajeetScroller = pajeetScroller;

        pajeetScroller.getEventBus().register(this);

        this.add(this.moveW = new Keybind("moveW", GLFW.GLFW_KEY_W));
        this.add(this.moveA = new Keybind("moveA", GLFW.GLFW_KEY_A));
        this.add(this.moveS = new Keybind("moveS", GLFW.GLFW_KEY_S));
        this.add(this.moveD = new Keybind("moveD", GLFW.GLFW_KEY_D));

        this.add(this.pauseGame = new Keybind("pauseGame", GLFW.GLFW_KEY_ESCAPE));
    }

    public PajeetScroller getPajeetScroller() {
        return pajeetScroller;
    }

    private void add(Keybind keybind) {
        keybind.setKeybindSystem(this);
        this.keybindList.add(keybind);
    }

    @Subscribe
    private void onKey(EventKey event) {
        synchronized (this.keybindList) {
            List<Keybind> keybinds = this.keybindList.stream().filter(keybind -> keybind.getKeyCode() == event.getKeyCode()).toList();

            for (Keybind keybind : keybinds) {
                keybind.setPressed(event.isPressed());
            }
        }
    }

    @Subscribe
    private void onFocus(EventWindowFocus event) {
        synchronized (this.keybindList) {
            for (Keybind keybind : this.keybindList) {
                keybind.setPressed(false);
            }
        }
    }
}
