import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Snake extends JPanel implements ActionListener {
    private JFrame gameFrame = null;
    private final int B_WIDTH = 800;
    private final int B_HEIGHT = 600;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 140;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;

    public Snake(JFrame gameFrame) {
        this.gameFrame = gameFrame;
        addKeyListener(new KeyboardController());
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setLayout(null);
        loadAllIcons();
        initGame();
    }

    private void loadAllIcons() {
        ball = Tools.loadImage("res/images/dot.png");
        apple = Tools.loadImage("res/images/apple.png");
        head = Tools.loadImage("res/images/head.png");
    }

    private void initGame() {
        //Begin with 3 dots
        dots = 3;
        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
        locateApple();
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if (KeyboardController.inGame) {
            g.drawImage(apple, apple_x, apple_y, this);
            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        } else {
            gameOver(g);
            JButton mainMenuButton = Tools.createButton("Main Menu", 300, 350);
            this.add(mainMenuButton);
            mainMenuButton.addActionListener(event -> {
                buttonAction("mainmenu");
            });
            JButton exitButton = Tools.createButton("Exit Game", 300, 400);
            this.add(exitButton);
            exitButton.addActionListener(event -> {
                buttonAction("exit");
            });
        }
    }

    private void buttonAction(String action) {
        if(action.equalsIgnoreCase("mainmenu")){
            //MainGame mainWindow = new MainGame();
            MainGame.startMainFrame();
            //MainGame.displayButtons();
            this.gameFrame.dispose();
        }

        if(action.equalsIgnoreCase("exit")){
            this.gameFrame.dispose();
        }
    }

    //Method to write the Game over text on the screen
    private void gameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    private void checkApple() {
        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            dots++;
            locateApple();
        }
    }

    //Method to indicate the movement of the snake
    private void move() {
        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (KeyboardController.leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (KeyboardController.rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (KeyboardController.upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (KeyboardController.downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    //Method to check if you hit the wall or the snake body
    private void checkCollision() {
        for (int z = dots; z > 0; z--) {
            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                KeyboardController.inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            KeyboardController.inGame = false;
        }

        if (y[0] < 0) {
            KeyboardController.inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            KeyboardController.inGame = false;
        }

        if (x[0] < 0) {
            KeyboardController.inGame = false;
        }

        if (!KeyboardController.inGame) {
            timer.stop();
        }
    }

    private void locateApple() {
        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (KeyboardController.inGame) {
            checkApple();
            checkCollision();
            move();
        }
        repaint();
    }

}//end of class
