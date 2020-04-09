package iia.games.nim;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BoardNimTest {

    @Test
    public void play() {
        BoardNim board = new BoardNim(3);
        MoveNim one = new MoveNim(1);
        MoveNim two = new MoveNim(2);
        MoveNim three = new MoveNim(3);

        BoardNim newBoard = board.play(one, RoleNim.FIRST);
        assertEquals(2, newBoard.getN_matches());
        newBoard = board.play(two, RoleNim.FIRST);
        assertEquals(1, newBoard.getN_matches());
        newBoard = board.play(three, RoleNim.FIRST);
        assertEquals(0, newBoard.getN_matches());

        newBoard = board.play(one, RoleNim.SECOND);
        assertEquals(2, newBoard.getN_matches());
        newBoard = board.play(two, RoleNim.SECOND);
        assertEquals(1, newBoard.getN_matches());
        newBoard = board.play(three, RoleNim.SECOND);
        assertEquals(0, newBoard.getN_matches());

        board = new BoardNim(7);
        newBoard = board.play(three, RoleNim.SECOND);
        assertEquals(4, newBoard.getN_matches());
        assertEquals(new BoardNim(4), newBoard);
        newBoard = board.play(one, RoleNim.FIRST);
        assertEquals(6, newBoard.getN_matches());
        assertEquals(new BoardNim(6), newBoard);

    }

    @Test
    public void possibleMoves() {
        BoardNim board = new BoardNim(0);
        ArrayList<MoveNim> allMoves = board.possibleMoves(RoleNim.FIRST);
        ArrayList<MoveNim> expected = new ArrayList<>();
        assertEquals(allMoves, expected);

        board = new BoardNim(1);
        expected.add(new MoveNim(1));
        allMoves = board.possibleMoves(RoleNim.FIRST);
        assertEquals(allMoves, expected);

        board = new BoardNim(2);
        expected.add(new MoveNim(2));
        allMoves = board.possibleMoves(RoleNim.FIRST);
        assertEquals(allMoves, expected);

        board = new BoardNim(3);
        expected.add(new MoveNim(3));
        allMoves = board.possibleMoves(RoleNim.FIRST);
        assertEquals(allMoves, expected);

        board = new BoardNim(4);
        allMoves = board.possibleMoves(RoleNim.FIRST);
        assertEquals(allMoves, expected);

        board = new BoardNim(7);
        allMoves = board.possibleMoves(RoleNim.FIRST);
        assertEquals(allMoves, expected);
    }

    @Test
    public void isGameOver() {
        BoardNim gameOverBoard = new BoardNim(0);
        Assert.assertTrue(gameOverBoard.isGameOver());

        BoardNim notGameOverBoard = new BoardNim(1);
        Assert.assertFalse(notGameOverBoard.isGameOver());

        notGameOverBoard = new BoardNim(2);
        Assert.assertFalse(notGameOverBoard.isGameOver());

        notGameOverBoard = new BoardNim(3);
        Assert.assertFalse(notGameOverBoard.isGameOver());

        notGameOverBoard = new BoardNim(4);
        Assert.assertFalse(notGameOverBoard.isGameOver());

        notGameOverBoard = new BoardNim(5);
        Assert.assertFalse(notGameOverBoard.isGameOver());

        notGameOverBoard = new BoardNim(6);
        Assert.assertFalse(notGameOverBoard.isGameOver());

        notGameOverBoard = new BoardNim(7);
        Assert.assertFalse(notGameOverBoard.isGameOver());
    }

    @Test
    public void isValidMove() {
        BoardNim board = new BoardNim(3);
        MoveNim one = new MoveNim(1);
        MoveNim two = new MoveNim(2);
        MoveNim three = new MoveNim(3);

        Assert.assertTrue(board.isValidMove(one, RoleNim.FIRST));
        Assert.assertTrue(board.isValidMove(one, RoleNim.SECOND));

        Assert.assertTrue(board.isValidMove(two, RoleNim.FIRST));
        Assert.assertTrue(board.isValidMove(two, RoleNim.SECOND));

        Assert.assertTrue(board.isValidMove(three, RoleNim.FIRST));
        Assert.assertTrue(board.isValidMove(three, RoleNim.SECOND));

        board = new BoardNim(2);
        Assert.assertTrue(board.isValidMove(one, RoleNim.FIRST));
        Assert.assertTrue(board.isValidMove(one, RoleNim.SECOND));

        Assert.assertTrue(board.isValidMove(two, RoleNim.FIRST));
        Assert.assertTrue(board.isValidMove(two, RoleNim.SECOND));

        Assert.assertFalse(board.isValidMove(three, RoleNim.FIRST));
        Assert.assertFalse(board.isValidMove(three, RoleNim.SECOND));

        board = new BoardNim(1);
        Assert.assertTrue(board.isValidMove(one, RoleNim.FIRST));
        Assert.assertTrue(board.isValidMove(one, RoleNim.SECOND));

        Assert.assertFalse(board.isValidMove(two, RoleNim.FIRST));
        Assert.assertFalse(board.isValidMove(two, RoleNim.SECOND));

        Assert.assertFalse(board.isValidMove(three, RoleNim.FIRST));
        Assert.assertFalse(board.isValidMove(three, RoleNim.SECOND));
    }

    @Test
    public void getN_matches() {
        BoardNim board = new BoardNim(1);
        assertEquals(1, board.getN_matches());
        board = new BoardNim(2);
        assertEquals(2, board.getN_matches());
        board = new BoardNim(4);
        assertEquals(4, board.getN_matches());
        board = new BoardNim(5);
        assertEquals(5, board.getN_matches());
    }
}