package MineSweeper;

import java.util.Scanner;

public class GameView {
    //根据grid的属性来绘制对应的格子
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

        for(y = 0; y <= 19; y++){

            System.out.print(y+1);
            if(y<9)
                System.out.print(" ");

            for( x = 0 ; x <= 19; x++){
                Grid griddraw = new Grid(x,y);
                if(griddraw.getType() == GridType.FLAG)
                    System.out.print(" ♠ ");
                else if(!griddraw.isSelected())
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
    //检测输入事件，传给controller
    public void getClicked(){

        GameController controller = new GameController();
        controller.sendToModel();

    }
    //具体输入函数
    public static int[] inputBoundsandBoom(String message) {
    	int[] bb = new int[2];
    	//bb[0]为边界，bb[1]为雷数
    	System.out.println(message);
    	Scanner scan = new Scanner(System.in);
    	while(true) {
            if (scan.hasNextLine()) {
                String str = scan.nextLine();
                String[] buff =  str.split(",|\\s");
                bb[0] = Integer.parseInt(buff[0]);
                bb[1] = Integer.parseInt(buff[1]);
            }
            if(bb[1]<bb[0]*bb[0])
            	break;
            System.out.println("输入不合法，请重新输入");
    	}

        scan.close();
        return bb;
    }
    
    public static int[] inputCo(String message, int bounds) {
    	int[] coordinate = new int[2];
    	System.out.println(message);
    	Scanner scan = new Scanner(System.in);
    	while(true) {
            if (scan.hasNextLine()) {
                String str = scan.nextLine();
                String[] buff =  str.split(",|\\s");
                coordinate[0] = Integer.parseInt(buff[0]);
                coordinate[1] = Integer.parseInt(buff[1]);
            }
            if(coordinate[0]<bounds && coordinate[0]>=0 && coordinate[1]<bounds && coordinate[1]>= 0)
            	break;
            System.out.println("输入不合法，请重新输入");
    	}

        scan.close();
        return coordinate;
    }
    
    public static int inputFliporBoom(String message) {
    	//mode=1为揭开格子，mode=2为插旗
    	int mode = 0;
    	System.out.println(message);
    	Scanner scan = new Scanner(System.in);
    	while(true) {
            if (scan.hasNextLine()) {
                String str = scan.nextLine();
                mode = Integer.parseInt(str);
            }
            if(mode==1||mode==2)
            	break;
            System.out.println("输入不合法，请重新输入");
    	}

        scan.close();
        return mode;
    }


}
