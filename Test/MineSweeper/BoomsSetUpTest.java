package MineSweeper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoomsSetUpTest {
    private GameModel model;
    private Grid[][] grids;
    @Before
    public void setup(){
        this.model = new GameModel();
        this.grids = this.model.generateEmptyMap(10);
    }

    @Test
    public void boomSetUpTest1() {

        int state =this.model.putBoomsInMap(10,1,grids);
        int num = 0;
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids.length; j++) {
                if (grids[i][j].getType()==GridType.BOOM){
                    num += 1;
                }
            }
        }
        Assert.assertEquals(1,num);
        Assert.assertNotNull(grids);
        Assert.assertEquals(0,state);
    }

    @Test
    public void boomSetUpTest2() {
        int state = this.model.putBoomsInMap(10,100,grids);
        Assert.assertEquals(-1,state);
    }

    @Test
    public void boomSetUpTest3() {
        int state = this.model.putBoomsInMap(10,-1,grids);
        Assert.assertEquals(-1,state);
    }

    @Test
    public void boomSetUpTest4() {
        int state = this.model.putBoomsInMap(-1,10,grids);
        Assert.assertEquals(-1,state);
    }

    @Test
    public void boomSetUpTest5() {
        int state = this.model.putBoomsInMap(10,1,null);
        Assert.assertEquals(-1,state);
    }

    @Test
    public void boomSetUpTest6() {
        int state = this.model.putBoomsInMap(11,1,grids);
        Assert.assertEquals(-1,state);
    }


}
