package iia.simpleGame.nim;

import iia.simpleGame.base.IGame;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class NimGameFirstTest {

    @Test
    public void getValue() {
        NimGameFirst board = new NimGameFirst(0);
        final int MIN = IGame.MIN_VALUE;
        final int MAX = IGame.MAX_VALUE;

        Assert.assertEquals(MAX, board.getValue(ANimGame.ROLE_FIRST));
        Assert.assertEquals(MIN, board.getValue(ANimGame.ROLE_SECOND));

        board = new NimGameFirst(1);
        Assert.assertEquals(6, board.getValue(ANimGame.ROLE_FIRST));
        Assert.assertEquals(6, board.getValue(ANimGame.ROLE_SECOND));

        board = new NimGameFirst(2);
        Assert.assertEquals(5, board.getValue(ANimGame.ROLE_FIRST));
        Assert.assertEquals(5, board.getValue(ANimGame.ROLE_SECOND));

        board = new NimGameFirst(3);
        Assert.assertEquals(4, board.getValue(ANimGame.ROLE_FIRST));
        Assert.assertEquals(4, board.getValue(ANimGame.ROLE_SECOND));

        board = new NimGameFirst(4);
        Assert.assertEquals(3, board.getValue(ANimGame.ROLE_FIRST));
        Assert.assertEquals(3, board.getValue(ANimGame.ROLE_SECOND));

        board = new NimGameFirst(5);
        Assert.assertEquals(2, board.getValue(ANimGame.ROLE_FIRST));
        Assert.assertEquals(2, board.getValue(ANimGame.ROLE_SECOND));

        board = new NimGameFirst(6);
        Assert.assertEquals(1, board.getValue(ANimGame.ROLE_FIRST));
        Assert.assertEquals(1, board.getValue(ANimGame.ROLE_SECOND));

        board = new NimGameFirst(7);
        Assert.assertEquals(0, board.getValue(ANimGame.ROLE_FIRST));
        Assert.assertEquals(0, board.getValue(ANimGame.ROLE_SECOND));

    }

    @Test
    public void play() {
        String one = "1";
        String two = "2";
        String three = "3";
        NimGameFirst game = new NimGameFirst(3);

        NimGameFirst newGame = (NimGameFirst) game.play(one, ANimGame.ROLE_FIRST);
        assertEquals(2, newGame.getN_matches());
        newGame = (NimGameFirst) game.play(two, ANimGame.ROLE_FIRST);
        assertEquals(1, newGame.getN_matches());
        newGame = (NimGameFirst) game.play(three, ANimGame.ROLE_FIRST);
        assertEquals(0, newGame.getN_matches());

        newGame = (NimGameFirst) game.play(one, ANimGame.ROLE_SECOND);
        assertEquals(2, newGame.getN_matches());
        newGame = (NimGameFirst) game.play(two, ANimGame.ROLE_SECOND);
        assertEquals(1, newGame.getN_matches());
        newGame = (NimGameFirst) game.play(three, ANimGame.ROLE_SECOND);
        assertEquals(0, newGame.getN_matches());
    }
}