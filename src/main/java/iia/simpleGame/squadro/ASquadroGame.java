package iia.simpleGame.squadro;

import iia.simpleGame.base.AGame;
import iia.simpleGame.base.IGame;

import java.util.ArrayList;

public abstract class ASquadroGame extends AGame {

    public ASquadroGame(){
        // TODO !
    }

    @Override
    public IGame play(String move, String role) {
        // TODO !
        return null;
    }

    @Override
    public ArrayList<String> possibleMoves(String role) {
        // TODO !
        return null;
    }

    @Override
    public boolean isValidMove(String move, String role) {
        // TODO !
        return false;
    }

    @Override
    public boolean isGameOver() {
        // TODO !
        return false;
    }
}
