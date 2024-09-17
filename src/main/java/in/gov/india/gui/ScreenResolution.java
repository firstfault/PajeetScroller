package in.gov.india.gui;

public class ScreenResolution {
    private final float width, height;

    public ScreenResolution(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public int getWidthI() {
        return (int) width;
    }

    public int getHeightI() {
        return (int) height;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
