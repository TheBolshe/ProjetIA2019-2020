package iia.games.nim;

import iia.games.algo.Minimax;


public class PlayNim {

    public static PlayerNim p1 = new PlayerNim("P1_minimax",
            new Minimax<MoveNim, RoleNim, BoardNim>(RoleNim.FIRST, RoleNim.SECOND, 5),
            RoleNim.FIRST, HeuristicNim.hTDFIRST);

    public static PlayerNim p2 = new PlayerNim("P2_minimax",
            new Minimax<MoveNim, RoleNim, BoardNim>(RoleNim.SECOND, RoleNim.FIRST, 5),
            RoleNim.SECOND, HeuristicNim.hTDSECOND);

    public static void main(String[] args) {
        final int start_n_matches = BoardNim.START_N_MATCHES;
        BoardNim board = new BoardNim(start_n_matches);

        PlayerNim p_current = null;
        while(!board.isGameOver()){
            p_current = otherPlayer(p_current);
            System.out.println(board);
            MoveNim move = p_current.chooseMove(board);
            RoleNim r_current = p_current.getRole();
            System.out.println("-> "  + p_current + " plays " + move);

            board = board.play(move, r_current);
        }
        System.out.println(p_current + " Loses !");
        p_current = otherPlayer(p_current);
        System.out.println(p_current + " Wins !");

    }

    public static PlayerNim otherPlayer(PlayerNim p){
        if (p == PlayNim.p1)
            return PlayNim.p2;
        else
            return PlayNim.p1;
    }

}
