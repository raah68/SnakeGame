import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tools {
    //No constructor needed here

    //This method will create a button
    public static JButton createButton(String buttonName, Integer xAxis, Integer yAxis) {
        JButton button = new JButton(buttonName);
        button.setBounds(xAxis, yAxis, 200, 30);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.black);
        button.setBorderPainted(true);
        //Create a new border
        Border border = new LineBorder(Color.ORANGE, 2, true);
        //Border border = BorderFactory.createBevelBorder();
        button.setBorder(border);
        return button;
    }//end of method createButton

    public static ImageIcon createIcon(String imageLocation, boolean originalSize){
        Image iconImage = Tools.loadImage(imageLocation);
        if(originalSize) {
            return new ImageIcon(iconImage);
        } else {
            Image resizedImage = iconImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        }
    }


    //Global method that will return a loaded image, you need to pass in the image location as the parameter
    public static BufferedImage loadImage(String imageURL) {
        BufferedImage image = null;
        try {
            File imageFile = new File(imageURL);
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.out.println("File not found exception");
        }
        return image;
    }//end of method loadImage

    //This method will crop an image
    public static void cropImageToRoundShape(String imageLocation){
        //Here is the photo to crop
        BufferedImage icon = loadImage(imageLocation);
        //Here is after the photo has been cropped
        BufferedImage rounded = makeRoundedCorner(icon, 1000);
        try {
            ImageIO.write(rounded, "png", new File("res/images/rounded.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//end of method cropImageToRounded

    //This method will crop the image to the desired shape
    public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        // This is what we want, but it only does hard-clipping, i.e. aliasing
        // g2.setClip(new RoundRectangle2D ...)
        // so instead fake soft-clipping by first drawing the desired clip shape
        // in fully opaque white with antialiasing enabled...
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));
        // ... then compositing the image on top,
        // using the white shape from above as alpha source
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return output;
    } //end of method makeRoundedCorner


}//end of class
