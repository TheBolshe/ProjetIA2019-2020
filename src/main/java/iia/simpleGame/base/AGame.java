package iia.simpleGame.base;

import java.util.ArrayList;

public abstract class AGame implements IGame{

    @Override
    public ArrayList<IGame> successors(String role) {
        ArrayList<IGame> succ = new ArrayList<IGame>();
        for (String m: this.possibleMoves(role)) {
            succ.add(this.play(m, role));
        }
        return succ;
    }

}
