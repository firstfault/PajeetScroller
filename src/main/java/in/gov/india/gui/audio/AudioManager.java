package in.gov.india.gui.audio;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALC10;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

public class AudioManager {
    private long device;
    private long context;
    private final Map<String, Sound> soundMap = new HashMap<>();

    public void init() {
        device = ALC10.alcOpenDevice((ByteBuffer) null);
        if (device == 0) {
            throw new IllegalStateException("Failed to open the default OpenAL device.");
        }
        context = ALC10.alcCreateContext(device, (IntBuffer) null);
        if (context == 0) {
            ALC10.alcCloseDevice(device);
            throw new IllegalStateException("Failed to create OpenAL context.");
        }
        ALC10.alcMakeContextCurrent(context);
        AL.createCapabilities(ALC.createCapabilities(device));
    }

    public void cleanup() {
        ALC10.alcDestroyContext(context);
        ALC10.alcCloseDevice(device);
    }

    public Sound getSound(String path) {
        return soundMap.computeIfAbsent(path, Sound::new);
    }
}
