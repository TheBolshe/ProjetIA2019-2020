package iia.games.squadro;

import iia.games.base.IGame;

public class SquadroGameV extends ASquadroGame {

    public SquadroGameV(int h) {
        super(h);
    }

    @Override
    public IGame play(String move, String role) {
        ASquadroGame nextState = (ASquadroGame) ASquadroGame.deepCopy(this);
        nextState.updateBoard(move, role);
        return nextState;
    }

    @Override
    public double getValue(String role) {
        // TODO heuristic for Vertical player
        /* Avant meme de choisir une fonction heuristique particuliere on applique ca a chaque fois */
        switch (whoWon()) {
            case "PERSONNE":
                if (this.h == 1) return basicHeuristic(role);
                if (this.h == 2) return advancedHeuristic(role);
            case "HORISONTAL":
                return super.MIN_VALUE;
            case "VERTICAL":
                return super.MAX_VALUE;
            default:
                return 0;
        }
    }
}
