package MineSweeper;

// Test for the input of the game

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class InputTest {
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
    }
    // Muxu pass
    @Test
    public void testGameInputData1() throws  Exception{
        // 正常输入
        String inputStr = "10 20";
        InputStream is = new ByteArrayInputStream(inputStr.getBytes());
        controller.view.setInStream(is);
        int[] bb = controller.view.inputBoundAndBoom();
        int[] expected = new int[]{10 ,20};
        assertEquals(expected[0], bb[0], 0.001);
        assertEquals(expected[1], bb[1], 0.001);
    }
    // Muxv pass
    @Test
    public void testGameInputData2() throws Exception{
        // 雷数=格子数
        String inputStr = "10 100";
        InputStream is = new ByteArrayInputStream(inputStr.getBytes());
        controller.view.setInStream(is);
        int[] bb = controller.view.inputBoundAndBoom();
        int[] expected = new int[]{10 ,-1};
        assertEquals(expected[0], bb[0], 0.001);
        assertEquals(expected[1], bb[1], 0.001);
    }

    // Muxv pass
    @Test
    public void testGameInputData3() throws Exception{
        // 雷数=格子数-1
        String inputStr = "10 99";
        InputStream is = new ByteArrayInputStream(inputStr.getBytes());
        controller.view.setInStream(is);
        int[] bb = controller.view.inputBoundAndBoom();
        int[] expected = new int[]{10 ,99};
        assertEquals(expected[0], bb[0], 0.001);
        assertEquals(expected[1], bb[1], 0.001);
    }

    // Muxv pass
    @Test
    public void testGameInputData4() throws Exception{
        // 雷数=格子数+1
        String inputStr = "10 101";
        InputStream is = new ByteArrayInputStream(inputStr.getBytes());
        controller.view.setInStream(is);
        int[] bb = controller.view.inputBoundAndBoom();
        int[] expected = new int[]{10 ,-1};
        assertEquals(expected[0], bb[0], 0.001);
        assertEquals(expected[1], bb[1], 0.001);
    }

    // Muxv pass
    @Test
    public void testGameInValidInput1() throws Exception{
        // 格子数为 -100
        String inputStr = "-100 1";
        InputStream is = new ByteArrayInputStream(inputStr.getBytes());
        controller.view.setInStream(is);
        int[] bb = controller.view.inputBoundAndBoom();
        int[] expected = new int[]{-1 ,1};
        assertEquals(expected[0], bb[0], 0.001);
        assertEquals(expected[1], bb[1], 0.001);
    }
    // Muxv pass
    @Test
    public void testGameInValidInput2() throws Exception{
        // 地雷数为 -100
        String inputStr = "10 -100";
        InputStream is = new ByteArrayInputStream(inputStr.getBytes());
        controller.view.setInStream(is);
        int[] bb = controller.view.inputBoundAndBoom();
        int[] expected = new int[]{10 ,-1};
        assertEquals(expected[0], bb[0], 0.001);
        assertEquals(expected[1], bb[1], 0.001);
    }
    @Test
    public void testGameInValidInput3() throws Exception{
        // 格子数，地雷数均为 -100
        String inputStr = "-100 -100";
        InputStream is = new ByteArrayInputStream(inputStr.getBytes());
        controller.view.setInStream(is);
        int[] bb = controller.view.inputBoundAndBoom();
        int[] expected = new int[]{-1 ,-1};
        assertEquals(expected[0], bb[0], 0.001);
        assertEquals(expected[1], bb[1], 0.001);
    }

    @Test
    public void testGameValidFormat1() throws Exception{
        // 输入前后均有空格
        String inputStr = "  10 5  ";
        InputStream is = new ByteArrayInputStream(inputStr.getBytes());
        controller.view.setInStream(is);
        int[] bb = controller.view.inputBoundAndBoom();
        int[] expected = new int[]{10 ,5};
        assertEquals(expected[0], bb[0], 0.001);
        assertEquals(expected[1], bb[1], 0.001);
    }
    @Test
    public void testGameValidFormat2() throws Exception{
        // 逗号分隔
        String inputStr = "10, 5";
        InputStream is = new ByteArrayInputStream(inputStr.getBytes());
        controller.view.setInStream(is);
        int[] bb = controller.view.inputBoundAndBoom();
        int[] expected = new int[]{10 ,5};
        assertEquals(expected[0], bb[0], 0.001);
        assertEquals(expected[1], bb[1], 0.001);
    }

    @Test
    public void testGameInValidFormat1() throws Exception{
        // 单个输入
        String inputStr = "10";
        InputStream is = new ByteArrayInputStream(inputStr.getBytes());
        controller.view.setInStream(is);
        int[] bb = controller.view.inputBoundAndBoom();
        int[] expected = new int[]{-2 ,-2};
        assertEquals(expected[0], bb[0], 0.001);
        assertEquals(expected[1], bb[1], 0.001);
    }

    @Test
    public void testGameInValidFormat2() throws Exception{
        // 大于两个输入
        String inputStr = "10, 5, 20";
        InputStream is = new ByteArrayInputStream(inputStr.getBytes());
        controller.view.setInStream(is);
        int[] bb = controller.view.inputBoundAndBoom();
        int[] expected = new int[]{-2 ,-2};
        assertEquals(expected[0], bb[0], 0.001);
        assertEquals(expected[1], bb[1], 0.001);
    }
    
    @Test
    public void testInputCoValid1() throws Exception{
        // 正常输入(边界测试1) 1,1,1
        makeUpTestGridUnselected();
        initGameForTest(2, testGrid);
        String inputStr = "1,1,1";
        InputStream is = new ByteArrayInputStream(inputStr.getBytes());
        controller.view.setInStream(is);
        int[] coordinate = controller.view.inputCo(2, testGrid);
        int[] expected = new int[]{1,1,1};
        assertEquals(expected[0], coordinate[0], 0.001);
        assertEquals(expected[1], coordinate[1], 0.001);
        assertEquals(expected[2], coordinate[2], 0.001);
    }
    
    @Test
    public void testInputCoValid2() throws Exception{
        // 正常输入(等价类) 1,1,2
        makeUpTestGridUnselected();
        initGameForTest(2, testGrid);
        String inputStr = "1,1,2";
        InputStream is = new ByteArrayInputStream(inputStr.getBytes());
        controller.view.setInStream(is);
        int[] coordinate = controller.view.inputCo(2, testGrid);
        int[] expected = new int[]{1,1,2};
        assertEquals(expected[0], coordinate[0], 0.001);
        assertEquals(expected[1], coordinate[1], 0.001);
        assertEquals(expected[2], coordinate[2], 0.001);
    }
    
    @Test
    public void testInputCoValid3() throws Exception{
        // 正常输入(边界测试3) 1,2,1
        makeUpTestGridUnselected();
        initGameForTest(2, testGrid);
        String inputStr = "1,2,1";
        InputStream is = new ByteArrayInputStream(inputStr.getBytes());
        controller.view.setInStream(is);
        int[] coordinate = controller.view.inputCo(2, testGrid);
        int[] expected = new int[]{1,2,1};
        assertEquals(expected[0], coordinate[0], 0.001);
        assertEquals(expected[1], coordinate[1], 0.001);
        assertEquals(expected[2], coordinate[2], 0.001);
    }
    
    @Test
    public void testInputCoValid4() throws Exception{
        // 正常输入(边界测试4) 2,2,1
        makeUpTestGridUnselected();
        initGameForTest(2, testGrid);
        String inputStr = "2,2,1";
        InputStream is = new ByteArrayInputStream(inputStr.getBytes());
        controller.view.setInStream(is);
        int[] coordinate = controller.view.inputCo(2, testGrid);
        int[] expected = new int[]{2,2,1};
        assertEquals(expected[0], coordinate[0], 0.001);
        assertEquals(expected[1], coordinate[1], 0.001);
        assertEquals(expected[2], coordinate[2], 0.001);
    }
    
    @Test
    public void testInputCoValid5() throws Exception{
        // 正常输入(边界测试4) 2,1,1
        makeUpTestGridUnselected();
        initGameForTest(2, testGrid);
        String inputStr = "2,1,1";
        InputStream is = new ByteArrayInputStream(inputStr.getBytes());
        controller.view.setInStream(is);
        int[] coordinate = controller.view.inputCo(2, testGrid);
        int[] expected = new int[]{2,1,1};
        assertEquals(expected[0], coordinate[0], 0.001);
        assertEquals(expected[1], coordinate[1], 0.001);
        assertEquals(expected[2], coordinate[2], 0.001);
    }
    
    @Test
    public void testInputCoInValid1() throws Exception{
        //坐标不合理
        makeUpTestGridUnselected();
        initGameForTest(2, testGrid);
        this.controller.turnToDebug();
        String inputStr = "-2,-2,1";
        InputStream is = new ByteArrayInputStream(inputStr.getBytes());
        controller.view.setInStream(is);
        int[] coordinate = controller.view.inputCo(2, testGrid);
        int[] expected = new int[]{-1, -2, 1};
        assertEquals(expected[0], coordinate[0], 0.001);
        assertEquals(expected[1], coordinate[1], 0.001);
        assertEquals(expected[2], coordinate[2], 0.001);
    }
    
    @Test
    public void testInputCoInValid2() throws Exception{
        //模式不合理
        makeUpTestGridUnselected();
        initGameForTest(2, testGrid);
        this.controller.turnToDebug();
        String inputStr = "1,1,3";
        InputStream is = new ByteArrayInputStream(inputStr.getBytes());
        controller.view.setInStream(is);
        int[] coordinate = controller.view.inputCo(2, testGrid);
        int[] expected = new int[]{1,1,-1};
        assertEquals(expected[0], coordinate[0], 0.001);
        assertEquals(expected[1], coordinate[1], 0.001);
        assertEquals(expected[2], coordinate[2], 0.001);
    }
    
    
    @Test
    public void testInputCoInValid3() throws Exception{
        //坐标，模式合理时，格子不合理
        makeUpTestGridSelected();
        initGameForTest(2, testGrid);
        this.controller.turnToDebug();
        String inputStr = "1,1,1";
        InputStream is = new ByteArrayInputStream(inputStr.getBytes());
        controller.view.setInStream(is);
        int[] coordinate = controller.view.inputCo(2, testGrid);
        int[] expected = new int[]{1,-1,1};
        assertEquals(expected[0], coordinate[0], 0.001);
        assertEquals(expected[1], coordinate[1], 0.001);
        assertEquals(expected[2], coordinate[2], 0.001);
    }
    
    @Test
    public void testInputCoInValid4() throws Exception{
        //模式，坐标不合理（坐标不合理情况下不测格子是否合理，无意义）
        makeUpTestGridUnselected();
        initGameForTest(2, testGrid);
        this.controller.turnToDebug();
        String inputStr = "-2,-2,3";
        InputStream is = new ByteArrayInputStream(inputStr.getBytes());
        controller.view.setInStream(is);
        int[] coordinate = controller.view.inputCo(2, testGrid);
        int[] expected = new int[]{-1,-2,-1};
        assertEquals(expected[0], coordinate[0], 0.001);
        assertEquals(expected[1], coordinate[1], 0.001);
        assertEquals(expected[2], coordinate[2], 0.001);
    }

    @Test
    public void testInputCoInValid5() throws Exception{
        //模式，格子不合理，坐标合理
        makeUpTestGridSelected();
        initGameForTest(2, testGrid);
        this.controller.turnToDebug();
        String inputStr = "1,1,3";
        InputStream is = new ByteArrayInputStream(inputStr.getBytes());
        controller.view.setInStream(is);
        int[] coordinate = controller.view.inputCo(2, testGrid);
        int[] expected = new int[]{1,-1,-1};
        assertEquals(expected[0], coordinate[0], 0.001);
        assertEquals(expected[1], coordinate[1], 0.001);
        assertEquals(expected[2], coordinate[2], 0.001);
    }
    
    @Test
    public void testInputCoInValidFormat() throws Exception{
        //输入参数个数不对
        makeUpTestGridUnselected();
        initGameForTest(2, testGrid);
        this.controller.turnToDebug();
        String inputStr = "-2,-2";
        InputStream is = new ByteArrayInputStream(inputStr.getBytes());
        controller.view.setInStream(is);
        int[] coordinate = controller.view.inputCo(2, testGrid);
        int[] expected = new int[]{-2,-2,-2};
        assertEquals(expected[0], coordinate[0], 0.001);
        assertEquals(expected[1], coordinate[1], 0.001);
        assertEquals(expected[2], coordinate[2], 0.001);
    }
}
