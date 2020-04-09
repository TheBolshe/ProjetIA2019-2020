package iia.games.base;

public interface IChallenger {

    String teamName();

    void setRole(String role);

    void iPlay(String move);

    void otherPlay(String move);

    String bestMove();

    String victory();

    String defeat();

    String tie();
}
