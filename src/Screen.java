
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Levak
 */
public class Screen {
    public static int width = 320, height = 240;
    public static BufferedImage generateErrorScreen(String errTitle) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int cx = width/2, cy = height/2;
        Graphics gc = img.getGraphics();
        gc.setColor(new Color(0xFFFFFF));
        gc.fillRect(0, 0, width, height);
        gc.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        ((Graphics2D)gc).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gc.setColor(new Color(0xC0C0C0));
        int titleW = gc.getFontMetrics().stringWidth(errTitle);
        gc.drawString(errTitle, cx-titleW/2, cy);
        return img;
    }
    
    public static BufferedImage generateLoadingScreen(String loadTitle) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int cx = width/2, cy = height/2;
        Graphics gc = img.getGraphics();
        gc.setColor(new Color(0xFFFFFF));
        gc.fillRect(0, 0, width, height);
        gc.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
        ((Graphics2D)gc).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gc.setColor(new Color(0xC0C0C0));
        int titleW = gc.getFontMetrics().stringWidth(loadTitle);
        gc.drawString(loadTitle, cx-titleW/2, cy+cy/2);
        Image load = new ImageIcon(Screen.class.getResource("load.png")).getImage();
        gc.drawImage(load, cx-load.getWidth(null)/2, cy-load.getHeight(null)/2, null);
        return img;
    }
}
