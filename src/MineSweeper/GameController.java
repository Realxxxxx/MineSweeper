package MineSweeper;

public class GameController {
    //判断view传来的消息应该调用model的哪些方法
    public GameModel model;
    public GameView view;
    private boolean debug = false;

    public GameController() {
        this.model = new GameModel();
        this.view = new GameView();
    }


    public void start(){

        int[] args = {-1, -1};
        while (args[0] < 0 || args[1] < 0){
            args = this.view.inputBoundAndBoom();
        }
        this.model.initGame(args[0], args[1]);
        this.view.drawGame(this.model.grids);

        while (true){

            // 1.获取玩家操作指令
            int[] pos = this.view.inputCo(args[0], this.model.grids);
            // 玩家给出的x,y 实际上为网格中的y-1,x-1
//            int isOver = 0;
//            System.out.println(pos[2]);
            // 2.执行玩家操作指令
            if (pos[2] == 1) { // 翻开操作
                int flipResult = this.model.filpGrid(this.model.grids, pos[1]-1, pos[0]-1);
            }else if(pos[2] == 2){  // 插旗操作
                int flagResult = this.model.markGridFlag(this.model.grids, pos[1]-1, pos[0]-1);
            }
            // 3. 判断游戏是否结束
            if (!debug) {
                // debug则游戏不结束
                this.model.isGameWin();
            }

            // 4. 绘制游戏
            this.view.drawGame(this.model.grids);
            // 5. 是否跳出主循环
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
