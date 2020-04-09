package iia.simpleGame.nim;

import iia.simpleGame.algo.Minimax;
import iia.simpleGame.base.IGame;
import iia.simpleGame.base.Player;

public class PlayNim {

    public static void main(String[] args) {
        Player p1 = new Player("George", ANimGame.ROLE_FIRST,
                new Minimax(ANimGame.ROLE_FIRST, ANimGame.ROLE_SECOND));

        Player p2 = new Player("Gertrude", ANimGame.ROLE_SECOND,
                new Minimax(ANimGame.ROLE_SECOND, ANimGame.ROLE_FIRST));

        IGame game_1 = new NimGameFirst();
        IGame game_2 = new NimGameSecond();
        Player p_current = null;
        IGame currentGame = null;
        while(!game_1.isGameOver()){
            // swap
            p_current = (p_current == p1)? p2 : p1  ;
            currentGame = (currentGame == game_1)? game_2 : game_1  ;

            System.out.println(currentGame);
            System.out.println(p_current);
            String move = p_current.chooseMove(currentGame);
            String r_current = p_current.getRole();
            System.out.println("-> "  + p_current + " plays " + move);

            game_1 = game_1.play(move, r_current);
            game_2 = game_2.play(move, r_current);
        }
        System.out.println(p_current + " Loses !");
        p_current = (p_current == p1)? p2 : p1  ;
        System.out.println(p_current + " Wins !");
    }
}
