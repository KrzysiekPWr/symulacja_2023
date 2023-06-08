package gradle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Hashtable;

import javax.swing.*;

public class mapGraphicsFrame extends JFrame {
    
    mapPanel panel;
    JSlider slider;
    JButton start_stop_button;


    mapGraphicsFrame(int slide_min, int slide_max) {


        //setting up a panel for vizualization
        panel = new mapPanel();
        panel.setLayout(new BorderLayout());
        //setting up a settings menu
        JPanel settings_panel  = new JPanel();
        int settings_panel_width =  panel.panel_width - panel.drawing_space_i_dim;
        int settings_panel_height =  panel.panel_height / 2;
        settings_panel.setLayout(new BorderLayout());
        settings_panel.setPreferredSize(new Dimension(settings_panel_width, settings_panel_height));

        Color settings_color = Color.BLACK;
        settings_panel.setBackground(settings_color);
        
        
        JLabel audio_label_1 = new JLabel("Shooting Stars");

        audio_label_1.setForeground(Color.CYAN);
        

       
        //setting up a start/stop simulation button
        start_stop_button = new JButton("Stop/Start");
        start_stop_button.setBackground(settings_color);
        start_stop_button.setPreferredSize(new Dimension(settings_panel_width, settings_panel_height / 10));
        //start_stop_button.setBounds(100, 100, 100, 100);
        

        //setting up a slider for controling simulation speed
        slider = new JSlider(1 ,slide_min, slide_max, 10);
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
        slider.setLabelTable(labelTable);

        settings_panel.add(audio_label_1);
        //settings_panel.add(start_stop_button, BorderLayout.SOUTH);
        settings_panel.add(slider, BorderLayout.NORTH);
        
        panel.add(settings_panel, BorderLayout.EAST);

        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    
}


