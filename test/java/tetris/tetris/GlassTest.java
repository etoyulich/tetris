package tetris;

import event.GlassActionEvent;
import event.GlassActionListener;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlassTest {

    private Glass glass;
    private int countEvent = 0;

    private class GlassListener implements GlassActionListener {

        @Override
        public void rowsCleared(@NotNull GlassActionEvent event) {
            countEvent++;
        }
    }

    @BeforeEach
    public void testSetup() {
        glass = new Glass(2, 2);
        countEvent = 0;
    }

    @Test
    public void test_create_withCorrectParams() {
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
        glass.addGlassActionListener(new GlassListener());
        Glass expGlass = new Glass(2, 2);
        int real = glass.getFilledRows();
        int expected = 0;
        int expCount = 0;

        assertEquals(expected, real);
        assertEquals(expGlass, glass);
        assertEquals(expCount, countEvent);
    }

    @Test
    public void test_getFilledRows_SeveralFilledCellsNoRows() {
        glass.addGlassActionListener(new GlassListener());
        Glass expGlass = new Glass(4, 4);
        glass = new Glass(4, 4);

        glass.cell(0, 0).setPiece(new Piece(Color.WHITE));
        glass.cell(0, 2).setPiece(new Piece(Color.BLACK));
        glass.cell(1, 1).setPiece(new Piece(Color.YELLOW));

        expGlass.cell(0, 0).setPiece(new Piece(Color.WHITE));
        expGlass.cell(0, 2).setPiece(new Piece(Color.BLACK));
        expGlass.cell(1, 1).setPiece(new Piece(Color.YELLOW));

        int real = glass.getFilledRows();
        int expected = 0;
        int expCount = 0;

        assertEquals(expected, real);
        assertEquals(expGlass, glass);
        assertEquals(expCount, countEvent);
    }

    @Test
    public void test_getFilledRows_OneFilledRow() {
        glass.addGlassActionListener(new GlassListener());
        Glass expGlass = new Glass(2, 2);
        for (int i = 0; i < glass.getWidth(); i++) {
            glass.cell(i, 0).setPiece(new Piece(Color.WHITE));
            expGlass.cell(i, 0).setPiece(new Piece(Color.WHITE));
        }

        int real = glass.getFilledRows();
        int expected = 1;
        int expCount = 0;

        assertEquals(expected, real);
        assertEquals(expGlass, glass);
        assertEquals(expCount, countEvent);
    }

    @Test
    public void test_getFilledRows_SeveralFilledRows() {
        Glass expGlass = new Glass(4, 4);
        glass = new Glass(4, 4);
        glass.addGlassActionListener(new GlassListener());
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
        int expCount = 0;

        assertEquals(expected, real);
        assertEquals(expGlass, glass);
        assertEquals(expCount, countEvent);
    }

    @Test
    public void test_moveRows_rowsMoved_OneFilledRow() {
        Glass expGlass = new Glass(4, 4);
        glass = new Glass(4, 4);
        glass.addGlassActionListener(new GlassListener());

        glass.cell(2, 1).setPiece(new Piece(Color.WHITE));
        glass.cell(2, 2).setPiece(new Piece(Color.WHITE));

        expGlass.cell(2, 0).setPiece(new Piece(Color.WHITE));
        expGlass.cell(2, 1).setPiece(new Piece(Color.WHITE));

        for (int x = 0; x < glass.getWidth(); x++) {
            glass.cell(x, 0).setPiece(new Piece(Color.WHITE));
        }

        glass.moveRows();
        int expCount = 1;

        assertEquals(expGlass, glass);
        assertEquals(expCount, countEvent);
    }

    @Test
    public void test_moveRows_severalRowsMoved() {
        Glass expGlass = new Glass(4, 4);
        expGlass.cell(1, 0).setPiece(new Piece(Color.WHITE));
        expGlass.cell(2, 0).setPiece(new Piece(Color.BLACK));
        expGlass.cell(2, 1).setPiece(new Piece(Color.WHITE));

        glass = new Glass(4, 4);
        glass.addGlassActionListener(new GlassListener());
        glass.cell(1, 2).setPiece(new Piece(Color.WHITE));
        glass.cell(2, 2).setPiece(new Piece(Color.BLACK));
        glass.cell(2, 3).setPiece(new Piece(Color.WHITE));

        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < glass.getWidth(); x++) {
                glass.cell(x, y).setPiece(new Piece(Color.WHITE));
            }
        }

        glass.moveRows();
        int expCount = 1;

        assertEquals(expGlass, glass);
        assertEquals(expCount, countEvent);
    }

    @Test
    public void test_moveRows_rowsNotMoved() {
        glass = new Glass(4, 4);
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
        int expCount = 0;

        assertEquals(expGlass, glass);
        assertEquals(expCount, countEvent);
    }
}
