package iia.simpleGame.squadro;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class SquadroGameV extends ASquadroGame {

    public SquadroGameV () {
        super();
        //this.board = board;
    }

    @Override
    public int getValue(String role) {
        // TODO heuristic for Vertical player
        /* Avant meme de choisir une fonction heuristique particuliere on applique ca a chaque fois */
        String vainqueur = whoWon();
        if (!vainqueur.equals("PERSONNE")){
            System.out.print("PROUT");
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

    /**
     * Fonction de recherche des differentes pieces du joueur "role"
     * @param role Le Joueur dont on va chercher les pieces
     * @return positions int[][] Un tableau a dse coordonnees des pieces.
     */
    private int[][] piecesPositions(String role){
        /* La taille du tableau est fonction ds pieces encore en jeu */
        ArrayList<int[]> positions = new ArrayList();
        switch (role){
            case "HORISONTAL":
                for (int ligne = 0; ligne < super.board.length; ligne ++){
                    for (int colonne = 0; colonne < super.board[ligne].length; colonne ++){
                        // On verifie les caractere mais si le caractere '<' est a la colonne 0 alors la piece en hors jeu et ne compte plus
                        if ((super.board[ligne][colonne].equals('<') && !(colonne == 0)) || super.board[ligne][colonne].equals('>')){
                            int[] pos = {ligne, colonne};
                            positions.add(pos);
                        }
                    }
                }
                break;
            case "VERTICAL":
                for (int ligne = 0; ligne < super.board.length; ligne ++){
                    for (int colonne = 0; colonne < super.board[ligne].length; colonne ++){
                        // On verifie les caractere mais si le caractere '<' est a la colonne 0 alors la piece en hors jeu et ne compte plus
                        if ((super.board[ligne][colonne].equals('v') && !(ligne == super.board.length - 1)) || super.board[ligne][colonne].equals('^')){
                            int[] pos = {ligne, colonne};
                            positions.add(pos);
                        }
                    }
                }
                break;
            default:
        }
        return positions.toArray(new int[0][]);
    }

    /**
     * Fonction dans laquelle on compte le nombre de pieces encore dans la partie.
     * @param role On ne cherche pas les meme pieces selon le role
     * @return nb un nombre de pieces
     */
    private int nombrePieces(String role){
        int nb = 0;
        switch (role){
            case "HORISONTAL":
                /* On compte tout les caracteres '>' ou '<' */
                for (Character[] lignes : super.board) {
                    for (Character ch : lignes) {
                        if (ch.equals('>') || ch.equals('<')){
                            nb++;
                        }
                    }
                }
                /* On decompte les pieces deja arrivees */
                for (Character[] lignes: super.board) {
                    if (lignes[0].equals('<')){
                        nb--;
                    }
                }
                break;
            case "VERTICAL":
                /* On compte tout les caracteres 'v' ou '^' */
                for (Character[] lignes : super.board) {
                    for (Character ch : lignes) {
                        if (ch.equals('v') || ch.equals('^')){
                            nb++;
                        }
                    }
                }
                /* On decompts les 'v' sur la ligne de depart-arrivee */
                for (Character c: super.board[6]) {
                    if (c.equals('v')){
                        nb--;
                    }
                }
                break;
            default:
        }
        return nb;
    }

    /**
     * Cette fonction calcule le nombre de pieces susceptibles d'etre directement renvoyee par une piece adverse
     * @param role Change les pieces cherchees.
     * @return nb_menaces Un nombre de 0 a 5.
     */
    private int nombresMenacees(String role){
        int nb_menaces = 0;
        int[][] speeds = super.getSpeed();
        int[][] positions_tableau = this.piecesPositions(role);

        switch (role){
            case "HORISONTAL":
                /* Dans cette  boucle on parcours les pieces*/
                for (int[] ligne : positions_tableau) {
                    int a = ligne[0];
                    int b = ligne[1];
                    for (int dessous = a; dessous < 7; dessous++){
                        /* L'ordre des valeurs dans le if compte */
                        if ((!(b==0) || !(b==6)) && super.board[dessous][b].equals('^') && ((dessous - a) <= speeds[1][b-1])){
                            nb_menaces++;
                        }
                    }
                    for (int dessus = a; dessus >= 0; dessus--){
                        if ((!(b==0) || !(b==6)) && super.board[dessus][b].equals('v') && ((a - dessus) <= speeds[0][b-1])){
                            nb_menaces++;
                        }
                    }
                }
                break;
            case  "VERTICAL":
                for (int[] ligne : positions_tableau) {
                    int a = ligne[0];
                    int b = ligne[1];
                    for (int gauche = b; gauche >= 0 ; gauche--){
                        /* L'ordre des valeurs dans le if compte | Les lignes du plateau concernees | La piece cherchee selon le sens | la distance entre la piece adverse et la notre */
                        if ((!(a==0) || !(a==6)) && super.board[a][gauche].equals('>') && ((b- gauche) <= speeds[0][a-1])){
                            nb_menaces++;
                        }
                    }
                    for (int droite = b; droite < 7; droite++){
                        if ((!(a==0) || !(a==6)) && super.board[a][droite].equals('<') && ((droite - b) <= speeds[1][a-1])){
                            nb_menaces++;
                        }
                    }
                }
                break;
            default:
        }
        return nb_menaces;
    }

    /**
     * Fonction qui permet de savoir qui a gagne.
     * @return "VERTICAL" (VERTICAL gagne), "HORISONTAL" (HORIZONTAL gagne)
     */
    private String whoWon() {
        int countV = 0;
        int countH = 0;
        for (int i = 1; i < 6; i++) {
            if (super.board[6][i] == 'v') countV++;
            if (super.board[i][0] == '<') countH++;
        }
        //return countH >=4 || countV >= 4;
        if (countV >= 4){
            return "VERTICAL";
        }
        if (countH >= 4){
            return "HORISONTAL";
        }
        return "PERSONNE";

    }


}
