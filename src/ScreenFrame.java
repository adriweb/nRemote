import javax.swing.*;
import java.awt.*;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.image.BufferedImage;

/**
 * @author Adriweb
 */
public class ScreenFrame extends javax.swing.JFrame {

    public boolean scanON;

    public ScreenFrame(boolean scan) {
        scanON = scan;
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
            if (scanON) scanScreen(icn);
            icn = scale(icn.getImage(), ratio);
            setSize(icn.getIconWidth(), icn.getIconHeight()+30);
            SCREEN.setIcon(icn);
        }
    }

    public void scanScreen(ImageIcon icn) {
        BufferedImage image = (BufferedImage) icn.getImage();
        int y = 0;
        String message = "";
        //Color c = new Color(image.getRGB(1, y));
        try {
            for(int x=0; x<320; x++){
                int clr = image.getRGB(x, y);

                int red = (clr & 0x00ff0000) >> 16;
                int green = (clr & 0x0000ff00) >> 8;
                int blue = clr & 0x000000ff;
                //System.out.println("RGB(" + x + "," + y + ") = (" + red + "," + green + "," + blue + ") = " + clr);
                String msg = decode(red, green, blue);
                if (!msg.equals("")) {
                    message += msg;
                } else {
                    break;
                }
            }
        } catch (Exception e) { }

        if (message.length() > 0) {
            nRemote.ircHandler.sendMessage(message);
            lastMsg = message;
            try { Remote.sendEvent("~shift_tab~"); } catch (Exception ignored) { }  //accept message -> .
        }
    }

    private String decode(int r, int g, int b) {
        int c = 0;
        c += (r >> 3) << 11;
        c += (g >> 2) << 5 ;
        c +=  b >> 3;
        char aa = Character.toChars(c >> 8)[0];
        char bb = Character.toChars(c & 0xFF)[0];
        if (c == 65535) {
            return "";
        } else {
            return Character.toString(aa) + Character.toString(bb);
        }
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
    private String lastMsg = "";
}
