import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    //Constructor
    public ImagePanel(){
        JPanel panel = new CirclePanel();
        add(panel);
    }//end of constructor

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D image2d = (Graphics2D) g;
        double alpha = 0.9; //draw half transparent
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)alpha );
        image2d.setComposite(ac);
        Image image = Tools.loadImage("res/images/background.png");
        image2d.drawImage(image, 0,0, 800, 600, this);
    }//end of method paintComponent

}
