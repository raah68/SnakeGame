import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardController extends KeyAdapter {
    //Set the default state of the direction
    public static boolean rightDirection = true;
    public static boolean leftDirection = false;
    public static boolean upDirection = false;
    public static boolean downDirection = false;
    public static boolean inGame = true;

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if ((key == KeyEvent.VK_LEFT) && (rightDirection != true)) {
            leftDirection = true;
            upDirection = false;
            downDirection = false;
        }

        if ((key == KeyEvent.VK_RIGHT) && (leftDirection != true)) {
            rightDirection = true;
            upDirection = false;
            downDirection = false;
        }

        if ((key == KeyEvent.VK_UP) && (downDirection != true)) {
            upDirection = true;
            rightDirection = false;
            leftDirection = false;
        }

        if ((key == KeyEvent.VK_DOWN) && (upDirection != true)) {
            downDirection = true;
            rightDirection = false;
            leftDirection = false;
        }
    }
}


