package iia.games.squadro;

import iia.games.base.APlayer;
import iia.games.algo.IAlgo;
import iia.games.base.IHeuristic;

public class PlayerSquadro extends APlayer<MoveSquadro, RoleSquadro, BoardSquadro> {

    public PlayerSquadro(String name, IAlgo<MoveSquadro, RoleSquadro, BoardSquadro> algo, RoleSquadro role, IHeuristic<RoleSquadro, BoardSquadro> h) {
        super(name, algo, role, h);
    }
}
