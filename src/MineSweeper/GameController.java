package MineSweeper;

public class GameController {
    //判断view传来的消息应该调用model的哪些方法
    private GameModel model;
    private GameView view;

    public GameController() {
        this.model = new GameModel();
        this.view = new GameView();
    }

    public void start(){
        int[] args = this.view.inputBoundsandBoom();
        Grid[][] grids = this.model.initGame(args[0],args[1]);
        this.view.drawGame(grids);
        while (true){
            int[] pos = this.view.inputCo(args[0]);
            int mode = this.view.inputFliporBoom();
            int isOver = 0;
            if (mode == 1) {
                isOver = this.model.filpGrid(grids, pos[0], pos[1]);
            }else{
                this.model.changeGridAttr(grids,pos[0],pos[1]);
            }
            if (isOver == -1){
                break;
            }
        }
    }

}
