package iia.games.base;

public interface IPlayer<M extends IMove, R extends IRole, B extends IBoard<M, R, B>> {

    M chooseMove(B b);
}
