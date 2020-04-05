package MineSweeper;

import java.io.InputStream;

public class GameController {
    //判断view传来的消息应该调用model的哪些方法
    public GameModel model;
    public GameView view;

    public GameController() {
        this.model = new GameModel();
        this.view = new GameView();
    }


    public void start(){
        int[] args = this.view.inputBoundsandBoom();
        this.model.initGame(args[0], args[1]);

        this.view.drawGame(this.model.grids);

        // TO DO
        // 1. first Filp
        // 2. while(1) { Filp }

//        while (true){
//            int[] pos = this.view.inputCo(args[0], this.model.grids);
//            int isOver = 0;
//            if (pos[2] == 1) {
//                isOver = this.model.filpGrid(this.model.grids, pos[0], pos[1]);
//            }else{
//                this.model.changeGridAttr(this.model.grids, pos[0], pos[1]);
//            }
//            if (isOver == -1){
//                break;
//            }
//        }
    }

}
