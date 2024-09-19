package in.gov.india.util;

import java.awt.*;

public class ColorUtil {
    public static int generateWhiteColor(int white) {
        return new Color(white, white, white).getRGB();
    }

    public static int changeAlpha(int color, int target) {
        return (color & 0x00FFFFFF) | (target << 24);
    }
}
