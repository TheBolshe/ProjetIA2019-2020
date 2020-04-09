package iia.simpleGame.nim;

import iia.simpleGame.base.IGame;

public class NimGameFirst extends ANimGame{

    public NimGameFirst(){
        super();
    }

    public NimGameFirst(int n){
        super(n);
    }

    @Override
    public int getValue(String role) {
        int n = getN_matches();
        if (role.equals(ROLE_FIRST)) {
            if (n == 0)
                return IGame.MAX_VALUE;
            else
                return START_N_MATCHES - n;
        }
        else {
            if (n == 0)
                return IGame.MIN_VALUE;
            else
                return START_N_MATCHES - n;
        }
    }

    @Override
    public IGame play(String move, String role) {
        int n = Integer.parseInt(move);
        final int new_n =  Math.max(0, n_matches - n);
        return new NimGameFirst(new_n);
    }

}
