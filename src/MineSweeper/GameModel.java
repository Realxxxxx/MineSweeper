package MineSweeper;

import java.util.Vector;

public class GameModel {
    public int bounds;
    public int numOfBooms;
    public Grid[][] grids;

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

    public void generateMap(Grid[][] grids, int numOfBooms, int safeX, int safeY){
        // safeRow, safeY为第一次点击的格子位置，必然不是地雷
    }

    //初始化盘大小，地雷数目，grids
    public void initGame(int bounds, int numOfBooms){
        this.bounds = bounds;
        this.numOfBooms = numOfBooms;
        this.grids = new Grid[bounds][bounds];
        /*
        int count = 0;
        //显然错的生成方式

        for (int i = 0; i <bounds ; i++) {
            for (int j = 0; j <bounds ; j++) {
                grids[i][j] = new Grid(i,j);
                count += 1;
                if (count<numOfBooms){
                    grids[i][j].setType(GridType.BOOM);
                }else{
                    grids[i][j].setType(GridType.EMPTY);
                }
            }
        }
         */
    }

    public int firstFlipGrid(Grid[][] grids, int x, int y){
        // 第一次点击grid后实时生成Map
        this.generateMap(grids, this.numOfBooms, x, y);
        /*
        调用 filpGrid
         */
        return 1;
    }

    //翻开grid之后的操作
    public int filpGrid(Grid[][] grids, int x, int y){
        Vector<Grid> gridQueue = new Vector<>();
        gridQueue.add(grids[x][y]);
        grids[x][y].setSelected(true);
        int winState = isWin(grids,x,y);
        if (winState==-1){
            return -1;
        }else if (winState==1){
            return 1;
        }
        //规则：上下左右的牌，1.已翻开的不加入队列 2.炸弹不加入队列 3.数字不加入队列 但是要翻开 4.棋子不加入队列
        while (!gridQueue.isEmpty()){
            gridQueue.remove(0);
            if (x!=0) {
                if (grids[x-1][y].getType() == GridType.EMPTY) {
                    gridQueue.add(grids[x - 1][y]);
                    grids[x - 1][y].setSelected(true);
                }else if (grids[x-1][y].getType() == GridType.DANGEROUS) {
                    grids[x - 1][y].setSelected(true);
                }
            }
            if (y!=0){
                if (grids[x][y-1].getType() == GridType.EMPTY) {
                    gridQueue.add(grids[x][y-1]);
                    grids[x][y-1].setSelected(true);
                }else if (grids[x][y-1].getType() == GridType.DANGEROUS) {
                    grids[x][y-1].setSelected(true);
                }
            }
            if (x!=bounds-1){
                if (grids[x+1][y].getType() == GridType.EMPTY) {
                    gridQueue.add(grids[x + 1][y]);
                    grids[x + 1][y].setSelected(true);
                }else if (grids[x+1][y].getType() == GridType.DANGEROUS) {
                    grids[x + 1][y].setSelected(true);
                }
            }
            if (y!=bounds-1){
                if (grids[x][y+1].getType() == GridType.EMPTY) {
                    gridQueue.add(grids[x][y+1]);
                    grids[x][y+1].setSelected(true);
                }else if (grids[x][y+1].getType() == GridType.DANGEROUS) {
                    grids[x][y+1].setSelected(true);
                }
            }
        }
        return 1;
    }

    //判断胜利
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


        return 0;
    }

    //响应view消息改变Grid的属性
    public void changeGridAttr(Grid[][] grids, int x, int y){
        grids[x][y].setType(GridType.FLAG);
    }


}
