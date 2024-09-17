package in.gov.india.gui.screen;

public class ScreenPosition {
    protected float x, y;
    protected float width, height;

    public ScreenPosition() {
    }

    public ScreenPosition(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public ScreenPosition(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setSize(float width, float height) {
        this.setWidth(width);
        this.setHeight(height);
    }

    public void setPosition(float x, float y) {
        this.setX(x);
        this.setY(y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public boolean isInside(float x, float y) {
        return x >= this.x && y >= this.y && x < this.x + this.width && y < this.y + this.height;
    }

    public boolean intersects(ScreenPosition other) {
        return this.x < other.x + other.width && this.x + this.width > other.x &&
                this.y < other.y + other.height && this.y + this.height > other.y;
    }
}
