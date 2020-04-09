package iia.games.base;

import java.util.ArrayList;

public abstract class ABoard<M extends IMove, R extends IRole, B extends ABoard<M, R, B>> implements IBoard<M, R, B>{

    public ArrayList<B> successors(R r) {
        ArrayList<B> succ = new ArrayList<B>();
        for (M m: this.possibleMoves(r)) {
            succ.add(this.play(m, r));
        }
        return succ;
    }

}
