package tetris.shapes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tetris.Direction;
import tetris.Glass;
import tetris.Piece;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class NonstandardShapeTest {

    private Glass glass;
    private Glass expGlass;

    @BeforeEach
    public void testSetup() {
        glass = new Glass(6, 5);
        expGlass = new Glass(6, 5);
    }

    private boolean assertVectors(Map<Point2D, Piece> realVectors, List<Point2D> expPoints, Color expColor) {
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
    public void test_generateW() {
        NonstandardShape shape = NonstandardShape.generateW(glass.getWidth(), glass.getHeight());
        Color color = new Color(57, 148, 169);

        List<Point2D> expPoints = new ArrayList<>();
        {
            expPoints.add(new Point2D.Double(0, 0));
            expPoints.add(new Point2D.Double(1, 1));
            expPoints.add(new Point2D.Double(1, 0));
            expPoints.add(new Point2D.Double(0, -1));
            expPoints.add(new Point2D.Double(-1, -1));
        }
        Point2D expCenter = new Point2D.Double(2, 7);

        assertEquals(expCenter, shape.getRotationCenter());
        assertTrue(assertVectors(shape.getVectors(), expPoints, color));
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_generateU() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());
        Color color = new Color(104, 175, 100);

        List<Point2D> expPoints = new ArrayList<>();
        {
            expPoints.add(new Point2D.Double(0, 0));
            expPoints.add(new Point2D.Double(1, 0));
            expPoints.add(new Point2D.Double(1, 1));
            expPoints.add(new Point2D.Double(-1, 0));
            expPoints.add(new Point2D.Double(-1, 1));
        }
        Point2D expCenter = new Point2D.Double(2, 6);

        assertEquals(expCenter, shape.getRotationCenter());
        assertTrue(assertVectors(shape.getVectors(), expPoints, color));
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_generateX() {
        NonstandardShape shape = NonstandardShape.generateX(glass.getWidth(), glass.getHeight());
        Color color = new Color(169, 57, 91);

        List<Point2D> expPoints = new ArrayList<>();
        {
            expPoints.add(new Point2D.Double(0, 0));
            expPoints.add(new Point2D.Double(1, 0));
            expPoints.add(new Point2D.Double(0, 1));
            expPoints.add(new Point2D.Double(-1, 0));
            expPoints.add(new Point2D.Double(0, -1));
        }
        Point2D expCenter = new Point2D.Double(2, 7);

        assertEquals(expCenter, shape.getRotationCenter());
        assertTrue(assertVectors(shape.getVectors(), expPoints, color));
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_generateP() {
        NonstandardShape shape = NonstandardShape.generateP(glass.getWidth(), glass.getHeight());
        Color color = new Color(179, 113, 113);

        List<Point2D> expPoints = new ArrayList<>();
        {
            expPoints.add(new Point2D.Double(0, 0));
            expPoints.add(new Point2D.Double(0, 1));
            expPoints.add(new Point2D.Double(1, 1));
            expPoints.add(new Point2D.Double(1, 0));
            expPoints.add(new Point2D.Double(0, -1));
        }
        Point2D expCenter = new Point2D.Double(2, 7);

        assertEquals(expCenter, shape.getRotationCenter());
        assertTrue(assertVectors(shape.getVectors(), expPoints, color));
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_generateY() {
        NonstandardShape shape = NonstandardShape.generateY(glass.getWidth(), glass.getHeight());
        Color color = new Color(57, 169, 148);

        List<Point2D> expPoints = new ArrayList<>();
        {
            expPoints.add(new Point2D.Double(-0.5, 0.5));
            expPoints.add(new Point2D.Double(-0.5, -0.5));
            expPoints.add(new Point2D.Double(-0.5, 1.5));
            expPoints.add(new Point2D.Double(-0.5, -1.5));
            expPoints.add(new Point2D.Double(-1.5, 0.5));
        }
        Point2D expCenter = new Point2D.Double(2.5, 7.5);

        assertEquals(expCenter, shape.getRotationCenter());
        assertTrue(assertVectors(shape.getVectors(), expPoints, color));
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_generateF() {
        NonstandardShape shape = NonstandardShape.generateF(glass.getWidth(), glass.getHeight());
        Color color = new Color(57, 102, 169);

        List<Point2D> expPoints = new ArrayList<>();
        {
            expPoints.add(new Point2D.Double(0, 0));
            expPoints.add(new Point2D.Double(0, 1));
            expPoints.add(new Point2D.Double(1, 1));
            expPoints.add(new Point2D.Double(-1, 0));
            expPoints.add(new Point2D.Double(0, -1));
        }
        Point2D expCenter = new Point2D.Double(2, 6);

        assertEquals(expCenter, shape.getRotationCenter());
        assertTrue(assertVectors(shape.getVectors(), expPoints, color));
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_generateN() {
        NonstandardShape shape = NonstandardShape.generateN(glass.getWidth(), glass.getHeight());
        Color color = new Color(57, 169, 148);

        List<Point2D> expPoints = new ArrayList<>();
        {
            expPoints.add(new Point2D.Double(0.5, 0.5));
            expPoints.add(new Point2D.Double(0.5, -0.5));
            expPoints.add(new Point2D.Double(1.5, 1.5));
            expPoints.add(new Point2D.Double(0.5, 1.5));
            expPoints.add(new Point2D.Double(0.5, -1.5));
            expPoints.add(new Point2D.Double(1.5, 0.5));
        }
        Point2D expCenter = new Point2D.Double(2.5, 7.5);

        assertEquals(expCenter, shape.getRotationCenter());
        assertTrue(assertVectors(shape.getVectors(), expPoints, color));
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_setShapeOnGlass_GlassNotNull() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());
        shape.setShapeOnGlass(glass);

        Color expColor = new Color(104, 175, 100);
        for (int x = 1; x < 4; x++) {
            if(x == 1 || x == 3)
                expGlass.cell(x, 7).setPiece(new Piece(expColor));
            expGlass.cell(x, 6).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_setShapeOnGlass_GlassIsNull() {
        NonstandardShape shape = NonstandardShape.generateF(glass.getWidth(), glass.getHeight());
        assertThrows(IllegalArgumentException.class, () -> shape.setShapeOnGlass(null));
    }

    @Test
    public void test_setShapeOnGlass_TwoTimesSet() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());
        shape.setShapeOnGlass(glass);

        assertThrows(IllegalArgumentException.class, () -> shape.setShapeOnGlass(new Glass(10, 20)));

        Color expColor = new Color(104, 175, 100);
        for (int x = 1; x < 4; x++) {
            if(x == 1 || x == 3)
                expGlass.cell(x, 7).setPiece(new Piece(expColor));
            expGlass.cell(x, 6).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_down_canMove_OnePieceUnderShape() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());
        shape.setShapeOnGlass(glass);
        boolean isMoved = shape.move(Direction.down());
        assertTrue(isMoved);

        glass.cell(2, 5).setPiece(new Piece(Color.WHITE));

        Color expColor = new Color(104, 175, 100);
        for (int x = 1; x < 4; x++) {
            if(x == 1 || x == 3)
                expGlass.cell(x, 6).setPiece(new Piece(expColor));
            expGlass.cell(x, 5).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_down_oneMove_SeveralPiecesAroundShape() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());
        shape.setShapeOnGlass(glass);

        glass.cell(2, 4).setPiece(new Piece(Color.WHITE));
        glass.cell(0, 6).setPiece(new Piece(Color.WHITE));
        glass.cell(4, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(2, 4).setPiece(new Piece(Color.WHITE));
        expGlass.cell(0, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(4, 6).setPiece(new Piece(Color.WHITE));

        boolean isMoved = shape.move(Direction.down());
        assertTrue(isMoved);

        Color expColor = new Color(104, 175, 100);
        for (int x = 1; x < 4; x++) {
            if(x == 1 || x == 3)
                expGlass.cell(x, 6).setPiece(new Piece(expColor));
            expGlass.cell(x, 5).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);

    }

    @Test
    public void test_move_down_fallToBottom_EmptyGlass() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());
        shape.setShapeOnGlass(glass);
        while (shape.move(Direction.down())){
            shape.move(Direction.down());
        }

        Color expColor = new Color(104, 175, 100);
        for (int x = 1; x < 4; x++) {
            if(x == 1 || x == 3)
                expGlass.cell(x, 1).setPiece(new Piece(expColor));
            expGlass.cell(x, 0).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_down_fallToBottom_SeveralPiecesAroundShape() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());
        shape.setShapeOnGlass(glass);

        glass.cell(0, 3).setPiece(new Piece(Color.WHITE));
        glass.cell(4, 3).setPiece(new Piece(Color.BLACK));
        glass.cell(0, 1).setPiece(new Piece(Color.WHITE));
        glass.cell(4, 0).setPiece(new Piece(Color.BLUE));

        expGlass.cell(0, 3).setPiece(new Piece(Color.WHITE));
        expGlass.cell(4, 3).setPiece(new Piece(Color.BLACK));
        expGlass.cell(0, 1).setPiece(new Piece(Color.WHITE));
        expGlass.cell(4, 0).setPiece(new Piece(Color.BLUE));

        while (shape.move(Direction.down())){
            shape.move(Direction.down());
        }

        Color expColor = new Color(104, 175, 100);
        for (int x = 1; x < 4; x++) {
            if(x == 1 || x == 3)
                expGlass.cell(x, 1).setPiece(new Piece(expColor));
            expGlass.cell(x, 0).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_down_fallToBottom_SeveralPiecesUnderShape() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());
        shape.setShapeOnGlass(glass);

        glass.cell(1, 3).setPiece(new Piece(Color.WHITE));
        glass.cell(3, 3).setPiece(new Piece(Color.BLACK));
        glass.cell(1, 1).setPiece(new Piece(Color.WHITE));
        glass.cell(3, 0).setPiece(new Piece(Color.BLUE));

        while (shape.move(Direction.down())){
            shape.move(Direction.down());
        }

        Color expColor = new Color(104, 175, 100);
        for (int x = 1; x < 4; x++) {
            if(x == 1 || x == 3)
                expGlass.cell(x, 1).setPiece(new Piece(expColor));
            expGlass.cell(x, 0).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_right_oneMove() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());
        shape.setShapeOnGlass(glass);

        boolean isMoved = shape.move(Direction.right());
        assertTrue(isMoved);

        isMoved = shape.move(Direction.right());
        assertFalse(isMoved);

        Color expColor = new Color(104, 175, 100);
        for (int x = 2; x < 5; x++) {
            if(x == 2 || x == 4)
                expGlass.cell(x, 7).setPiece(new Piece(expColor));
            expGlass.cell(x, 6).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_right_severalPiecesOnRightFromShape() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());
        shape.setShapeOnGlass(glass);

        glass.cell(4, 6).setPiece(new Piece(Color.BLACK));
        glass.cell(3, 5).setPiece(new Piece(Color.WHITE));
        glass.cell(4, 7).setPiece(new Piece(Color.CYAN));

        expGlass.cell(3, 5).setPiece(new Piece(Color.WHITE));

        boolean isMoved = shape.move(Direction.right());
        assertTrue(isMoved);

        Color expColor = new Color(104, 175, 100);
        for (int x = 2; x < 5; x++) {
            if(x == 2 || x == 4)
                expGlass.cell(x, 7).setPiece(new Piece(expColor));
            expGlass.cell(x, 6).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_right_severalPiecesNearShape() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());
        shape.setShapeOnGlass(glass);

        glass.cell(0, 6).setPiece(new Piece(Color.WHITE));
        glass.cell(3, 5).setPiece(new Piece(Color.WHITE));
        glass.cell(2, 5).setPiece(new Piece(Color.BLACK));
        expGlass.cell(0, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(3, 5).setPiece(new Piece(Color.WHITE));
        expGlass.cell(2, 5).setPiece(new Piece(Color.BLACK));

        boolean isMoved = shape.move(Direction.right());
        assertTrue(isMoved);

        Color expColor = new Color(104, 175, 100);
        for (int x = 2; x < 5; x++) {
            if(x == 2 || x == 4)
                expGlass.cell(x, 7).setPiece(new Piece(expColor));
            expGlass.cell(x, 6).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_right_movedToWall_severalPiecesNearShape() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());
        shape.setShapeOnGlass(glass);

        glass.cell(4, 6).setPiece(new Piece(Color.WHITE));
        glass.cell(3, 5).setPiece(new Piece(Color.WHITE));
        glass.cell(2, 5).setPiece(new Piece(Color.BLACK));

        expGlass.cell(3, 5).setPiece(new Piece(Color.WHITE));
        expGlass.cell(2, 5).setPiece(new Piece(Color.BLACK));

        while (shape.move(Direction.right())){
            shape.move(Direction.right());
        }

        Color expColor = new Color(104, 175, 100);
        for (int x = 2; x < 5; x++) {
            if(x == 2 || x == 4)
                expGlass.cell(x, 7).setPiece(new Piece(expColor));
            expGlass.cell(x, 6).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_left_oneMove() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());
        shape.setShapeOnGlass(glass);

        boolean isMoved = shape.move(Direction.left());
        assertTrue(isMoved);

        Color expColor = new Color(104, 175, 100);
        for (int x = 0; x < 3; x++) {
            if(x == 0 || x == 2)
                expGlass.cell(x, 7).setPiece(new Piece(expColor));
            expGlass.cell(x, 6).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_left_severalPiecesOnLeftFromShape() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());
        shape.setShapeOnGlass(glass);

        glass.cell(0, 6).setPiece(new Piece(Color.BLACK));
        glass.cell(0, 7).setPiece(new Piece(Color.WHITE));
        glass.cell(3, 5).setPiece(new Piece(Color.CYAN));

        expGlass.cell(3, 5).setPiece(new Piece(Color.CYAN));

        boolean isMoved = shape.move(Direction.left());
        assertTrue(isMoved);

        Color expColor = new Color(104, 175, 100);
        for (int x = 0; x < 3; x++) {
            if(x == 0 || x == 2)
                expGlass.cell(x, 7).setPiece(new Piece(expColor));
            expGlass.cell(x, 6).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_left_severalPiecesNearShape() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());
        shape.setShapeOnGlass(glass);

        glass.cell(0, 8).setPiece(new Piece(Color.WHITE));
        glass.cell(3, 5).setPiece(new Piece(Color.WHITE));
        glass.cell(2, 5).setPiece(new Piece(Color.BLACK));
        expGlass.cell(0, 8).setPiece(new Piece(Color.WHITE));
        expGlass.cell(3, 5).setPiece(new Piece(Color.WHITE));
        expGlass.cell(2, 5).setPiece(new Piece(Color.BLACK));

        boolean isMoved = shape.move(Direction.left());
        assertTrue(isMoved);

        Color expColor = new Color(104, 175, 100);
        for (int x = 0; x < 3; x++) {
            if(x == 0 || x == 2)
                expGlass.cell(x, 7).setPiece(new Piece(expColor));
            expGlass.cell(x, 6).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_left_movedToWall_severalPiecesNearShape() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());
        shape.setShapeOnGlass(glass);

        glass.cell(0, 6).setPiece(new Piece(Color.WHITE));
        glass.cell(4, 6).setPiece(new Piece(Color.WHITE));
        glass.cell(3, 5).setPiece(new Piece(Color.WHITE));
        glass.cell(2, 5).setPiece(new Piece(Color.BLACK));

        expGlass.cell(4, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(3, 5).setPiece(new Piece(Color.WHITE));
        expGlass.cell(2, 5).setPiece(new Piece(Color.BLACK));

        while (shape.move(Direction.left())){
            shape.move(Direction.left());
        }

        Color expColor = new Color(104, 175, 100);
        for (int x = 0; x < 3; x++) {
            if(x == 0 || x == 2)
                expGlass.cell(x, 7).setPiece(new Piece(expColor));
            expGlass.cell(x, 6).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_rotate_shapeRotated_EmptyGlass() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());
        shape.setShapeOnGlass(glass);
        assertTrue(shape.rotate());

        Color expColor = new Color(104, 175, 100);
        for (int y = 5; y < 8; y++) {
            if(y == 7 || y == 5)
                expGlass.cell(3, y).setPiece(new Piece(expColor));
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_rotate_shapeRotated_SeveralPiecesInGlass() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());
        shape.setShapeOnGlass(glass);

        glass.cell(2, 5).setPiece(new Piece(Color.WHITE));
        glass.cell(1, 1).setPiece(new Piece(Color.WHITE));
        glass.cell(4, 5).setPiece(new Piece(Color.BLACK));
        glass.cell(0, 7).setPiece(new Piece(Color.YELLOW));

        expGlass.cell(4, 5).setPiece(new Piece(Color.BLACK));
        expGlass.cell(1, 1).setPiece(new Piece(Color.WHITE));
        expGlass.cell(0, 7).setPiece(new Piece(Color.YELLOW));

        assertTrue(shape.rotate());

        Color expColor = new Color(104, 175, 100);
        for (int y = 5; y < 8; y++) {
            if(y == 7 || y == 5)
                expGlass.cell(3, y).setPiece(new Piece(expColor));
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_rotate_shapeRotated_ShapeNearWall() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());
        shape.setShapeOnGlass(glass);

        glass.cell(2, 5).setPiece(new Piece(Color.WHITE));
        glass.cell(1, 1).setPiece(new Piece(Color.WHITE));
        glass.cell(4, 5).setPiece(new Piece(Color.BLACK));

        expGlass.cell(2, 5).setPiece(new Piece(Color.WHITE));
        expGlass.cell(1, 1).setPiece(new Piece(Color.WHITE));

        shape.move(Direction.right());
        assertTrue(shape.rotate());

        Color expColor = new Color(104, 175, 100);
        for (int y = 5; y < 8; y++) {
            if(y == 7 || y == 5)
                expGlass.cell(4, y).setPiece(new Piece(expColor));
            expGlass.cell(3, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_rotate_shapeNotRotated() {
        NonstandardShape shape = NonstandardShape.generateY(glass.getWidth(), glass.getHeight());
        shape.setShapeOnGlass(glass);

        glass.cell(4, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(4, 6).setPiece(new Piece(Color.WHITE));

        shape.move(Direction.right());
        assertFalse(shape.rotate());

        Color expColor = new Color(57, 169, 148);
        for (int y = 6; y < 10; y++) {
            if(y == 8)
                expGlass.cell(2, y).setPiece(new Piece(expColor));
            expGlass.cell(3, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_canMove_ShapeCanMove_down_noObstacles() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());
        shape.setShapeOnGlass(glass);

        Map<Point2D, Piece> newPos = shape.getVectors();
        Point2D newCenter = new Point2D.Double(shape.getRotationCenter().getX(), shape.getRotationCenter().getY() - 1);
        assertTrue(shape.canMove(newCenter, newPos));

        Color expColor = new Color(104, 175, 100);
        for (int x = 1; x < 4; x++) {
            if(x == 1 || x == 3)
                expGlass.cell(x, 7).setPiece(new Piece(expColor));
            expGlass.cell(x, 6).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_canMove_ShapeCanMove_down_obstacleUnderShape() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());
        shape.setShapeOnGlass(glass);
        glass.cell(3, 5).setPiece(new Piece(Color.WHITE));
        expGlass.cell(3, 5).setPiece(new Piece(Color.WHITE));

        Map<Point2D, Piece> newPos = shape.getVectors();
        Point2D newCenter = new Point2D.Double(shape.getRotationCenter().getX(), shape.getRotationCenter().getY() - 1);
        assertTrue(shape.canMove(newCenter, newPos));

        Color expColor = new Color(104, 175, 100);
        for (int x = 1; x < 4; x++) {
            if(x == 1 || x == 3)
                expGlass.cell(x, 7).setPiece(new Piece(expColor));
            expGlass.cell(x, 6).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_canMove_ShapeCanMove_right_noObstacleNearShape() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());
        shape.setShapeOnGlass(glass);

        glass.cell(4, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(4, 6).setPiece(new Piece(Color.WHITE));

        Map<Point2D, Piece> newPos = shape.getVectors();
        Point2D newCenter = new Point2D.Double(shape.getRotationCenter().getX() + 1, shape.getRotationCenter().getY());
        assertTrue(shape.canMove(newCenter, newPos));

        Color expColor = new Color(104, 175, 100);
        for (int x = 1; x < 4; x++) {
            if(x == 1 || x == 3)
                expGlass.cell(x, 7).setPiece(new Piece(expColor));
            expGlass.cell(x, 6).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_canMove_ShapeCanMove_right_obstacleNearShape() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());
        shape.setShapeOnGlass(glass);

        Map<Point2D, Piece> newPos = shape.getVectors();
        Point2D newCenter = new Point2D.Double(shape.getRotationCenter().getX() + 1, shape.getRotationCenter().getY());
        assertTrue(shape.canMove(newCenter, newPos));

        Color expColor = new Color(104, 175, 100);
        for (int x = 1; x < 4; x++) {
            if(x == 1 || x == 3)
                expGlass.cell(x, 7).setPiece(new Piece(expColor));
            expGlass.cell(x, 6).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_canMove_shapeCanMove_left_noObstacles() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());
        shape.setShapeOnGlass(glass);

        Map<Point2D, Piece> newPos = shape.getVectors();
        Point2D newCenter = new Point2D.Double(shape.getRotationCenter().getX() - 1, shape.getRotationCenter().getY());
        assertTrue(shape.canMove(newCenter, newPos));

        Color expColor = new Color(104, 175, 100);
        for (int x = 1; x < 4; x++) {
            if(x == 1 || x == 3)
                expGlass.cell(x, 7).setPiece(new Piece(expColor));
            expGlass.cell(x, 6).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_canMove_shapeCanMove_left_obstacleNearShape() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());
        shape.setShapeOnGlass(glass);

        glass.cell(0, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(0, 6).setPiece(new Piece(Color.WHITE));

        Map<Point2D, Piece> newPos = shape.getVectors();
        Point2D newCenter = new Point2D.Double(shape.getRotationCenter().getX() - 1, shape.getRotationCenter().getY());
        assertTrue(shape.canMove(newCenter, newPos));

        Color expColor = new Color(104, 175, 100);
        for (int x = 1; x < 4; x++) {
            if(x == 1 || x == 3)
                expGlass.cell(x, 7).setPiece(new Piece(expColor));
            expGlass.cell(x, 6).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_canMove_shapeNotSetOnGlass() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());

        Map<Point2D, Piece> newPos = shape.getVectors();
        Point2D newCenter = new Point2D.Double(shape.getRotationCenter().getX() - 1, shape.getRotationCenter().getY());
        assertThrows(IllegalArgumentException.class, () -> shape.canMove(newCenter, newPos));
    }

    @Test
    public void test_getShapeSize() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());
        assertEquals(new Dimension(3, 2), shape.getShapeSize());
    }

    @Test
    public void test_relativeCenterPos() {
        NonstandardShape shape = NonstandardShape.generateU(glass.getWidth(), glass.getHeight());
        assertEquals(new Point2D.Double(1, 0), shape.relativeCenterPos());
    }
}
