package iia.games.squadro;

import java.util.ArrayList;

public class PlaySquadro {
    public static void main(String[] args) {
        System.out.println("Hello world !");

        /* On teste le calcul basique de l'heuristique sur le plateau de depart */
        SquadroGameV h1 = new SquadroGameV();
        h1.printBoard();
        int truc = h1.getValue("VERTICAL");                 // Calcul d'heuristique associee au plateau
        ArrayList possibles = h1.possibleMoves("VERTICAL");
        String play = String.valueOf(possibles.get(0));

        /* On reteste avec un premier coup */
        SquadroGameV new_board = (SquadroGameV) h1.play(play, "VERTICAL");
        new_board.printBoard();
        int new_heuristic = new_board.getValue("HORISONTAL"); // Calcul d'heuristique du nouveau plateau apres le coup
        System.out.println(new_heuristic);

    }
}
