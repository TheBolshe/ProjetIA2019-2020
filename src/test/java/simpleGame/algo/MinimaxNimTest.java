package iia.simpleGame.algo;

import iia.simpleGame.base.IGame;
import iia.simpleGame.nim.ANimGame;
import iia.simpleGame.nim.NimGameFirst;
import iia.simpleGame.nim.NimGameSecond;
import org.junit.Assert;
import org.junit.Test;


public class MinimaxNimTest {

    @Test
    public void maxmin() {
        Minimax algoFirst = new Minimax(ANimGame.ROLE_FIRST, ANimGame.ROLE_SECOND);
        NimGameFirst gameFirst = new NimGameFirst(7);

        Assert.assertEquals(0, algoFirst.maxmin(gameFirst, 0));
        Assert.assertEquals(3, algoFirst.maxmin(gameFirst, 1));
        Assert.assertEquals(4, algoFirst.maxmin(gameFirst, 2));
        Assert.assertEquals(6, algoFirst.maxmin(gameFirst, 3));
        Assert.assertEquals(IGame.MAX_VALUE, algoFirst.maxmin(gameFirst, 4));

        Minimax algoSecond = new Minimax(ANimGame.ROLE_SECOND, ANimGame.ROLE_FIRST);
        NimGameSecond gameSecond = new NimGameSecond(7);

        Assert.assertEquals(0, algoSecond.maxmin(gameSecond, 0));
        Assert.assertEquals(3, algoSecond.maxmin(gameSecond, 1));
        Assert.assertEquals(4, algoSecond.maxmin(gameSecond, 2));
        Assert.assertEquals(6, algoSecond.maxmin(gameSecond, 3));
        Assert.assertEquals(IGame.MAX_VALUE, algoSecond.maxmin(gameSecond, 4));

    }

    @Test
    public void maxminMore() {
        Minimax algoFirst = new Minimax(ANimGame.ROLE_FIRST, ANimGame.ROLE_SECOND);
        Assert.assertEquals(4, algoFirst.maxmin(new NimGameFirst(7), 2));
        Assert.assertEquals(6, algoFirst.maxmin(new NimGameFirst(6), 3));
    }

    @Test
    public void minmax() {
        Minimax algoFirst = new Minimax(ANimGame.ROLE_FIRST, ANimGame.ROLE_SECOND);
        NimGameFirst gameFirst = new NimGameFirst(7);

        Assert.assertEquals(0, algoFirst.minmax(gameFirst, 0));
        Assert.assertEquals(1, algoFirst.minmax(gameFirst, 1));
        Assert.assertEquals(4, algoFirst.minmax(gameFirst, 2));
        Assert.assertEquals(5, algoFirst.minmax(gameFirst, 3));
        Assert.assertEquals(IGame.MIN_VALUE, algoFirst.minmax(gameFirst, 4));

        Minimax algoSecond = new Minimax(ANimGame.ROLE_SECOND, ANimGame.ROLE_FIRST);
        NimGameSecond gameSecond = new NimGameSecond(7);

        Assert.assertEquals(0, algoSecond.minmax(gameSecond, 0));
        Assert.assertEquals(1, algoSecond.minmax(gameSecond, 1));
        Assert.assertEquals(4, algoSecond.minmax(gameSecond, 2));
        Assert.assertEquals(5, algoSecond.minmax(gameSecond, 3));
        Assert.assertEquals(IGame.MIN_VALUE, algoSecond.minmax(gameSecond, 4));

    }

    @Test
    public void minmaxMore() {
        Minimax algoFirst = new Minimax(ANimGame.ROLE_FIRST, ANimGame.ROLE_SECOND);
        Assert.assertEquals(4, algoFirst.minmax(new NimGameFirst(4), 1));
        Assert.assertEquals(6, algoFirst.minmax(new NimGameFirst(6), 3));
    }

}