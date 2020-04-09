package iia.simpleGame.base;

import iia.simpleGame.algo.IAlgo;

public class Player {

    String name;
    String role;
    IAlgo algo;

    public Player(String name, String role, IAlgo algo){
        this.name = name;
        this.role = role;
        this.algo = algo;
    }

    public String chooseMove(IGame game){
        return algo.bestMove(game, role);
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return name
                +"{" + algo +
                ", " + role +
                '}';
    }
}
