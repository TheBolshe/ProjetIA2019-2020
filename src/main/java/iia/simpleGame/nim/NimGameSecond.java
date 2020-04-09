package iia.simpleGame.nim;

import iia.simpleGame.base.IGame;

public class NimGameSecond extends ANimGame{

    public NimGameSecond(){
        super();
    }

    public NimGameSecond(int n) {
        super(n);
    }

    @Override
    public int getValue(String role) {
        int n = getN_matches();
        if (role.equals(ROLE_SECOND)) {
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
        return new NimGameSecond(new_n);
    }

}
