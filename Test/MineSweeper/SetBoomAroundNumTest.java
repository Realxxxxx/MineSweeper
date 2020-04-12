package MineSweeper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SetBoomAroundNumTest {
    private GameModel model;
    private Grid[][] grids;
    @Before
    public void SetUp(){
        this.model = new GameModel();
        this.grids = model.generateEmptyMap(10);
        model.putBoomsInMap(10,1,grids);
    }

    @Test
    public void Test1(){
        int state = model.setBoomsAroundInMap(10,grids);
        Assert.assertEquals(0,state);
    }

    @Test
    public void Test2(){
        int state = model.setBoomsAroundInMap(-1,grids);
        Assert.assertEquals(-1,state);

    }

    @Test
    public void Test3(){
        int state = model.setBoomsAroundInMap(10,null);
        Assert.assertEquals(-1,state);
    }

    @Test
    public void Test4(){
        int state = model.setBoomsAroundInMap(11,grids);
        Assert.assertEquals(-1,state);
    }

}
