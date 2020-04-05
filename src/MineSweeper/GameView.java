package MineSweeper;

import java.io.InputStream;
import java.util.Scanner;

public class GameView {




    //根据grid的属性来绘制对应的格子


    // 用于控制输入
    private InputStream inStream = System.in;
    // setter ; use for test
    public void setInStream(InputStream inStream) {
        this.inStream = inStream;
    }

    public void drawGame(Grid[][] grids){
        //
        int x, y;
        //最大行列数设定为20
        //没有翻开为 □ ，empty为■，BOOM为×，Flag为♠ 

        System.out.print(" ");
        for( int i =0; i<=8;i++){
            System.out.print("| ");
            System.out.print(i+1);
        }
        for( int i =9; i<=19;i++){
            System.out.print("|");
            System.out.print(i+1);
        }

        System.out.print("\n");

        for(  y = 0; y <= 19; y++ ){

            System.out.print(y+1);
            if(y<9)
                System.out.print(" ");

            for( x = 0 ; x <= 19; x++){
                Grid griddraw = new Grid(x,y);
                if(griddraw.getType() == GridType.FLAG)
                    System.out.print(" ♠ ");
                else if(griddraw.isSelected() == false)
                    System.out.print(" □ ");
                else if(griddraw.getType() == GridType.EMPTY )
                    System.out.print(" ■ ");
                else if(griddraw.getType() == GridType.BOOM)
                    System.out.print(" × ");
                else if(griddraw.getType() == GridType.DANGEROUS){
                    System.out.print(" ");
                    System.out.print(griddraw.getBoomsAround());
                    System.out.print(" ");
                }

            }
            System.out.print("\n");
        }


    }

    //具体输入函数
    public int[] inputBoundsandBoom() {
    	int[] bb = new int[2];
    	//bb[0]为边界，bb[1]为雷数
        System.out.println("请输入棋盘大小和地雷数目");
    	Scanner scan = new Scanner(inStream);
    	try{
            while(true) {
                if (scan.hasNextLine()) {
                    String str = scan.nextLine();
                    str = str.trim(); // 去除前后空格
                    str = str.replaceAll("，", ",");  // 顺便处理中文逗号
                    String[] buff =  str.split("\\s+,|,\\s+|,|\\s+");// 根据逗号或者空格分隔
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
        scan.close();
        return bb;
    }
    

    public int[] inputCo(int bounds, Grid[][] grids) {
    	int[] coordinate = new int[3];
    	System.out.println("请输入选择的坐标与操作（1为揭开，2为插旗，如12,12,2表示坐标12,12处插旗）：");
    	Scanner scan = new Scanner(System.in);
    	while(true) {
            if (scan.hasNextLine()) {
                String str = scan.nextLine();
                String[] buff =  str.split(",|\\s");
                coordinate[0] = Integer.parseInt(buff[0]);
                coordinate[1] = Integer.parseInt(buff[1]);
                coordinate[2] = Integer.parseInt(buff[2]);
            }
            
            if((coordinate[2] == 1||coordinate[2] == 2)&&!grids[coordinate[0]][coordinate[1]].isSelected()
            		&&coordinate[0]<bounds && coordinate[0]>=0 && coordinate[1]<bounds && coordinate[1]>= 0 )
            	break;
            System.out.println("输入不合法，请重新输入");
    	}

        scan.close();
        return coordinate;
    }


}
