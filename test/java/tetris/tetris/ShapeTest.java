package tetris;

import event.ShapeActionEvent;
import event.ShapeActionListener;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShapeTest {

    private Glass glass;
    private Glass expGlass;
    private int countClearEvent = 0;
    private int countFilledEvent = 0;

    private class ShapeListener implements ShapeActionListener{

        @Override
        public void cellsCleared(@NotNull ShapeActionEvent event, @NotNull List<Optional<Cell>> cells) {
            countClearEvent++;
        }

        @Override
        public void cellsFilled(@NotNull ShapeActionEvent event, @NotNull List<Optional<Cell>> cells) {
            countFilledEvent++;
        }
    }

    @BeforeEach
    public void testSetup() {
        countClearEvent = 0;
        countFilledEvent = 0;
        glass = new Glass(6, 5);
        expGlass = new Glass(6, 5);
    }

    private boolean assertVectors(Map<Point2D, Piece> realVectors, List<Point2D> expPoints, Color expColor){
        boolean equals;
        int i = 0;

        equals = realVectors.size() == expPoints.size();
        for (Map.Entry<Point2D, Piece> pieceEntry : realVectors.entrySet()) {
            equals = equals && pieceEntry.getKey().equals(expPoints.get(i)) && pieceEntry.getValue().getColor().equals(expColor);
            i++;
        }

        return equals;
    }

    @Test
    public void test_generateL() {
        Shape shape = Shape.generateL(glass.getWidth(), glass.getHeight());
        Color color = new Color(142, 232, 119);

        List<Point2D> expPoints = new ArrayList<>();{
            expPoints.add(new Point2D.Double(0, 0));
            expPoints.add(new Point2D.Double(0, 1));
            expPoints.add(new Point2D.Double(0, -1));
            expPoints.add(new Point2D.Double(1, -1));
        }
        assertTrue(assertVectors(shape.getVectors(), expPoints, color));
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_generateI() {
        Shape shape = Shape.generateI(glass.getWidth(), glass.getHeight());
        Color color = new Color(212, 208, 89);

        List<Point2D> expPoints = new ArrayList<>();{
            expPoints.add(new Point2D.Double(-0.5, 0.5));
            expPoints.add(new Point2D.Double(-0.5, -0.5));
            expPoints.add(new Point2D.Double(-0.5, 1.5));
            expPoints.add(new Point2D.Double(-0.5, -1.5));
        }
        assertTrue(assertVectors(shape.getVectors(), expPoints, color));

        assertEquals(expGlass, glass);
    }

    @Test
    public void test_generateO() {
        Shape shape = Shape.generateO(glass.getWidth(), glass.getHeight());
        Color color = new Color(212, 104, 89);

        List<Point2D> expPoints = new ArrayList<>();{
            expPoints.add(new Point2D.Double(-0.5, 0.5));
            expPoints.add(new Point2D.Double(0.5, 0.5));
            expPoints.add(new Point2D.Double(0.5, -0.5));
            expPoints.add(new Point2D.Double(-0.5, -0.5));
        }
        assertTrue(assertVectors(shape.getVectors(), expPoints, color));

        assertEquals(expGlass, glass);
    }

    @Test
    public void test_generateJ() {
        Shape shape = Shape.generateJ(glass.getWidth(), glass.getHeight());
        Color color = new Color(89, 212, 175);

        List<Point2D> expPoints = new ArrayList<>();{
            expPoints.add(new Point2D.Double(0, 1));
            expPoints.add(new Point2D.Double(0, 0));
            expPoints.add(new Point2D.Double(0, -1));
            expPoints.add(new Point2D.Double(-1, -1));
        }
        assertTrue(assertVectors(shape.getVectors(), expPoints, color));

        assertEquals(expGlass, glass);
    }

    @Test
    public void test_generateZ() {
        Shape shape = Shape.generateZ(glass.getWidth(), glass.getHeight());
        Color color = new Color(71, 110, 185);

        List<Point2D> expPoints = new ArrayList<>();{
            expPoints.add(new Point2D.Double(0, 0));
            expPoints.add(new Point2D.Double(-1, 0));
            expPoints.add(new Point2D.Double(0, -1));
            expPoints.add(new Point2D.Double(1, -1));
        }
        assertTrue(assertVectors(shape.getVectors(), expPoints, color));

        assertEquals(expGlass, glass);
    }

    @Test
    public void test_generateT() {
        Shape shape = Shape.generateT(glass.getWidth(), glass.getHeight());
        Color color = new Color(217, 127, 208);

        List<Point2D> expPoints = new ArrayList<>();{
            expPoints.add(new Point2D.Double(0, 0));
            expPoints.add(new Point2D.Double(1, 0));
            expPoints.add(new Point2D.Double(0, -1));
            expPoints.add(new Point2D.Double(-1, 0));
        }
        assertTrue(assertVectors(shape.getVectors(), expPoints, color));

        assertEquals(expGlass, glass);
    }

    @Test
    public void test_generateS() {
        Shape shape = Shape.generateS(glass.getWidth(), glass.getHeight());
        Color color = new Color(218, 154, 79);

        List<Point2D> expPoints = new ArrayList<>();{
            expPoints.add(new Point2D.Double(0, 0));
            expPoints.add(new Point2D.Double(1, 0));
            expPoints.add(new Point2D.Double(0, -1));
            expPoints.add(new Point2D.Double(-1, -1));
        }
        assertTrue(assertVectors(shape.getVectors(), expPoints, color));

        assertEquals(expGlass, glass);
    }

    @Test
    public void test_setShapeOnGlass_GlassNotNull() {
        Shape shape = Shape.generateI(glass.getWidth(), glass.getHeight());
        shape.addShapeActionListener(new ShapeListener());
        shape.setShapeOnGlass(glass);

        int expCountEvents = 1;
        assertEquals(expCountEvents, countFilledEvent);
        assertEquals(expCountEvents, countClearEvent);

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_setShapeOnGlass_GlassIsNull() {
        Shape shape = Shape.generateS(glass.getWidth(), glass.getHeight());
        assertThrows(IllegalArgumentException.class, () -> shape.setShapeOnGlass(null));
    }

    @Test
    public void test_setShapeOnGlass_TwoTimesSet() {
        Shape shape = Shape.generateI(glass.getWidth(), glass.getHeight());
        shape.addShapeActionListener(new ShapeListener());
        shape.setShapeOnGlass(glass);

        int expCountEvents = 1;
        assertEquals(expCountEvents, countFilledEvent);
        assertEquals(expCountEvents, countClearEvent);

        assertThrows(IllegalArgumentException.class, () -> shape.setShapeOnGlass(new Glass(10, 20)));

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_down_canMoveOnce_OnePieceUnderShape() {
        Shape shape = Shape.generateI(glass.getWidth(), glass.getHeight());
        shape.addShapeActionListener(new ShapeListener());
        shape.setShapeOnGlass(glass);
        boolean isMoved = shape.move(Direction.down());
        assertTrue(isMoved);

        glass.cell(2, 4).setPiece(new Piece(Color.WHITE));
        expGlass.cell(2, 4).setPiece(new Piece(Color.WHITE));

        isMoved = shape.move(Direction.down());
        assertFalse(isMoved);

        int expCountEvents = 2;
        assertEquals(expCountEvents, countFilledEvent);
        assertEquals(expCountEvents, countClearEvent);

        Color expColor = new Color(212, 208, 89);
        for (int y = 5; y < 9; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_down_canMoveOnce_SeveralPiecesAroundShape() {
        Shape shape = Shape.generateI(glass.getWidth(), glass.getHeight());
        shape.addShapeActionListener(new ShapeListener());
        shape.setShapeOnGlass(glass);

        glass.cell(2, 4).setPiece(new Piece(Color.WHITE));
        glass.cell(1, 6).setPiece(new Piece(Color.WHITE));
        glass.cell(3, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(2, 4).setPiece(new Piece(Color.WHITE));
        expGlass.cell(1, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(3, 6).setPiece(new Piece(Color.WHITE));

        boolean isMoved = shape.move(Direction.down());
        assertTrue(isMoved);

        int expCountEvents = 2;
        assertEquals(expCountEvents, countFilledEvent);
        assertEquals(expCountEvents, countClearEvent);

        Color expColor = new Color(212, 208, 89);
        for (int y = 5; y < 9; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);

    }

    @Test
    public void test_move_down_fallToBottom() {
        Shape shape = Shape.generateI(glass.getWidth(), glass.getHeight());
        shape.addShapeActionListener(new ShapeListener());
        shape.setShapeOnGlass(glass);
        while (shape.move(Direction.down())){
            shape.move(Direction.down());
        }

        int expCountEvents = 7;
        assertEquals(expCountEvents, countFilledEvent);
        assertEquals(expCountEvents, countClearEvent);

        Color expColor = new Color(212, 208, 89);
        for (int y = 0; y < 4; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_down_fallToBottom_SeveralPiecesAroundShape() {
        Shape shape = Shape.generateI(glass.getWidth(), glass.getHeight());
        shape.addShapeActionListener(new ShapeListener());
        shape.setShapeOnGlass(glass);

        glass.cell(1, 3).setPiece(new Piece(Color.WHITE));
        glass.cell(3, 3).setPiece(new Piece(Color.BLACK));
        glass.cell(1, 1).setPiece(new Piece(Color.WHITE));
        glass.cell(3, 0).setPiece(new Piece(Color.BLUE));

        expGlass.cell(1, 3).setPiece(new Piece(Color.WHITE));
        expGlass.cell(3, 3).setPiece(new Piece(Color.BLACK));
        expGlass.cell(1, 1).setPiece(new Piece(Color.WHITE));
        expGlass.cell(3, 0).setPiece(new Piece(Color.BLUE));

        while (shape.move(Direction.down())){
            shape.move(Direction.down());
        }

        int expCountEvents = 7;
        assertEquals(expCountEvents, countFilledEvent);
        assertEquals(expCountEvents, countClearEvent);

        Color expColor = new Color(212, 208, 89);
        for (int y = 0; y < 4; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_down_cantMove() {
        Shape shape = Shape.generateI(glass.getWidth(), glass.getHeight());
        shape.addShapeActionListener(new ShapeListener());
        shape.setShapeOnGlass(glass);

        glass.cell(2, 5).setPiece(new Piece(Color.WHITE));
        expGlass.cell(2, 5).setPiece(new Piece(Color.WHITE));

        boolean isMoved = shape.move(Direction.down());
        assertFalse(isMoved);

        int expCountEvents = 1;
        assertEquals(expCountEvents, countFilledEvent);
        assertEquals(expCountEvents, countClearEvent);

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_right_canMoveOnce() {
        Shape shape = Shape.generateI(glass.getWidth(), glass.getHeight());
        shape.addShapeActionListener(new ShapeListener());
        shape.setShapeOnGlass(glass);

        boolean isMoved = shape.move(Direction.right());
        assertTrue(isMoved);

        glass.cell(4, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(4, 6).setPiece(new Piece(Color.WHITE));

        isMoved = shape.move(Direction.right());
        assertFalse(isMoved);

        int expCountEvents = 2;
        assertEquals(expCountEvents, countFilledEvent);
        assertEquals(expCountEvents, countClearEvent);

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(3, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_right_cantMove() {
        Shape shape = Shape.generateI(glass.getWidth(), glass.getHeight());
        shape.addShapeActionListener(new ShapeListener());
        shape.setShapeOnGlass(glass);

        glass.cell(3, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(3, 6).setPiece(new Piece(Color.WHITE));

        boolean isMoved = shape.move(Direction.right());
        assertFalse(isMoved);

        int expCountEvents = 1;
        assertEquals(expCountEvents, countFilledEvent);
        assertEquals(expCountEvents, countClearEvent);

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_right_canMove_SeverPiecesNearShape() {
        Shape shape = Shape.generateI(glass.getWidth(), glass.getHeight());
        shape.addShapeActionListener(new ShapeListener());
        shape.setShapeOnGlass(glass);

        glass.cell(0, 6).setPiece(new Piece(Color.WHITE));
        glass.cell(4, 6).setPiece(new Piece(Color.WHITE));
        glass.cell(2, 5).setPiece(new Piece(Color.WHITE));
        expGlass.cell(0, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(4, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(2, 5).setPiece(new Piece(Color.WHITE));

        boolean isMoved = shape.move(Direction.right());
        assertTrue(isMoved);

        int expCountEvents = 2;
        assertEquals(expCountEvents, countFilledEvent);
        assertEquals(expCountEvents, countClearEvent);

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(3, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_right_canMove_MovedToWall() {
        Shape shape = Shape.generateI(glass.getWidth(), glass.getHeight());
        shape.addShapeActionListener(new ShapeListener());
        shape.setShapeOnGlass(glass);

        glass.cell(0, 6).setPiece(new Piece(Color.WHITE));
        glass.cell(2, 5).setPiece(new Piece(Color.WHITE));
        expGlass.cell(0, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(2, 5).setPiece(new Piece(Color.WHITE));

        while (shape.move(Direction.right())){
            shape.move(Direction.right());
        }

        int expCountEvents = 3;
        assertEquals(expCountEvents, countFilledEvent);
        assertEquals(expCountEvents, countClearEvent);

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(4, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_left_canMoveOnce() {
        Shape shape = Shape.generateI(glass.getWidth(), glass.getHeight());
        shape.addShapeActionListener(new ShapeListener());
        shape.setShapeOnGlass(glass);

        boolean isMoved = shape.move(Direction.left());
        assertTrue(isMoved);

        glass.cell(0, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(0, 6).setPiece(new Piece(Color.WHITE));

        isMoved = shape.move(Direction.left());
        assertFalse(isMoved);

        int expCountEvents = 2;
        assertEquals(expCountEvents, countFilledEvent);
        assertEquals(expCountEvents, countClearEvent);

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(1, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_left_cantMove() {
        Shape shape = Shape.generateI(glass.getWidth(), glass.getHeight());
        shape.addShapeActionListener(new ShapeListener());
        shape.setShapeOnGlass(glass);

        glass.cell(1, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(1, 6).setPiece(new Piece(Color.WHITE));

        boolean isMoved = shape.move(Direction.left());
        assertFalse(isMoved);

        int expCountEvents = 1;
        assertEquals(expCountEvents, countFilledEvent);
        assertEquals(expCountEvents, countClearEvent);

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_left_canMove_SeverPiecesNearShape() {
        Shape shape = Shape.generateI(glass.getWidth(), glass.getHeight());
        shape.addShapeActionListener(new ShapeListener());
        shape.setShapeOnGlass(glass);

        glass.cell(0, 6).setPiece(new Piece(Color.WHITE));
        glass.cell(4, 6).setPiece(new Piece(Color.WHITE));
        glass.cell(2, 5).setPiece(new Piece(Color.WHITE));
        expGlass.cell(0, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(4, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(2, 5).setPiece(new Piece(Color.WHITE));

        boolean isMoved = shape.move(Direction.left());
        assertTrue(isMoved);

        int expCountEvents = 2;
        assertEquals(expCountEvents, countFilledEvent);
        assertEquals(expCountEvents, countClearEvent);

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(1, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_left_canMove_MovedToWall() {
        Shape shape = Shape.generateI(glass.getWidth(), glass.getHeight());
        shape.addShapeActionListener(new ShapeListener());
        shape.setShapeOnGlass(glass);

        glass.cell(0, 4).setPiece(new Piece(Color.WHITE));
        glass.cell(2, 5).setPiece(new Piece(Color.WHITE));
        expGlass.cell(0, 4).setPiece(new Piece(Color.WHITE));
        expGlass.cell(2, 5).setPiece(new Piece(Color.WHITE));

        while (shape.move(Direction.left())){
            shape.move(Direction.left());
        }

        int expCountEvents = 3;
        assertEquals(expCountEvents, countFilledEvent);
        assertEquals(expCountEvents, countClearEvent);

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(0, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_rotate_shapeRotated_EmptyGlass() {
        Shape shape = Shape.generateI(glass.getWidth(), glass.getHeight());
        shape.addShapeActionListener(new ShapeListener());
        shape.setShapeOnGlass(glass);
        assertTrue(shape.rotate());

        int expCountEvents = 2;
        assertEquals(expCountEvents, countFilledEvent);
        assertEquals(expCountEvents, countClearEvent);

        Color expColor = new Color(212, 208, 89);
        for (int x = 1; x < 5; x++) {
            expGlass.cell(x, 8).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_rotate_shapeRotated_SeveralPiecesInGlass() {
        Shape shape = Shape.generateI(glass.getWidth(), glass.getHeight());
        shape.addShapeActionListener(new ShapeListener());
        shape.setShapeOnGlass(glass);

        glass.cell(2, 5).setPiece(new Piece(Color.WHITE));
        glass.cell(1, 1).setPiece(new Piece(Color.WHITE));
        glass.cell(4, 5).setPiece(new Piece(Color.BLACK));
        expGlass.cell(2, 5).setPiece(new Piece(Color.WHITE));
        expGlass.cell(1, 1).setPiece(new Piece(Color.WHITE));
        expGlass.cell(4, 5).setPiece(new Piece(Color.BLACK));

        assertTrue(shape.rotate());

        int expCountEvents = 2;
        assertEquals(expCountEvents, countFilledEvent);
        assertEquals(expCountEvents, countClearEvent);

        Color expColor = new Color(212, 208, 89);
        for (int x = 1; x < 5; x++) {
            expGlass.cell(x, 8).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_rotate_shapeRotated_ShapeNearWall() {
        Shape shape = Shape.generateI(glass.getWidth(), glass.getHeight());
        shape.addShapeActionListener(new ShapeListener());
        shape.setShapeOnGlass(glass);

        glass.cell(2, 5).setPiece(new Piece(Color.WHITE));
        glass.cell(1, 1).setPiece(new Piece(Color.WHITE));
        glass.cell(4, 5).setPiece(new Piece(Color.BLACK));
        expGlass.cell(2, 5).setPiece(new Piece(Color.WHITE));
        expGlass.cell(1, 1).setPiece(new Piece(Color.WHITE));
        expGlass.cell(4, 5).setPiece(new Piece(Color.BLACK));

        assertTrue(shape.rotate());
        shape.move(Direction.right());
        assertTrue(shape.rotate());

        int expCountEvents = 3;
        assertEquals(expCountEvents, countFilledEvent);
        assertEquals(expCountEvents, countClearEvent);

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(3, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_rotate_shapeNotRotated() {
        Shape shape = Shape.generateI(glass.getWidth(), glass.getHeight());
        shape.addShapeActionListener(new ShapeListener());
        shape.setShapeOnGlass(glass);

        glass.cell(1, 8).setPiece(new Piece(Color.WHITE));
        expGlass.cell(1, 8).setPiece(new Piece(Color.WHITE));

        assertFalse(shape.rotate());

        int expCountEvents = 1;
        assertEquals(expCountEvents, countFilledEvent);
        assertEquals(expCountEvents, countClearEvent);

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_canMove_ShapeCanMove_down() {
        Shape shape = Shape.generateI(glass.getWidth(), glass.getHeight());
        shape.addShapeActionListener(new ShapeListener());
        shape.setShapeOnGlass(glass);

        assertTrue(shape.canMove(Direction.down()));

        int expCountEvents = 3;
        assertEquals(expCountEvents, countFilledEvent);
        assertEquals(expCountEvents, countClearEvent);

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_canMove_ShapeCanMove_right() {
        Shape shape = Shape.generateI(glass.getWidth(), glass.getHeight());
        shape.addShapeActionListener(new ShapeListener());
        shape.setShapeOnGlass(glass);

        assertTrue(shape.canMove(Direction.right()));

        int expCountEvents = 3;
        assertEquals(expCountEvents, countFilledEvent);
        assertEquals(expCountEvents, countClearEvent);

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_canMove_ShapeCanMove_left() {
        Shape shape = Shape.generateI(glass.getWidth(), glass.getHeight());
        shape.addShapeActionListener(new ShapeListener());
        shape.setShapeOnGlass(glass);

        assertTrue(shape.canMove(Direction.left()));

        int expCountEvents = 3;
        assertEquals(expCountEvents, countFilledEvent);
        assertEquals(expCountEvents, countClearEvent);

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_canMove_ShapeCantMove_down() {
        Shape shape = Shape.generateI(glass.getWidth(), glass.getHeight());
        shape.addShapeActionListener(new ShapeListener());
        shape.setShapeOnGlass(glass);

        glass.cell(2, 5).setPiece(new Piece(Color.WHITE));
        expGlass.cell(2, 5).setPiece(new Piece(Color.WHITE));

        assertFalse(shape.canMove(Direction.down()));

        int expCountEvents = 1;
        assertEquals(expCountEvents, countFilledEvent);
        assertEquals(expCountEvents, countClearEvent);

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_canMove_ShapeCantMove_right() {
        Shape shape = Shape.generateI(glass.getWidth(), glass.getHeight());
        shape.addShapeActionListener(new ShapeListener());
        shape.setShapeOnGlass(glass);

        glass.cell(3, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(3, 6).setPiece(new Piece(Color.WHITE));

        assertFalse(shape.canMove(Direction.right()));

        int expCountEvents = 1;
        assertEquals(expCountEvents, countFilledEvent);
        assertEquals(expCountEvents, countClearEvent);

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_canMove_ShapeCantMove_left() {
        Shape shape = Shape.generateI(glass.getWidth(), glass.getHeight());
        shape.addShapeActionListener(new ShapeListener());
        shape.setShapeOnGlass(glass);

        glass.cell(1, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(1, 6).setPiece(new Piece(Color.WHITE));

        assertFalse(shape.canMove(Direction.left()));

        int expCountEvents = 1;
        assertEquals(expCountEvents, countFilledEvent);
        assertEquals(expCountEvents, countClearEvent);

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }
}
