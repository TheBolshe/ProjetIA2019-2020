package iia.games.base;

public interface Part2 {

    Part2 setFromFile(String fileName);
    void saveToFile(String fileName);
    boolean isValidMove(String move, String role);
    boolean gameOver();
    String[] possibleMoves(String role);
    Part2 play(String move, String role);
}
