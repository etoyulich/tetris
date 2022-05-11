package tetris.view;

import event.GlassActionEvent;
import event.GlassActionListener;
import net.miginfocom.swing.MigLayout;
import org.jetbrains.annotations.NotNull;
import tetris.Cell;
import tetris.Glass;
import tetris.Piece;
import tetris.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Map;

public class GlassView extends JPanel {
    private final Glass glass;

    public GlassView(@NotNull Glass glass, Color background, Color cellBackground) {
        this.glass = glass;
        setLayout(new GridLayout(glass.getHeight(), glass.getWidth(), 0, 0));
        setBackground(background);
        fillGlass(background, cellBackground);
        glass.addGlassActionListener(new GlassController());
    }

    private void fillGlass(Color background, Color cellBackground) {
        for (int y = glass.getHeight() - 1; y >= 0; y--) {
            JPanel row = createRow(y, background, cellBackground);
            add(row);
        }
    }

    private JPanel createRow(int rowIndex, Color background, Color cellBackground) {
        JPanel row = new JPanel();
        row.setLayout(new MigLayout("", "[]1", "1[]0"));
        row.setBackground(background);

        for (int x = 0; x < glass.getWidth(); ++x) {
            Point point = new Point(x, rowIndex);
            Cell cell = glass.cell(point);

            CellWidget cellWidget = new CellWidget(cell, cellBackground);
            row.add(cellWidget);
        }
        return row;
    }

    private CellWidget getCellWidget(@NotNull Cell cell) {
        for (Component widget : this.getComponents()) {
            JPanel panel = (JPanel) widget;
            for (Component comp : panel.getComponents()) {
                if (((CellWidget) comp).getCell() == cell) {
                     return (CellWidget) comp;
                }
            }
        }
        return null;
    }

    private CellWidget getCellWidgetByPos(@NotNull Point pos) {
        for (Component widget : this.getComponents()) {
            JPanel panel = (JPanel) widget;
            for (Component comp : panel.getComponents()) {
                if (((CellWidget) comp).getCell().getPosition().equals(pos)) {
                    return (CellWidget) comp;
                }
            }
        }
        return null;
    }

    void drawNextShape(Shape next){
        for (Component widget : this.getComponents()) {
            JPanel panel = (JPanel) widget;
            for (Component comp : panel.getComponents()) {
                CellWidget cellWidget = (CellWidget) comp;
                cellWidget.setBackground(new Color(48, 47, 47));
            }
        }

        Map<Point2D, Piece> pieceMap = next.getVectors();
        double center = 2;
        for (Map.Entry<Point2D, Piece> pieceEntry : pieceMap.entrySet()) {
            CellWidget cellWidget = getCellWidgetByPos(new Point((int) (center + pieceEntry.getKey().getX()), ((int) (center + pieceEntry.getKey().getY()))));
            cellWidget.setBackground(pieceEntry.getValue().getColor());
        }
        repaint();
    }

    void changeCellColor(Color color, Cell cell){
        if(getCellWidgetByPos(cell.getPosition()) != null)
            getCellWidgetByPos(cell.getPosition()).setBackground(color);
    }

    private class GlassController implements GlassActionListener{
        @Override
        public void rowsCleared(@NotNull GlassActionEvent event) {
            for (int y = 0; y < GlassView.this.glass.getHeight(); y++) {
                for (int x = 0; x < GlassView.this.glass.getWidth(); x++) {
                    CellWidget cellWidget = GlassView.this.getCellWidget(GlassView.this.glass.cell(x, y));
                    if(GlassView.this.glass.cell(x, y) != null && cellWidget!= null){
                        if(GlassView.this.glass.cell(x, y).isEmpty())
                            cellWidget.setBackground(new Color(83, 78, 78, 255));
                        else{
                            cellWidget.setBackground(GlassView.this.glass.cell(x, y).getPiece().getColor());
                        }
                    }
                }
            }
        }
    }
}
