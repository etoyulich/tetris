package tetris.shapes;

import tetris.Piece;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public class NonstandardShape extends AbstractShape {

    protected NonstandardShape(Map<Point2D, Piece> vectors, Point2D center, Color color) {
        super(vectors, center, color);
    }

    static NonstandardShape generateW(int width, int height) {
        Color color = new Color(57, 148, 169);
        Map<Point2D, Piece> vectors = new HashMap<>();
        vectors.put(new Point2D.Double(0, 0), new Piece(color));
        vectors.put(new Point2D.Double(1, 1), new Piece(color));
        vectors.put(new Point2D.Double(1, 0), new Piece(color));
        vectors.put(new Point2D.Double(0, -1), new Piece(color));
        vectors.put(new Point2D.Double(-1, -1), new Piece(color));

        return new NonstandardShape(vectors, new Point2D.Double(width / 2, height + 1), color);
    }

    static NonstandardShape generateU(int width, int height) {
        Color color = new Color(104, 175, 100);
        Map<Point2D, Piece> vectors = new HashMap<>();
        vectors.put(new Point2D.Double(0, 0), new Piece(color));
        vectors.put(new Point2D.Double(1, 0), new Piece(color));
        vectors.put(new Point2D.Double(1, 1), new Piece(color));
        vectors.put(new Point2D.Double(-1, 0), new Piece(color));
        vectors.put(new Point2D.Double(-1, 1), new Piece(color));

        return new NonstandardShape(vectors, new Point2D.Double(width / 2, height), color);
    }

    static NonstandardShape generateX(int width, int height) {
        Color color = new Color(169, 57, 91);
        Map<Point2D, Piece> vectors = new HashMap<>();
        vectors.put(new Point2D.Double(0, 0), new Piece(color));
        vectors.put(new Point2D.Double(1, 0), new Piece(color));
        vectors.put(new Point2D.Double(0, 1), new Piece(color));
        vectors.put(new Point2D.Double(-1, 0), new Piece(color));
        vectors.put(new Point2D.Double(0, -1), new Piece(color));

        return new NonstandardShape(vectors, new Point2D.Double(width / 2, height + 1), color);
    }

    static NonstandardShape generateP(int width, int height) {
        Color color = new Color(179, 113, 113);
        Map<Point2D, Piece> vectors = new HashMap<>();
        vectors.put(new Point2D.Double(0, 0), new Piece(color));
        vectors.put(new Point2D.Double(0, 1), new Piece(color));
        vectors.put(new Point2D.Double(1, 1), new Piece(color));
        vectors.put(new Point2D.Double(1, 0), new Piece(color));
        vectors.put(new Point2D.Double(0, -1), new Piece(color));

        return new NonstandardShape(vectors, new Point2D.Double(width / 2, height + 1), color);
    }

    static NonstandardShape generateY(int width, int height) {
        Color color = new Color(57, 169, 148);
        Map<Point2D, Piece> vectors = new HashMap<>();
        vectors.put(new Point2D.Double(-0.5, 1.5), new Piece(color));
        vectors.put(new Point2D.Double(-0.5, 0.5), new Piece(color));
        vectors.put(new Point2D.Double(-0.5, -0.5), new Piece(color));
        vectors.put(new Point2D.Double(-0.5, -1.5), new Piece(color));
        vectors.put(new Point2D.Double(-1.5, 0.5), new Piece(color));
        return new NonstandardShape(vectors, new Point2D.Double(width / 2 + 0.5, height + 1.5), color);
    }

    static NonstandardShape generateF(int width, int height) {
        Color color = new Color(57, 102, 169);
        Map<Point2D, Piece> vectors = new HashMap<>();
        vectors.put(new Point2D.Double(0, 0), new Piece(color));
        vectors.put(new Point2D.Double(0, 1), new Piece(color));
        vectors.put(new Point2D.Double(1, 1), new Piece(color));
        vectors.put(new Point2D.Double(-1, 0), new Piece(color));
        vectors.put(new Point2D.Double(0, -1), new Piece(color));

        return new NonstandardShape(vectors, new Point2D.Double(width / 2, height), color);
    }

    static NonstandardShape generateN(int width, int height) {
        Color color = new Color(57, 169, 148);
        Map<Point2D, Piece> vectors = new HashMap<>();
        vectors.put(new Point2D.Double(0.5, 1.5), new Piece(color));
        vectors.put(new Point2D.Double(0.5, 0.5), new Piece(color));
        vectors.put(new Point2D.Double(0.5, -0.5), new Piece(color));
        vectors.put(new Point2D.Double(0.5, -1.5), new Piece(color));
        vectors.put(new Point2D.Double(1.5, 1.5), new Piece(color));
        vectors.put(new Point2D.Double(1.5, 0.5), new Piece(color));
        return new NonstandardShape(vectors, new Point2D.Double(width / 2 + 0.5, height + 1.5), color);
    }

    @Override
    protected void affectObstacles(Map<Point2D, Piece> newPos, Point2D center) {
        for (Map.Entry<Point2D, Piece> pieceEntry : newPos.entrySet()) {
            if (!getGlass().cell(getPieceCoordinate(center, pieceEntry.getKey())).isEmpty()) {
                getGlass().cell(getPieceCoordinate(center, pieceEntry.getKey())).clearCell();
            }
        }
    }

    @Override
    protected boolean canMoveWithObstacles(Point2D newCenter, Map<Point2D, Piece> newPos) {
        return true;
    }
}
