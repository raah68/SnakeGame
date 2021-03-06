import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainGame extends JFrame {
    //Below are class variable and they can be accessed anywhere inside this class
    private static MainGame window; //This variable will store our main window object
    private static JButton startButton; //This variable will store our start button
    private static JButton exitButton; //this variable will store our exit button

    //Constructor
    public MainGame() {
        ImagePanel imagePanel = new ImagePanel();
        setContentPane(imagePanel);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        ImageIcon icon = Tools.createIcon("res/images/rounded.jpg", true);
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } //end of constructor

    public static void main(String[] args) throws IOException{
        startMainFrame();
    }

    public static void startMainFrame(){
        EventQueue.invokeLater(() -> {
            //Main Window Frame
            window = new MainGame();
            window.setTitle("Snake");
            displayButtons();
        });
    }

    public static void displayButtons(){
        //Play Button
        startButton = Tools.createButton("Play", 100,400);
        actionForStartButton();
        //Exit Button
        exitButton = Tools.createButton("Exit", 100,450);
        actionForExitButton();
        window.getContentPane().add(startButton);
        window.getContentPane().add(exitButton);
    }

    public static void actionForStartButton(){
        startButton.addActionListener(event -> {
            JFrame gameFrame = new JFrame();
            gameFrame.setSize(800, 600);
            gameFrame.setTitle("Snake");
            gameFrame.setVisible(true);
            gameFrame.setLocationRelativeTo(null);
            Snake snakePanel = new Snake(gameFrame);
            KeyboardController.inGame = true;
            gameFrame.add(snakePanel);
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(false);
            window.dispose();
        });
    }

    public static void actionForExitButton(){
        exitButton.addActionListener(event -> {
            window.dispose();
        });
    }

} //end of the class
