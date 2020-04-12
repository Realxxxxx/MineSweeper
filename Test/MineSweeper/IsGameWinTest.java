package MineSweeper;

//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class IsGameWinTest {
    GameController controller;
    Grid[][] testGrid;

    private void initGameForTest(int bounds, Grid[][] grids){
        // act as initGame
        controller.model.setBounds(3);
        controller.model.setGrids(grids);

        this.controller.model.setBoomsAroundInMap(
                controller.model.bounds,
                controller.model.grids);
    }

    private void makeUpTestGrid(){
        /*
           all grid are not Selected
                1  2  3
            1   B  1  0
            2   1  1  0
            3   0  0  0
         */

        testGrid = new Grid[3][3];

        for (int i=0; i<3;i++){
            for (int j=0;j<3;j++){
                Grid g = new Grid(i, j);
                g.setType(GridType.EMPTY);
                testGrid[i][j] = g;
            }
        }

        Grid boomGrid = new Grid(0, 0);
        boomGrid.setType(GridType.BOOM);
        testGrid[0][0] = boomGrid;

        Grid dangerGrid1 = new Grid(0, 1);
        dangerGrid1.setType(GridType.EMPTY);
        testGrid[0][1] = dangerGrid1;

        Grid dangerGrid2 = new Grid(1, 0);
        dangerGrid2.setType(GridType.EMPTY);
        testGrid[1][0] = dangerGrid2;

        Grid emptyGrid = new Grid(1, 1);
        emptyGrid.setType(GridType.EMPTY);
        testGrid[1][1] = emptyGrid;
    }

    @Before
    public void SetUp() throws Exception{
        controller = new GameController();
    }

    @After
    public void TearDown() throws Exception{
        controller = null;
        testGrid = null;
    }

    @Test
    public void testGameWin() {
        /*
        测试 3x3的地图中胜利的过程
         */
        makeUpTestGrid();
        initGameForTest(3, testGrid);

        controller.model.markGridFlag(controller.model.grids, 0, 0);
        controller.view.drawGame(controller.model.grids);
        controller.model.isGameWin();
        assertSame(GameState.InGame, controller.model.state);

        controller.model.filpGrid(controller.model.grids, 1, 0);
        controller.view.drawGame(controller.model.grids);
        controller.model.isGameWin();
        assertSame(GameState.InGame, controller.model.state);

        controller.model.filpGrid(controller.model.grids, 0, 1);
        controller.view.drawGame(controller.model.grids);
        controller.model.isGameWin();
        assertSame(GameState.InGame, controller.model.state);

        controller.model.filpGrid(controller.model.grids, 1, 1);
        controller.view.drawGame(controller.model.grids);
        controller.model.isGameWin();
        assertSame(GameState.InGame, controller.model.state);

        controller.model.filpGrid(controller.model.grids, 1, 2);
        controller.view.drawGame(controller.model.grids);
        controller.model.isGameWin();
        assertSame(GameState.Win, controller.model.state);

    }

}
