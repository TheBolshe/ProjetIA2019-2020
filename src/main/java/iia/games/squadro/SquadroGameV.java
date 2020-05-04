package iia.games.squadro;

import iia.games.base.IGame;

public class SquadroGameV extends ASquadroGame {

    public SquadroGameV() {
        super();
    }

    @Override
    public IGame play(String move, String role) {
        ASquadroGame nextState = (ASquadroGame) ASquadroGame.deepCopy(this);
        nextState.updateBoard(move, role);
        return nextState;
    }

    @Override
    public int getValue(String role) {
        // TODO heuristic for Vertical player
        /* Avant meme de choisir une fonction heuristique particuliere on applique ca a chaque fois */
        switch (whoWon()) {
            case "PERSONNE":
                return basicHeuristic(role);
            case "HORISONTAL":
                return super.MIN_VALUE;
            case "VERTICAL":
                return super.MAX_VALUE;
            default:
                return 0;
        }
    }
}
