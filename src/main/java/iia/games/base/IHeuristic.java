package iia.games.base;


public interface IHeuristic<R extends IRole, B extends IBoard<?, R, ?>> {
    int MAX_VALUE = Integer.MAX_VALUE;
    int MIN_VALUE = - MAX_VALUE;
    int eval(B b, R r);
}
