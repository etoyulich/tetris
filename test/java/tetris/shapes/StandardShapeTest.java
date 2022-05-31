package tetris.shapes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tetris.Direction;
import tetris.Glass;
import tetris.Piece;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StandardShapeTest {

    private Glass glass;
    private Glass expGlass;

    @BeforeEach
    public void testSetup() {
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
        StandardShape standardShape = StandardShape.generateL(glass.getWidth(), glass.getHeight());
        Color color = new Color(142, 232, 119);

        List<Point2D> expPoints = new ArrayList<>();{
            expPoints.add(new Point2D.Double(0, 0));
            expPoints.add(new Point2D.Double(0, 1));
            expPoints.add(new Point2D.Double(0, -1));
            expPoints.add(new Point2D.Double(1, -1));
        }
        Point2D expCenter = new Point2D.Double(2, 7);

        assertEquals(expCenter, standardShape.getRotationCenter());
        assertTrue(assertVectors(standardShape.getVectors(), expPoints, color));
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_generateI() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        Color color = new Color(212, 208, 89);

        List<Point2D> expPoints = new ArrayList<>();{
            expPoints.add(new Point2D.Double(-0.5, 0.5));
            expPoints.add(new Point2D.Double(-0.5, -0.5));
            expPoints.add(new Point2D.Double(-0.5, 1.5));
            expPoints.add(new Point2D.Double(-0.5, -1.5));
        }
        Point2D expCenter = new Point2D.Double(2.5, 7.5);

        assertEquals(expCenter, standardShape.getRotationCenter());
        assertTrue(assertVectors(standardShape.getVectors(), expPoints, color));
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_generateO() {
        StandardShape standardShape = StandardShape.generateO(glass.getWidth(), glass.getHeight());
        Color color = new Color(212, 104, 89);

        List<Point2D> expPoints = new ArrayList<>();{
            expPoints.add(new Point2D.Double(-0.5, 0.5));
            expPoints.add(new Point2D.Double(0.5, 0.5));
            expPoints.add(new Point2D.Double(0.5, -0.5));
            expPoints.add(new Point2D.Double(-0.5, -0.5));
        }
        Point2D expCenter = new Point2D.Double(2.5, 6.5);

        assertEquals(expCenter, standardShape.getRotationCenter());
        assertTrue(assertVectors(standardShape.getVectors(), expPoints, color));
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_generateJ() {
        StandardShape standardShape = StandardShape.generateJ(glass.getWidth(), glass.getHeight());
        Color color = new Color(89, 212, 175);

        List<Point2D> expPoints = new ArrayList<>();{
            expPoints.add(new Point2D.Double(0, 1));
            expPoints.add(new Point2D.Double(0, 0));
            expPoints.add(new Point2D.Double(0, -1));
            expPoints.add(new Point2D.Double(-1, -1));
        }
        Point2D expCenter = new Point2D.Double(2, 7);

        assertEquals(expCenter, standardShape.getRotationCenter());
        assertTrue(assertVectors(standardShape.getVectors(), expPoints, color));
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_generateZ() {
        StandardShape standardShape = StandardShape.generateZ(glass.getWidth(), glass.getHeight());
        Color color = new Color(71, 110, 185);

        List<Point2D> expPoints = new ArrayList<>();{
            expPoints.add(new Point2D.Double(0, 0));
            expPoints.add(new Point2D.Double(-1, 0));
            expPoints.add(new Point2D.Double(0, -1));
            expPoints.add(new Point2D.Double(1, -1));
        }
        Point2D expCenter = new Point2D.Double(2, 7);

        assertEquals(expCenter, standardShape.getRotationCenter());
        assertTrue(assertVectors(standardShape.getVectors(), expPoints, color));
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_generateT() {
        StandardShape standardShape = StandardShape.generateT(glass.getWidth(), glass.getHeight());
        Color color = new Color(217, 127, 208);

        List<Point2D> expPoints = new ArrayList<>();{
            expPoints.add(new Point2D.Double(0, 0));
            expPoints.add(new Point2D.Double(1, 0));
            expPoints.add(new Point2D.Double(0, -1));
            expPoints.add(new Point2D.Double(-1, 0));
        }
        Point2D expCenter = new Point2D.Double(2, 7);

        assertEquals(expCenter, standardShape.getRotationCenter());
        assertTrue(assertVectors(standardShape.getVectors(), expPoints, color));
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_generateS() {
        StandardShape standardShape = StandardShape.generateS(glass.getWidth(), glass.getHeight());
        Color color = new Color(218, 154, 79);

        List<Point2D> expPoints = new ArrayList<>();{
            expPoints.add(new Point2D.Double(0, 0));
            expPoints.add(new Point2D.Double(1, 0));
            expPoints.add(new Point2D.Double(0, -1));
            expPoints.add(new Point2D.Double(-1, -1));
        }
        Point2D expCenter = new Point2D.Double(2, 7);

        assertEquals(expCenter, standardShape.getRotationCenter());
        assertTrue(assertVectors(standardShape.getVectors(), expPoints, color));
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_setShapeOnGlass_GlassNotNull() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        standardShape.setShapeOnGlass(glass);

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_setShapeOnGlass_GlassIsNull() {
        StandardShape standardShape = StandardShape.generateS(glass.getWidth(), glass.getHeight());
        assertThrows(IllegalArgumentException.class, () -> standardShape.setShapeOnGlass(null));
    }

    @Test
    public void test_setShapeOnGlass_TwoTimesSet() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        standardShape.setShapeOnGlass(glass);

        assertThrows(IllegalArgumentException.class, () -> standardShape.setShapeOnGlass(new Glass(10, 20)));

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_down_canMoveOnce_OnePieceUnderShape() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        standardShape.setShapeOnGlass(glass);
        boolean isMoved = standardShape.move(Direction.down());
        assertTrue(isMoved);

        glass.cell(2, 4).setPiece(new Piece(Color.WHITE));
        expGlass.cell(2, 4).setPiece(new Piece(Color.WHITE));

        isMoved = standardShape.move(Direction.down());
        assertFalse(isMoved);

        Color expColor = new Color(212, 208, 89);
        for (int y = 5; y < 9; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_down_canMoveOnce_SeveralPiecesAroundShape() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        standardShape.setShapeOnGlass(glass);

        glass.cell(2, 4).setPiece(new Piece(Color.WHITE));
        glass.cell(1, 6).setPiece(new Piece(Color.WHITE));
        glass.cell(3, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(2, 4).setPiece(new Piece(Color.WHITE));
        expGlass.cell(1, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(3, 6).setPiece(new Piece(Color.WHITE));

        boolean isMoved = standardShape.move(Direction.down());
        assertTrue(isMoved);

        Color expColor = new Color(212, 208, 89);
        for (int y = 5; y < 9; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);

    }

    @Test
    public void test_move_down_fallToBottom() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        standardShape.setShapeOnGlass(glass);
        while (standardShape.move(Direction.down())){
            standardShape.move(Direction.down());
        }

        Color expColor = new Color(212, 208, 89);
        for (int y = 0; y < 4; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_down_fallToBottom_SeveralPiecesAroundShape() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        standardShape.setShapeOnGlass(glass);

        glass.cell(1, 3).setPiece(new Piece(Color.WHITE));
        glass.cell(3, 3).setPiece(new Piece(Color.BLACK));
        glass.cell(1, 1).setPiece(new Piece(Color.WHITE));
        glass.cell(3, 0).setPiece(new Piece(Color.BLUE));

        expGlass.cell(1, 3).setPiece(new Piece(Color.WHITE));
        expGlass.cell(3, 3).setPiece(new Piece(Color.BLACK));
        expGlass.cell(1, 1).setPiece(new Piece(Color.WHITE));
        expGlass.cell(3, 0).setPiece(new Piece(Color.BLUE));

        while (standardShape.move(Direction.down())){
            standardShape.move(Direction.down());
        }

        Color expColor = new Color(212, 208, 89);
        for (int y = 0; y < 4; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_down_cantMove() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        standardShape.setShapeOnGlass(glass);

        glass.cell(2, 5).setPiece(new Piece(Color.WHITE));
        expGlass.cell(2, 5).setPiece(new Piece(Color.WHITE));

        boolean isMoved = standardShape.move(Direction.down());
        assertFalse(isMoved);

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_right_canMoveOnce() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        standardShape.setShapeOnGlass(glass);

        boolean isMoved = standardShape.move(Direction.right());
        assertTrue(isMoved);

        glass.cell(4, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(4, 6).setPiece(new Piece(Color.WHITE));

        isMoved = standardShape.move(Direction.right());
        assertFalse(isMoved);

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(3, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_right_cantMove() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        standardShape.setShapeOnGlass(glass);

        glass.cell(3, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(3, 6).setPiece(new Piece(Color.WHITE));

        boolean isMoved = standardShape.move(Direction.right());
        assertFalse(isMoved);

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_right_canMove_SeverPiecesNearShape() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        standardShape.setShapeOnGlass(glass);

        glass.cell(0, 6).setPiece(new Piece(Color.WHITE));
        glass.cell(4, 6).setPiece(new Piece(Color.WHITE));
        glass.cell(2, 5).setPiece(new Piece(Color.WHITE));
        expGlass.cell(0, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(4, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(2, 5).setPiece(new Piece(Color.WHITE));

        boolean isMoved = standardShape.move(Direction.right());
        assertTrue(isMoved);

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(3, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_right_canMove_MovedToWall() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        standardShape.setShapeOnGlass(glass);

        glass.cell(0, 6).setPiece(new Piece(Color.WHITE));
        glass.cell(2, 5).setPiece(new Piece(Color.WHITE));
        expGlass.cell(0, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(2, 5).setPiece(new Piece(Color.WHITE));

        while (standardShape.move(Direction.right())){
            standardShape.move(Direction.right());
        }

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(4, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_left_canMoveOnce() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        standardShape.setShapeOnGlass(glass);

        boolean isMoved = standardShape.move(Direction.left());
        assertTrue(isMoved);

        glass.cell(0, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(0, 6).setPiece(new Piece(Color.WHITE));

        isMoved = standardShape.move(Direction.left());
        assertFalse(isMoved);

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(1, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_left_cantMove() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        standardShape.setShapeOnGlass(glass);

        glass.cell(1, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(1, 6).setPiece(new Piece(Color.WHITE));

        boolean isMoved = standardShape.move(Direction.left());
        assertFalse(isMoved);

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_left_canMove_SeverPiecesNearShape() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        standardShape.setShapeOnGlass(glass);

        glass.cell(0, 6).setPiece(new Piece(Color.WHITE));
        glass.cell(4, 6).setPiece(new Piece(Color.WHITE));
        glass.cell(2, 5).setPiece(new Piece(Color.WHITE));
        expGlass.cell(0, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(4, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(2, 5).setPiece(new Piece(Color.WHITE));

        boolean isMoved = standardShape.move(Direction.left());
        assertTrue(isMoved);

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(1, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_move_left_canMove_MovedToWall() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        standardShape.setShapeOnGlass(glass);

        glass.cell(0, 4).setPiece(new Piece(Color.WHITE));
        glass.cell(2, 5).setPiece(new Piece(Color.WHITE));
        expGlass.cell(0, 4).setPiece(new Piece(Color.WHITE));
        expGlass.cell(2, 5).setPiece(new Piece(Color.WHITE));

        while (standardShape.move(Direction.left())){
            standardShape.move(Direction.left());
        }

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(0, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_rotate_shapeRotated_EmptyGlass() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        standardShape.setShapeOnGlass(glass);
        assertTrue(standardShape.rotate());

        Color expColor = new Color(212, 208, 89);
        for (int x = 1; x < 5; x++) {
            expGlass.cell(x, 8).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_rotate_shapeRotated_SeveralPiecesInGlass() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        standardShape.setShapeOnGlass(glass);

        glass.cell(2, 5).setPiece(new Piece(Color.WHITE));
        glass.cell(1, 1).setPiece(new Piece(Color.WHITE));
        glass.cell(4, 5).setPiece(new Piece(Color.BLACK));
        expGlass.cell(2, 5).setPiece(new Piece(Color.WHITE));
        expGlass.cell(1, 1).setPiece(new Piece(Color.WHITE));
        expGlass.cell(4, 5).setPiece(new Piece(Color.BLACK));

        assertTrue(standardShape.rotate());

        Color expColor = new Color(212, 208, 89);
        for (int x = 1; x < 5; x++) {
            expGlass.cell(x, 8).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_rotate_shapeRotated_ShapeNearWall() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        standardShape.setShapeOnGlass(glass);

        glass.cell(2, 5).setPiece(new Piece(Color.WHITE));
        glass.cell(1, 1).setPiece(new Piece(Color.WHITE));
        glass.cell(4, 5).setPiece(new Piece(Color.BLACK));
        expGlass.cell(2, 5).setPiece(new Piece(Color.WHITE));
        expGlass.cell(1, 1).setPiece(new Piece(Color.WHITE));
        expGlass.cell(4, 5).setPiece(new Piece(Color.BLACK));

        assertTrue(standardShape.rotate());
        standardShape.move(Direction.right());
        assertTrue(standardShape.rotate());

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(3, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_rotate_shapeNotRotated() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        standardShape.setShapeOnGlass(glass);

        glass.cell(1, 8).setPiece(new Piece(Color.WHITE));
        expGlass.cell(1, 8).setPiece(new Piece(Color.WHITE));

        assertFalse(standardShape.rotate());

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_canMove_ShapeCanMove_down() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        standardShape.setShapeOnGlass(glass);

        Map<Point2D, Piece> newPos = standardShape.getVectors();
        Point2D newCenter = new Point2D.Double(standardShape.getRotationCenter().getX(), standardShape.getRotationCenter().getY() - 1);
        assertTrue(standardShape.canMove(newCenter, newPos));

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_canMove_ShapeCanMove_right() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        standardShape.setShapeOnGlass(glass);

        Map<Point2D, Piece> newPos = standardShape.getVectors();
        Point2D newCenter = new Point2D.Double(standardShape.getRotationCenter().getX() + 1, standardShape.getRotationCenter().getY());
        assertTrue(standardShape.canMove(newCenter, newPos));

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_canMove_ShapeCanMove_left() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        standardShape.setShapeOnGlass(glass);

        Map<Point2D, Piece> newPos = standardShape.getVectors();
        Point2D newCenter = new Point2D.Double(standardShape.getRotationCenter().getX() - 1, standardShape.getRotationCenter().getY());
        assertTrue(standardShape.canMove(newCenter, newPos));


        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_canMove_ShapeCantMove_down() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        standardShape.setShapeOnGlass(glass);

        glass.cell(2, 5).setPiece(new Piece(Color.WHITE));
        expGlass.cell(2, 5).setPiece(new Piece(Color.WHITE));

        Map<Point2D, Piece> newPos = standardShape.getVectors();
        Point2D newCenter = new Point2D.Double(standardShape.getRotationCenter().getX(), standardShape.getRotationCenter().getY() - 1);
        assertFalse(standardShape.canMove(newCenter, newPos));

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_canMove_ShapeCantMove_right() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        standardShape.setShapeOnGlass(glass);

        glass.cell(3, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(3, 6).setPiece(new Piece(Color.WHITE));

        Map<Point2D, Piece> newPos = standardShape.getVectors();
        Point2D newCenter = new Point2D.Double(standardShape.getRotationCenter().getX() + 1, standardShape.getRotationCenter().getY());
        assertFalse(standardShape.canMove(newCenter, newPos));

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_canMove_ShapeCantMove_left() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        standardShape.setShapeOnGlass(glass);

        glass.cell(1, 6).setPiece(new Piece(Color.WHITE));
        expGlass.cell(1, 6).setPiece(new Piece(Color.WHITE));

        Map<Point2D, Piece> newPos = standardShape.getVectors();
        Point2D newCenter = new Point2D.Double(standardShape.getRotationCenter().getX() - 1, standardShape.getRotationCenter().getY());
        assertFalse(standardShape.canMove(newCenter, newPos));

        Color expColor = new Color(212, 208, 89);
        for (int y = 6; y < 10; y++) {
            expGlass.cell(2, y).setPiece(new Piece(expColor));
        }
        assertEquals(expGlass, glass);
    }

    @Test
    public void test_canMove_shapeNotSetOnGlass() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());

        Map<Point2D, Piece> newPos = standardShape.getVectors();
        Point2D newCenter = new Point2D.Double(standardShape.getRotationCenter().getX() - 1, standardShape.getRotationCenter().getY());
        assertThrows(IllegalArgumentException.class, () -> standardShape.canMove(newCenter, newPos));
    }

    @Test
    public void test_getShapeSize() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        assertEquals(new Dimension(1, 4), standardShape.getShapeSize());
    }

    @Test
    public void test_relativeCenterPos() {
        StandardShape standardShape = StandardShape.generateI(glass.getWidth(), glass.getHeight());
        assertEquals(new Point2D.Double(0.5, 1.5), standardShape.relativeCenterPos());
    }
}
