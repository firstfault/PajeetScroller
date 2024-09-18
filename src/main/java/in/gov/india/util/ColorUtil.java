package in.gov.india.util;

import java.awt.*;

public class ColorUtil {
    public static int generateWhiteColor(int white) {
        return new Color(white, white, white).getRGB();
    }
}
