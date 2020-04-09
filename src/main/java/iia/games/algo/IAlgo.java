package iia.games.algo;

import iia.games.base.IBoard;
import iia.games.base.IHeuristic;
import iia.games.base.IMove;
import iia.games.base.IRole;

public interface IAlgo<M extends IMove, R extends IRole, B extends IBoard<M, R, B>> {

    M bestMove(B b, R r, IHeuristic<R, B> h);

}
