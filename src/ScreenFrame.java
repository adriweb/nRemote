import javax.swing.*;
import java.awt.*;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.image.BufferedImage;

/**
 * @author Adriweb
 */
public class ScreenFrame extends javax.swing.JFrame {

    public ScreenFrame() {
        initComponents();
        ImageIcon icon = new ImageIcon(getClass().getResource("nremote.png"));
        this.setIconImage(icon.getImage());
        setTitle("Target Screen");
    }

    public void setScreenImage(ImageIcon icn) {
        if (icn != null) {
            float ratioX = (float) this.getWidth() / (float) icn.getIconWidth();
            float ratioY = (float) this.getHeight() / (float) icn.getIconHeight();
            float ratio = Math.min(ratioX, ratioY);
            if (ratio < 0.1) ratio = 0.1f;
            icn = scale(icn.getImage(), ratio);
            setSize(icn.getIconWidth(), icn.getIconHeight() + 20);
            SCREEN.setIcon(icn);
        }
    }

    public void scanScreen() {
        BufferedImage image = (BufferedImage) SCREEN.getIcon();
        int clr=  image.getRGB(100,40);
       // Color c = new Color(image.getRGB(x, y));
        int  red   = (clr & 0x00ff0000) >> 16;
        int  green = (clr & 0x0000ff00) >> 8;
        int  blue  =  clr & 0x000000ff;
        System.out.println("Red Color value = "+ red);
        System.out.println("Green Color value = "+ green);
        System.out.println("Blue Color value = "+ blue);
    }

    private ImageIcon scale(Image src, float ratio) {
        int w = (int) (ratio * src.getWidth(this));
        int h = (int) (ratio * src.getHeight(this));
        int type = BufferedImage.TYPE_INT_RGB;
        BufferedImage dst = new BufferedImage(w, h, type);
        Graphics2D g2 = dst.createGraphics();
        g2.drawImage(src, 0, 0, w, h, this);
        g2.dispose();
        return new ImageIcon(dst);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setSize(new java.awt.Dimension(320, 240));

        screen = new JPanel();
        SCREEN = new JLabel();

        SCREEN.setBackground(new java.awt.Color(0, 0, 0));
        SCREEN.setForeground(new java.awt.Color(255, 255, 255));
        SCREEN.setText(null);
        SCREEN.setVisible(true);
        screen.add(SCREEN);

        screen.setBackground(new java.awt.Color(0, 0, 0));
        screen.setForeground(new java.awt.Color(255, 255, 255));
        screen.setLayout(new java.awt.FlowLayout(FlowLayout.LEADING, 0, 0));

        screen.setVisible(true);

        setResizable(true);

        getContentPane().addHierarchyBoundsListener(new HierarchyBoundsListener() {
            @Override
            public void ancestorMoved(HierarchyEvent e) {
                //System.out.println(e);
            }

            @Override
            public void ancestorResized(HierarchyEvent e) {
                screen.setSize(getWidth(), getHeight());
                SCREEN.setSize(getWidth(), getHeight());
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(screen, 320, 320, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(screen, 240, 240, Short.MAX_VALUE)
        );

        setFocusable(false);

        pack();
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TrigFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrigFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrigFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrigFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrigFrame().setVisible(true);
            }
        });
    }

    private JPanel screen;
    private JLabel SCREEN;

}
