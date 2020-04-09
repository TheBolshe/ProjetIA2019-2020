package iia.games.algo;

import iia.games.base.IMove;
import iia.games.base.IRole;
import iia.games.base.IBoard;
import iia.games.base.IHeuristic;

import java.util.ArrayList;
import java.util.Random;

public class Alea<M extends IMove, R extends IRole, B extends IBoard<M, R, B>> implements IAlgo<M, R, B> {

    @Override
    public M bestMove(B b, R r, IHeuristic<R, B> h) {
        System.out.print("[Alea]");
        ArrayList<M> all_moves = b.possibleMoves(r);
        int i = new Random().nextInt(all_moves.size());
        System.out.println(" choosed move number " + i);
        return all_moves.get(i);
    }

    @Override
    public String toString() {
        return "Alea";
    }
}
