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
        int[] bb = controller.view.inputBoundsandBoom();
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
        int[] bb = controller.view.inputBoundsandBoom();
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
        int[] bb = controller.view.inputBoundsandBoom();
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
        int[] bb = controller.view.inputBoundsandBoom();
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
        int[] bb = controller.view.inputBoundsandBoom();
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
        int[] bb = controller.view.inputBoundsandBoom();
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
        int[] bb = controller.view.inputBoundsandBoom();
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
        int[] bb = controller.view.inputBoundsandBoom();
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
        int[] bb = controller.view.inputBoundsandBoom();
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
        int[] bb = controller.view.inputBoundsandBoom();
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
        int[] bb = controller.view.inputBoundsandBoom();
        int[] expected = new int[]{-2 ,-2};
        assertEquals(expected[0], bb[0], 0.001);
        assertEquals(expected[1], bb[1], 0.001);
    }


}
