package in.gov.india.gui.screen.buttons;

import in.gov.india.events.EventMouseButton;
import in.gov.india.gui.Window;
import in.gov.india.gui.misc.MouseButton;
import in.gov.india.gui.render.FontRenderer;
import in.gov.india.gui.screen.ScreenPosition;
import in.gov.india.gui.textures.Texture;
import in.gov.india.util.ColorUtil;

public class GuiButton extends ScreenPosition {
    private final String text;
    private final Runnable action;

    public GuiButton(String text, Runnable action) {
        this.text = text;
        this.action = action;
    }

    public void handleClick(EventMouseButton event) {
        if (event.isPressed() && event.getButton() == MouseButton.LEFT) {

            this.action.run();
        }
    }

    public void draw(Window window) {
        final boolean hovered = this.isInside(window.getMousePosition());
        Texture texture = window.getPajeetScroller().getTextureManager().getTexture("button.png");
        texture.drawQuad(this, hovered ? 1.F : 0.8F);
        FontRenderer font = window.getRenderer().getSatisfyRegular().get(32.F);
        float stringWidth = font.getStringWidth(this.text);
        font.drawString(this.text, this.x + (this.getWidth() / 2.F) - (stringWidth / 2.F), this.y + 5.f, ColorUtil.generateWhiteColor(hovered ? 250 : 200));
    }

    public String getText() {
        return text;
    }
}
