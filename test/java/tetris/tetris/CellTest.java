package tetris;

import org.junit.jupiter.api.Test;
//import org.testng.annotations.Test;
//import org.junit.jupiter.api.Test;

import java.awt.*;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

    @Test
    public void test_setNeighbor_doubleSided() {
        Cell cell = new Cell(new Point(0, 0));
        Cell neighborCell = new Cell(new Point(0, 1));
        Direction direction = Direction.up();

        cell.setNeighbor(direction, neighborCell);
        neighborCell.setNeighbor(direction.opposite(), cell);
        assertEquals(neighborCell, cell.neighbor(direction));
        assertEquals(cell, neighborCell.neighbor(direction.opposite()));
    }

    @Test
    public void test_setNeighbor() {
        Cell cell = new Cell(new Point(0, 0));
        Cell neighborCell = new Cell(new Point(0, 1));
        Direction direction = Direction.up();

        cell.setNeighbor(direction, neighborCell);
        assertEquals(neighborCell, cell.neighbor(direction));
        assertEquals(cell, neighborCell.neighbor(direction.opposite()));
    }

    @Test
    public void test_isNeighbor_WhenNeighborCellExists() {
        Cell cell = new Cell(new Point(0, 0));
        Cell neighborCell = new Cell(new Point(0, 1));
        Direction direction = Direction.up();

        cell.setNeighbor(direction, neighborCell);
        assertTrue(cell.isNeighbor(neighborCell));
    }

    @Test
    public void test_isNeighbor_WhenNeighborCellNotExists() {
        Cell cell = new Cell(new Point(-1, -1));
        Cell neighborCell = new Cell(new Point(5, 5));

        assertFalse(cell.isNeighbor(neighborCell));
    }

    @Test
    public void test_neighbor_WhenCellHasNotNeighbor() {
        Cell cell = new Cell(new Point(0, 0));

        assertNull(cell.neighbor(Direction.up()));
    }

    @Test
    public void test_neighbor_WhenCellHasNeighbor() {
        Cell cell = new Cell(new Point(0, 0));
        Cell neighborCell = new Cell(new Point(0, 1));
        Direction direction = Direction.up();
        cell.setNeighbor(direction, neighborCell);

        assertEquals(neighborCell, cell.neighbor(direction));
    }

    @Test
    public void test_hasNeighbor_WhenCellHasNotNeighbor() {
        Cell cell = new Cell(new Point(0, 0));

        assertFalse(cell.hasNeighbor(cell));
    }

    @Test
    public void test_hasNeighbor_WhenCellHasNeighbor() {
        Cell cell = new Cell(new Point(0, 0));
        Cell neighborCell = new Cell(new Point(0, 1));
        Direction direction = Direction.up();
        cell.setNeighbor(direction, neighborCell);

        assertTrue(cell.hasNeighbor(neighborCell));
    }

    @Test
    public void test_setPiece_InEmptyCell() {
        Cell cell = new Cell(new Point(0, 0));
        Piece piece = new Piece(Color.WHITE);

        cell.setPiece(piece);

        assertEquals(piece, cell.getPiece());
        assertEquals(cell, piece.getCell());
    }


    @Test
    public void test_setPiece_InFilledCell() {
        Cell cell = new Cell(new Point(0, 0));
        Piece piece = new Piece(Color.WHITE);
        Piece newPiece = new Piece(Color.WHITE);

        cell.setPiece(piece);
        cell.setPiece(newPiece);

        assertEquals(piece, cell.getPiece());
        assertEquals(cell, piece.getCell());
        assertNull(newPiece.getCell());
    }

    @Test
    public void test_clearCell() {
        Cell cell = new Cell(new Point(0, 0));
        Piece piece = new Piece(Color.WHITE);

        cell.setPiece(piece);
        cell.clearCell();

        assertNull(cell.getPiece());
        assertNull(piece.getCell());
    }


}
