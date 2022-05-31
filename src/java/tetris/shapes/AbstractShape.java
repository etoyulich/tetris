package tetris.shapes;

import org.jetbrains.annotations.NotNull;
import tetris.Cell;
import tetris.Direction;
import tetris.Glass;
import tetris.Piece;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;

abstract public class AbstractShape implements Cloneable {

    public Map<Point2D, Piece> getVectors() {
        Map<Point2D, Piece> vectors = new HashMap<>();
        for (Map.Entry<Point2D, Piece> pieceEntry : this.vectors.entrySet()) {
            Piece piece = new Piece(pieceEntry.getValue().getColor());
            vectors.put(new Point2D.Double(pieceEntry.getKey().getX(), pieceEntry.getKey().getY()), piece);
        }

        return vectors;
    }

    private int getWidthOfShape() {
        double maxAbscissa = -rotationCenter.getX();
        double minAbscissa = rotationCenter.getX();
        for (Point2D pointEntry : vectors.keySet()) {
            if (getPieceCoordinate(rotationCenter, pointEntry).getX() >= maxAbscissa)
                maxAbscissa = getPieceCoordinate(rotationCenter, pointEntry).getX();
            if (getPieceCoordinate(rotationCenter, pointEntry).getX() <= minAbscissa)
                minAbscissa = getPieceCoordinate(rotationCenter, pointEntry).getX();
        }

        return (int) (maxAbscissa - minAbscissa + 1);
    }

    private int getHeightOfShape() {
        double maxOrdinate = -rotationCenter.getY();
        double minOrdinate = rotationCenter.getY();
        for (Point2D pointEntry : vectors.keySet()) {
            if (getPieceCoordinate(rotationCenter, pointEntry).getY() >= maxOrdinate)
                maxOrdinate = getPieceCoordinate(rotationCenter, pointEntry).getY();
            if (getPieceCoordinate(rotationCenter, pointEntry).getY() <= minOrdinate)
                minOrdinate = getPieceCoordinate(rotationCenter, pointEntry).getY();
        }

        return (int) (maxOrdinate - minOrdinate + 1);
    }

    protected Glass getGlass() {
        return glass;
    }

    private Glass glass;

    public Point2D getRotationCenter() {
        return new Point2D.Double(rotationCenter.getX(), rotationCenter.getY());
    }

    private Point2D rotationCenter;

    private Map<Point2D, Piece> vectors;

    private final Color color;

    protected AbstractShape(Map<Point2D, Piece> vectors, Point2D center, Color color) {
        this.vectors = vectors;
        this.rotationCenter = center;
        this.color = color;
    }

    public boolean setShapeOnGlass(@NotNull Glass glass) {
        if (this.glass == null) {
            this.glass = glass;
            return update(rotationCenter, vectors);
        } else {
            throw new IllegalArgumentException("У фигурки уже есть стакан");
        }
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
            return update(new Point2D.Double(rotationCenter.getX(), rotationCenter.getY() - 1), vectors);
        } else if (dir.equals(Direction.right())) {
            return update(new Point2D.Double(rotationCenter.getX() + 1, rotationCenter.getY()), vectors);
        } else if (dir.equals(Direction.left())) {
            return update(new Point2D.Double(rotationCenter.getX() - 1, rotationCenter.getY()), vectors);
        }
        return false;
    }

    public boolean canMove(Point2D newCenter, Map<Point2D, Piece> newPos) {
        boolean canUpdate = true;
        if (glass == null)
            throw new IllegalArgumentException("Фигурка не установлена на стакан.");

        for (Map.Entry<Point2D, Piece> pieceEntry : newPos.entrySet()) {
            canUpdate = canUpdate && glass.containsCoordinate(getPieceCoordinate(newCenter, pieceEntry.getKey()));
        }

        if (canUpdate) {
            for (Map.Entry<Point2D, Piece> pieceEntry : vectors.entrySet()) {
                pieceEntry.getValue().freeCell();
            }

            for (Map.Entry<Point2D, Piece> pieceEntry : newPos.entrySet()) {
                Cell filledInCell = getGlass().cell(getPieceCoordinate(newCenter, pieceEntry.getKey()));
                canUpdate = canUpdate && (pieceEntry.getValue().canFill(filledInCell));
            }

            if (!canUpdate)
                canUpdate = canMoveWithObstacles(newCenter, newPos);

            for (Map.Entry<Point2D, Piece> pieceEntry : vectors.entrySet()) {
                Cell filledCell = glass.cell(getPieceCoordinate(rotationCenter, pieceEntry.getKey()));
                pieceEntry.getValue().fillCell(filledCell);
            }
        }
        return canUpdate;
    }

    private boolean update(Point2D newCenter, Map<Point2D, Piece> newPos) {
        if (canMove(newCenter, newPos)) {
            for (Map.Entry<Point2D, Piece> pieceEntry : vectors.entrySet()) {
                pieceEntry.getValue().freeCell();
            }
            affectObstacles(newPos, newCenter);
            for (Map.Entry<Point2D, Piece> pieceEntry : newPos.entrySet()) {
                Cell filledCell = glass.cell(getPieceCoordinate(newCenter, pieceEntry.getKey()));
                pieceEntry.getValue().fillCell(filledCell);
            }
            vectors = newPos;
            rotationCenter = newCenter;
            return true;
        }
        return false;
    }

    abstract protected void affectObstacles(Map<Point2D, Piece> newPos, Point2D center);

    abstract protected boolean canMoveWithObstacles(Point2D newCenter, Map<Point2D, Piece> newPos);

    public Dimension getShapeSize() {
        return new Dimension(getWidthOfShape(), getHeightOfShape());
    }

    public Point2D relativeCenterPos() {
        Double minx = null;
        Double miny = null;
        for (Point2D vector : vectors.keySet()) {
            if (minx == null || vector.getX() < minx) {
                minx = vector.getX();
            }
            if (miny == null || vector.getY() < miny) {
                miny = vector.getY();
            }
        }

        minx = -minx;
        miny = -miny;

        return new Point2D.Double(minx, miny);
    }

    protected Point getPieceCoordinate(Point2D center, Point2D shift) {
        return new Point((int) (center.getX() + shift.getX()), (int) (center.getY() + shift.getY()));
    }

    @Override
    public AbstractShape clone() {
        try {
            AbstractShape clone = (AbstractShape) super.clone();
            clone.rotationCenter = new Point2D.Double(this.rotationCenter.getX(), this.rotationCenter.getY());
            clone.vectors = getVectors();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
