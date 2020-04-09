package iia.games.nim;

import iia.games.base.IHeuristic;
import org.junit.Assert;
import org.junit.Test;


public class HeuristicNimTest {

    @Test
    public void testTDFirst(){
        BoardNim board = new BoardNim(0);
        final int MIN = IHeuristic.MIN_VALUE;
        final int MAX = IHeuristic.MAX_VALUE;
        final IHeuristic<RoleNim, BoardNim> h = HeuristicNim.hTDFIRST;
        RoleNim roleFirst = RoleNim.FIRST;
        RoleNim roleSecond = RoleNim.SECOND;

        Assert.assertEquals(MAX, h.eval(board, roleFirst));
        Assert.assertEquals(MIN, h.eval(board, roleSecond));

        board = new BoardNim(1);
        Assert.assertEquals(6, h.eval(board, roleFirst));
        Assert.assertEquals(6, h.eval(board, roleSecond));

        board = new BoardNim(2);
        Assert.assertEquals(5, h.eval(board, roleFirst));
        Assert.assertEquals(5, h.eval(board, roleSecond));

        board = new BoardNim(3);
        Assert.assertEquals(4, h.eval(board, roleFirst));
        Assert.assertEquals(4, h.eval(board, roleSecond));

        board = new BoardNim(4);
        Assert.assertEquals(3, h.eval(board, roleFirst));
        Assert.assertEquals(3, h.eval(board, roleSecond));

        board = new BoardNim(5);
        Assert.assertEquals(2, h.eval(board, roleFirst));
        Assert.assertEquals(2, h.eval(board, roleSecond));

        board = new BoardNim(6);
        Assert.assertEquals(1, h.eval(board, roleFirst));
        Assert.assertEquals(1, h.eval(board, roleSecond));

        board = new BoardNim(7);
        Assert.assertEquals(0, h.eval(board, roleFirst));
        Assert.assertEquals(0, h.eval(board, roleSecond));

    }

    @Test
    public void testTDSecond(){
        BoardNim board = new BoardNim(0);
        final int MIN = IHeuristic.MIN_VALUE;
        final int MAX = IHeuristic.MAX_VALUE;
        final IHeuristic<RoleNim, BoardNim> h = HeuristicNim.hTDSECOND;
        RoleNim roleFirst = RoleNim.FIRST;
        RoleNim roleSecond = RoleNim.SECOND;

        Assert.assertEquals(MIN, h.eval(board, roleFirst));
        Assert.assertEquals(MAX, h.eval(board, roleSecond));

        board = new BoardNim(1);
        Assert.assertEquals(6, h.eval(board, roleFirst));
        Assert.assertEquals(6, h.eval(board, roleSecond));

        board = new BoardNim(2);
        Assert.assertEquals(5, h.eval(board, roleFirst));
        Assert.assertEquals(5, h.eval(board, roleSecond));

        board = new BoardNim(3);
        Assert.assertEquals(4, h.eval(board, roleFirst));
        Assert.assertEquals(4, h.eval(board, roleSecond));

        board = new BoardNim(4);
        Assert.assertEquals(3, h.eval(board, roleFirst));
        Assert.assertEquals(3, h.eval(board, roleSecond));

        board = new BoardNim(5);
        Assert.assertEquals(2, h.eval(board, roleFirst));
        Assert.assertEquals(2, h.eval(board, roleSecond));

        board = new BoardNim(6);
        Assert.assertEquals(1, h.eval(board, roleFirst));
        Assert.assertEquals(1, h.eval(board, roleSecond));

        board = new BoardNim(7);
        Assert.assertEquals(0, h.eval(board, roleFirst));
        Assert.assertEquals(0, h.eval(board, roleSecond));

    }

}