package iia.games.base;

import org.junit.Assert;
import org.junit.Test;


public class IHeuristicTest {
    @Test
    public void infinitySymmetry(){
        Assert.assertEquals(IHeuristic.MAX_VALUE, - IHeuristic.MIN_VALUE);
        Assert.assertEquals(IHeuristic.MIN_VALUE, - IHeuristic.MAX_VALUE);
    }

}