package in.gov.india.gui.screen.impl.gameselect;

import in.gov.india.gui.Window;
import in.gov.india.gui.screen.actions.GuiAction;
import in.gov.india.gui.screen.buttons.GuiButtonSquareImage;
import in.gov.india.gui.screen.impl.GuiGame;
import in.gov.india.gui.screen.impl.GuiMainMenu;
import in.gov.india.gui.screen.impl.mainmenu.GuiAbstractMainMenu;

import java.util.Arrays;

public final class GuiGameSelect extends GuiAbstractMainMenu {
    @Override
    protected void initialize(Window window) {
        super.initialize(window);

        this.addHorizontalMiddleButtons(window, window.getResolution().getHeight() / 2.4F, Arrays.stream(GameType.values())
                .map(value -> new GuiButtonSquareImage(value.getName(),
                        () -> window.setScreen(new GuiGame(value.createGame(window.getPajeetScroller()))), value.getAssetBackground()))
                .toArray(GuiButtonSquareImage[]::new));
    }

    @Override
    protected void fireAction(Window window, GuiAction action) {
        if (action == GuiAction.ESCAPE) {
            window.setScreen(new GuiMainMenu());
        }
    }

    @Override
    protected void draw(Window window) {
        super.draw(window);

        window.getRenderer().getSatisfyRegular().get(30.F).drawStringCentered("Select Game", window.getResolution().getWidthMiddle(), window.getResolution().getHeight() / 2.4F - 40.5F, -1);
    }
}
