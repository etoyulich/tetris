package tetris.view;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuView extends JFrame {

    private int width = 10;
    private int height = 20;
    private final int maxWidth = 30;
    private final int maxHeight = 30;
    private final Color background = new Color(30, 29, 29);
    private final Font font = new Font("Rounded Mplus 1c Light", Font.PLAIN, 16);
    private final JSpinner widthSpinner = createSpinner(10, maxWidth);
    private final JSpinner heightSpinner = createSpinner(20, maxHeight);

    private final ArrayList<String> buttonName = new ArrayList<>();
    {
        buttonName.add("Начать игру");
        buttonName.add("Размер стакана");
        buttonName.add("Выйти");
    }

    private JPanel createMenu(){

        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(background);

        menuPanel.setLayout(new GridLayout(3, 1, 0, 5));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(50, 70, 50, 70));

        for (String name: buttonName) {
            MyButton button = new MyButton(name);
            menuPanel.add(button);
            button.addActionListener(new MenuListener());
        }

        return menuPanel;
    }

    private JSpinner createSpinner(int min, int max){
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(min, min, max, 1));
        Component c = spinner.getEditor().getComponent(0);
        spinner.setFont(font);
        spinner.setBorder(new BorderUIResource.EmptyBorderUIResource(0, 0, 0, 0));
        c.setForeground(Color.WHITE);
        c.setBackground(new Color(48, 47, 47));
        ((JFormattedTextField) c).setEditable(false);
        return spinner;
    }

    private JPanel createSettings(){
        JPanel settings = new JPanel();
        settings.setBackground(background);

        settings.setLayout(new GridLayout(5, 1, 0, 7));
        settings.setBorder(BorderFactory.createEmptyBorder(50, 70, 50, 70));

        JLabel widthLabel = new JLabel("Выберите ширину стакана (от 10 до 30)");
        widthLabel.setFont(font);
        widthLabel.setForeground(Color.WHITE);
        settings.add(widthLabel);

        settings.add(widthSpinner);

        JLabel heightLabel = new JLabel("Выберите высоту стакана (от 20 до 30)");
        heightLabel.setForeground(Color.WHITE);
        heightLabel.setFont(font);
        settings.add(heightLabel);

        settings.add(heightSpinner);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 2, 5, 5));
        buttons.setBackground(background);
        MyButton button = new MyButton("Ок");
        buttons.add(button);
        button.addActionListener(new MenuListener());

        button = new MyButton("Отмена");
        buttons.add(button);
        button.addActionListener(new MenuListener());

        settings.add(buttons);

        return settings;
    }

    public MenuView(){

        JPanel menuPanel = createMenu();

        this.getContentPane().add(menuPanel);
        pack();
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class MenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if ("Начать игру".equals(command)) {
                MenuView.this.setVisible(false);
                new GamePanel(width, height);
            }
            else if ("Размер стакана".equals(command)) {
                MenuView.this.getContentPane().remove(0);
                MenuView.this.getContentPane().add(createSettings());
                MenuView.this.pack();
            }
            else if ("Выйти".equals(command)){
                System.exit(0);
            }
            else if("Ок".equals(command)){
                height = (int) heightSpinner.getValue();
                width = (int) widthSpinner.getValue();

                MenuView.this.getContentPane().remove(0);
                MenuView.this.getContentPane().add(createMenu());
                MenuView.this.pack();
            }
            else if("Отмена".equals(command)){
                heightSpinner.setValue(height);
                widthSpinner.setValue(width);

                MenuView.this.getContentPane().remove(0);
                MenuView.this.getContentPane().add(createMenu());
                MenuView.this.pack();
            }

        }
    }
}
