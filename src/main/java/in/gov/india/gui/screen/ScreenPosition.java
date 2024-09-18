package in.gov.india.gui.screen;

import javax.vecmath.Vector2d;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector4d;
import javax.vecmath.Vector4f;

public class ScreenPosition extends Vector4f {
    public ScreenPosition() {
    }

    public ScreenPosition(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.w = width;
        this.z = height;
    }

    public ScreenPosition(float width, float height) {
        this.w = width;
        this.z = height;
    }

    public void setSize(float width, float height) {
        this.setWidth(width);
        this.setHeight(height);
    }

    public void setPosition(float x, float y) {
        this.setX(x);
        this.setY(y);
    }

    public float getWidth() {
        return w;
    }

    public float getHeight() {
        return z;
    }

    public void setWidth(float width) {
        this.w = width;
    }

    public void setHeight(float height) {
        this.z = height;
    }

    public boolean isInside(float x, float y) {
        return x >= this.x && y >= this.y && x < this.x + this.getWidth() && y < this.y + this.getHeight();
    }

    public boolean intersects(ScreenPosition other) {
        return this.x < other.x + other.getWidth() && this.x + this.getWidth() > other.x &&
                this.y < other.y + other.getHeight() && this.y + this.getHeight()> other.y;
    }

    protected boolean isInside(Vector2f position) {
        return this.isInside(position.x, position.y);
    }
}
