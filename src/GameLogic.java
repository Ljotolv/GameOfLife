import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GameLogic extends JPanel implements ActionListener{
    final int SIZE = 600;
    final int POINT_SIZE = 8;
    private Image cell;
    private boolean[][] generation = new boolean[SIZE / POINT_SIZE][SIZE / POINT_SIZE];
    private boolean[][] nextGeneration = new boolean[SIZE / POINT_SIZE][SIZE / POINT_SIZE];
    Random random = new Random();
    private boolean inGame = true;
    private Timer timer;

    public GameLogic(){
        setBackground(Color.black);
        imageLoad();
        initGame();
        setFocusable(true);
    }


    public void initGame(){
        //Задание начальных значений
        for (int x = 0; x < (SIZE / POINT_SIZE); x++) {
            for (int y = 0; y < (SIZE / POINT_SIZE); y++) {
                generation[x][y] = random.nextBoolean();
            }
        }
        timer = new Timer(250, this);
        timer.start();
    }

    //Определение изображений
    public void imageLoad(){
        ImageIcon imageBeing = new ImageIcon("img/being.png");
        cell = imageBeing.getImage();
    }

    //Счетчик соседей
    int countNeighbors(int x, int y) {
        int count = 0;
        for (int dx = -1; dx < 2; dx++) {
            for (int dy = -1; dy < 2; dy++) {
                int nX = x + dx;
                int nY = y + dy;
                nX = (nX < 0) ? (SIZE / POINT_SIZE) - 1 : nX;
                nY = (nY < 0) ? (SIZE / POINT_SIZE) - 1 : nY;
                nX = (nX > (SIZE / POINT_SIZE) - 1) ? 0 : nX;
                nY = (nY > (SIZE / POINT_SIZE) - 1) ? 0 : nY;
                count += (generation[nX][nY]) ? 1 : 0;
            }
        }
        if (generation[x][y]) { count--; }
        return count;
    }

    //Определение следующего шага
    public void evolve(){
        for (int x = 0; x < (SIZE / POINT_SIZE); x++) {
            for (int y = 0; y < (SIZE / POINT_SIZE); y++) {
                int count = countNeighbors(x, y);
                nextGeneration[x][y] = generation[x][y];
                nextGeneration[x][y] = (count == 3) ? true : nextGeneration[x][y];
                nextGeneration[x][y] = ((count < 2) || (count > 3)) ? false : nextGeneration[x][y];
            }
        }
        for (int x = 0; x < (SIZE / POINT_SIZE); x++) {
            System.arraycopy(nextGeneration[x], 0, generation[x], 0, (SIZE / POINT_SIZE));
        }
    }

    // Отрисовка клеток
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(inGame){
            for (int x = 0; x < (SIZE / POINT_SIZE); x++) {
                for (int y = 0; y < (SIZE / POINT_SIZE); y++) {
                    if(generation[x][y]){
                        g.drawImage(cell, x * POINT_SIZE, y * POINT_SIZE,this);
                    }
                }
            }
        }
    }

    //Перерисовка
    @Override
    public void actionPerformed(ActionEvent e) {
        evolve();
        repaint();
    }
}