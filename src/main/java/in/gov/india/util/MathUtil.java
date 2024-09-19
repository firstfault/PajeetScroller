package in.gov.india.util;

import java.util.List;
import java.util.Random;

public class MathUtil {
    private static final Random RANDOM = new Random();

    public static float clamp_float(float num, float min, float max) {
        return num < min ? min : (Math.min(num, max));
    }

    public static <T> T getRandomElement(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List must not be null or empty");
        }
        return list.get(RANDOM.nextInt(list.size()));
    }

    public static Random getRandom() {
        return RANDOM;
    }
}
