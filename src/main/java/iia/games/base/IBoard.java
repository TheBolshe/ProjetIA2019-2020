package iia.games.base;

import java.util.ArrayList;

public interface IBoard<M extends IMove, R extends IRole, B extends IBoard<M, R, B>> {

    B play(M move, R role);

    ArrayList<B> successors(R r);

    ArrayList<M> possibleMoves(R r);

    boolean isGameOver();

    boolean isValidMove(M move, R role);
}

