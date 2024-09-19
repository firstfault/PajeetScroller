package in.gov.india.gui.render;

import org.lwjgl.nanovg.NVGColor;

import java.nio.FloatBuffer;

import static org.lwjgl.nanovg.NanoVG.*;

public class FontRenderer {
    private final FontType fontType;
    private final float size;

    public FontRenderer(FontType fontType, float size) {
        this.fontType = fontType;
        this.size = size;
    }

    private void setContext() {
        nvgFontFace(fontType.getWindow().getNanoVGContext(), fontType.getName());
        nvgFontSize(fontType.getWindow().getNanoVGContext(), size);
    }

    public float getStringWidth(String text) {
        setContext();
        return nvgTextBounds(fontType.getWindow().getNanoVGContext(), 0, 0, text, (FloatBuffer) null);
    }

    public float drawString(String text, float x, final float y, int color) {
        setContext();

        try (final NVGColor nvgColor = NanoVGUtil.createColor(color)) {
            nvgFillColor(fontType.getWindow().getNanoVGContext(), nvgColor);
        }

        return nvgText(fontType.getWindow().getNanoVGContext(), x, y + size - 2.5f, text);
    }

    public FontType getFontType() {
        return fontType;
    }

    public float getSize() {
        return size;
    }
}
