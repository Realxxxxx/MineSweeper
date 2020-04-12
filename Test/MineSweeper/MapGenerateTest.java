package MineSweeper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MapGenerateTest {

    private GameModel model;

    @Before
    public void setUp(){
        this.model = new GameModel();
    }

    @Test
    public void testEmptyMapGen1(){
        Grid[][] grids = model.generateEmptyMap(10);
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids.length; j++) {
                Assert.assertEquals(GridType.EMPTY,grids[i][j].getType());
            }
        }
    }

    @Test
    public void testEmptyMapGen2(){
        Grid[][] grids = model.generateEmptyMap(-1);
        Assert.assertNull(grids);
    }

    @Test
    public void testRandomlyMapGen(){
        Grid[][] gTest =  model.generateMap(8, 10);
        Assert.assertEquals(8, gTest.length, 0.01);
        int boomCount = 0;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (gTest[i][j].getType().equals(GridType.BOOM))
                    boomCount += 1;
            }
        }
        Assert.assertEquals(10, boomCount, 0.01);
    }

}
