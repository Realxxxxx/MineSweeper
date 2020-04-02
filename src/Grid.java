public class Grid {
    private int x;
    private int y;
    private int boomsAround;
    private boolean isSelected;
    private GridType type;

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
}
