package iia.simpleGame.squadro;

import iia.simpleGame.base.AGame;
import iia.simpleGame.base.IGame;

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

        int[] ret =  {n, dictLetterToInt.get(chars[0]), m, dictLetterToInt.get(chars[3])};
        return ret;
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
            System.out.println("DIFF" + diff);
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
    public IGame play(String move, String role) {
        ASquadroGame nextState = null;
        switch (role) {
            case "VERTICAL":
                nextState = new SquadroGameV();
                break;
            case "HORISONTAL":
                nextState = new SquadroGameH();
                break;
            default:
        }
        nextState.updateBoard(move, role);
        return nextState;
    }

    @Override
    public ArrayList<String> possibleMoves(String role) {
        // init la liste a renvoyer
        ArrayList<String> coups = new ArrayList<String>();
        // Joueur : "horizontal"
        if (role.equals("HORISONTAL")) {
            for (int ligne = 1; ligne <= 5; ligne++) {
                String coup = "";
                // si le pion n'est pas en position finale
                if (this.board[ligne][0] != '<') {
                    // on trouve la position du pion
                    int posColonne = 0;
                    for (int colonne = 0; colonne < 7; colonne++) {
                        if (this.board[ligne][colonne] != ' ') {
                            posColonne = colonne;
                            // on enregistre la position de depart
                            coup += posToString(ligne, posColonne) + "-";
                            break;
                        }
                    }
                    // on joue le coup pour obtenir la position d'arrivée
                    // on regarde l'orientation de la piece
                    if (this.board[ligne][posColonne] == '>') {
                        // on recupere la vitesse du pion
                        int moves = this.speed[0][ligne - 1];
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
                                coup += posToString(ligne, posColonne);
                            }
                            // sinon la case est vide et on passe juste a la suivante
                        }
                        coups.add(coup);
                    } else if (this.board[ligne][posColonne] == '<') {
                        // on recupere la vitesse du pion
                        int moves = this.speed[1][ligne - 1];
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
                                coup += posToString(ligne, posColonne);
                            }
                            // sinon la case est vide et on passe juste a la suivante
                        }
                        coups.add(coup);
                    }
                }
            }
            // Joueur : "vertical"
        } else {
            for (int colonne = 1; colonne <= 5; colonne++) {
                String coup = "";
                // si le pion n'est pas en position finale
                if (this.board[6][colonne] != '<') {
                    // on trouve la position du pion
                    int posLigne = 0;
                    for (int ligne = 0; ligne < 7; ligne++) {
                        if (this.board[ligne][colonne] != ' ') {
                            posLigne = ligne;
                            // on enregistre la position de depart
                            coup += posToString(posLigne, colonne) + "-";
                            break;
                        }
                    }
                    // on joue le coup pour obtenir la position d'arrivée
                    // on regarde l'orientation de la piece
                    if (this.board[posLigne][colonne] == '^') {
                        // on recupere la vitesse du pion
                        int moves = this.speed[0][colonne - 1];
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
                                coup += posToString(posLigne, colonne);
                            }
                            // sinon la case est vide et on passe juste a la suivante
                        }
                        coups.add(coup);
                    } else if (this.board[posLigne][colonne] == 'v') {
                        // on recupere la vitesse du pion
                        int moves = this.speed[1][colonne - 1];
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
                                coup += posToString(posLigne, colonne);
                            }
                            // sinon la case est vide et on passe juste a la suivante
                        }
                        coups.add(coup);
                    }
                }
            }
        }
        return coups;
    }

    @Override
    public boolean isValidMove(String move, String role) {
        ArrayList<String> temp = this.possibleMoves(role);
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).equals(move)) {
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
}
