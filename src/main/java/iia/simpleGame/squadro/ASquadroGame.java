package iia.simpleGame.squadro;

import iia.simpleGame.base.AGame;
import iia.simpleGame.base.IGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public abstract class ASquadroGame extends AGame {

    private Integer score_horizontal;
    private Integer score_vertical;
    private Players current_player;

    private enum Players {
        horizontal,
        vertical
    }

    private static final int[][] speed = {
            {1, 3, 2, 3, 1},
            {3, 1, 2, 1, 3}
    };

    private HashMap<Integer, Character> dictIntToLetter;
    private HashMap<Character, Integer> dictLetterToInt;
    private Character[][] board;

    public ASquadroGame(){

        // cree un tableau a 2d 7x7
        board = new Character[7][7];
        for (Character[] x : board) {
            Arrays.fill(x, ' ');
        }
        this.score_vertical = 0;
        this.score_horizontal = 0;
        this.current_player = Players.horizontal;
        for (int i = 1; i < 6; i++) {
            board[6][i] = '^';
            board[i][0] = '>';
        }
        initMaps();
    }

    private void initMaps() {
        Character[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
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
        int[] ret =  {dictLetterToInt.get(chars[0]), chars[1] + 1};
        return ret;
    }


    @Override
    public IGame play(String move, String role) {
        // TODO !
        return null;
    }

    @Override
    public ArrayList<String> possibleMoves(String role) {
        // init la liste a renvoyer
        ArrayList<String> coups = new ArrayList<String>();
        // Joueur : "horizontal"
        if (role.equals("horizontal")) {
            for (int ligne = 1; ligne <= 5; ligne++) {
                // si le pion n'est pas en position finale
                if (this.board[ligne][0] != '<') {
                    String coup = new String();
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
        } else if (role.equals("vertical")) {
            for (int colonne = 1; colonne <= 5; colonne++) {git
                // si le pion n'est pas en position finale
                if (this.board[6][colonne] != '<') {
                    String coup = new String();
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
        // TODO !
        return false;
    }

    @Override
    public boolean isGameOver() {
        // TODO !
        return false;
    }
}
