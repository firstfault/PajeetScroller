package in.gov.india.gui.misc;

import static org.lwjgl.glfw.GLFW.*;

public enum MouseButton {
    LEFT(GLFW_MOUSE_BUTTON_LEFT),
    RIGHT(GLFW_MOUSE_BUTTON_RIGHT),
    MIDDLE(GLFW_MOUSE_BUTTON_MIDDLE),
    UNKNOWN(0);

    private final int buttonCode;

    MouseButton(int buttonCode) {
        this.buttonCode = buttonCode;
    }

    public int getButtonCode() {
        return buttonCode;
    }

    public static MouseButton fromCode(int code) {
        for (MouseButton button : values()) {
            if (button.buttonCode == code) {
                return button;
            }
        }
        return UNKNOWN;
    }
}
