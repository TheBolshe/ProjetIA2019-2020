package iia.games.nim;

import org.junit.Assert;
import org.junit.Test;


public class MoveNimTest {

    @Test
    public void testToString() {
        MoveNim move = new MoveNim(1);
        Assert.assertEquals("1", move.toString());

        move = new MoveNim(2);
        Assert.assertEquals("2", move.toString());

        move = new MoveNim(3);
        Assert.assertEquals("3", move.toString());
    }

}