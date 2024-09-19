package in.gov.india.gui.render;

import in.gov.india.gui.Window;
import org.lwjgl.nanovg.NVGColor;

import javax.vecmath.Tuple4f;

import static org.lwjgl.nanovg.NanoVG.*;

public class Renderer {
    private final Window window;
    private FontType satisfyRegular;

    public Renderer(Window window) {
        this.window = window;
        this.satisfyRegular = new FontType(window, "Satisfy-Regular");
    }

    public FontType getSatisfyRegular() {
        return satisfyRegular;
    }

    public void drawRect(Tuple4f pos, int color) {
        drawRect(pos.x, pos.y, pos.w, pos.z, color);
    }

    public void drawRect(float x, float y, float w, float h, int color) {
        try (final NVGColor nvgColor = NanoVGUtil.createColor(color)) {
            final long context = window.getNanoVGContext();

            nvgFillColor(context, nvgColor);
            nvgBeginPath(context);
            nvgRect(context, x, y, w, h);
            nvgFill(context);
            nvgClosePath(context);
        }
    }
}
