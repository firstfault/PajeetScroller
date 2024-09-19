package in.gov.india.events;

public class EventWindowFocus {
    private final boolean focused;

    public EventWindowFocus(boolean focused) {
        this.focused = focused;
    }

    public boolean isFocused() {
        return focused;
    }
}
