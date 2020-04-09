package iia.simpleGame.nim;

import iia.simpleGame.base.IGame;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ANimGameTest {

    static class NimGameZero extends ANimGame{

        public NimGameZero(int i) {
            super(i);
        }

        @Override
        public int getValue(String role) {
            return 0;
        }

        @Override
        public IGame play(String move, String role) {
            return null;
        }
    }

    @Test
    public void possibleMoves() {
        NimGameZero board = new NimGameZero(0);
        ArrayList<String> allMoves = board.possibleMoves("FIRST");
        ArrayList<String> expected = new ArrayList<>();
        Assert.assertEquals(allMoves, expected);

        board = new NimGameZero(1);
        allMoves = board.possibleMoves("FIRST");
        expected.add("1");
        Assert.assertEquals(allMoves, expected);

        board = new NimGameZero(2);
        allMoves = board.possibleMoves("FIRST");
        expected.add("2");
        Assert.assertEquals(allMoves, expected);

        board = new NimGameZero(3);
        allMoves = board.possibleMoves("FIRST");
        expected.add("3");
        Assert.assertEquals(allMoves, expected);

        board = new NimGameZero(4);
        allMoves = board.possibleMoves("FIRST");
        Assert.assertEquals(allMoves, expected);

        board = new NimGameZero(7);
        allMoves = board.possibleMoves("FIRST");
        Assert.assertEquals(allMoves, expected);

    }

    @Test
    public void isValidMove() {
        String one = "1";
        String two = "2";
        String three = "3";
        NimGameZero board = new NimGameZero(0);
        Assert.assertFalse(board.isValidMove(one, ANimGame.ROLE_FIRST));
        Assert.assertFalse(board.isValidMove(one, ANimGame.ROLE_SECOND));
        Assert.assertFalse(board.isValidMove(two, ANimGame.ROLE_FIRST));
        Assert.assertFalse(board.isValidMove(two, ANimGame.ROLE_SECOND));
        Assert.assertFalse(board.isValidMove(three, ANimGame.ROLE_FIRST));
        Assert.assertFalse(board.isValidMove(three, ANimGame.ROLE_SECOND));

        board = new NimGameZero(1);
        Assert.assertTrue(board.isValidMove(one, ANimGame.ROLE_FIRST));
        Assert.assertTrue(board.isValidMove(one, ANimGame.ROLE_SECOND));
        Assert.assertFalse(board.isValidMove(two, ANimGame.ROLE_FIRST));
        Assert.assertFalse(board.isValidMove(two, ANimGame.ROLE_SECOND));
        Assert.assertFalse(board.isValidMove(three, ANimGame.ROLE_FIRST));
        Assert.assertFalse(board.isValidMove(three, ANimGame.ROLE_SECOND));
        board = new NimGameZero(2);
        Assert.assertTrue(board.isValidMove(one, ANimGame.ROLE_FIRST));
        Assert.assertTrue(board.isValidMove(one, ANimGame.ROLE_SECOND));
        Assert.assertTrue(board.isValidMove(two, ANimGame.ROLE_FIRST));
        Assert.assertTrue(board.isValidMove(two, ANimGame.ROLE_SECOND));
        Assert.assertFalse(board.isValidMove(three, ANimGame.ROLE_FIRST));
        Assert.assertFalse(board.isValidMove(three, ANimGame.ROLE_SECOND));
        board = new NimGameZero(3);
        Assert.assertTrue(board.isValidMove(one, ANimGame.ROLE_FIRST));
        Assert.assertTrue(board.isValidMove(one, ANimGame.ROLE_SECOND));
        Assert.assertTrue(board.isValidMove(two, ANimGame.ROLE_FIRST));
        Assert.assertTrue(board.isValidMove(two, ANimGame.ROLE_SECOND));
        Assert.assertTrue(board.isValidMove(three, ANimGame.ROLE_FIRST));
        Assert.assertTrue(board.isValidMove(three, ANimGame.ROLE_SECOND));

    }

    @Test
    public void isGameOver() {
        NimGameZero gameOverBoard = new NimGameZero(0);
        Assert.assertTrue(gameOverBoard.isGameOver());
        NimGameZero notGameOverBoard = new NimGameZero(1);
        Assert.assertFalse(notGameOverBoard.isGameOver());
        notGameOverBoard = new NimGameZero(2);
        Assert.assertFalse(notGameOverBoard.isGameOver());
        notGameOverBoard = new NimGameZero(3);
        Assert.assertFalse(notGameOverBoard.isGameOver());
        notGameOverBoard = new NimGameZero(4);
        Assert.assertFalse(notGameOverBoard.isGameOver());
        notGameOverBoard = new NimGameZero(5);
        Assert.assertFalse(notGameOverBoard.isGameOver());
        notGameOverBoard = new NimGameZero(6);
        Assert.assertFalse(notGameOverBoard.isGameOver());
        notGameOverBoard = new NimGameZero(7);
        Assert.assertFalse(notGameOverBoard.isGameOver());
    }

    @Test
    public void getN_matches() {
        NimGameZero board = new NimGameZero(0);
        assertEquals(0, board.getN_matches());

        board = new NimGameZero(1);
        assertEquals(1, board.getN_matches());

        board = new NimGameZero(2);
        assertEquals(2, board.getN_matches());

        board = new NimGameZero(3);
        assertEquals(3, board.getN_matches());

        board = new NimGameZero(4);
        assertEquals(4, board.getN_matches());

        board = new NimGameZero(5);
        assertEquals(5, board.getN_matches());
        board = new NimGameZero(6);
        assertEquals(6, board.getN_matches());
        board = new NimGameZero(7);
        assertEquals(7, board.getN_matches());

    }
}