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
        this.grids = this.model.GenerateEmptyMap(10);
    }

    @Test
    public void BoomSetUpTest1() {

        int state =this.model.PutBoomsInMap(10,1,grids);
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
    public void BoomSetUpTest2() {
        int state = this.model.PutBoomsInMap(10,100,grids);
        Assert.assertEquals(-1,state);
    }

    @Test
    public void BoomSetUpTest3() {
        int state = this.model.PutBoomsInMap(10,-1,grids);
        Assert.assertEquals(-1,state);
    }

    @Test
    public void BoomSetUpTest4() {
        int state = this.model.PutBoomsInMap(-1,10,grids);
        Assert.assertEquals(-1,state);
    }

    @Test
    public void BoomSetUpTest5() {
        int state = this.model.PutBoomsInMap(10,0,grids);
        Assert.assertEquals(-1,state);
    }

    @Test
    public void BoomSetUpTest6() {
        int state = this.model.PutBoomsInMap(10,0,null);
        Assert.assertEquals(-1,state);
    }

    @Test
    public void BoomSetUpTest7() {
        int state = this.model.PutBoomsInMap(11,1,grids);
        Assert.assertEquals(-1,state);
    }


}
