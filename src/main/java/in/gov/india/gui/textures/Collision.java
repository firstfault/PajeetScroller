package in.gov.india.gui.textures;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;

public class Collision {
    private final Texture texture;
    private ByteBuffer collisionMask;

    private static final Map<Integer, ByteBuffer> maskCache = new HashMap<>();

    public Collision(Texture texture) {
        this.texture = texture;
        this.initialize();
    }

    private void initialize() {
        int textureId = texture.getTextureId();
        if (maskCache.containsKey(textureId)) {
            collisionMask = maskCache.get(textureId);
        } else {
            collisionMask = ByteBuffer.allocate(texture.getWidth() * texture.getHeight());

            glBindTexture(GL_TEXTURE_2D, textureId);
            ByteBuffer buffer = ByteBuffer.allocateDirect(texture.getWidth() * texture.getHeight() * 4);
            glGetTexImage(GL_TEXTURE_2D, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

            for (int i = 0; i < texture.getWidth() * texture.getHeight(); i++) {
                int alpha = buffer.get(i * 4 + 3) & 0xFF;
                collisionMask.put(i, (byte) (alpha > 5 ? 1 : 0));
                if (i == 0) {
                    System.out.println(texture.getAssetPath() + " : " + alpha);
                }
            }
            maskCache.put(textureId, collisionMask);
        }
    }

    public boolean isColliding(Collision other, float x1, float y1, float x2, float y2) {
        int width1 = texture.getWidth();
        int height1 = texture.getHeight();
        int width2 = other.texture.getWidth();
        int height2 = other.texture.getHeight();

        int xStart = (int) Math.max(x1, x2);
        int yStart = (int) Math.max(y1, y2);
        int xEnd = (int) Math.min(x1 + width1, x2 + width2);
        int yEnd = (int) Math.min(y1 + height1, y2 + height2);

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                int tex1X = (int) (x - x1);
                int tex1Y = (int) (y - y1);
                int tex2X = (int) (x - x2);
                int tex2Y = (int) (y - y2);

                if ((collisionMask.get(tex1Y * width1 + tex1X) & 0xFF) != 0 &&
                    (other.collisionMask.get(tex2Y * width2 + tex2X) & 0xFF) != 0) {
                    return true;
                }
            }
        }
        return false;
    }
}