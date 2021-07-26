package inventory.system.model;

public class RowColumnModel {
    public int row;
    public int column;

    public RowColumnModel(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public RowColumnModel() {
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
