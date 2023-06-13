package gradle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Hashtable;

import javax.swing.*;

public class mapGraphicsFrame extends JFrame {
    
    mapPanel panel;
    JSlider slider;
    JButton start_stop_button;

    mapGraphicsFrame() {

        //setting up a panel for vizualization
        panel = new mapPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(panel.panel_width, panel.panel_height));

        

        //setting up a slider menu
        JPanel settings_panel  = new JPanel();
        int settings_panel_width =  panel.panel_width - panel.drawing_space_i_dim;
        int settings_panel_height =  panel.panel_height;
        settings_panel.setLayout(new BorderLayout());
        settings_panel.setPreferredSize(new Dimension(settings_panel_width, settings_panel_height));
      

        Color settings_color = Color.BLACK;
        settings_panel.setBackground(settings_color);
        
        //setting up a label for audio
        JLabel audio_label_1 = new JLabel("Shooting Stars");
        audio_label_1.setForeground(Color.CYAN);
              
        

        //setting up a slider for controling simulation speed
        slider = new JSlider(1 , 0 , 100, 10);
        slider.setBackground(settings_color);
        slider.setForeground(Color.CYAN);
        

        slider.setPreferredSize(new Dimension(settings_panel_width, settings_panel_height));
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        
        Hashtable<Integer, JLabel> labelTable = 
        new Hashtable<Integer, JLabel>();

        
        labelTable.put( 0,
        new JLabel("Stop") );
        labelTable.put( 20,
        new JLabel("Slow") );
        labelTable.put(50,
        new JLabel("Fast") );
        labelTable.put(100,
        new JLabel("Super Fast") );
        
        for(JLabel label : labelTable.values()){
            label.setForeground(Color.CYAN);
        }
        slider.setLabelTable(labelTable);
        
        
        //Here GUI is assembled
        settings_panel.add(audio_label_1);
        settings_panel.add(slider, BorderLayout.NORTH);
        panel.add(settings_panel, BorderLayout.EAST);


        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        this.pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        //this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    
}


