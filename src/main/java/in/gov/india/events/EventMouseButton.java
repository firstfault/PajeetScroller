package in.gov.india.events;

import in.gov.india.gui.misc.MouseButton;

import javax.vecmath.Vector2f;

public class EventMouseButton {
    private final Vector2f coordinates;
    private final MouseButton button;
    private final boolean pressed;

    public EventMouseButton(Vector2f coordinates, MouseButton button, boolean pressed) {
        this.coordinates = coordinates;
        this.button = button;
        this.pressed = pressed;
    }

    public Vector2f getCoordinates() {
        return coordinates;
    }

    public MouseButton getButton() {
        return button;
    }

    public boolean isPressed() {
        return pressed;
    }

    public boolean isReleased() {
        return !isPressed();
    }
}
