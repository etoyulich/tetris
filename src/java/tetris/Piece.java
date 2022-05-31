package tetris;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;


public class Piece {

    public Piece(Color color) {
        this.color = color;
    }

    void setCell(@NotNull Cell cell) {
        if (cell.isEmpty() || cell.getPiece() == this) {
            if (this.cell == null) {
                this.cell = cell;
                cell.setPiece(this);
            }
        }
    }

    private Cell cell;

    public Color getColor() {
        return color;
    }

    private final Color color;

    public void fillCell(@NotNull Cell cell) {
        if (cell.isEmpty()) {
            if (this.cell != null)
                this.freeCell();

            this.cell = cell;
            cell.setPiece(this);
        }
    }

    public void freeCell() {
        if (this.cell != null) {
            Cell tmp = this.cell;
            this.cell = null;
            tmp.clearCell();
        }
    }

    public boolean canFill(Cell cell) {
        return cell.isEmpty();
    }

    public Cell getCell() {
        return cell;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece piece)) return false;
        return Objects.equals(cell.getPosition(), piece.cell.getPosition()) && Objects.equals(color, piece.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cell, color);
    }
}
