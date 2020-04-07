package MineSweeper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.Random;

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
        Grid[][] grids = GenerateEmptyMap(bounds);
        PutBoomsInMap(bounds, numOfBooms, grids);
        SetBoomsAroundInMap(bounds, grids);
        return grids;

    }

    public int SetBoomsAroundInMap(int bounds, Grid[][] grids) {
        try {
            if (bounds<0||grids==null){
                return -1;
            }
            for (int i = 0; i < bounds; i++) {
                for (int j = 0; j < bounds; j++) {
                    if (grids[i][j].getType() == GridType.EMPTY) {
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
                            if (grids[i + 1][j + 1].getType() == GridType.BOOM) {
                                grids[i][j].setBoomsAround(grids[i][j].getBoomsAround() + 1);
                            }
                        }
                        if (j < bounds - 1) {
                            if (grids[i][j + 1].getType() == GridType.BOOM) {
                                grids[i][j].setBoomsAround(grids[i][j].getBoomsAround() + 1);
                            }
                        }
                        if (grids[i][j].getBoomsAround() > 0) {
                            grids[i][j].setType(GridType.DANGEROUS);
                        }
                    }
                }
            }
            return 0;
        }catch (Exception e){
            return -1;
        }

    }

    public int PutBoomsInMap(int bounds, int numOfBooms, Grid[][] grids) {
        try {
            if (numOfBooms<=0 || numOfBooms>=bounds*bounds || bounds>grids.length){
                return -1;
            }else {
                Random rand = new Random();
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
            return 0;
        }catch (Exception e){
            System.out.print(e);
            return -1;
        }

    }

    public Grid[][] GenerateEmptyMap(int bounds) {
        try {
            Grid[][] grids = new Grid[bounds][bounds];
            for (int i = 0; i < bounds; i++) {
                for (int j = 0; j < bounds; j++) {
                    grids[i][j] = new Grid(i, j);
                    grids[i][j].setType(GridType.EMPTY);
                }
            }
            return grids;
        }catch (Exception e){
            System.out.print(e);
            return null;
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
    public int filpGrid(Grid[][] grids, int x, int y){
        // return number
        /*
            -1 filp flag
            -2 flip Selected
            -3 flip Boom
            1  flip Successfully
         */

        if(grids[x][y].isFlag()){
            System.out.println("🚩不能被翻开");
            return -1;
        }
        if(grids[x][y].isSelected()){
            System.out.println("该格已经被翻开");
            return -2;
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
            return -3;
        }
        //3. 处理未触雷
        //规则：上下左右的牌，1.已翻开的不加入队列 2.炸弹不加入队列 3.数字不加入队列 但是要翻开 4.旗子不加入队列
        while (!gridQueue.isEmpty()) {
            Grid grid = gridQueue.remove(0);
            x = grid.getX();
            y = grid.getY();
            // 挑选出备选进入加入队列的格子
            ArrayList<Grid> trackGridsList = new ArrayList<>();
            if (x != 0)
                trackGridsList.add(grids[x - 1][y]);
            if (y != 0)
                trackGridsList.add(grids[x][y - 1]);
            if (x != bounds - 1)
                trackGridsList.add(grids[x + 1][y]);
            if (y != bounds - 1)
                trackGridsList.add(grids[x][y + 1]);
            for (Grid trackGrid : trackGridsList) {
                if (trackGrid.getType() != GridType.BOOM && !trackGrid.isSelected()) {
                    gridQueue.add(trackGrid);
                    trackGrid.setSelected(true);
                }
            }
        }
        return 1;
    }

    public void isGameWin(){
        // 游戏已经失败则直接返回
        if (this.state == GameState.Fail){
            return;
        }
        // 判断游戏是否胜利
        for (int x = 0; x < grids.length; x++)
            for (int y = 0; y < grids[0].length; y++){
                // 只需存在BOOM+isflag 和 !BOOM+isSelected
                Grid g = grids[x][y];
                if ((g.getType() == GridType.BOOM && g.isFlag()) ||
                     g.getType() != GridType.BOOM && g.isSelected()){
                    continue;
                }
                else {
                    this.state = GameState.InGame;
                    return;
                }
            }
        // 全部通过之后
        this.state = GameState.Win;

    }

    public boolean isBooming(Grid[][] grids, int x, int y){
        if (grids[x][y].getType() == GridType.BOOM && grids[x][y].isSelected())
            return true;
        return false;
    }

    //判断胜利  见函数isGameWin和isBooming 想把该函数拆分为 检测触雷+检测胜利
    // 把判断游戏胜利的逻辑 提出到Controller里进行判断
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

    // 插旗操作
    public int markGridFlag(Grid[][] grids, int x, int y){
        int result ;
        if (grids[x][y].isSelected()) { // 该格子已经被翻开
            System.out.println("该位置已经被打开");
            result = 1; // 插旗翻开的格子

        } else if (grids[x][y].isFlag()){
            System.out.println("该位置已经被插旗,即将拔旗");
            grids[x][y].setFlag(false);
            this.grids = grids;
            result = 2;// 拔旗
        } else { // 插旗未打开的格子
            grids[x][y].setFlag(true);
            this.grids = grids;
            result = 0;// 成功插旗
        }
        return result;
    }


}
