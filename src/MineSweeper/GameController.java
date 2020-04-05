package MineSweeper;

public class GameController {
    //判断view传来的消息应该调用model的哪些方法
    public GameModel model;
    public GameView view;

    public GameController() {
        this.model = new GameModel();
        this.view = new GameView();
    }


    public void start(){
        int[] args = this.view.inputBoundAndBoom();
        this.model.initGame(args[0], args[1]);
        this.view.drawGame(this.model.grids);
        // TO DO
        // 1. first Filp and map generate
        // 2. while(1) { Filp }
        while (true){
            int[] pos = this.view.inputCo(args[0], this.model.grids);
            // 玩家给出的x,y 实际上为网格中的y-1,x-1
            int isOver = 0;
//            System.out.println(pos[2]);
            if (pos[2] == 1) { // 翻开操作
                this.model.filpGrid(this.model.grids, pos[1]-1, pos[0]-1);
            }else if(pos[2] == 2){  // 插旗操作
                int flagResult = this.model.markGridFlag(this.model.grids, pos[1]-1, pos[0]-1);
            }

            this.model.isGameWin();

            this.view.drawGame(this.model.grids);
            if (this.model.state == GameState.Win){ // 游戏结束
                System.out.println("恭喜你，游戏胜利了");
                break;
            }
            else if (this.model.state == GameState.Fail){
                System.out.println("真可惜，游戏失败了");
                break;
            }
        }
    }

}
