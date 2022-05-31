package tetris;

import event.CellActionEvent;
import event.CellActionListener;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlassTest {

    private Glass glass;
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
        glass = new Glass(2, 2);
        countClear = 0;
        countFill = 0;
    }

    private void addListener(){
        for (int y = 0; y < glass.getHeight(); y++) {
            for (int x = 0; x < glass.getWidth(); x++) {
                glass.cell(x, y).addCellActionListener(new CellListener());
            }
        }
    }

    @Test
    public void test_create_withCorrectParams() {
        addListener();
        Cell cell_0_0 = glass.cell(new Point(0, 0));
        Cell cell_0_1 = glass.cell(new Point(1, 0));
        Cell cell_1_0 = glass.cell(new Point(0, 1));
        Cell cell_1_1 = glass.cell(new Point(1, 1));

        assertTrue(cell_0_0.isNeighbor(cell_1_0));
        assertTrue(cell_0_1.isNeighbor(cell_1_1));
        assertTrue(cell_1_1.isNeighbor(cell_0_1));
        assertTrue(cell_1_0.isNeighbor(cell_0_0));
        assertTrue(cell_0_0.isNeighbor(cell_0_1));
        assertTrue(cell_1_0.isNeighbor(cell_1_1));
        assertTrue(cell_0_1.isNeighbor(cell_0_0));
        assertTrue(cell_1_1.isNeighbor(cell_1_0));

        int expCount = 0;
        assertEquals(expCount, countClear);
        assertEquals(expCount, countFill);
    }

    @Test
    public void test_create_withNegativeWidth() {
        assertThrows(IllegalArgumentException.class, () -> new Glass(0, -1));
    }

    @Test
    public void test_create_withZeroWidth() {
        assertThrows(IllegalArgumentException.class, () -> new Glass(1, 0));
    }

    @Test
    public void test_create_withNegativeHeight() {
        assertThrows(IllegalArgumentException.class, () -> new Glass(-1, 1));
    }

    @Test
    public void test_create_withZeroHeight() {
        assertThrows(IllegalArgumentException.class, () -> new Glass(0, 1));
    }

    @Test
    public void test_getFilledRows_NoFilledRows() {
        addListener();
        Glass expGlass = new Glass(2, 2);
        int real = glass.getFilledRows();
        int expected = 0;
        int expCount = 0;

        assertEquals(expected, real);
        assertEquals(expGlass, glass);
        assertEquals(expCount, countClear);
        assertEquals(expCount, countFill);
    }

    @Test
    public void test_getFilledRows_SeveralFilledCellsNoRows() {
        Glass expGlass = new Glass(4, 4);
        glass = new Glass(4, 4);
        addListener();

        glass.cell(0, 0).setPiece(new Piece(Color.WHITE));
        glass.cell(0, 2).setPiece(new Piece(Color.BLACK));
        glass.cell(1, 1).setPiece(new Piece(Color.YELLOW));

        expGlass.cell(0, 0).setPiece(new Piece(Color.WHITE));
        expGlass.cell(0, 2).setPiece(new Piece(Color.BLACK));
        expGlass.cell(1, 1).setPiece(new Piece(Color.YELLOW));

        int real = glass.getFilledRows();
        int expected = 0;
        int expClear = 0;
        int expFill = 3;

        assertEquals(expected, real);
        assertEquals(expGlass, glass);

        assertEquals(expClear, countClear);
        assertEquals(expFill, countFill);
    }

    @Test
    public void test_getFilledRows_OneFilledRow() {
        addListener();
        Glass expGlass = new Glass(2, 2);
        for (int i = 0; i < glass.getWidth(); i++) {
            glass.cell(i, 0).setPiece(new Piece(Color.WHITE));
            expGlass.cell(i, 0).setPiece(new Piece(Color.WHITE));
        }

        int real = glass.getFilledRows();
        int expected = 1;
        int expClear = 0;
        int expFill = 2;

        assertEquals(expected, real);
        assertEquals(expGlass, glass);
        assertEquals(expClear, countClear);
        assertEquals(expFill, countFill);
    }

    @Test
    public void test_getFilledRows_SeveralFilledRows() {
        Glass expGlass = new Glass(4, 4);
        glass = new Glass(4, 4);
        addListener();
        glass.cell(3, 3).setPiece(new Piece(Color.WHITE));
        expGlass.cell(3,3).setPiece(new Piece(Color.WHITE));

        for (int y = 0; y < 3; y+=2) {
            for (int x = 0; x < glass.getWidth(); x++) {
                glass.cell(x, y).setPiece(new Piece(Color.WHITE));
                expGlass.cell(x, y).setPiece(new Piece(Color.WHITE));
            }
        }

        int real = glass.getFilledRows();
        int expected = 2;
        int expClear = 0;
        int expFill = 9;

        assertEquals(expected, real);
        assertEquals(expGlass, glass);
        assertEquals(expClear, countClear);
        assertEquals(expFill, countFill);
    }

    @Test
    public void test_moveRows_rowsMoved_OneFilledRow() {
        Glass expGlass = new Glass(4, 4);
        glass = new Glass(4, 4);
        addListener();

        glass.cell(2, 1).setPiece(new Piece(Color.WHITE));
        glass.cell(2, 2).setPiece(new Piece(Color.WHITE));

        expGlass.cell(2, 0).setPiece(new Piece(Color.WHITE));
        expGlass.cell(2, 1).setPiece(new Piece(Color.WHITE));

        for (int x = 0; x < glass.getWidth(); x++) {
            glass.cell(x, 0).setPiece(new Piece(Color.WHITE));
        }

        glass.moveRows();
        int expClear = 6;
        int expFill = 8;

        assertEquals(expGlass, glass);
        assertEquals(expClear, countClear);
        assertEquals(expFill, countFill);
    }

    @Test
    public void test_moveRows_severalRowsMoved() {
        Glass expGlass = new Glass(4, 4);
        expGlass.cell(1, 0).setPiece(new Piece(Color.WHITE));
        expGlass.cell(2, 0).setPiece(new Piece(Color.BLACK));
        expGlass.cell(2, 1).setPiece(new Piece(Color.WHITE));

        glass = new Glass(4, 4);
        addListener();
        glass.cell(1, 2).setPiece(new Piece(Color.WHITE));
        glass.cell(2, 2).setPiece(new Piece(Color.BLACK));
        glass.cell(2, 3).setPiece(new Piece(Color.WHITE));

        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < glass.getWidth(); x++) {
                glass.cell(x, y).setPiece(new Piece(Color.WHITE));
            }
        }

        glass.moveRows();
        int expClear = 14;
        int expFill = 17;

        assertEquals(expGlass, glass);
        assertEquals(expClear, countClear);
        assertEquals(expFill, countFill);
    }

    @Test
    public void test_moveRows_rowsNotMoved() {
        glass = new Glass(4, 4);
        addListener();
        glass.cell(1, 1).setPiece(new Piece(Color.WHITE));
        glass.cell(2, 1).setPiece(new Piece(Color.BLACK));
        glass.cell(0, 2).setPiece(new Piece(Color.BLUE));
        glass.cell(2, 2).setPiece(new Piece(Color.YELLOW));
        glass.cell(3, 2).setPiece(new Piece(Color.CYAN));
        glass.cell(2, 3).setPiece(new Piece(Color.GRAY));
        glass.cell(2, 0).setPiece(new Piece(Color.WHITE));

        Glass expGlass = new Glass(4, 4);

        expGlass.cell(1, 1).setPiece(new Piece(Color.WHITE));
        expGlass.cell(2, 1).setPiece(new Piece(Color.BLACK));
        expGlass.cell(0, 2).setPiece(new Piece(Color.BLUE));
        expGlass.cell(2, 2).setPiece(new Piece(Color.YELLOW));
        expGlass.cell(3, 2).setPiece(new Piece(Color.CYAN));
        expGlass.cell(2, 3).setPiece(new Piece(Color.GRAY));
        expGlass.cell(2, 0).setPiece(new Piece(Color.WHITE));

        glass.moveRows();
        int expClear = 0;
        int expFill = 7;

        assertEquals(expGlass, glass);
        assertEquals(expClear, countClear);
        assertEquals(expFill, countFill);
    }

    @Test
    public void test_containsCoordinate_contains() {
        assertTrue(glass.containsCoordinate(new Point(0, 0)));
    }

    @Test
    public void test_containsCoordinate_notContains() {
        assertFalse(glass.containsCoordinate(new Point(10, 10)));
    }

}
