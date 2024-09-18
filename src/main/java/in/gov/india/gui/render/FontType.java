package in.gov.india.gui.render;

import in.gov.india.gui.Window;
import org.lwjgl.nanovg.NVGColor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.BufferUtils.createByteBuffer;
import static org.lwjgl.nanovg.NanoVG.*;
import static org.lwjgl.system.MemoryUtil.memSlice;

public class FontType {
    private final Window window;
    private final String name;
    private final ByteBuffer buffer;
    private final Map<Float, FontRenderer> fontRendererMap = new HashMap<>();

    public FontType(Window window, String name) {
        this.name = name;
        this.window = window;
        ByteBuffer fontBuffer = null;
        try {
            fontBuffer = getByteBuffer(new FileInputStream("assets/" + name + ".ttf"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Font not found: " + name, e);
        }
        nvgCreateFontMem(window.getNanoVGContext(), name, fontBuffer, false);
        this.buffer = fontBuffer;
    }

    public FontRenderer get(float size) {
        return fontRendererMap.computeIfAbsent(size, x -> new FontRenderer(this, size));
    }

    public Window getWindow() {
        return window;
    }

    private static ByteBuffer getByteBuffer(final InputStream inputStream) {
        try {
            ByteBuffer buffer;
            try (final ReadableByteChannel rbc = Channels.newChannel(inputStream)) {
                buffer = createByteBuffer(524288);

                while (true) {
                    int bytes = rbc.read(buffer);
                    if (bytes == -1) {
                        break;
                    }
                    if (buffer.remaining() == 0) {
                        buffer = resizeBuffer(buffer, buffer.capacity() * 3 / 2);
                    }
                }
            }
            buffer.flip();
            return memSlice(buffer);
        } catch (IOException e) {
            throw new NullPointerException();
        }
    }

    private static ByteBuffer resizeBuffer(final ByteBuffer buffer, final int newCapacity) {
        final ByteBuffer newBuffer = createByteBuffer(newCapacity);
        buffer.flip();
        newBuffer.put(buffer);
        return newBuffer;
    }

    public String getName() {
        return name;
    }
}
