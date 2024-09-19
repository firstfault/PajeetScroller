package in.gov.india.util;

public class MathUtil {
    public static float clamp_float(float num, float min, float max) {
        return num < min ? min : (Math.min(num, max));
    }
}
