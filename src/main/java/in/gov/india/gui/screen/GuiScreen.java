package in.gov.india.gui.screen;

import in.gov.india.PajeetScroller;
import in.gov.india.gui.ScreenResolution;
import in.gov.india.gui.screen.buttons.GuiButton;

import java.util.ArrayList;
import java.util.List;

public abstract class GuiScreen {
    protected final PajeetScroller ps;
    protected final List<GuiButton> buttons = new ArrayList<>();

    public GuiScreen(PajeetScroller ps) {
        this.ps = ps;
    }

    public final void drawGui(ScreenResolution sr) {
        draw(sr);

        for (GuiButton button : buttons) {
            button.draw();
        }
    }

    public final void initializeGui(ScreenResolution sr) {
        buttons.clear();

        initialize(sr);

        for (GuiButton button : buttons) {
            button.setGuiScreen(this);
        }
    }

    public PajeetScroller getPajeetScroller() {
        return ps;
    }

    protected abstract void initialize(ScreenResolution sr);
    protected abstract void draw(ScreenResolution sr);
}
