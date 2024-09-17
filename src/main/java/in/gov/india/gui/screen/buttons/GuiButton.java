package in.gov.india.gui.screen.buttons;

import in.gov.india.gui.screen.GuiScreen;
import in.gov.india.gui.screen.ScreenPosition;

public class GuiButton extends ScreenPosition {
    private GuiScreen guiScreen;
    private String text;

    public GuiButton(String text, float x, float y, float width, float height) {
        super(x, y, width, height);
        this.text = text;
    }

    public void setGuiScreen(GuiScreen guiScreen) {
        this.guiScreen = guiScreen;
    }

    public void draw() {
        guiScreen.getPajeetScroller().getTextureManager().getTexture("button.png").drawQuad(this.x, this.y, this.width, this.height);
    }

    public String getText() {
        return text;
    }
}
