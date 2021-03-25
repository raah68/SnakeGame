import javax.swing.*;
import java.awt.*;

public class CirclePanel extends JPanel {
    //This is the constructor
    public CirclePanel(){
        setBounds(100, 280, 200, 200);
        ImageIcon icon = Tools.createIcon("res/images/rounded.jpg", false);
        JLabel lbl = new JLabel(icon);
        add(lbl);
    }//end of the constructor

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.ORANGE);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(48,4, 103, 103);
        //g.fillOval (48, 4, 103, 103);
    }//end of the method paintComponent

}//end of the class
