package in.gov.india.gui.textures;

import in.gov.india.gui.screen.ScreenPosition;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import javax.vecmath.Tuple4f;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL14.GL_BLEND_SRC_RGB;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

public class Texture {
    private final int textureId;
    private final int width, height;

    public Texture(String assetPath) {
        String path = "assets/" + assetPath;
        int textureId;

        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer width = stack.mallocInt(1);
            IntBuffer height = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            ByteBuffer image = STBImage.stbi_load(path, width, height, channels, 4);
            if (image == null) {
                throw new RuntimeException("Failed to load texture file " + path + ": " + STBImage.stbi_failure_reason());
            }

            this.width = width.get();
            this.height = height.get();

            textureId = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, textureId);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, this.width, this.height, 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
            glGenerateMipmap(GL_TEXTURE_2D);

            STBImage.stbi_image_free(image);
        }

        this.textureId = textureId;
    }


    public void drawQuad(float x, float y) {
        drawQuad(x, y, 1.F);
    }

    public void drawQuad(float x, float y, float alpha) {
        drawQuad(x, y, this.width, this.height, alpha);
    }

    public void drawQuad(Tuple4f pos) {
        drawQuad(pos, 1.F);
    }

    public void drawQuad(Tuple4f pos, float alpha) {
        drawQuad(pos.x, pos.y, pos.w, pos.z, alpha);
    }

    public void drawQuad(float x, float y, float width, float height) {
        drawQuad(x, y, width, height, 1.F);
    }

    public void drawQuad(float x, float y, float width, float height, float alpha) {
        glEnable(GL_TEXTURE_2D);
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, textureId);

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glColor4f(1.0f, 1.0f, 1.0f, alpha);

        glBegin(GL_QUADS);
        glTexCoord2f(0, 0); glVertex2f(x, y);
        glTexCoord2f(1, 0); glVertex2f(x + width, y);
        glTexCoord2f(1, 1); glVertex2f(x + width, y + height);
        glTexCoord2f(0, 1); glVertex2f(x, y + height);
        glEnd();

        glDisable(GL_BLEND);
        glBindTexture(GL_TEXTURE_2D, 0);
        glDisable(GL_TEXTURE_2D);
    }

    public int getTextureId() {
        return textureId;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
