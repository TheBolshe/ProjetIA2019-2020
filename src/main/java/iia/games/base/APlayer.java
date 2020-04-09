package iia.games.base;

import iia.games.algo.IAlgo;

public abstract class APlayer <M extends IMove, R extends IRole, B extends ABoard<M, R, B>> implements IPlayer<M, R, B> {

        private IAlgo<M, R, B> algo;
        private R role;
        private IHeuristic<R, B> heuristic;
        private String name;

        public APlayer(String name,
                  IAlgo<M, R, B> algo,
                  R role,
                  IHeuristic<R, B> h){
            this.name = name;
            this.algo = algo;
            this.role = role;
            this.heuristic = h;
        }

        @Override
        public M chooseMove(B board) {
            return algo.bestMove(board, role, heuristic);
        }

        public String getName() {
            return name;
        }

        public R getRole() {
            return role;
        }

        public IAlgo<M, R, B> getAlgo(){ return algo; }

        public IHeuristic<R, B> getHeuristic() { return heuristic; }

    @Override
    public String toString() {
        return name
                +"{" + algo +
                ", " + role +
                '}';
    }
}
