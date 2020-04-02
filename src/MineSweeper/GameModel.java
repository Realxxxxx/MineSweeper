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
    public void initGame(){
    }

    //翻开gird之后的操作
    public int filpGrid(Grid[][] grids, int x, int y){
        Vector<Grid> gridQueue = new Vector<>();
        gridQueue.add(grids[x][y]);
        //refactor
        while (!gridQueue.isEmpty()){
            gridQueue.remove(0);
            if (x!=0 && !grids[x-1][y].isSelected()){
                gridQueue.add(grids[x-1][y]);
            }
            if (y!=0 && !grids[x][y-1].isSelected()){
                gridQueue.add(grids[x][y-1]);
            }
            if (x!=bounds-1 && !grids[x+1][y].isSelected()){
                gridQueue.add(grids[x+1][y]);
            }
            if (y!=bounds-1 && !grids[x][y+1].isSelected()){
                gridQueue.add(grids[x][y+1]);
            }
        }
        return 1;
    }

    //判断胜利
    public void isWin(){

    }

    //响应view消息改变Grid的属性
    public void changeGridAttr(){

    }


}
