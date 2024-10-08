package in.gov.india.gui;

import in.gov.india.gui.screen.ScreenPosition;

public class ScreenResolution extends ScreenPosition {
    public ScreenResolution(float width, float height) {
        this.setPosition(0, 0);
        this.setSize(width, height);
    }

    public int getWidthI() {
        return (int) getWidth();
    }

    public int getHeightI() {
        return (int) getHeight();
    }

    public float getWidthMiddle() {
        return getWidth() / 2.F;
    }

    public float getHeightMiddle() {
        return getHeight() / 2.F;
    }
}
