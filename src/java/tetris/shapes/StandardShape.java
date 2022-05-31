package tetris.shapes;

import tetris.Piece;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public class StandardShape extends AbstractShape {
    protected StandardShape(Map<Point2D, Piece> vectors, Point2D center, Color color) {
        super(vectors, center, color);
    }

    @Override
    protected void affectObstacles(Map<Point2D, Piece> newPos, Point2D center) {
    }

    @Override
    protected boolean canMoveWithObstacles(Point2D newCenter, Map<Point2D, Piece> newPos) {
        return false;
    }

    static public StandardShape generateL(int width, int height) {
        Color color = new Color(142, 232, 119);
        Map<Point2D, Piece> vectors = new HashMap<>();
        vectors.put(new Point2D.Double(0, 0), new Piece(color));
        vectors.put(new Point2D.Double(0, 1), new Piece(color));
        vectors.put(new Point2D.Double(0, -1), new Piece(color));
        vectors.put(new Point2D.Double(1, -1), new Piece(color));

        return new StandardShape(vectors, new Point2D.Double(width / 2, height + 1), color);
    }

    static public StandardShape generateI(int width, int height) {
        Color color = new Color(212, 208, 89);
        Map<Point2D, Piece> vectors = new HashMap<>();
        vectors.put(new Point2D.Double(-0.5, 1.5), new Piece(color));
        vectors.put(new Point2D.Double(-0.5, 0.5), new Piece(color));
        vectors.put(new Point2D.Double(-0.5, -0.5), new Piece(color));
        vectors.put(new Point2D.Double(-0.5, -1.5), new Piece(color));
        return new StandardShape(vectors, new Point2D.Double(width / 2 + 0.5, height + 1.5), color);
    }

    static public StandardShape generateO(int width, int height) {
        Color color = new Color(212, 104, 89);
        Map<Point2D, Piece> vectors = new HashMap<>();
        vectors.put(new Point2D.Double(-0.5, 0.5), new Piece(color));
        vectors.put(new Point2D.Double(0.5, 0.5), new Piece(color));
        vectors.put(new Point2D.Double(0.5, -0.5), new Piece(color));
        vectors.put(new Point2D.Double(-0.5, -0.5), new Piece(color));

        return new StandardShape(vectors, new Point2D.Double(width / 2 + 0.5, height + 0.5), color);
    }

    static public StandardShape generateJ(int width, int height) {
        Color color = new Color(89, 212, 175);
        Map<Point2D, Piece> vectors = new HashMap<>();
        vectors.put(new Point2D.Double(0, 1), new Piece(color));
        vectors.put(new Point2D.Double(0, 0), new Piece(color));
        vectors.put(new Point2D.Double(0, -1), new Piece(color));
        vectors.put(new Point2D.Double(-1, -1), new Piece(color));

        return new StandardShape(vectors, new Point2D.Double(width / 2, height + 1), color);
    }

    static public StandardShape generateZ(int width, int height) {
        Color color = new Color(71, 110, 185);
        Map<Point2D, Piece> vectors = new HashMap<>();
        vectors.put(new Point2D.Double(0, 0), new Piece(color));
        vectors.put(new Point2D.Double(-1, 0), new Piece(color));
        vectors.put(new Point2D.Double(0, -1), new Piece(color));
        vectors.put(new Point2D.Double(1, -1), new Piece(color));

        return new StandardShape(vectors, new Point2D.Double(width / 2, height + 1), color);
    }

    static public StandardShape generateT(int width, int height) {
        Color color = new Color(217, 127, 208);
        Map<Point2D, Piece> vectors = new HashMap<>();
        vectors.put(new Point2D.Double(0, 0), new Piece(color));
        vectors.put(new Point2D.Double(1, 0), new Piece(color));
        vectors.put(new Point2D.Double(0, -1), new Piece(color));
        vectors.put(new Point2D.Double(-1, 0), new Piece(color));

        return new StandardShape(vectors, new Point2D.Double(width / 2, height + 1), color);
    }

    static public StandardShape generateS(int width, int height) {
        Color color = new Color(218, 154, 79);
        Map<Point2D, Piece> vectors = new HashMap<>();
        vectors.put(new Point2D.Double(0, 0), new Piece(color));
        vectors.put(new Point2D.Double(1, 0), new Piece(color));
        vectors.put(new Point2D.Double(0, -1), new Piece(color));
        vectors.put(new Point2D.Double(-1, -1), new Piece(color));

        return new StandardShape(vectors, new Point2D.Double(width / 2, height + 1), color);
    }

}
