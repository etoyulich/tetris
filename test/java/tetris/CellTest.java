package tetris;

import event.CellActionEvent;
import event.CellActionListener;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

    private int countClear = 0;
    private int countFill = 0;

    private class CellListener implements CellActionListener {

        @Override
        public void cellCleared(@NotNull CellActionEvent event, @NotNull Cell cell) {
            countClear++;
        }

        @Override
        public void cellFilled(@NotNull CellActionEvent event, @NotNull Cell cell) {
            countFill++;
        }
    }

    @BeforeEach
    public void testSetup() {
        countClear = 0;
        countFill = 0;
    }

    @Test
    public void test_setNeighbor_doubleSided() {
        Cell cell = new Cell(new Point(0, 0));
        cell.addCellActionListener(new CellListener());
        Cell neighborCell = new Cell(new Point(0, 1));
        neighborCell.addCellActionListener(new CellListener());
        Direction direction = Direction.up();

        cell.setNeighbor(direction, neighborCell);
        neighborCell.setNeighbor(direction.opposite(), cell);
        assertEquals(neighborCell, cell.neighbor(direction));
        assertEquals(cell, neighborCell.neighbor(direction.opposite()));

        int expCount = 0;
        assertEquals(expCount, countClear);
        assertEquals(expCount, countFill);
    }

    @Test
    public void test_setNeighbor() {
        Cell cell = new Cell(new Point(0, 0));
        cell.addCellActionListener(new CellListener());

        Cell neighborCell = new Cell(new Point(0, 1));
        neighborCell.addCellActionListener(new CellListener());

        Direction direction = Direction.up();

        cell.setNeighbor(direction, neighborCell);
        assertEquals(neighborCell, cell.neighbor(direction));
        assertEquals(cell, neighborCell.neighbor(direction.opposite()));

        int expCount = 0;
        assertEquals(expCount, countClear);
        assertEquals(expCount, countFill);
    }

    @Test
    public void test_isNeighbor_WhenNeighborCellExists() {
        Cell cell = new Cell(new Point(0, 0));
        cell.addCellActionListener(new CellListener());

        Cell neighborCell = new Cell(new Point(0, 1));
        neighborCell.addCellActionListener(new CellListener());

        Direction direction = Direction.up();

        cell.setNeighbor(direction, neighborCell);
        assertTrue(cell.isNeighbor(neighborCell));

        int expCount = 0;
        assertEquals(expCount, countClear);
        assertEquals(expCount, countFill);
    }

    @Test
    public void test_isNeighbor_WhenNeighborCellNotExists() {
        Cell cell = new Cell(new Point(-1, -1));
        cell.addCellActionListener(new CellListener());

        Cell neighborCell = new Cell(new Point(5, 5));
        neighborCell.addCellActionListener(new CellListener());

        assertFalse(cell.isNeighbor(neighborCell));


        int expCount = 0;
        assertEquals(expCount, countClear);
        assertEquals(expCount, countFill);
    }

    @Test
    public void test_neighbor_WhenCellHasNotNeighbor() {
        Cell cell = new Cell(new Point(0, 0));
        cell.addCellActionListener(new CellListener());

        int expCount = 0;
        assertEquals(expCount, countClear);
        assertEquals(expCount, countFill);

        assertNull(cell.neighbor(Direction.up()));
    }

    @Test
    public void test_neighbor_WhenCellHasNeighbor() {
        Cell cell = new Cell(new Point(0, 0));
        cell.addCellActionListener(new CellListener());

        Cell neighborCell = new Cell(new Point(0, 1));
        neighborCell.addCellActionListener(new CellListener());

        Direction direction = Direction.up();
        cell.setNeighbor(direction, neighborCell);

        assertEquals(neighborCell, cell.neighbor(direction));

        int expCount = 0;
        assertEquals(expCount, countClear);
        assertEquals(expCount, countFill);
    }

    @Test
    public void test_hasNeighbor_WhenCellHasNotNeighbor() {
        Cell cell = new Cell(new Point(0, 0));
        cell.addCellActionListener(new CellListener());

        int expCount = 0;
        assertEquals(expCount, countClear);
        assertEquals(expCount, countFill);
        assertFalse(cell.hasNeighbor(cell));
    }

    @Test
    public void test_hasNeighbor_WhenCellHasNeighbor() {
        Cell cell = new Cell(new Point(0, 0));
        cell.addCellActionListener(new CellListener());

        Cell neighborCell = new Cell(new Point(0, 1));
        neighborCell.addCellActionListener(new CellListener());

        Direction direction = Direction.up();
        cell.setNeighbor(direction, neighborCell);

        int expCount = 0;
        assertEquals(expCount, countClear);
        assertEquals(expCount, countFill);
        assertTrue(cell.hasNeighbor(neighborCell));
    }

    @Test
    public void test_setPiece_InEmptyCell() {
        Cell cell = new Cell(new Point(0, 0));
        cell.addCellActionListener(new CellListener());
        Piece piece = new Piece(Color.WHITE);

        cell.setPiece(piece);

        assertEquals(piece, cell.getPiece());
        assertEquals(cell, piece.getCell());

        int expFill = 1;
        int expClear = 0;
        assertEquals(expClear, countClear);
        assertEquals(expFill, countFill);
    }


    @Test
    public void test_setPiece_InFilledCell_DifferentPieceColor() {
        Cell cell = new Cell(new Point(0, 0));
        cell.addCellActionListener(new CellListener());
        Piece piece = new Piece(Color.WHITE);
        Piece newPiece = new Piece(Color.BLACK);

        cell.setPiece(piece);
        cell.setPiece(newPiece);

        assertEquals(piece, cell.getPiece());
        assertEquals(cell, piece.getCell());
        assertNull(newPiece.getCell());

        int expFill = 1;
        int expClear = 0;
        assertEquals(expClear, countClear);
        assertEquals(expFill, countFill);
    }

    @Test
    public void test_setPiece_InFilledCell_SamePieceColor() {
        Cell cell = new Cell(new Point(0, 0));
        cell.addCellActionListener(new CellListener());
        Piece piece = new Piece(Color.WHITE);
        Piece newPiece = new Piece(Color.WHITE);

        cell.setPiece(piece);
        cell.setPiece(newPiece);

        assertEquals(piece, cell.getPiece());
        assertEquals(cell, piece.getCell());
        assertNull(newPiece.getCell());

        int expFill = 1;
        int expClear = 0;
        assertEquals(expClear, countClear);
        assertEquals(expFill, countFill);
    }

    @Test
    public void test_clearCell_NotEmptyCell() {
        Cell cell = new Cell(new Point(0, 0));
        cell.addCellActionListener(new CellListener());
        Piece piece = new Piece(Color.WHITE);

        cell.setPiece(piece);
        cell.clearCell();

        assertNull(cell.getPiece());
        assertNull(piece.getCell());

        int expFill = 1;
        int expClear = 1;
        assertEquals(expClear, countClear);
        assertEquals(expFill, countFill);
    }

    @Test
    public void test_clearCell_EmptyCell() {
        Cell cell = new Cell(new Point(0, 0));
        cell.addCellActionListener(new CellListener());

        cell.clearCell();

        assertNull(cell.getPiece());

        int expCount = 0;
        assertEquals(expCount, countClear);
        assertEquals(expCount, countFill);
    }

}
