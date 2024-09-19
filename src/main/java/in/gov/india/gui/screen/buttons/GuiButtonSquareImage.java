package in.gov.india.gui.screen.buttons;

import in.gov.india.gui.Window;
import in.gov.india.gui.render.FontRenderer;
import in.gov.india.gui.textures.Texture;
import in.gov.india.util.ColorUtil;

public class GuiButtonSquareImage extends GuiButton {
    private final String imageAsset;

    public GuiButtonSquareImage(String text, Runnable action, String imageAsset) {
        super(text, action);
        this.imageAsset = imageAsset;
        this.setSize(200.F, 200.F);
    }

    @Override
    public void draw(Window window) {
        final boolean hovered = this.isInside(window.getMousePosition());
        if (!hovered) window.getRenderer().drawRect(this, Integer.MIN_VALUE);
        Texture texture = window.getPajeetScroller().getTextureManager().getTexture(this.imageAsset);
        texture.drawQuad(this, hovered ? 1.F : 0.8F);
        FontRenderer font = window.getRenderer().getSatisfyRegular().get(32.F);
        float stringWidth = font.getStringWidth(this.getText());
        font.drawString(this.getText(), this.x + (this.getWidth() / 2.F) - (stringWidth / 2.F), this.y + this.getHeight()/2.f-16.F, ColorUtil.generateWhiteColor(hovered ? 250 : 200));
    }
}
