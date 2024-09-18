package in.gov.india.gui.render;

import in.gov.india.gui.Window;

public class Renderer {
    private final Window window;
    private FontType satisfyRegular;

    public Renderer(Window window) {
        this.window = window;
        this.satisfyRegular = new FontType(window, "Satisfy-Regular");
    }

    public FontType getSatisfyRegular() {
        return satisfyRegular;
    }
}
