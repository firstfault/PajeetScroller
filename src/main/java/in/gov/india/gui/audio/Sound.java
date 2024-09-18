package in.gov.india.gui.audio;

import org.lwjgl.stb.STBVorbisInfo;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.stb.STBVorbis.*;

public class Sound {
    private int bufferId;
    private int sourceId;

    public Sound(String filePath) {
        bufferId = alGenBuffers();
        
        try (MemoryStack stack = MemoryStack.stackPush()) {
            STBVorbisInfo info = STBVorbisInfo.mallocStack(stack);
            ShortBuffer pcm = readVorbis("assets/sounds/" + filePath, info);

            alBufferData(bufferId, info.channels() == 1 ? AL_FORMAT_MONO16 : AL_FORMAT_STEREO16, pcm, info.sample_rate());
            MemoryUtil.memFree(pcm);
        }
        
        sourceId = alGenSources();
        alSourcei(sourceId, AL_BUFFER, bufferId);
    }

    private ShortBuffer readVorbis(String resource, STBVorbisInfo info) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer error = stack.mallocInt(1);
            long decoder = stb_vorbis_open_filename(resource, error, null);

            if (decoder == 0) {
                throw new RuntimeException("Failed to open Ogg Vorbis file. Error: " + error.get(0));
            }

            stb_vorbis_get_info(decoder, info);

            int channels = info.channels();
            int lengthSamples = stb_vorbis_stream_length_in_samples(decoder);

            ShortBuffer pcm = MemoryUtil.memAllocShort(lengthSamples * channels);

            stb_vorbis_get_samples_short_interleaved(decoder, channels, pcm);
            stb_vorbis_close(decoder);

            return pcm;
        }
    }

    public void play() {
        alSourcePlay(sourceId);
    }

    public void stop() {
        alSourceStop(sourceId);
    }

    public void replay() {
        alSourceRewind(sourceId);
        play();
    }

    public boolean isPlaying() {
        int state = alGetSourcei(sourceId, AL_SOURCE_STATE);
        return state == AL_PLAYING;
    }

    public void cleanup() {
        alDeleteSources(sourceId);
        alDeleteBuffers(bufferId);
    }
}