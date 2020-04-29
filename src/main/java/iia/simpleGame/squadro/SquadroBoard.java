package iia.simpleGame.squadro;

import iia.simpleGame.base.IPartie2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SquadroBoard implements IPartie2 {

    private Integer score_horizontal;
    private Integer score_vertical;
    private players current_player;

    private enum players {
        horizontal,
        vertical
    }

    private HashMap<Integer, Character> dictIntToLetter;
    private HashMap<Character, Integer> dictLetterToInt;
    private Character[][] board;

    private SquadroBoard() {
        // cree un tableau a 2d 7x7
        board = new Character[7][7];
        for (Character[] x : board) {
            Arrays.fill(x, ' ');
        }
        this.score_vertical = 0;
        this.score_horizontal = 0;
        this.current_player = players.horizontal;
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

    public String toString(String player) {
        StringBuilder temp = new StringBuilder("");
        temp.append("%  ABCDEFG");
        temp.append("\n");


        for (int x = 0; x < 7; x++) {
            temp.append(0).append(x + 1).append(" ");
            for (int i = 0; i < 7; i++) {
                char a = this.board.get(x).get(i);
                temp.append(a);
            }
            temp.append(" ").append(0).append(x + 1);
            temp.append("\n");
        }
        temp.append("%  ABCDEFG");
        temp.append("\n");
        temp.append(this.current_player.toString());
        return temp.toString();
    }

    public void setFromFile(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        // Ces deux variables serviront a compter le nombre de pieces de chaque joueur presentes sur le terrain.
        // Les points de chaque joueur seront ensuite 5 - cette variable
        Integer vertical_points = 0;
        Integer horizontal_points = 0;
        // On affecte this.board
        for (int i = 1; i < 8; i++) {
            for (int j = 3; j < 10; j++) {
                Character courant = lines.get(i).charAt(j);
                this.board.get(i - 1).set(j - 3, courant);
                if (courant.equals('^') || courant.equals('v')) {
                    vertical_points++;
                } else if (courant.equals('>') || courant.equals('<')) {
                    horizontal_points++;
                }
            }
        }
        // On affecte les autres variables d'instances
        this.score_horizontal = 5 - horizontal_points;
        this.score_vertical = 5 - vertical_points;
        // On verifie d'abord pour etre sur qu'il n'y ai pas de valeur autre que "horizontal" ou "vertical"
        if (players.horizontal.toString().equals(lines.get(lines.size() - 1))) {
            this.current_player = players.horizontal;
        } else if (players.vertical.toString().equals(lines.get(lines.size() - 1))) {
            this.current_player = players.vertical;
        }

    }

    public void saveToFile(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        String str = this.toString(this.current_player.toString());
        writer.write(str);
        writer.close();
    }

    public boolean isValidMove(String move, String player) {

        String[] temp = this.possibleMoves(player);
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].equals(move)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> possibleMoves(String player) {
        // init la liste a renvoyer
        ArrayList<String> coups = new ArrayList<String>();
        // Joueur : "horizontal"
        if (player.equals("horizontal")) {
            for (int ligne = 1; ligne <= 5; ligne++) {
                // si le pion n'est pas en position finale
                if (this.board[ligne][0] != '<') {
                    String coup = new StringBuilder(5);
                    // on enregistre la position du pion
                    int posColonne = 0;
                    for (int colonne = 0; colonne < 7; colonne++) {
                        if (this.board[ligne][colonne] != ' ') {
                            posColonne = colonne;
                            break;
                        }
                    }
                    // on enregistre la position de depart
                    // on joue le coup pour obtenir la position d'arrivée
                }
            }
        }


        // On lui cree un tableau correspondant au nombre de pieces qu'il lui reste.
        //String tableau_coups[] = new String[5 - this.score_horizontal];
        ArrayList<String> tableau_coups = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            // On est sur un ligne on peut donc creer un mouvement
            StringBuilder str = new StringBuilder("");
            // On va chercher la piece
            for (int j = 0; j < 7; j++) {
                // On a trouer une piece, on va donc agr defferement selon le sens de la piece.
                if (this.board.get(i).get(j).equals('>')) {
                    str.append(fromColumnToChar(j)).append(i + 1).append('-');
                    Integer arrive = j + mouvement(true, "horizontal", i);
                    // Si le deplacement arrive au bord du terrain on l'arrete sinon on le fait.
                    if (arrive < 8) {
                        // On cherche a partir de la position d'arrive calculee la premiere case vide.
                        // Si elle est deja vide on s'y met sinon on cherche a la case suivante
                        while (!this.board.get(i).get(arrive).equals('.')) {
                            arrive++;
                        }
                        str.append(fromColumnToChar(arrive + 1)).append(i + 1);
                    } else {
                        str.append('G').append(i + 1);
                    }
                    break;
                } else if (this.board.get(i).get(j).equals('<')) {
                    str.append(fromColumnToChar(j)).append(i + 1).append('-');
                    Integer arrive = j - mouvement(true, "horizontal", i);
                    if (arrive > 0) {
                        while (!this.board.get(i).get(arrive).equals('.')) {
                            arrive--;
                        }
                        str.append(fromColumnToChar(arrive + 1)).append(i + 1);
                    } else {
                        str.append('G').append(i + 1);
                    }
                    break;
                }
            }
            tableau_coups.add(str.toString());
        }
        String[] temp = new String[5];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = tableau_coups.get(i);
            System.out.println(temp[i]);
        }
        return temp;
    } else

    {
        ArrayList<String> tableau_coups = new ArrayList<>();
        // On parcours tout les tableau jusqu'a trouver 'v' ou '^'
        for (int i = 0; i < this.board.size(); i++) {
            System.out.println(i);
            for (int j = 0; j < this.board.get(i).size() - 1; j++) {
                // C'est si on trouve un des deux qu'on commence a ecrire.
                if (this.board.get(i).get(j).equals('^')) {
                    System.out.println(i);
                    StringBuilder str = new StringBuilder("");
                    str.append(fromColumnToChar(j)).append(i + 1).append('-');
                    Integer arrive = i - mouvement(true, "vertical", j);
                    if (arrive <= 0) {
                        str.append(fromColumnToChar(j)).append(0);
                    } else {
                        // Il faut inverser, ici ce sont les i qui doivent rechanger. On fixe la colonne.
                        while (!this.board.get(arrive).get(j).equals('.')) {
                            arrive--;
                        }
                        //Colonne d'arrivee - Ligne d'arrivee
                        str.append(fromColumnToChar(j)).append(arrive + 1);
                        System.out.println(str.toString());
                    }
                    tableau_coups.add(str.toString());
                } else if (this.board.get(i).get(j).equals('v')) {
                    StringBuilder str = new StringBuilder("");
                    str.append(fromColumnToChar(j)).append(i + 1).append('-');
                    Integer arrive = i + mouvement(false, "vertical", j);
                    if (arrive >= this.board.size()) {
                        str.append(fromColumnToChar(j)).append(this.board.size() + 1);
                    } else {
                        while (!this.board.get(arrive).get(j).equals('.')) {
                            arrive++;
                        }
                        str.append(fromColumnToChar(j)).append(this.board.size() + 1);
                    }
                    tableau_coups.add(str.toString());
                }
            }
        }
        String[] temp = new String[5];
        System.out.println(tableau_coups);
        for (int i = 0; i < temp.length; i++) {
            temp[i] = tableau_coups.get(i);
        }
        return temp;
    }

}

    public void play(String move, String player) {
        // Plusieurs choses a faire
        Integer i = Character.getNumericValue(move.charAt(1));
        Integer j = fromLetterToInt(move.charAt(0));
        Integer x = Character.getNumericValue(move.charAt(4)) - 1;
        Integer y = fromLetterToInt(move.charAt(3));
        System.out.println(y);
        // 1. Faire le deplacement
        Character ch = this.board.get(i - 1).get(j);
        this.board.get(i - 1).set(j, '.');
        if (player.equals("horizontal")) {

            if (ch.equals('>')) {
                if (y.equals(7)) {
                    // 2. Changer le sens du mouvement si on arrive a la premiere extremite
                    this.board.get(x).set(y, '<');
                } else {
                    this.board.get(x).set(y, '>');
                }
                // On deplace les pions quand le notre passe dessus
                // De j a y
                for (int k = j; k < y; k++) {
                    Character c = this.board.get(i).get(k);
                    if (c.equals('^')) {
                        this.board.get(7).set(k, '^');
                        this.board.get(i).set(k, '.');
                    } else if (c.equals('v')) {
                        this.board.get(0).set(k, 'v');
                        this.board.get(i).set(k, '.');
                    }
                }

            } else if (ch.equals('<')) {
                if (y.equals(0)) {
                    this.board.get(x).set(y, '.');
                    // Incrementer le score du joueur si un pion revient au point de depart
                    this.score_horizontal++;
                } else {
                    this.board.get(x).set(y, '<');
                }
                // de y a j
                for (int k = y; k < j; k++) {
                    Character c = this.board.get(i).get(k);
                    if (c.equals('^')) {
                        this.board.get(7).set(k, '^');
                        this.board.get(i).set(k, '.');
                    } else if (c.equals('v')) {
                        this.board.get(0).set(k, 'v');
                        this.board.get(i).set(k, '.');
                    }
                }
            }
            // Le joueur passe la main
            this.current_player = players.vertical;
        } else {
            if (ch.equals('^')) {
                if (x.equals(0)) {
                    this.board.get(x).set(y, 'v');
                } else {
                    this.board.get(x).set(y, '^');
                }
                // de x a i
                for (int k = x; k < i; k++) {
                    Character c = this.board.get(k).get(j);
                    if (c.equals('>')) {
                        this.board.get(k).set(0, '>');
                        this.board.get(k).set(j, '.');
                    } else if (c.equals('<')) {
                        this.board.get(k).set(7, '<');
                        this.board.get(k).set(j, '.');
                    }
                }
            } else {
                if (x.equals(7)) {
                    this.board.get(x).set(y, '.');
                    this.score_vertical++;
                } else {
                    this.board.get(x).set(y, '^');
                }
                // de i a x
                for (int k = i; k < x; k++) {
                    Character c = this.board.get(k).get(j);
                    if (c.equals('>')) {
                        this.board.get(k).set(0, '>');
                        this.board.get(k).set(j, '.');
                    } else if (c.equals('<')) {
                        this.board.get(k).set(7, '<');
                        this.board.get(k).set(j, '.');
                    }
                }
            }
            this.current_player = players.horizontal;
        }

    }

    public boolean gameOver() {
        return (this.score_horizontal >= 4 || this.score_vertical >= 4);
    }

    public void printBoard() {
        for (int i = 0; i < this.board.size(); i++) {
            System.out.println(this.board.get(i));
        }
    }

    // Fonction pour calculer selon la piece, le numero de ligne ou de colonne et le joueur quel est le nombre de pas reglementaire que la piece peut faire
    // @param piece : vrai (aller : > | ^) ou faux (retour : < | v)
    // @param num : la ligne/colonne correspondante
    // @param player : le joueur qui joue
    // On suppose la piece presente et le mouvement s'effectuant dans le bon sens rapport a la piece et au joueur, et les joueurs possibles "horizontal" ou "vertical".
    public static Integer mouvement(Boolean aller, String player, Integer num) {
        if (player.equals("horizontal")) {
            if (aller) {
                if (num.equals(1) || num.equals(5)) {
                    return 1;
                } else if (num.equals(2) || num.equals(4)) {
                    return 3;
                } else {
                    return 2;
                }
            } else {
                if (num.equals(1) || num.equals(5)) {
                    return 3;
                } else if (num.equals(2) || num.equals(4)) {
                    return 1;
                } else {
                    return 2;
                }
            }
        } else {
            if (aller) {
                if (num.equals(1) || num.equals(5)) {
                    return 3;
                } else if (num.equals(2) || num.equals(4)) {
                    return 1;
                } else {
                    return 2;
                }
            } else {
                if (num.equals(1) || num.equals(5)) {
                    return 1;
                } else if (num.equals(2) || num.equals(4)) {
                    return 3;
                } else {
                    return 2;
                }
            }
        }
    }

    public static void printTab(ArrayList<ArrayList<Character>> t) {
        for (int i = 0; i < t.size() - 1; i++) {
            System.out.println(t.get(i));
        }
    }

    public static void main(String[] args) throws IOException {
        SquadroBoard n = new SquadroBoard(false);
        //n.printBoard();
        // On teste l'enregistrement du board dans plateau.txt qui est a la racine du projet
        n.saveToFile("plateau.txt");
        // On teste l'importation d'un fichier .txt au format convenu dans un board
        SquadroBoard test = new SquadroBoard(true);
        test.printBoard();
        test.setFromFile("test.txt");
        test.printBoard();
        System.out.println();

        String move = "F7-F4";

        System.out.println(test.possibleMoves("horizontal"));
        System.out.println();
        System.out.println(test.possibleMoves("vertical"));
        System.out.println(test.isValidMove(move, "vertical"));
        System.out.println(move);
        System.out.println(move.getClass());
        test.play(move, "vertical");
        test.printBoard();

        //test.isValidMove(move, test.current_player.toString());
    }
}




/*
import java.util.Arrays;

public class SquadroBoard {
    private static final int[][] speed = {
            {1, 3, 2, 3, 1},
            {3, 1, 2, 1, 3}
    };

    private String[][] board;

    public SquadroBoard() {
        board = new String[7][7];
        for (String[] x : board) {
            Arrays.fill(x, " ");
        }
        for (int i = 1; i < 6; i++) {
            board[6][i] = "^";
            board[i][0] = ">";
        }
    }

    public void backToStart(int lign, int column) {
        switch (this.board[lign][column]) {
            case "<":
                this.board[lign][column] = " ";
                this.board[lign][6] = "<";
                break;
            case ">":
                this.board[lign][column] = " ";
                this.board[lign][0] = ">";
                break;
            case "^":
                this.board[lign][column] = " ";
                this.board[6][column] = "^";
                break;
            case "v":
                this.board[lign][column] = " ";
                this.board[0][column] = "v";
                break;
            default:
        }
    }

    public void move(String player, int piece) {
        if (player == "v") {
            int posColumn = piece;
            int posLign = 6;
            for (int lign = 0; lign < 7; lign++) {
                if (this.board[lign][piece] != " ") {
                    posLign = lign;
                }
            }
            switch (this.board[posLign][posColumn]) {
                case "^":
                    int moves = this.speed[1][piece - 1];
                    this.board[posLign][posColumn] = " ";
                    while (moves > 0) {
                        posLign--;
                        if (posLign == 0) {
                            moves = 0;
                            this.board[posLign][posColumn] = "v";
                        } else if (this.board[posLign][posColumn] == " ") {
                            moves--;
                            if (moves == 0) this.board[posLign][posColumn] = "^";
                        } else {
                            moves = 1;
                            backToStart(posLign, posColumn);
                        }
                    }
                    break;
                case "v":
            }
        }


    }
}
*/