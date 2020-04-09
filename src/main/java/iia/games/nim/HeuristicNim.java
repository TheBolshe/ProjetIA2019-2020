package iia.games.nim;

import iia.games.base.IHeuristic;

public class HeuristicNim {

    public static final IHeuristic<RoleNim, BoardNim> hZero = (boardNim, r) -> 0;

    public static final IHeuristic<RoleNim, BoardNim> hRemaining = (boardNim, r) -> boardNim.getN_matches();

    public static final IHeuristic<RoleNim, BoardNim> hTDFIRST = (boardNim, r) -> {
        int n = boardNim.getN_matches();
        if (r == RoleNim.SECOND) {
            if (n == 0)
                return IHeuristic.MIN_VALUE;
            else
                return BoardNim.START_N_MATCHES - n;
        }
        else {
            if (n == 0)
                return IHeuristic.MAX_VALUE;
            else
                return BoardNim.START_N_MATCHES - n;
        }
    };

    public static final IHeuristic<RoleNim, BoardNim> hTDSECOND = (boardNim, r) -> {
        int n = boardNim.getN_matches();
        if (r == RoleNim.FIRST) {
            if (n == 0)
                return IHeuristic.MIN_VALUE;
            else
                return BoardNim.START_N_MATCHES - n;
        }
        else {
            if (n == 0)
                return IHeuristic.MAX_VALUE;
            else
                return BoardNim.START_N_MATCHES - n;
        }
    };

}
