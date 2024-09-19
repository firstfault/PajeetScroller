package in.gov.india.gui.screen;

import com.google.common.eventbus.Subscribe;
import in.gov.india.events.EventKeybind;
import in.gov.india.events.EventMouseButton;
import in.gov.india.gui.ScreenResolution;
import in.gov.india.gui.Window;
import in.gov.india.gui.screen.actions.GuiAction;
import in.gov.india.gui.screen.buttons.GuiButton;

import java.util.ArrayList;
import java.util.Arrays;
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
    private void onMouseButton(EventMouseButton event) {
        for (GuiButton button : buttons) {
            if (button.isInside(event.getCoordinates())) {
                button.handleClick(event);
                break;
            }
        }
    }

    @Subscribe
    private void onKeybind(EventKeybind event) {
        if (event.isPressed() && event.getKeybindSystem().pauseGame == event.getKeybind()) {
            this.fireAction(event.getKeybindSystem().getPajeetScroller().getWindow(), GuiAction.ESCAPE);
        }
    }

    public abstract void close(Window window, GuiScreen nextScreen);
    protected abstract void initialize(Window window);
    protected abstract void draw(Window window);
    protected abstract void fireAction(Window window, GuiAction action);

    protected final void addVerticalMiddleButtons(Window window, float y, GuiButton... buttons) {
        ScreenResolution sr = window.getResolution();

        this.buttons.clear();

        for (GuiButton button : buttons) {
            float btnWidth = button.getWidth();
            float btnHeight = button.getHeight();

            button.setPosition(sr.getWidth() / 2.F - btnWidth / 2.F, y);
            this.buttons.add(button);

            y += btnHeight + 10.F;
        }
    }

    protected final void addHorizontalMiddleButtons(Window window, float y, GuiButton... buttons) {
        ScreenResolution sr = window.getResolution();

        this.buttons.clear();

        float x = sr.getWidth() / 2.F -
                Arrays.stream(buttons)
                .map(GuiButton::getWidth)
                .reduce(0.0f, Float::sum) / 2.F;

        for (GuiButton button : buttons) {
            float btnWidth = button.getWidth();

            button.setPosition(x, y);

            this.buttons.add(button);

            x += btnWidth + 10.F;
        }
    }
}
