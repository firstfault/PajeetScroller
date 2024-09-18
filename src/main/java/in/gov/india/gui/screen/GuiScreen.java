package in.gov.india.gui.screen;

import com.google.common.eventbus.Subscribe;
import in.gov.india.events.EventMouseButton;
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

    protected abstract void initialize(Window sr);
    protected abstract void draw(Window sr);
}
