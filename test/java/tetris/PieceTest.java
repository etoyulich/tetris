package tetris;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {

    private Cell cell;
    private Piece piece;

    @BeforeEach
    public void testSetup() {
        cell = new Cell(new Point(0, 0));
        piece = new Piece(Color.WHITE);
    }

    @Test
    public void test_canFill_PieceCanFillCell() {
        assertTrue(piece.canFill(cell));
    }

    @Test
    public void test_canFill_PieceCantFillCell() {
        Piece newPiece = new Piece(Color.WHITE);
        piece.fillCell(cell);
        assertFalse(newPiece.canFill(cell));
    }

    @Test
    public void test_fillCell_WhenCellIsEmpty() {
        piece.fillCell(cell);

        assertEquals(cell, piece.getCell());
        assertEquals(piece, cell.getPiece());
    }

    @Test
    public void test_fillCell_WhenCellNotEmpty() {
        Piece newPiece = new Piece(Color.WHITE);
        piece.fillCell(cell);
        newPiece.fillCell(cell);

        assertEquals(cell, piece.getCell());
        assertEquals(piece, cell.getPiece());
        assertNull(newPiece.getCell());
    }

    @Test
    public void test_setCell_WhenCellIsEmpty() {
        piece.setCell(cell);

        assertEquals(cell, piece.getCell());
        assertEquals(piece, cell.getPiece());
    }

    @Test
    public void test_setCell_WhenCellNotEmpty() {
        Piece newPiece = new Piece(Color.WHITE);
        piece.setCell(cell);
        newPiece.setCell(cell);

        assertEquals(cell, piece.getCell());
        assertEquals(piece, cell.getPiece());
        assertNull(newPiece.getCell());
    }

    @Test
    public void test_freeCell() {
        piece.setCell(cell);
        piece.freeCell();

        assertNull(cell.getPiece());
        assertNull(piece.getCell());
    }
}
