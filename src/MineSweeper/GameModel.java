package MineSweeper;

import java.util.Vector;
import java.util.Random;
public class GameModel {
    public int bounds;
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


    //初始化盘大小，地雷数目，grids
    public Grid[][] initGame(int bounds, int numOfBooms){
        Grid[][] grids = new Grid[bounds][bounds];

        // 生成地图
        for (int i = 0; i <bounds ; i++) {
            for (int j = 0; j <bounds ; j++) {
                grids[i][j] = new Grid(i,j);
                grids[i][j].setType(GridType.EMPTY);
            }
        }
        Random rand = new Random();

        for (int i = 0; i < numOfBooms; i++) {
            int posX = rand.nextInt(100)%bounds;
            int posY = rand.nextInt(100)%bounds;
                while (true) {
                if (grids[posX][posY].getType() != GridType.BOOM) {
                    grids[posX][posY].setType(GridType.BOOM);
                    break;
                }
                posX = rand.nextInt()%bounds;
                posY = rand.nextInt()%bounds;
            }
        }
        for (int i = 0; i <bounds ; i++) {
            for (int j = 0; j <bounds ; j++) {
                if (grids[i][j].getType()==GridType.EMPTY) {
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
                        if (grids[i + 1][+1].getType() == GridType.BOOM) {
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
        this.grids = grids;
        return  grids;
    }

    //翻开gird之后的操作
    public int filpGrid(Grid[][] grids, int x, int y){
        if(grids[x][y].getType() == GridType.FLAG){
            System.out.println("🚩不能被翻开");
            return 0;
        }
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
