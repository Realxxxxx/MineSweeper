package MineSweeper;

import java.util.Vector;

public class GameModel {
    private int bounds;
    private Grid[][] grids;

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

    //初始化盘大小，地雷数目，grids
    public Grid[][] initGame(int bounds,int numOfBooms){
        Grid[][] grids = new Grid[bounds][bounds];


        return  grids;
    }

    //翻开gird之后的操作
    public int filpGrid(Grid[][] grids, int x, int y){
        Vector<Grid> gridQueue = new Vector<>();
        gridQueue.add(grids[x][y]);
        grids[x][y].setSelected(true);
        if (isWin(grids,x,y)){
            return -1;
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
    public boolean isWin(Grid[][] grids, int x, int y){
        if (grids[x][y].getType() == GridType.BOOM && grids[x][y].isSelected()){
            return false;
        }
        return true;
    }

    //响应view消息改变Grid的属性
    public void changeGridAttr(Grid[][] grids, int x, int y){
        grids[x][y].setType(GridType.FLAG);
    }


}
