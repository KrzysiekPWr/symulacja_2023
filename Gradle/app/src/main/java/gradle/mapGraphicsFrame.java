package gradle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.swing.*;

public class mapGraphicsFrame extends JFrame {
    
    mapPanel panel;
    JSlider slider;
    JButton start_stop_button;
    List <JTextField> settings_simulation_parameters_text_fields = new ArrayList<JTextField>(); 

    mapGraphicsFrame() {


        //setting up a panel for vizualization
        panel = new mapPanel();
        panel.setLayout(new BorderLayout());
        //setting up a settings menu
        JPanel settings_panel  = new JPanel();
        int settings_panel_width =  panel.panel_width - panel.drawing_space_i_dim;
        int settings_panel_height =  panel.panel_height / 2;
        settings_panel.setLayout(new BorderLayout());
        settings_panel.setPreferredSize(new Dimension(settings_panel_width, settings_panel_height));

        HashMap<String, Double> settings_simulation_parameters_hash_map = new HashMap<String, Double>();
        settings_simulation_parameters_hash_map.put("Size", 100.0);
        settings_simulation_parameters_hash_map.put("Planetation", 0.02);
        settings_simulation_parameters_hash_map.put("Stars quantity", 2.0);
        settings_simulation_parameters_hash_map.put("Black holes quantity", 2.0);
        settings_simulation_parameters_hash_map.put("Aggresive civilizations quantity", 2.0);
        settings_simulation_parameters_hash_map.put("Pacifistic civilizations quantity", 2.0);
        settings_simulation_parameters_hash_map.put("Default black holes sucking range", 2.0);
        settings_simulation_parameters_hash_map.put("Default stars power rate", 10.0);
        settings_simulation_parameters_hash_map.put("Default lower bound of resources for planets", 100.0);
        settings_simulation_parameters_hash_map.put("Default upper bound of resources for planets", 1000.0);
        settings_simulation_parameters_hash_map.put("Default stars shining range", 2.0);
        settings_simulation_parameters_hash_map.put("Default pacifistic civilizations mining abilities", 15.0);
        settings_simulation_parameters_hash_map.put("Default pacifistic civilizations ship price", 40.0);
        settings_simulation_parameters_hash_map.put("Default pacifistic civilizations ship fuel", 7.0);
        settings_simulation_parameters_hash_map.put("Default pacifistic civilizations ship jump cooldown", 4.0);
        settings_simulation_parameters_hash_map.put("Default pacifistic civilizations ship speed", 5.0);
        settings_simulation_parameters_hash_map.put("Default aggressive civilizations mining abilities", 8.0);
        settings_simulation_parameters_hash_map.put("Default aggressive civilizations ship price", 50.0);
        settings_simulation_parameters_hash_map.put("Default aggressive civilizations ship fuel", 7.0);
        settings_simulation_parameters_hash_map.put("Default aggressive civilizations ship jump cooldown", 8.0);
        settings_simulation_parameters_hash_map.put("Default aggressive civilizations ship speed", 5.0);
        settings_simulation_parameters_hash_map.put("Default aggressive ships attack power", 1.1);

        for(String key : settings_simulation_parameters_hash_map.keySet()) {
            JLabel label = new JLabel(key + ": ");
            JTextField text_field = new JTextField();
            settings_simulation_parameters_text_fields.add(text_field);
        }
        

        for(JTextField text_field : settings_simulation_parameters_text_fields) {
            text_field.setPreferredSize(new Dimension(settings_panel_width, settings_panel_height / 10));
            text_field.setCaretColor(Color.CYAN);
            
            settings_panel.add(text_field, BorderLayout.SOUTH);
        }

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


