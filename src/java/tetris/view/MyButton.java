package tetris.view;

import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {

    private Color hoverBackgroundColor;
    private Color pressedBackgroundColor = new Color(72, 67, 67);
    private Color background = new Color(48, 47, 47);
    private static final Font font = new Font("Rounded Mplus 1c Light", Font.PLAIN, 16);

    public MyButton() {
        this(null);
    }

    public MyButton(String text) {
        super(text);
        super.setContentAreaFilled(false);
        setPressedBackgroundColor(pressedBackgroundColor);
        setBackground(background);
        setBorderPainted(false);
        setFocusPainted(false);
        setFont(font);
        setForeground(Color.WHITE);
        setPreferredSize(new Dimension(150, 50));
        setMinimumSize(new Dimension(150, 50));
    }


    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(pressedBackgroundColor);
        }else {
            g.setColor(getBackground());
        }
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(g);
    }

    @Override
    public void setContentAreaFilled(boolean b) {
    }

    public Color getHoverBackgroundColor() {
        return hoverBackgroundColor;
    }

    public void setHoverBackgroundColor(Color hoverBackgroundColor) {
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    public Color getPressedBackgroundColor() {
        return pressedBackgroundColor;
    }

    public void setPressedBackgroundColor(Color pressedBackgroundColor) {
        this.pressedBackgroundColor = pressedBackgroundColor;
    }
}