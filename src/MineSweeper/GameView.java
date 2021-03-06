package MineSweeper;

import java.io.InputStream;
import java.util.Scanner;

/**
 * @author Zhao,Yang,Shen
 */
public class GameView {

	private boolean debug;

    // 用于控制输入
    private InputStream inStream = System.in;

    public void turnToDebug(){
        this.debug = true;
    }

    public void setInStream(InputStream inStream) {
        this.inStream = inStream;
    }

    public void drawGame(Grid[][] grids){
        // 列数
        int horizontalLength = grids[0].length;
        // 行数
        int verticalLength = grids.length;

        //最大行列数设定为20
        //没有翻开为 □ ，empty为■，BOOM为×，Flag为♠
        System.out.print(" ");
        // 输出头一行  个位数和十位数的打印不同
        int blankSpace = 8;
        if (horizontalLength <= blankSpace){
            for(int i=0; i < horizontalLength; i++){
                System.out.print("| ");
                System.out.print(i+1);
            }
        }else{
            for(int i=0; i<=blankSpace; i++){
                System.out.print("| ");
                System.out.print(i+1);
            }
            for(int i=9; i<horizontalLength; i++){
                System.out.print("|");
                System.out.print(i+1);
            }
        }
        System.out.print("\n");

        // 输出实际内容
        for(int y = 0; y < verticalLength; y++ ) {
            System.out.print(y + 1);
            if (y <= blankSpace) {
                System.out.print(" ");
            }
            for (int x = 0; x < horizontalLength; x++) {
                Grid griddraw = grids[x][y];
                if (griddraw.isFlag()) {
                    System.out.print(" F ");
                }
                else if (!griddraw.isSelected()) {
                    System.out.print(" ■ ");
                } else if (griddraw.getType() == GridType.EMPTY){
                    System.out.print(" □ ");
                } else if (griddraw.getType() == GridType.BOOM){
                    System.out.print(" × ");
                } else if (griddraw.getType() == GridType.DANGEROUS) {
                    System.out.print(" ");
                    System.out.print(griddraw.getBoomsAround());
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }
    }


    public int[] inputBoundAndBoom() {
    	int[] bb = new int[2];
    	//bb[0]为边界，bb[1]为雷数
        System.out.println("请输入棋盘大小和地雷数目");
    	Scanner scan = new Scanner(inStream);
    	try{
            while(true) {
                if (scan.hasNextLine()) {
                    String str = scan.nextLine();
                    str = str.trim();
                    // 去除前后空格
                    str = str.replaceAll("，", ",");
                    // 顺便处理中文逗号
                    String[] buff =  str.split("\\s+,|,\\s+|,|\\s+");
                    // 根据逗号或者空格分隔
                    if (buff.length != 2) {
                        bb = new int[]{-2, -2};
                        System.out.println("输入参数数量不对");
                        break;
                    }
                    bb[0] = Integer.parseInt(buff[0]);
                    bb[1] = Integer.parseInt(buff[1]);
                }
                if (bb[0] <= 0){
                    System.out.println("棋盘输入不合理，请重新输入");
                    bb[0] = -1;
                }
                if (bb[1] <= 0){
                    System.out.println("地雷数目不合理，请重新输入");
                    bb[1] = -1;
                }
                if(bb[1] >= bb[0] * bb[0] && bb[0] > 0 && bb[1] > 0) {
                    // too many booms
                    System.out.println("输入雷数过多，请重新输入");
                    bb[1] = -1;
                }
                break;
            }
        }catch (Exception e){
    	    // -2, -2代表输入格式错误
    	    bb = new int[]{-2, -2};
    	    System.out.println(e);
        }
        return bb;
    }


    public int[] inputCo(int bounds, Grid[][] grids) {
        int[] coordinate = new int[3];
        System.out.println("请输入选择的坐标与操作(1为揭开，2为插旗，如4,8,2表示坐标[4,8]处插旗)");
        Scanner scan = new Scanner(inStream);
        while(true) {
            if (scan.hasNextLine()) {
                String str = scan.nextLine();
                str = str.trim();
                // 去除前后空格
                str = str.replaceAll("，", ",");
                // 顺便处理中文逗号
                String[] buff = str.split("\\s+,|,\\s+|,|\\s+");
                if (buff.length != 3) {
                    System.out.println("输入参数数量不对"+buff.length);
                    if(debug) {
                    	coordinate = new int[]{-2,-2,-2};
                    	break;
                    }
                    else {
                        continue;
                    }
                }
                
                coordinate[0] = Integer.parseInt(buff[0]);
                // x
                coordinate[1] = Integer.parseInt(buff[1]);
                // y
                coordinate[2] = Integer.parseInt(buff[2]);
                // perform
            }
            

            if ((coordinate[2] != 1 && coordinate[2] != 2)) {
                System.out.println("操作参数只能为1和2");
                System.out.println("请重新输入");
                if(debug) {
                    coordinate[2] = -1;// 第三个值返回-1 代表操作参数出界
                }
                else {
                    continue;
                }
            }
            if (!(coordinate[0] <= bounds
                    && coordinate[0] >= 1
                    && coordinate[1] <= bounds
                    && coordinate[1] >= 1)) {
                System.out.println("输入坐标出界");
                System.out.println("请重新输入");
                if(debug) {
                    coordinate[0] = -1;
                    //coordinate[1] = -1;
                }
                else {
                    continue;
                }
            }
            // 此处的coor[0] coor[1]   ->  x+1 y+1
            else if (grids[coordinate[0]-1][coordinate[1]-1].isSelected()) {
                System.out.println("选中的格子已经打开");
                System.out.println("请重新输入");
                if(debug) {
                	coordinate[1] = -1;
                }
                else {
                    continue;
                }
            }
            break;
        }
        return coordinate;
    }


}
