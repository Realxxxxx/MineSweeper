package MineSweeper;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MapGenerateTest {
    private GameModel model;

    @Before
    public void SetUp(){
        this.model = new GameModel();
    }

    @Test
    public void MapGenTest1(){
        Grid[][] grids = model.generateMap(10,1);
        int num = 0;
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids.length; j++) {
                if (grids[i][j].getType()==GridType.BOOM){
                    num += 1;
                }
            }
        }
        Assert.assertEquals(1,num);
    }

    @Test
    public void MapGenTest2() {
        Grid[][] grids = model.generateMap(1,1);
        int num = 0;
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids.length; j++) {
                if (grids[i][j].getType()==GridType.BOOM){
                    num += 1;
                }
            }
        }
        Assert.assertEquals(1,num);
    }



}
