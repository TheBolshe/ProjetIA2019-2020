package iia.games.squadro;

import iia.games.base.IGame;

public class SquadroGameH extends ASquadroGame {

    public SquadroGameH() {
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
        /* Avant meme de choisir une fonction heuristique particuliere on applique ca a chaque fois */
        switch (whoWon()) {
            case "PERSONNE":
                return basicHeuristic(role);
            case "HORISONTAL":
                return super.MAX_VALUE;
            case "VERTICAL":
                return super.MIN_VALUE;
            default:
                return 0;
        }
    }
    /* On peut ici choisir une nouvelle fonction heuristique qu'on a choisi */
}
