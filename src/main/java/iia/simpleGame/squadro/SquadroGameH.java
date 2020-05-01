package iia.simpleGame.squadro;

public class SquadroGameH extends ASquadroGame {

    public SquadroGameH (Character[][] board) {
        super();
        this.board = board;
    }

    @Override
    public int getValue(String role) {
        // TODO heuristic for Horizontal player
        return 0;
    }
}
