package teleton;

import java.awt.EventQueue;
import java.net.InetAddress;
import javax.swing.JFrame;
import org.jvnet.substance.SubstanceLookAndFeel;

public class Teleton {

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    System.out.println(InetAddress.getLocalHost().getHostAddress());
                    System.out.println(InetAddress.getLocalHost().getHostName());
                    JFrame.setDefaultLookAndFeelDecorated(true);
                    SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.BusinessSkin");
                    //SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceTerracotta");

                } catch (Exception e) {
                }
                Splash.setDefaultLookAndFeelDecorated(false);
                new Splash().setVisible(true);
                
            }
        });
    }
}
