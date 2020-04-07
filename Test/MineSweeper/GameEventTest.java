package MineSweeper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class GameEventTest {
    GameController controller;
    Grid[][] testGrid;

    private void initGameForTest(int bounds, Grid[][] grids){
        // act as initGame
        controller.model.setBounds(2);
        controller.model.setGrids(grids);
    }

    private void makeUpTestGridSelected(){
        /*
           all grid are Selected!
                1  2
            1   B  D
            2   D  E
         */

        testGrid = new Grid[2][2];

        Grid boomGrid = new Grid(0, 0);
        boomGrid.setType(GridType.BOOM);
        boomGrid.setSelected(true);
        testGrid[0][0] = boomGrid;

        Grid dangerGrid1 = new Grid(0, 1);
        dangerGrid1.setType(GridType.DANGEROUS);
        dangerGrid1.setSelected(true);
        testGrid[0][1] = dangerGrid1;

        Grid dangerGrid2 = new Grid(1, 0);
        dangerGrid2.setType(GridType.DANGEROUS);
        dangerGrid1.setSelected(true);
        testGrid[1][0] = dangerGrid2;

        Grid emptyGrid = new Grid(1, 1);
        emptyGrid.setType(GridType.EMPTY);
        emptyGrid.setSelected(true);
        testGrid[1][1] = emptyGrid;
    }

    
    private void makeUpTestGridUnselected(){
        /*
           all grid are not Selected
                1  2
            1   B  D
            2   D  E
         */

        testGrid = new Grid[2][2];

        Grid boomGrid = new Grid(0, 0);
        boomGrid.setType(GridType.BOOM);
        testGrid[0][0] = boomGrid;

        Grid dangerGrid1 = new Grid(0, 1);
        dangerGrid1.setType(GridType.DANGEROUS);
        testGrid[0][1] = dangerGrid1;

        Grid dangerGrid2 = new Grid(1, 0);
        dangerGrid2.setType(GridType.DANGEROUS);
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
    public void testFlipBoom() {
        makeUpTestGridUnselected();
        initGameForTest(2, testGrid);

        // test
        int flipReturn = controller.model.filpGrid(controller.model.grids, 0, 0);
        GameState expectedState = GameState.Fail;
        int expectedReturn = -3;
        assertSame(expectedState, controller.model.state);
        assertEquals(expectedReturn, flipReturn, 0.001);
    }

    @Test
    public void testFlipDanger() {
        makeUpTestGridUnselected();
        initGameForTest(2, testGrid);
        // test
        int flipReturn = controller.model.filpGrid(controller.model.grids, 0, 1);
        GameState expectedState = GameState.InGame;
        int expectedReturn = 1;
        assertSame(expectedState, controller.model.state);
        assertEquals(expectedReturn, flipReturn, 0.001);
    }

    @Test
    public void testFlipEmpty() {
        makeUpTestGridUnselected();
        initGameForTest(2, testGrid);
        // test
        int flipReturn = controller.model.filpGrid(controller.model.grids, 1, 1);
        GameState expectedState = GameState.InGame;
        int expectedReturn = 1;
        assertSame(expectedState, controller.model.state);
        assertEquals(expectedReturn, flipReturn, 0.001);
    }

    @Test
    public void testFilpSelected(){
        makeUpTestGridSelected();
        initGameForTest(2, testGrid);

        int flipReturn = controller.model.filpGrid(controller.model.grids, 0, 0);
        GameState expectedState = GameState.InGame;
        int expectedReturn = -2;
        assertSame(expectedState, controller.model.state);
        assertEquals(expectedReturn, flipReturn, 0.001);

        flipReturn = controller.model.filpGrid(controller.model.grids, 0, 1);
        assertSame(expectedState, controller.model.state);
        assertEquals(expectedReturn, flipReturn, 0.001);

        flipReturn = controller.model.filpGrid(controller.model.grids, 1, 1);
        assertSame(expectedState, controller.model.state);
        assertEquals(expectedReturn, flipReturn, 0.001);
    }
}
