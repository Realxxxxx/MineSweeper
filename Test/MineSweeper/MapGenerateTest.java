package MineSweeper;

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
    public void EmptyMapGenTest1(){
        Grid[][] grids = model.GenerateEmptyMap(10);
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids.length; j++) {
                Assert.assertEquals(GridType.EMPTY,grids[i][j].getType());
            }
        }
    }

    @Test
    public void EmptyMapGenTest2(){
        Grid[][] grids = model.GenerateEmptyMap(-1);
        Assert.assertNull(grids);
    }


}
