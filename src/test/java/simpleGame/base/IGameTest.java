package iia.simpleGame.base;

import org.junit.Assert;
import org.junit.Test;


public class IGameTest {
    @Test
    public void infinitySymmetry(){
        Assert.assertEquals(IGame.MAX_VALUE, - IGame.MIN_VALUE);
        Assert.assertEquals(IGame.MIN_VALUE, - IGame.MAX_VALUE);
    }

}