package tetris.view;

import event.CellActionEvent;
import event.CellActionListener;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import org.jetbrains.annotations.NotNull;
import tetris.*;

import javax.swing.*;
import java.awt.*;

public class GlassView extends JPanel {
    private final Glass glass;
    private final Color cellBackground = new Color(83, 78, 78, 255);

    public GlassView(@NotNull Glass glass, Color background) {
        this.glass = glass;
        setLayout(new MigLayout(new LC().width(String.valueOf(glass.getWidth())).height(String.valueOf(glass.getHeight())).wrap().gridGap("0", "0")));
        setBackground(background);
        fillGlass(background);
    }

    private void fillGlass(Color background) {
        for (int y = glass.getHeight() - 1; y >= 0; y--) {
            JPanel row = createRow(y, background);
            add(row);
        }
    }

    private JPanel createRow(int rowIndex, Color background) {
        JPanel row = new JPanel();
        row.setLayout(new MigLayout("", "0[]1", "0[]1"));
        row.setBackground(background);

        for (int x = 0; x < glass.getWidth(); ++x) {
            Point point = new Point(x, rowIndex);
            Cell cell = glass.cell(point);

            CellWidget cellWidget = new CellWidget(cell, cellBackground);
            cell.addCellActionListener(new CellController());
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

    void changeCellColor(Color color, Cell cell){
        if(getCellWidgetByPos(cell.getPosition()) != null)
            getCellWidgetByPos(cell.getPosition()).setBackground(color);
    }

    private class CellController implements CellActionListener{

        @Override
        public void cellCleared(@NotNull CellActionEvent event, @NotNull Cell cell) {
            changeCellColor(cellBackground, cell);
            repaint();
        }

        @Override
        public void cellFilled(@NotNull CellActionEvent event, @NotNull Cell cell) {
            changeCellColor(cell.getPiece().getColor(), cell);
            repaint();
        }
    }
}
