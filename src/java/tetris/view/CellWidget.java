package tetris.view;

import tetris.Cell;

import javax.swing.*;
import java.awt.*;

public class CellWidget extends JPanel {

    public static final int CELL_SIZE = 25;

    public CellWidget(Cell cell, Color color) {
        this.cell = cell;
        setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        setMaximumSize(new Dimension(CELL_SIZE, CELL_SIZE));
        setMinimumSize(new Dimension(CELL_SIZE, CELL_SIZE));
        setBackground(color);
    }

    public Cell getCell() {
        return cell;
    }

    private final Cell cell;
}
