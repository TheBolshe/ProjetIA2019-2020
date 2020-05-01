package iia.simpleGame.squadro;

public class SquadroGameV extends ASquadroGame {

    public SquadroGameV (Character[][] board) {
        super();
        this.board = board;
    }

    @Override
    public int getValue(String role) {
        // TODO heuristic for Vertical player
        return 0;
    }
}
