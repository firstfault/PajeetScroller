package in.gov.india.gui.screen;

import com.google.common.eventbus.Subscribe;
import in.gov.india.events.EventMouseButton;
import in.gov.india.gui.ScreenResolution;
import in.gov.india.gui.Window;
import in.gov.india.gui.screen.buttons.GuiButton;

import java.util.ArrayList;
import java.util.List;

public abstract class GuiScreen {
    protected final List<GuiButton> buttons = new ArrayList<>();

    public final void drawGui(Window window) {
        draw(window);

        for (GuiButton button : buttons) {
            button.draw(window);
        }
    }

    public final void initializeGui(Window window) {
        buttons.clear();

        initialize(window);
    }

    @Subscribe
    public void onMouseButton(EventMouseButton event) {
        for (GuiButton button : buttons) {
            if (button.isInside(event.getCoordinates())) {
                button.handleClick(event);
                break;
            }
        }
    }

    public abstract void close(Window window);
    protected abstract void initialize(Window window);
    protected abstract void draw(Window window);

    protected final void addHorizontalMiddleButtons(Window window, float y, GuiButton... buttons) {
        ScreenResolution sr = window.getResolution();

        float btnWidth = 250.F;
        float btnHeight = 50.F;

        this.buttons.clear();

        for (GuiButton button : buttons) {
            button.setPosition(sr.getWidth() / 2.F - btnWidth / 2.F, y);
            button.setSize(btnWidth, btnHeight);
            this.buttons.add(button);

            y += btnHeight + 10.F;
        }
    }
}
