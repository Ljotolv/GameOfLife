import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame {
    final String WINDOW_TITLE = "Game of life";
    final int WINDOW_WIGTH = 600;
    final int WINDOW_HEIGHT = 600;
    final int START_LOCATION = 200;
    final int PANEL_HIGHT = 60;
    Canvas canvasPanel;
    JFrame frame;

    public static void main(String[] args) {
        new GameOfLife();
    }

    GameOfLife(){
        setTitle(WINDOW_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(START_LOCATION, START_LOCATION);
        setSize(WINDOW_WIGTH, WINDOW_HEIGHT + PANEL_HIGHT);
        add(new GameLogic());
        setResizable(false);


        JButton fillButton = new JButton("Fill");
        JButton stepButton = new JButton("Step");
        JButton startButton = new JButton("Start");

        JPanel panel1 = new JPanel();
        panel1.add(fillButton);
        panel1.add(stepButton);
        panel1.add(startButton);

        getContentPane().add(BorderLayout.SOUTH, panel1);
        setVisible(true);
    }
}
