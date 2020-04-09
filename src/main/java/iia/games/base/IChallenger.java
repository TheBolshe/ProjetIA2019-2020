package iia.games.base;

public interface IChallenger<M extends IMove> {

    M pickMove(M previousMove);
}
