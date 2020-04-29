package iia.simpleGame.base;

public interface IChallenger {

    /**
     * Returns your team name as "Name1_Name2"
     */
    String teamName();

    /**
     * The referee assigns you a role at the begining.
     * This is the perfect moment to prepare before the game start.
     */
    void setRole(String role);

    /**
     * The referee accepted your move and actually played it.
     * You should update your internal representation of the board.
     */
    void iPlay(String move);

    /**
     * The referee accepted your enemy's move and actually played it.
     * You should update your internal representation of the board.
     */
    void otherPlay(String move);

    /**
     * The referee is waiting for your next move.
     * Choose wisely.
     */
    String bestMove();

    /**
     * You win ! Here you can return a banner or a small victory speach.
     */
    String victory();

    /**
     * You lost ! Here you can return a banner or a small defeated speach.
     */
    String defeat();

    /**
     * It's a tie ! Here you can return a banner or a small speach.
     */
    String tie();
}