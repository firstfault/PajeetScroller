package in.gov.india.gui.render;

import org.lwjgl.nanovg.NVGColor;

public class NanoVGUtil {
    public static NVGColor createColor(final int rgb) {
        final float r = (rgb >> 16) & 0xFF;
        final float g = (rgb >> 8) & 0xFF;
        final float b = rgb & 0xFF;
        final float a = (rgb >> 24) & 0xFF;
        final NVGColor color = NVGColor.calloc();
        color.r(r / 255.0F);
        color.g(g / 255.0F);
        color.b(b / 255.0F);
        color.a(a / 255.0F);
        return color;
    }
}
