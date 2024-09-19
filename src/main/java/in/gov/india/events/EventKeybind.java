package in.gov.india.events;

import in.gov.india.keys.Keybind;
import in.gov.india.keys.KeybindSystem;

public class EventKeybind {
    private final KeybindSystem keybindSystem;
    private final Keybind keybind;
    private final boolean pressed;

    public EventKeybind(KeybindSystem keybindSystem, Keybind keybind, boolean pressed) {
        this.keybindSystem = keybindSystem;
        this.keybind = keybind;
        this.pressed = pressed;
    }

    public KeybindSystem getKeybindSystem() {
        return keybindSystem;
    }

    public Keybind getKeybind() {
        return keybind;
    }

    public boolean isPressed() {
        return pressed;
    }

    public boolean isReleased() {
        return !isPressed();
    }
}
