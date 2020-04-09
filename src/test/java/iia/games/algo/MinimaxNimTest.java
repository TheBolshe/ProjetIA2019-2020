package iia.games.algo;

import iia.games.base.IHeuristic;
import iia.games.nim.BoardNim;
import iia.games.nim.HeuristicNim;
import iia.games.nim.MoveNim;
import iia.games.nim.RoleNim;
import org.junit.Assert;
import org.junit.Test;


public class MinimaxNimTest {

    private Minimax<MoveNim, RoleNim, BoardNim> algoFirst = new Minimax<>(RoleNim.FIRST, RoleNim.SECOND, 2);
    private IHeuristic<RoleNim, BoardNim> hFirst = HeuristicNim.hTDFIRST;
    private Minimax<MoveNim, RoleNim, BoardNim> algoSecond = new Minimax<>(RoleNim.SECOND, RoleNim.FIRST, 2);
    private IHeuristic<RoleNim, BoardNim> hSecond = HeuristicNim.hTDSECOND;

    @Test
    public void bestMove() {
    }

    @Test
    public void maxmin() {
        BoardNim board = new BoardNim(7);

        Assert.assertEquals(0, algoFirst.maxmin(board, 0, hFirst));
        Assert.assertEquals(3, algoFirst.maxmin(board, 1, hFirst));
        Assert.assertEquals(4, algoFirst.maxmin(board, 2, hFirst));
        Assert.assertEquals(6, algoFirst.maxmin(board, 3, hFirst));
        Assert.assertEquals(IHeuristic.MAX_VALUE, algoFirst.maxmin(board, 4, hFirst));

        Assert.assertEquals(0, algoSecond.maxmin(board, 0, hSecond));
        Assert.assertEquals(3, algoSecond.maxmin(board, 1, hSecond));
        Assert.assertEquals(4, algoSecond.maxmin(board, 2, hSecond));
        Assert.assertEquals(6, algoSecond.maxmin(board, 3, hSecond));
        Assert.assertEquals(IHeuristic.MAX_VALUE, algoSecond.maxmin(board, 4, hSecond));

    }

    @Test
    public void maxminMore() {
        Assert.assertEquals(4, algoFirst.maxmin(new BoardNim(7), 2, hFirst));
        Assert.assertEquals(6, algoFirst.maxmin(new BoardNim(6), 3, hFirst));
    }

    @Test
    public void minmax() {
        BoardNim board = new BoardNim(7);

        Assert.assertEquals(0, algoFirst.minmax(board, 0, hFirst));
        Assert.assertEquals(1, algoFirst.minmax(board, 1, hFirst));
        Assert.assertEquals(4, algoFirst.minmax(board, 2, hFirst));
        Assert.assertEquals(5, algoFirst.minmax(board, 3, hFirst));
        Assert.assertEquals(IHeuristic.MIN_VALUE, algoFirst.minmax(board, 4, hFirst));

        Assert.assertEquals(0, algoSecond.minmax(board, 0, hSecond));
        Assert.assertEquals(1, algoSecond.minmax(board, 1, hSecond));
        Assert.assertEquals(4, algoSecond.minmax(board, 2, hSecond));
        Assert.assertEquals(5, algoSecond.minmax(board, 3, hSecond));
        Assert.assertEquals(IHeuristic.MIN_VALUE, algoSecond.minmax(board, 4, hSecond));

    }

    @Test
    public void minmaxMore() {
        Assert.assertEquals(4, algoFirst.minmax(new BoardNim(4), 1, hFirst));
        Assert.assertEquals(6, algoFirst.minmax(new BoardNim(6), 3, hFirst));
    }
}