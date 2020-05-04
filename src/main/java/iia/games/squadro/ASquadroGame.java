package iia.games.squadro;

import iia.games.base.AGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public abstract class ASquadroGame extends AGame {

    /**
     * speed[0] pour HORIZONTAL au aller et VERTICAL au retour
     * speed[1] pour VERTICAL a l'aller et HORIZONTAL au retour
     */
    private static final int[][] speed = {
            {1, 3, 2, 3, 1},
            {3, 1, 2, 1, 3}
    };

    private HashMap<Integer, Character> dictIntToLetter;
    private HashMap<Character, Integer> dictLetterToInt;
    protected Character[][] board;

    public ASquadroGame(){

        // cree un tableau a 2d 7x7
        board = new Character[7][7];
        for (Character[] x : board) {
            Arrays.fill(x, ' ');
        }
        for (int i = 1; i < 6; i++) {
            board[6][i] = '^';
            board[i][0] = '>';
        }
        initMaps();
    }

    protected int[][] getSpeed(){
        return speed;
    }

    /**
     * Methode pour afficher le plateau. On en a souvent besoin en cours de developpement.
     */
    protected void printBoard(){
        for (Character[] ligne : this.board) {
            for (Character el: ligne) {
                System.out.print(el);
            }
            System.out.println();
        }
    }

    private void initMaps() {
        Character[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        this.dictIntToLetter = new HashMap<>(7);
        this.dictLetterToInt = new HashMap<>(7);
        for (Integer i = 0; i < 7; i++) {
            dictIntToLetter.put(i, letters[i]);
            dictLetterToInt.put(letters[i], i);
        }
    }

    private String posToString(int ligne, int colonne) {
        return dictIntToLetter.get(colonne).toString() + (ligne + 1);
    }

    private int[] stringToPos(String string) {
        char[] chars = string.toCharArray();
        int n = Character.getNumericValue(chars[1])-1;
        int m = Character.getNumericValue(chars[4])-1;

        return new int[]{n, dictLetterToInt.get(chars[0]), m, dictLetterToInt.get(chars[3])};
    }

    private void toStart (int ligne, int colonne){
        switch (this.board[ligne][colonne]) {
            case '<':
                this.board[ligne][6] = '<';
                break;
            case '>':
                this.board[ligne][0] = '>';
                break;
            case '^':
                this.board[6][colonne] = '^';
                break;
            case 'v':
                this.board[0][colonne] = 'v';
                break;
            default:
        }
        this.board[ligne][colonne] = ' ';
    }

    public void updateBoard (String move, String player) {
        int[] positions = stringToPos(move);
        int[] depart = {positions[0], positions[1]};
        int[] arrivee = {positions[2], positions[3]};
        Character piece = this.board[depart[0]][depart[1]];
        this.board[depart[0]][depart[1]] = ' ';
        if (player == "HORISONTAL") {
            int diff = arrivee[1] - depart[1];
            int moves = Math.abs(diff);
            int pas = diff / moves;
            for (int colonne = depart[1]; colonne != arrivee[1]; colonne += pas) {
                if (this.board[depart[0]][colonne] != ' ') {
                    toStart(depart[0], colonne);
                }
            }
            if (arrivee[1] == 6) {
                this.board[arrivee[0]][arrivee[1]] = '<';
            } else {
                this.board[arrivee[0]][arrivee[1]] = piece;
            }
        } else {
            int diff = arrivee[0] - depart[0];
            int moves = Math.abs(diff);
            int pas = diff / moves;
            for (int ligne = depart[0]; ligne != arrivee[0]; ligne += pas) {
                if (this.board[ligne][depart[1]] != ' ') {
                    toStart(ligne, depart[1]);
                }
            }
            if (arrivee[0] == 0) {
                this.board[arrivee[0]][arrivee[1]] = 'v';
            } else {
                this.board[arrivee[0]][arrivee[1]] = piece;
            }
        }
    }



    @Override
    public ArrayList<String> possibleMoves(String role) {
        this.printBoard();
        System.out.println(role);
        // init la liste a renvoyer
        System.out.println("Etape 1");
        ArrayList<String> coups = new ArrayList<>();
        System.out.println("Etape 1.1");
        // Joueur : "horizontal"
        if (role == "HORISONTAL") {
            System.out.println("Etape 2");
            for (int ligne = 1; ligne <= 5; ligne++) {
                System.out.println("Etape 3 : ligne " + ligne);
                StringBuilder coup = new StringBuilder();
                // si le pion n'est pas en position finale
                if (this.board[ligne][0] != '<') {
                    // on trouve la position du pion
                    int posColonne = 0;
                    for (int colonne = 0; colonne < 7; colonne++) {
                        System.out.print(colonne + " ");
                        if (this.board[ligne][colonne] != ' ') {
                            posColonne = colonne;
                            System.out.println(posColonne);
                            // on enregistre la position de depart
                            coup.append(posToString(ligne, posColonne)).append("-");
                            break;
                        }
                    }
                    // on joue le coup pour obtenir la position d'arrivée
                    // on regarde l'orientation de la piece
                    if (this.board[ligne][posColonne] == '>') {
                        // on recupere la vitesse du pion
                        int moves = speed[0][ligne - 1];
                        // on avance case par case
                        while (moves > 0) {
                            // on va a droite
                            posColonne++;
                            moves--;
                            if (this.board[ligne][posColonne] != ' ') {
                                // si la case n'est pas vide on passe a la case suivante et il ne reste plus qu'une case de deplacement
                                moves = 1;
                            } else if (moves == 0 || posColonne >= 6) {
                                // si on a utilisé toute la vitesse ou si on est au bord on renvoie la position actuelle en cible
                                coup.append(posToString(ligne, posColonne));
                            }
                            // sinon la case est vide et on passe juste a la suivante
                        }
                        coups.add(coup.toString());
                    } else if (this.board[ligne][posColonne] == '<') {
                        // on recupere la vitesse du pion
                        int moves = speed[1][ligne - 1];
                        // on avance case par case
                        while (moves > 0) {
                            // on va a gauche
                            posColonne--;
                            moves--;
                            if (this.board[ligne][posColonne] != ' ') {
                                // si la case n'est pas vide on passe a la case suivante et il ne reste plus qu'une case de deplacement
                                moves = 1;
                            } else if (moves == 0 || posColonne <= 0) {
                                // si on a utilisé toute la vitesse ou si on est au bord on renvoie la position actuelle en cible
                                coup.append(posToString(ligne, posColonne));
                            }
                            // sinon la case est vide et on passe juste a la suivante
                        }
                        coups.add(coup.toString());
                    }
                }
            }
            // Joueur : "vertical"
        } else {
            System.out.println("Etape 2");
            for (int colonne = 1; colonne <= 5; colonne++) {
                System.out.println("Etape 3 : colonne " + colonne);
                StringBuilder coup = new StringBuilder();
                // si le pion n'est pas en position finale
                if (this.board[6][colonne] != '<') {
                    // on trouve la position du pion
                    int posLigne = 0;
                    for (int ligne = 0; ligne < 7; ligne++) {
                        if (this.board[ligne][colonne] != ' ') {
                            posLigne = ligne;
                            // on enregistre la position de depart
                            coup.append(posToString(posLigne, colonne)).append("-");
                            break;
                        }
                    }
                    // on joue le coup pour obtenir la position d'arrivée
                    // on regarde l'orientation de la piece
                    if (this.board[posLigne][colonne] == '^') {
                        // on recupere la vitesse du pion
                        int moves = speed[1][colonne - 1];
                        // on avance case par case
                        while (moves > 0) {
                            // on monte
                            posLigne--;
                            moves--;
                            if (this.board[posLigne][colonne] != ' ') {
                                // si la case n'est pas vide on passe a la case suivante et il ne reste plus qu'une case de deplacement
                                moves = 1;
                            } else if (moves == 0 || posLigne <= 0) {
                                // si on a utilisé toute la vitesse ou si on est au bord on renvoie la position actuelle en cible
                                coup.append(posToString(posLigne, colonne));
                            }
                            // sinon la case est vide et on passe juste a la suivante
                        }
                        coups.add(coup.toString());
                    } else if (this.board[posLigne][colonne] == 'v') {
                        // on recupere la vitesse du pion
                        int moves = speed[0][colonne - 1];
                        // on avance case par case
                        while (moves > 0) {
                            // on descend
                            posLigne++;
                            moves--;
                            if (this.board[posLigne][colonne] != ' ') {
                                // si la case n'est pas vide on passe a la case suivante et il ne reste plus qu'une case de deplacement
                                moves = 1;
                            } else if (moves == 0 || posLigne >= 6) {
                                // si on a utilisé toute la vitesse ou si on est au bord on renvoie la position actuelle en cible
                                coup.append(posToString(posLigne, colonne));
                            }
                            // sinon la case est vide et on passe juste a la suivante
                        }
                        coups.add(coup.toString());
                    }
                }
            }
        }
        return coups;
    }

    @Override
    public boolean isValidMove(String move, String role) {
        ArrayList<String> temp = this.possibleMoves(role);
        for (String item : temp) {
            if (item.equals(move)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isGameOver() {
        int countV = 0;
        int countH = 0;
        for (int i = 1; i < 6; i++) {
            if (this.board[6][i] == 'v') countV++;
            if (this.board[i][0] == '<') countH++;
        }
        return countH >=4 || countV >= 4;
    }

    /**
     * Fonction qui permet de savoir qui a gagne.
     * @return "VERTICAL" (VERTICAL gagne), "HORISONTAL" (HORIZONTAL gagne)
     */
    protected String whoWon() {
        int countV = 0;
        int countH = 0;
        for (int i = 1; i < 6; i++) {
            if (this.board[6][i] == 'v') countV++;
            if (this.board[i][0] == '<') countH++;
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

    /**
     * Cette fonction calcule le nombre de pieces susceptibles d'etre directement renvoyee par une piece adverse
     * @param role Change les pieces cherchees.
     * @return nb_menaces Un nombre de 0 a 5.
     */
    protected int nombresMenacees(String role){
        int nb_menaces = 0;
        int[][] speeds = getSpeed();
        int[][] positions_tableau = piecesPositions(role);

        switch (role){
            case "HORISONTAL":
                /* Dans cette  boucle on parcours les pieces*/
                for (int[] ligne : positions_tableau) {
                    int a = ligne[0];
                    int b = ligne[1];
                    for (int dessous = a; dessous < 7; dessous++){
                        /* L'ordre des valeurs dans le if compte */
                        if ((!(b==0) || !(b==6)) && this.board[dessous][b] =='^' && ((dessous - a) <= speeds[1][b-1])){
                            nb_menaces++;
                        }
                    }
                    for (int dessus = a; dessus >= 0; dessus--){
                        if ((!(b==0) || !(b==6)) && this.board[dessus][b] =='v' && ((a - dessus) <= speeds[0][b-1])){
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
                        if ((!(a==0) || !(a==6)) && this.board[a][gauche] =='>' && ((b- gauche) <= speeds[0][a-1])){
                            nb_menaces++;
                        }
                    }
                    for (int droite = b; droite < 7; droite++){
                        if ((!(a==0) || !(a==6)) && this.board[a][droite] =='<' && ((droite - b) <= speeds[1][a-1])){
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
     * Fonction de recherche des differentes pieces du joueur "role"
     * @param role Le Joueur dont on va chercher les pieces
     * @return positions int[][] Un tableau a dse coordonnees des pieces.
     */
    protected int[][] piecesPositions(String role){
        /* La taille du tableau est fonction ds pieces encore en jeu */
        ArrayList<int[]> positions = new ArrayList();
        switch (role){
            case "HORISONTAL":
                for (int ligne = 0; ligne < this.board.length; ligne ++){
                    for (int colonne = 0; colonne < this.board[ligne].length; colonne ++){
                        // On verifie les caractere mais si le caractere '<' est a la colonne 0 alors la piece en hors jeu et ne compte plus
                        if ((this.board[ligne][colonne] == '<' && !(colonne == 0)) || this.board[ligne][colonne] == '>'){
                            int[] pos = {ligne, colonne};
                            positions.add(pos);
                        }
                    }
                }
                break;
            case "VERTICAL":
                for (int ligne = 0; ligne < this.board.length; ligne ++){
                    for (int colonne = 0; colonne < this.board[ligne].length; colonne ++){
                        // On verifie les caractere mais si le caractere '<' est a la colonne 0 alors la piece en hors jeu et ne compte plus
                        if ((this.board[ligne][colonne] == 'v' && !(ligne == this.board.length - 1)) || this.board[ligne][colonne] == '^'){
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
    protected int nombrePieces(String role){
        int nb = 0;
        switch (role){
            case "VERTICAL":
                for (int colonne = 1; colonne < 6; colonne++) {
                    if (this.board[6][colonne] != 'v') {nb++;}
                }
                break;
            case "HORISONTAL":
                for (int ligne = 1; ligne < 6; ligne++) {
                    if (this.board[ligne][0] != '<') {nb++;}
                }
                break;
            default:
        }
        return nb;
    }

}
