package tetris;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Glass {

    // ---------------------- Размеры -----------------------------

    private final int width;
    private final int height;
    private static final int cellsForShape = 4;


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    // --------------------------- Ячейки ----------------------
    private final HashMap<Point, Cell> cells = new HashMap<>();

    public Cell cell(Point pos) {
        return cells.get(pos);
    }

    public Cell cell(int x, int y) {
        return cell(new Point(x, y));
    }

    // ---------------------------- Порождение ---------------------

    public Glass(int height, int width) {
        if (height < 1 || width < 1) {
            throw new IllegalArgumentException("Высота стакана не может быть меньше 20, а ширина не может быть меньше 10");
        }
        this.height = height;
        this.width = width;
        buildField();
    }

    private void buildField() {

        // Создаем ячейки
        for (int y = 0; y < getHeight() + cellsForShape; y++) {
            for (int x = 0; x < getWidth(); x++) {
                Point pos = new Point(x, y);
                cells.put(pos, new Cell(pos));
            }
        }

        // Связываем ячейки
        for (int y = 0; y < getHeight() + cellsForShape; y++) {
            for (int x = 0; x < getWidth(); x++) {

                Cell cell = cell(x, y);

                if (getHeight() > 1 && y < getHeight() - 1 + cellsForShape) {
                    cell.setNeighbor(Direction.up(), cell(x, y + 1));
                }
                if (y > 0) {
                    cell.setNeighbor(Direction.down(), cell(x, y - 1));
                }
                if (getWidth() > 1 && x < getWidth() - 1) {
                    cell.setNeighbor(Direction.right(), cell(x + 1, y));
                }
                if (x > 0) {
                    cell.setNeighbor(Direction.left(), cell(x - 1, y));
                }
            }
        }
    }

    //------------------------------------------------------------------------

    int getFilledRows() {
        List<Integer> filledRows = new ArrayList<>();
        for (int col = 0; col < getHeight(); col++) {
            boolean isFilled = true;
            for (int row = 0; row < getWidth(); row++) {
                isFilled = isFilled && !this.cell(row, col).isEmpty();
            }
            if (isFilled)
                filledRows.add(col);
        }
        return filledRows.size();
    }

    private List<Cell> getRow(int row) {
        if (row < getHeight() && row >= 0) {
            List<Cell> rowOfCells = new ArrayList<>();
            for (int i = 0; i < getWidth(); i++) {
                rowOfCells.add(cell(i, row));
            }
            return rowOfCells;
        } else {
            throw new IllegalArgumentException("Такого ряда не существует");
        }
    }

    void moveRows() {
        int filledRows = getFilledRows();
        if (filledRows != 0) {
            for (int y = getHeight() - 1; y >= 0; y--) {
                if(isRowFilled(y)){
                    clearRow(y);
                    shiftRows(y);
                }
            }
        }
    }

    private void shiftRows(int rowIndex){
        for (int y = rowIndex; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if (!cell(x, y + 1).isEmpty()) {
                    cell(x, y + 1).getPiece().fillCell(cell(x, y));
                }
            }
        }
    }

    private void clearRow(int y){
        List<Cell> cells = getRow(y);
        for (Cell cell : cells) {
            cell.clearCell();
        }
    }

    private boolean isRowFilled(int row) {
        List<Cell> cells = getRow(row);
        boolean isRowFilled = true;
        for (Cell cell : cells) {
            isRowFilled = isRowFilled && !cell.isEmpty();
        }
        return isRowFilled;
    }

    public boolean containsCoordinate(Point coordinate){
        return !(coordinate.getX() >= width) && !(coordinate.getX() < 0) && !(coordinate.getY() >= height + cellsForShape) && !(coordinate.getY() < 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Glass glass)) return false;
        return width == glass.width && height == glass.height && Objects.equals(cells, glass.cells);
    }

}