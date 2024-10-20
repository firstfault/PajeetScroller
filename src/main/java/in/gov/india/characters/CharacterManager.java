package in.gov.india.characters;

import in.gov.india.PajeetScroller;

import java.util.List;

public class CharacterManager {
    private final PajeetScroller pajeetScroller;
    private final List<Character> characters;
    private Character selected;

    public CharacterManager(PajeetScroller pajeetScroller) {
        this.pajeetScroller = pajeetScroller;
        this.characters = List.of(new Character("Aditya", pajeetScroller, "aditya.png"), new Character("Pajeet", pajeetScroller, "pajeet.png"));
        this.selected = characters.get(0);
    }

    public Character getSelected() {
        return selected;
    }

    public List<Character> getCharacters() {
        return characters;
    }
}
