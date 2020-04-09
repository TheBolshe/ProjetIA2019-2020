package iia.simpleGame.nim;

import iia.simpleGame.base.IGame;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class NimGameSecondTest {

    @Test
    public void getValue() {
        NimGameSecond board = new NimGameSecond(0);
        final int MIN = IGame.MIN_VALUE;
        final int MAX = IGame.MAX_VALUE;

        Assert.assertEquals(MAX, board.getValue(ANimGame.ROLE_SECOND));
        Assert.assertEquals(MIN, board.getValue(ANimGame.ROLE_FIRST));

        board = new NimGameSecond(1);
        Assert.assertEquals(6, board.getValue(ANimGame.ROLE_FIRST));
        Assert.assertEquals(6, board.getValue(ANimGame.ROLE_SECOND));

        board = new NimGameSecond(2);
        Assert.assertEquals(5, board.getValue(ANimGame.ROLE_FIRST));
        Assert.assertEquals(5, board.getValue(ANimGame.ROLE_SECOND));

        board = new NimGameSecond(3);
        Assert.assertEquals(4, board.getValue(ANimGame.ROLE_FIRST));
        Assert.assertEquals(4, board.getValue(ANimGame.ROLE_SECOND));

        board = new NimGameSecond(4);
        Assert.assertEquals(3, board.getValue(ANimGame.ROLE_FIRST));
        Assert.assertEquals(3, board.getValue(ANimGame.ROLE_SECOND));

        board = new NimGameSecond(5);
        Assert.assertEquals(2, board.getValue(ANimGame.ROLE_FIRST));
        Assert.assertEquals(2, board.getValue(ANimGame.ROLE_SECOND));

        board = new NimGameSecond(6);
        Assert.assertEquals(1, board.getValue(ANimGame.ROLE_FIRST));
        Assert.assertEquals(1, board.getValue(ANimGame.ROLE_SECOND));

        board = new NimGameSecond(7);
        Assert.assertEquals(0, board.getValue(ANimGame.ROLE_FIRST));
        Assert.assertEquals(0, board.getValue(ANimGame.ROLE_SECOND));

    }

    @Test
    public void play() {
        String one = "1";
        String two = "2";
        String three = "3";
        NimGameSecond game = new NimGameSecond(3);

        NimGameSecond newGame = (NimGameSecond) game.play(one, ANimGame.ROLE_FIRST);
        assertEquals(2, newGame.getN_matches());
        newGame = (NimGameSecond) game.play(two, ANimGame.ROLE_FIRST);
        assertEquals(1, newGame.getN_matches());
        newGame = (NimGameSecond) game.play(three, ANimGame.ROLE_FIRST);
        assertEquals(0, newGame.getN_matches());

        newGame = (NimGameSecond) game.play(one, ANimGame.ROLE_SECOND);
        assertEquals(2, newGame.getN_matches());
        newGame = (NimGameSecond) game.play(two, ANimGame.ROLE_SECOND);
        assertEquals(1, newGame.getN_matches());
        newGame = (NimGameSecond) game.play(three, ANimGame.ROLE_SECOND);
        assertEquals(0, newGame.getN_matches());

    }
}