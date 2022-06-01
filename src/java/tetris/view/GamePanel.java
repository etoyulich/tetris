package tetris.view;

import event.GameActionEvent;
import event.GameActionListener;
import net.miginfocom.swing.MigLayout;
import org.jetbrains.annotations.NotNull;
import tetris.*;
import tetris.shapes.AbstractShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GamePanel extends JFrame {

    private final GameModel game;
    private final Timer timer;

    private final Font font = new Font("Rounded Mplus 1c Light", Font.PLAIN, 16);
    private final Color background = new Color(30, 29, 29);
    private final Color rightPanelBackground = new Color(48, 47, 47);

    private final JFrame gameOver = createGameOver();{
        gameOver.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gameOver.setResizable(false);
    }

    private final JPanel nextShapePanel = new NextShapeView();
    private final JLabel pointsLabel = new JLabel("<html>Очки<br/><br/><pre> 0<pre/></html>", SwingConstants.CENTER);
    {
        pointsLabel.setFont(font);
        pointsLabel.setForeground(Color.WHITE);
        pointsLabel.setPreferredSize(new Dimension(42, 114));
        pointsLabel.setMaximumSize(new Dimension(42, 114));
        pointsLabel.setMinimumSize(new Dimension(42, 114));
    }

    private JFrame createGameOver() {

        JFrame jFrame = new JFrame();
        JPanel gameOverPanel = new JPanel();
        gameOverPanel.setBackground(new Color(30, 29, 29));

        gameOverPanel.setLayout(new GridLayout(3, 1, 0, 7));
        gameOverPanel.setBorder(BorderFactory.createEmptyBorder(50, 70, 50, 70));
        JLabel label = new JLabel("Игра окончена", SwingConstants.CENTER);

        label.setFont(font);
        label.setForeground(Color.WHITE);
        gameOverPanel.add(label);

        MyButton button = new MyButton("Начать заново");
        button.addActionListener(new ButtonListener());
        gameOverPanel.add(button);

        button = new MyButton("Выйти в меню");
        button.addActionListener(new ButtonListener());
        gameOverPanel.add(button);

        jFrame.add(gameOverPanel);

        return jFrame;
    }

    public GamePanel(int width, int height) throws HeadlessException {
        JPanel content = (JPanel) this.getContentPane();
        game = new GameModel(width, height, new GameController());

        content.setLayout(new MigLayout("", "20[]50[]20", "[]"));
        content.setBackground(background);

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //---------Создание стакана--------
        GlassView glassView = new GlassView(game.getGlass(), background);
        content.add(glassView);

        //---------Создание правой панели--------
        JPanel rightContent = new JPanel();
        rightContent.setLayout(new MigLayout("wrap", "[]", "[]20[]"));
        rightContent.setBackground(background);

        //-------Создание панели со следующей фигуркой--------
        JPanel nextShapePanel = new JPanel();
        nextShapePanel.setBackground(rightPanelBackground);
        nextShapePanel.setLayout(new MigLayout("wrap", "5[]5", "[]5[]"));

        JLabel nextShapeLabel = new JLabel("Следующая фигурка", SwingConstants.CENTER);
        nextShapeLabel.setFont(font);
        nextShapeLabel.setForeground(Color.WHITE);
        nextShapePanel.add(nextShapeLabel, "center");
        nextShapePanel.add(this.nextShapePanel, "center");

        rightContent.add(nextShapePanel);

        //-------Панель с очками--------
        JPanel pointsPanel = new JPanel();
        pointsPanel.setBackground(rightPanelBackground);
        pointsPanel.setLayout(new MigLayout("fill", "[]", "[]"));
        pointsPanel.add(pointsLabel, "center");
        rightContent.add(pointsPanel, "south");

        content.add(rightContent);

        addKeyListener(new KeyController());
        setVisible(true);
        pack();

        timer = new Timer(700, e -> game.makeOneStep());
        timer.setRepeats(true);
        timer.start();
    }

    private class KeyController implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            if (code == KeyEvent.VK_DOWN) {  // перемещаемся вниз
                game.getCurrentShape().move(Direction.down());
            } else if (code == KeyEvent.VK_LEFT) {  // перемещаемся влево
                game.getCurrentShape().move(Direction.left());//
            } else if (code == KeyEvent.VK_RIGHT) { // перемещаемся вправо
                game.getCurrentShape().move(Direction.right());//
            } else if (code == KeyEvent.VK_Z) {
                game.getCurrentShape().rotate();//повернуть фигурку
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}
    }

    private final class GameController implements GameActionListener {

        @Override
        public void scoresUpdated(@NotNull GameActionEvent event) {
            pointsLabel.setText("<html>Очки<br/><br/>" + game.getPoints() + "<br/><br/><br/></html>");
        }

        @Override
        public void gameIsOver(@NotNull GameActionEvent event) {
            timer.stop();
            gameOver.pack();
            gameOver.setVisible(true);
        }

        @Override
        public void nextShapeUpdated(@NotNull GameActionEvent event, @NotNull AbstractShape next) {
            ((NextShapeView) nextShapePanel).drawNextShape(next);
            pack();
        }
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if ("Начать заново".equals(command)) {
                GamePanel.this.gameOver.setVisible(false);
                GamePanel.this.setVisible(false);
                new GamePanel(GamePanel.this.game.getGlass().getWidth(), GamePanel.this.game.getGlass().getHeight());
            } else if ("Выйти в меню".equals(command)) {
                GamePanel.this.gameOver.setVisible(false);
                GamePanel.this.setVisible(false);
                new MenuView();
            }

        }
    }
}
