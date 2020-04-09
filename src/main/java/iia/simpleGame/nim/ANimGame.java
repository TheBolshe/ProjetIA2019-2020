package iia.simpleGame.nim;

import iia.simpleGame.base.AGame;

import java.util.ArrayList;

public abstract class ANimGame extends AGame {

    protected final int n_matches;
    public final static int START_N_MATCHES = 7;
    public final static int MAX_N = 3;
    public final static int MIN_N = 1;

    public static final String ROLE_FIRST = "FIRST";
    public static final String ROLE_SECOND = "SECOND";

    public ANimGame(){
        n_matches = START_N_MATCHES;
    }

    public ANimGame(int n){
        n_matches = n;
    }

    @Override
    public ArrayList<String> possibleMoves(String role) {
        ArrayList<String> all_moves = new ArrayList<>();
        for (int i=MIN_N; i <= MAX_N && i <= n_matches; i++)
            all_moves.add(Integer.toString(i));
        return all_moves;
    }

    @Override
    public boolean isValidMove(String move, String role) {
        int n = Integer.parseInt(move);
        return n <= MAX_N && n <= n_matches;
    }

    @Override
    public boolean isGameOver() {
        return n_matches == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BoardNim{ ");
        for (int i=0; i<n_matches; i++)
            sb.append('|');
        sb.append(" }");
        return sb.toString();
    }

    public int getN_matches() {
        return n_matches;
    }

}
