package MineSweeper;

import java.util.ArrayList;
import java.util.Vector;
import java.util.Random;

/**
 * @author Xiong,Shen
 */
public class GameModel {
    public int bounds;
    public Grid[][] grids;
    public GameState state = GameState.InGame;
    public Grid[][] getGrids() {
        return grids;
    }

    public void setGrids(Grid[][] grids) {
        this.grids = grids;
    }

    public int getBounds() {
        return bounds;
    }

    public void setBounds(int bounds) {
        this.bounds = bounds;
    }

    // 独立出来以供测试
    public Grid[][] generateMap(int bounds, int numOfBooms){
        Grid[][] grids = new Grid[bounds][bounds];
        GenerateEmptyMap(bounds, grids);
        Random rand = new Random();
        PutBoomsInMap(bounds, numOfBooms, grids, rand);
        SetBoomsAroundInMap(bounds, grids);
        return grids;

    }

    private Grid[][] SetBoomsAroundInMap(int bounds, Grid[][] grids) {
        for (int i = 0; i <bounds ; i++) {
            for (int j = 0; j <bounds ; j++) {
                if (grids[i][j].getType()== GridType.EMPTY) {
                    if (i > 0) {
                        if (grids[i - 1][j].getType() == GridType.BOOM) {
                            grids[i][j].setBoomsAround(grids[i][j].getBoomsAround() + 1);
                        }
                    }
                    if (i > 0 && j < bounds - 1) {
                        if (grids[i - 1][j + 1].getType() == GridType.BOOM) {
                            grids[i][j].setBoomsAround(grids[i][j].getBoomsAround() + 1);
                        }
                    }
                    if (i > 0 && j > 0) {
                        if (grids[i - 1][j - 1].getType() == GridType.BOOM) {
                            grids[i][j].setBoomsAround(grids[i][j].getBoomsAround() + 1);
                        }
                    }
                    if (j > 0) {
                        if (grids[i][j - 1].getType() == GridType.BOOM) {
                            grids[i][j].setBoomsAround(grids[i][j].getBoomsAround() + 1);
                        }
                    }
                    if (i < bounds - 1) {
                        if (grids[i + 1][j].getType() == GridType.BOOM) {
                            grids[i][j].setBoomsAround(grids[i][j].getBoomsAround() + 1);
                        }
                    }
                    if (i < bounds - 1 && j > 0) {
                        if (grids[i + 1][j - 1].getType() == GridType.BOOM) {
                            grids[i][j].setBoomsAround(grids[i][j].getBoomsAround() + 1);
                        }
                    }
                    if (i < bounds - 1 && j < bounds - 1) {
                        if (grids[i + 1][j+1].getType() == GridType.BOOM) {
                            grids[i][j].setBoomsAround(grids[i][j].getBoomsAround() + 1);
                        }
                    }
                    if (j < bounds - 1) {
                        if (grids[i][j + 1].getType() == GridType.BOOM) {
                            grids[i][j].setBoomsAround(grids[i][j].getBoomsAround() + 1);
                        }
                    }
                    if (grids[i][j].getBoomsAround()>0){
                        grids[i][j].setType(GridType.DANGEROUS);
                    }
                }
            }
        }
        return grids;
    }

    private void PutBoomsInMap(int bounds, int numOfBooms, Grid[][] grids, Random rand) {
        for (int i = 0; i < numOfBooms; i++) {
            int posX = rand.nextInt(bounds);
            int posY = rand.nextInt(bounds);
            while (true) {
                if (grids[posX][posY].getType() != GridType.BOOM) {
                    grids[posX][posY].setType(GridType.BOOM);
                    break;
                }
                posX = rand.nextInt(bounds);
                posY = rand.nextInt(bounds);
            }
        }
    }

    private void GenerateEmptyMap(int bounds, Grid[][] grids) {
        for (int i = 0; i <bounds ; i++) {
            for (int j = 0; j <bounds ; j++) {
                grids[i][j] = new Grid(i,j);
                grids[i][j].setType(GridType.EMPTY);
            }
        }
    }

    //初始化盘大小，地雷数目，grids
    public Grid[][] initGame(int bounds, int numOfBooms){

        this.bounds = bounds;
        // 生成地图
        this.grids = generateMap(bounds, numOfBooms);
        return grids;
    }

    //翻开gird之后的操作
    public void filpGrid(Grid[][] grids, int x, int y){

        if(grids[x][y].isFlag()){
            System.out.println("🚩不能被翻开");
            return;
        }
        if(grids[x][y].isSelected()){
            System.out.println("该格已经被翻开");
            return;
        }
        Vector<Grid> gridQueue = new Vector<>();
        gridQueue.add(grids[x][y]);
        // 1. 选中的格子翻开
        grids[x][y].setSelected(true);
        // 2. 检测翻开的格子是不是炸弹
        if (isBooming(grids, x, y))
        {
            // 是炸弹就直接修改游戏状态
            state = GameState.Fail;
            grids[x][y].setSelected(true);
            return;
        }
        //3. 处理未触雷
        //规则：上下左右的牌，1.已翻开的不加入队列 2.炸弹不加入队列 3.数字不加入队列 但是要翻开 4.旗子不加入队列
        while (!gridQueue.isEmpty()){
            Grid grid = gridQueue.remove(0);
            x = grid.getX();
            y = grid.getY();
            // 挑选出备选进入加入队列的格子
            ArrayList<Grid> trackGridsList = new ArrayList<>();
            if (x!=0) {
                trackGridsList.add(grids[x - 1][y]);
            }
            if (y!=0) {
                trackGridsList.add(grids[x][y - 1]);
            }
            if (x!=bounds-1) {
                trackGridsList.add(grids[x + 1][y]);
            }
            if (y!=bounds-1) {
                trackGridsList.add(grids[x][y + 1]);
            }
            for (Grid trackGrid: trackGridsList) {
                if (trackGrid.getType() != GridType.BOOM && !trackGrid.isSelected()) {
                    if (trackGrid.getType() == GridType.EMPTY) {
                        gridQueue.add(trackGrid);
                    }
                    trackGrid.setSelected(true);
                }
            }
            // 处理左边一格
//            System.out.println(gridQueue.size());
//            if (x!=0) {
//                if (grids[x-1][y].getType() == GridType.EMPTY) {
//                    gridQueue.add(grids[x - 1][y]);
//                    grids[x - 1][y].setSelected(true);
//                }else if (grids[x-1][y].getType() == GridType.DANGEROUS) {
//                    grids[x - 1][y].setSelected(true);
//                }
//            }

//            // 处理上边一格
//            if (y!=0){
//                if (grids[x][y-1].getType() == GridType.EMPTY) {
//                    gridQueue.add(grids[x][y-1]);
//                    grids[x][y-1].setSelected(true);
//                }else if (grids[x][y-1].getType() == GridType.DANGEROUS) {
//                    grids[x][y-1].setSelected(true);
//                }
//            }
//            // 处理右边一个
//            if (x!=bounds-1){
//                if (grids[x+1][y].getType() == GridType.EMPTY) {
//                    gridQueue.add(grids[x + 1][y]);
//                    grids[x + 1][y].setSelected(true);
//                }else if (grids[x+1][y].getType() == GridType.DANGEROUS) {
//                    grids[x + 1][y].setSelected(true);
//                }
//            }
//            // 处理上边一个
//            if (y!=bounds-1){
//                if (grids[x][y+1].getType() == GridType.EMPTY) {
//                    gridQueue.add(grids[x][y+1]);
//                    grids[x][y+1].setSelected(true);
//                }else if (grids[x][y+1].getType() == GridType.DANGEROUS) {
//                    grids[x][y+1].setSelected(true);
//                }
//            }
        }
    }

    public void isGameWin(){
        // 游戏已经失败则直接返回
        if (this.state == GameState.Fail){
            return;
        }
        // 判断游戏是否胜利
        for (int x = 0; x < grids.length; x++) {
            for (int y = 0; y < grids[0].length; y++) {
                // 只需存在BOOM+isflag 和 !BOOM+isSelected
                Grid g = grids[x][y];
                if ((g.getType() == GridType.BOOM && g.isFlag()) ||
                        g.getType() != GridType.BOOM && g.isSelected()) {
                    continue;
                } else {
                    this.state = GameState.InGame;
                    return;
                }
            }
        }
        // 全部通过之后
        this.state = GameState.Win;

    }

    public boolean isBooming(Grid[][] grids, int x, int y){
        if (grids[x][y].getType() == GridType.BOOM && grids[x][y].isSelected()) {
            return true;
        }
        return false;
    }

    /*
    *
    *
    * 判断胜利  见函数isGameWin和isBooming 想把该函数拆分为 检测触雷+检测胜利
    * 把判断游戏胜利的逻辑 提出到Controller里进行判断
    */
    public int isWin(Grid[][] grids, int x, int y){

        if (grids[x][y].getType() == GridType.BOOM && grids[x][y].isSelected()){
            return -1;
        }
        int flag = 0;
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids.length; j++) {
                if (grids[i][j].isSelected()==false && grids[i][j].getType()!=GridType.BOOM){
                    flag = 1;
                    break;
                }
            }
        }
        if (flag == 0){
            return 1;
        }
        this.grids = grids;

        return 0;
    }

    //响应view消息改变Grid的属性
    /*
        public void changeGridAttr(Grid[][] grids, int x, int y){
        if (grids[x][y].getType() == GridType.FLAG){
            System.out.println("该位置已经被插旗");
        }

        grids[x][y].setType(GridType.FLAG);
        this.grids = grids;
    }
     */

    // 插旗操作

    public int markGridFlag(Grid[][] grids, int x, int y){
        int result ;
        if (grids[x][y].isSelected()) {
            // 该格子已经被翻开
            System.out.println("该位置已经被打开");
            result = 1;
            // 插旗翻开的格子

        } else if (grids[x][y].isFlag()){
            System.out.println("该位置已经被插旗,即将拔旗");
            grids[x][y].setFlag(false);
            this.grids = grids;
            result = 2;
            // 拔旗
        } else { // 插旗未打开的格子
            grids[x][y].setFlag(true);
            this.grids = grids;
            result = 0;
            // 成功插旗
        }
        return result;
    }


}
