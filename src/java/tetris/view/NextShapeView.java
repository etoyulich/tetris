package tetris.view;

import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import tetris.Piece;
import tetris.shapes.AbstractShape;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public class NextShapeView extends JPanel{

    private final Color background = new Color(48, 47, 47);
    public NextShapeView() {
        setBackground(background);
        setVisible(true);
    }

    void drawNextShape(AbstractShape next){
        this.removeAll();
        Dimension size = next.getShapeSize();
        int margin = 1;
        int height = size.height + 2*margin;
        int width = size.width + 2*margin;

        this.setLayout(new MigLayout(new LC().width(String.valueOf(width)).height(String.valueOf(height)).wrap().gridGap("0", "0")));

        Map<Point2D, Piece> vectorMap = next.getVectors();
        Point2D center = new Point2D.Double(next.relativeCenterPos().getX() + margin, next.relativeCenterPos().getY() + margin);
        Map<Point, Piece> posMap = new HashMap<>();
        for (Map.Entry<Point2D, Piece> pieceEntry : vectorMap.entrySet()) {
            Point pos = new Point((int) (center.getX() + pieceEntry.getKey().getX()), ((int) (center.getY() + pieceEntry.getKey().getY())));
            posMap.put(pos, pieceEntry.getValue());
        }

        for (int y = height-1; y >= 0; y--) {
            JPanel row = new JPanel();
            row.setBackground(background);
            row.setLayout(new MigLayout("", "1[]0", "0[]1"));
            for (int x = 0; x < width; x++) {
                if(posMap.containsKey(new Point(x, y))){
                    row.add(newCell(posMap.get(new Point(x, y)).getColor()));
                }
                else {
                    row.add(newCell(background));
                }
            }
            this.add(row);
        }
    }

    private final static int CELL_SIZE = CellWidget.CELL_SIZE;

    private JPanel newCell(Color background){
        JPanel cell = new JPanel();
        cell.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        cell.setMaximumSize(new Dimension(CELL_SIZE, CELL_SIZE));
        cell.setMinimumSize(new Dimension(CELL_SIZE, CELL_SIZE));
        cell.setBackground(background);
        return cell;
    }

}
