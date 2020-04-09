package iia.games.algo;

import iia.games.base.IMove;
import iia.games.base.IRole;
import iia.games.base.IBoard;
import iia.games.base.IHeuristic;

import java.util.ArrayList;

public class AlwaysFirst<M extends IMove, R extends IRole, B extends IBoard<M, R, B>> implements IAlgo<M, R, B> {

    @Override
    public M bestMove(B b, R r, IHeuristic<R, B> h) {
        ArrayList<M> all_moves = b.possibleMoves(r);
        return (all_moves.size() > 0) ? all_moves.get(0) : null;
    }

    @Override
    public String toString() {
        return "AlwaysFirst";
    }
}
