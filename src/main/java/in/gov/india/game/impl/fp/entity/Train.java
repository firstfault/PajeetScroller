package in.gov.india.game.impl.fp.entity;

public class Train {
    public float x;
    protected float gapY;
    public boolean passed;

    public Train(float x, float gapY) {
        this.x = x;
        this.gapY = gapY;
        this.passed = false;
    }
}
