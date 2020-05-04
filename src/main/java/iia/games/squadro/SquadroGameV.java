package iia.games.squadro;

import iia.games.base.IGame;

public class SquadroGameV extends ASquadroGame {

    public SquadroGameV () {super();}

    public SquadroGameV (Character[][] board) {
        super();
        super.board = board;
    }

    @Override
    public IGame play(String move, String role) {
        ASquadroGame nextState;
        nextState = new SquadroGameH(this.board);
        nextState.updateBoard(move, role);
        return nextState;
    }

    @Override
    public int getValue(String role) {
        // TODO heuristic for Vertical player
        /* Avant meme de choisir une fonction heuristique particuliere on applique ca a chaque fois */
        String vainqueur = whoWon();
        if (vainqueur != "PESONNE"){
            switch (vainqueur){
                case "HORISONTAL":
                    return super.MIN_VALUE;
                case "VERTICAL":
                    return super.MAX_VALUE;
                default:
            }
        }
        /* On peut ici choisir une nouvelle fonction heuristique qu'on a choisi */
        int heuristicValue = basicHeuristic(role);
        return heuristicValue;
    }

    /**
     * Une premiere fonction basique de calcul d'heuristique.
     * n terme, n correspondant au nombre de pieces restantes
     * Pour chaque n :
     * On devalorise le fait d'avoir une piece directement vulnerable.
     * On valorise n'importe quel coup de la meme maniere.
     * On valorise le fait d'avoir gagne un point.
     * @param role : Indique le tour du joueur sur le plateau. Le calcul est different si c'est AMI ou ENNEMI qui doit jouer
     * @return int value : La valeur heuristique calculee
     */
    private int basicHeuristic(String role){
        int value = 0;
        int[][] positions;
        int in_game;
        int nb_pieces_menacees;
        /* On fixe ici les constantes associees aux differents parametres */
        int points = 10;        // La valeur associee au nombre de points actuel marque par le joueur.
        int risque = -50;       // Ce qu'on va ajouter par piece vulnerable
        int mouvement = 5;      // Point marque par mouvement possible.

        /* On compte le nombre de pieces encore en jeu */
        in_game = nombrePieces(role);
        /* On cherche les positions de nos pieces */
        positions = piecesPositions(role);
        /* On cherche s'il y a des pieces menacantes pour le joueur "role"  */
        nb_pieces_menacees = nombresMenacees(role);

        /* On effectue le calcul de notre heuristique selon les valeurs */
        value += (points * (5 - in_game)) + (nb_pieces_menacees * risque)  + (in_game * mouvement);

        return value;
    }
}
