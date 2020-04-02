import java.util.Scanner;

public class GameView {
    //根据grid的属性来绘制对应的格子
    public void drawGame(Grid grids[][]){
        //
    }
    //检测点击事件，传给controller
    public void getClicked(){

    }
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
