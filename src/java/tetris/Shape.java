package tetris;

import event.ShapeActionEvent;
import event.ShapeActionListener;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

public class Shape {

    private Glass glass;

    private Point2D rotationCenter;

    private final Color color;

    public Map<Point2D, Piece> getVectors() {
        Map<Point2D, Piece> vectors = new HashMap<>();
        for (Map.Entry<Point2D, Piece> pieceEntry : this.vectors.entrySet()) {
            Piece piece = new Piece(pieceEntry.getValue().getColor());
            vectors.put(new Point2D.Double(pieceEntry.getKey().getX(), pieceEntry.getKey().getY()), piece);
        }

        return vectors;
    }

    private Map<Point2D, Piece> vectors;

    public Shape(@NotNull Shape shape) {
        this.color = new Color(shape.color.getRGB());
        this.rotationCenter = new Point2D.Double(shape.rotationCenter.getX(), shape.rotationCenter.getY());
        this.vectors = shape.getVectors();
    }

    private Shape(Map<Point2D, Piece> vectors, Point2D center, Color color) {
        this.vectors = vectors;
        this.rotationCenter = center;
        this.color = color;
    }

    boolean setShapeOnGlass(@NotNull Glass glass) {
        if (this.glass == null) {
            this.glass = glass;
            return update(rotationCenter, vectors);
        } else {
            throw new IllegalArgumentException("У фигурки уже есть стакан");
        }
    }

    static public Shape generateL(int width, int height) {
        Color color = new Color(142, 232, 119);
        Map<Point2D, Piece> vectors = new HashMap<>();
        vectors.put(new Point2D.Double(0, 0), new Piece(color));
        vectors.put(new Point2D.Double(0, 1), new Piece(color));
        vectors.put(new Point2D.Double(0, -1), new Piece(color));
        vectors.put(new Point2D.Double(1, -1), new Piece(color));

        return new Shape(vectors, new Point2D.Double(width / 2, height + 1), color);
    }

    static public Shape generateI(int width, int height) {
        Color color = new Color(212, 208, 89);
        Map<Point2D, Piece> vectors = new HashMap<>();
        vectors.put(new Point2D.Double(-0.5, 1.5), new Piece(color));
        vectors.put(new Point2D.Double(-0.5, 0.5), new Piece(color));
        vectors.put(new Point2D.Double(-0.5, -0.5), new Piece(color));
        vectors.put(new Point2D.Double(-0.5, -1.5), new Piece(color));
        return new Shape(vectors, new Point2D.Double(width / 2 + 0.5, height + 1.5), color);
    }

    static public Shape generateO(int width, int height) {
        Color color = new Color(212, 104, 89);
        Map<Point2D, Piece> vectors = new HashMap<>();
        vectors.put(new Point2D.Double(-0.5, 0.5), new Piece(color));
        vectors.put(new Point2D.Double(0.5, 0.5), new Piece(color));
        vectors.put(new Point2D.Double(0.5, -0.5), new Piece(color));
        vectors.put(new Point2D.Double(-0.5, -0.5), new Piece(color));

        return new Shape(vectors, new Point2D.Double(width / 2 + 0.5, height + 0.5), color);
    }

    static public Shape generateJ(int width, int height) {
        Color color = new Color(89, 212, 175);
        Map<Point2D, Piece> vectors = new HashMap<>();
        vectors.put(new Point2D.Double(0, 1), new Piece(color));
        vectors.put(new Point2D.Double(0, 0), new Piece(color));
        vectors.put(new Point2D.Double(0, -1), new Piece(color));
        vectors.put(new Point2D.Double(-1, -1), new Piece(color));

        return new Shape(vectors, new Point2D.Double(width / 2, height + 1), color);
    }

    static public Shape generateZ(int width, int height) {
        Color color = new Color(71, 110, 185);
        Map<Point2D, Piece> vectors = new HashMap<>();
        vectors.put(new Point2D.Double(0, 0), new Piece(color));
        vectors.put(new Point2D.Double(-1, 0), new Piece(color));
        vectors.put(new Point2D.Double(0, -1), new Piece(color));
        vectors.put(new Point2D.Double(1, -1), new Piece(color));

        return new Shape(vectors, new Point2D.Double(width / 2, height + 1), color);
    }

    static public Shape generateT(int width, int height) {
        Color color = new Color(217, 127, 208);
        Map<Point2D, Piece> vectors = new HashMap<>();
        vectors.put(new Point2D.Double(0, 0), new Piece(color));
        vectors.put(new Point2D.Double(1, 0), new Piece(color));
        vectors.put(new Point2D.Double(0, -1), new Piece(color));
        vectors.put(new Point2D.Double(-1, 0), new Piece(color));

        return new Shape(vectors, new Point2D.Double(width / 2, height + 1), color);
    }

    static public Shape generateS(int width, int height) {
        Color color = new Color(218, 154, 79);
        Map<Point2D, Piece> vectors = new HashMap<>();
        vectors.put(new Point2D.Double(0, 0), new Piece(color));
        vectors.put(new Point2D.Double(1, 0), new Piece(color));
        vectors.put(new Point2D.Double(0, -1), new Piece(color));
        vectors.put(new Point2D.Double(-1, -1), new Piece(color));

        return new Shape(vectors, new Point2D.Double(width / 2, height + 1), color);
    }

    public boolean rotate() {
        Map<Point2D, Piece> newVectors = new HashMap<>();

        for (Map.Entry<Point2D, Piece> pieceEntry : vectors.entrySet()) {
            newVectors.put(new Point2D.Double(pieceEntry.getKey().getY(), -pieceEntry.getKey().getX()), pieceEntry.getValue());
        }
        return update(rotationCenter, newVectors);
    }

    public boolean move(Direction dir) {
        if (dir.equals(Direction.down())) {
            return update(new Point2D.Double(this.rotationCenter.getX(), this.rotationCenter.getY() - 1), vectors);
        } else if (dir.equals(Direction.right())) {
            return update(new Point2D.Double(this.rotationCenter.getX() + 1, this.rotationCenter.getY()), vectors);
        } else if (dir.equals(Direction.left())) {
            return update(new Point2D.Double(this.rotationCenter.getX() - 1, this.rotationCenter.getY()), vectors);
        } else if (dir.equals(Direction.up())) {
            return update(new Point2D.Double(this.rotationCenter.getX(), this.rotationCenter.getY() + 1), vectors);
        }
        return false;
    }

    public boolean canMove(Direction dir) {
        if (move(dir)) {
            move(dir.opposite());
            return true;
        }
        return false;
    }

    private boolean update(Point2D center, Map<Point2D, Piece> newPos) {

        List<Optional<Cell>> cells = new ArrayList<>();
        for (Map.Entry<Point2D, Piece> pieceEntry : vectors.entrySet()) {
            cells.add(Optional.ofNullable(pieceEntry.getValue().getCell()));
            pieceEntry.getValue().freeCell();
        }

        boolean canUpdate = true;
        for (Map.Entry<Point2D, Piece> pieceEntry : newPos.entrySet()) {
            if (this.glass.cell((int) (center.getX() + pieceEntry.getKey().getX()), (int) (center.getY() + pieceEntry.getKey().getY())) != null) {
                canUpdate = canUpdate && pieceEntry.getValue().canFill(glass.cell((int) (center.getX() + pieceEntry.getKey().getX()), (int) (center.getY() + pieceEntry.getKey().getY())));
            } else {
                canUpdate = false;
            }
        }

        if (canUpdate) {
            fireCellsCleared(cells);
            cells = new ArrayList<>();
            for (Map.Entry<Point2D, Piece> pieceEntry : newPos.entrySet()) {
                pieceEntry.getValue().fillCell(glass.cell((int) (center.getX() + pieceEntry.getKey().getX()), (int) (center.getY() + pieceEntry.getKey().getY())));
                cells.add(Optional.ofNullable(pieceEntry.getValue().getCell()));
            }
            vectors = newPos;
            rotationCenter = center;
            fireCellsFilled(cells);
        } else {
            for (Map.Entry<Point2D, Piece> pieceEntry : vectors.entrySet()) {
                pieceEntry.getValue().fillCell(glass.cell((int) (rotationCenter.getX() + pieceEntry.getKey().getX()), (int) (rotationCenter.getY() + pieceEntry.getKey().getY())));
            }
        }
        return canUpdate;
    }

//    public boolean getTopOfShape(){
//      //  Point top = new Point(0, 0);
//        for (Map.Entry<Point2D, Piece> pieceEntry : vectors.entrySet()) {
//            if(this.rotationCenter.getY() + pieceEntry.getValue().getCell().getPosition().getY() > glass.getHeight() + 4 && pieceEntry.getValue().getColor() != )
//                return true;
//        }
//        return false;
//    }

    //-----------------------------------------------------
    private final ArrayList<ShapeActionListener> shapeActionListeners = new ArrayList<>();

    public void addShapeActionListener(@NotNull ShapeActionListener listener) {
        shapeActionListeners.add(listener);
    }

    private void fireCellsCleared(List<Optional<Cell>> cells) {
        for (ShapeActionListener listener : shapeActionListeners) {
            ShapeActionEvent event = new ShapeActionEvent(listener);
            listener.cellsCleared(event, cells);
        }
    }

    private void fireCellsFilled(List<Optional<Cell>> cells) {
        for (ShapeActionListener listener : shapeActionListeners) {
            ShapeActionEvent event = new ShapeActionEvent(listener);
            listener.cellsFilled(event, cells);
        }
    }
}
