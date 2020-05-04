package iia.games.base;

import java.util.ArrayList;

public abstract class AGame implements IGame{

    @Override
    public ArrayList<IGame> successors(String role) {
        ArrayList<IGame> succ = new ArrayList<IGame>();
        ArrayList<String> list = this.possibleMoves(role);
        for (String s : list) {System.out.print(s + " ");}
        System.out.println();
        for (String m: list) {
            succ.add(this.play(m, role));
        }
        return succ;
    }

}
