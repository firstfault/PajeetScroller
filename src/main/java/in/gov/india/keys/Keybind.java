package in.gov.india.keys;

import in.gov.india.events.EventKeybind;

public final class Keybind {
    private final String name;
    private int keyCode;
    private boolean pressed;
    private KeybindSystem keybindSystem;

    public Keybind(String name, int keyCode) {
        this.name = name;
        this.keyCode = keyCode;
    }

    void setKeybindSystem(KeybindSystem keybindSystem) {
        this.keybindSystem = keybindSystem;
    }

    public String getName() {
        return name;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        if (this.pressed == pressed) {
            return;
        }

        this.pressed = pressed;
        keybindSystem.getPajeetScroller().getEventBus().post(new EventKeybind(this.keybindSystem, this, this.pressed));
    }
}
