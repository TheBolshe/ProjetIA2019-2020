package iia.games.nim;

import iia.games.algo.IAlgo;
import iia.games.base.IHeuristic;
import iia.games.base.IPlayer;

public class PlayerNim implements IPlayer<MoveNim, RoleNim, BoardNim> {

    private IAlgo<MoveNim, RoleNim, BoardNim> algo;
    private RoleNim role;
    private IHeuristic<RoleNim, BoardNim> heuristic;
    private String name;

    public PlayerNim(String name,
              IAlgo<MoveNim, RoleNim, BoardNim> algo,
              RoleNim role,
              IHeuristic<RoleNim, BoardNim> h){
        this.name = name;
        this.algo = algo;
        this.role = role;
        this.heuristic = h;
    }

    @Override
    public MoveNim chooseMove(BoardNim board) {
        MoveNim move = algo.bestMove(board, role, heuristic);
        return move;
    }

    public String getName() {
        return name;
    }

    public RoleNim getRole() {
        return role;
    }

    @Override
    public String toString() {
        return name;
    }
}
