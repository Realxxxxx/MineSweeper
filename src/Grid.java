public class Grid {
    private int x;
    private int y;//格子的坐标
    private int boomsAround;//附近的额炸弹数
    private boolean isSelected;//是否被翻开
    private GridType type;//是空白格，被棋子插了，还是有炸弹，还是显示数字

    public Grid(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getBoomsAround() {
        return boomsAround;
    }

    public void setBoomsAround(int boomsAround) {
        this.boomsAround = boomsAround;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
    
    public GridType getType() {
        return type;
    }
}
