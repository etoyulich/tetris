package tetris;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Cell {

    public Point getPosition() {
        return position;
    }

    private final Point position;

    public Piece getPiece() {
        return piece;
    }

    private Piece piece;


    void setPiece(Piece piece) {
        if(this.piece == null){
            piece.setCell(this);
            this.piece = piece;
        }
    }

    public void clearCell(){
        if(piece != null){
            Piece tmp = piece;
            this.piece = null;
            tmp.freeCell();
        }
    }

    // ---------------------- Соседи -----------------------
    private final Map<Direction, Cell> _neighbors = new HashMap<>();

    public Cell(Point position) {
        this.position = position;
    }

    void setNeighbor(Direction direct, Cell neighbor) {
        if(neighbor != this && !isNeighbor(neighbor)) {
            _neighbors.put(direct, neighbor);
            neighbor.setNeighbor(direct.opposite(), this);
        }
    }

    public Cell neighbor(Direction dir){
        if(_neighbors.containsKey(dir)) {
            return _neighbors.get(dir);
        }
        return null;
    }

    public boolean isNeighbor(Cell other) {
        return _neighbors.containsValue(other);
    }

    public boolean hasNeighbor(Cell cell){
        return _neighbors.containsValue(cell);
    }

    public boolean isEmpty(){
        return this.piece == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell cell)) return false;
        return Objects.equals(position, cell.position) && Objects.equals(piece, cell.piece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, piece, _neighbors);
    }
}
