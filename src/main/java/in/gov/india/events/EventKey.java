package in.gov.india.events;

public class EventKey {
    private final boolean pressed;
    private final int keyCode;

    public EventKey(boolean pressed, int keyCode) {
        this.pressed = pressed;
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public boolean isPressed() {
        return pressed;
    }

    public boolean isReleased() {
        return !isPressed();
    }
}
