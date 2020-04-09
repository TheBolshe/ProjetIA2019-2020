package iia.games.squadro;

import iia.games.base.ABoard;

import java.util.ArrayList;

public class BoardSquadro extends ABoard<MoveSquadro, RoleSquadro, BoardSquadro> {

    /*
        INTERFACE
     */

    @Override
    public BoardSquadro play(MoveSquadro move, RoleSquadro role) {
        return null;
    }

    @Override
    public ArrayList<MoveSquadro> possibleMoves(RoleSquadro role) {
        return null;
    }


    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public boolean isValidMove(MoveSquadro move, RoleSquadro role) {
            return false;
    }

}
