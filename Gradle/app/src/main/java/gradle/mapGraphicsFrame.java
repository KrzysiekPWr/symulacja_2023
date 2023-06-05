package gradle;

import javax.swing.*;

public class mapGraphicsFrame extends JFrame {
    
    mapPanel panel;

    mapGraphicsFrame() {

        panel = new mapPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    
}


