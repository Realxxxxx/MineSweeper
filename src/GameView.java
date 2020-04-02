public class GameView {
    //根据grid的属性来绘制对应的格子
    public void drawGame(Grid grids[][]){
        //
        int x=0,y=0;
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
                if(griddraw.getType() == FLAG)
                    System.out.print(" ♠ ");
                else if(griddraw.isSelected() == false)
                    System.out.print(" □ ");
                else if(griddraw.getType() == EMPTY )
                    System.out.print(" ■ ");
                else if(griddraw.getType() == BOOM)
                    System.out.print(" × ");
                else if(griddraw.getType() == DANGEROUS){
                    System.out.print(" ");
                    System.out.print(griddraw.boomsAround);
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

}
