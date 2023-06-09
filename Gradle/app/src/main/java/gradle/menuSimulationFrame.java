package gradle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class menuSimulationFrame extends JFrame implements ActionListener{
    
    JPanel menu_panel;
    JButton start_button;
    ArrayList<JTextField> text_fields_list;
    
    menuSimulationFrame () {

        
        //setting up a main parameters of simulation
        menu_panel = new JPanel();
        text_fields_list = new ArrayList<JTextField>();
        
        int menu_panel_width =  1000;
        int menu_panel_height =  600;
        
        menu_panel.setPreferredSize(new Dimension(menu_panel_width, menu_panel_height));

        GridLayout menu_panel_layout = new GridLayout(0, 2);
        menu_panel.setLayout(menu_panel_layout);
        menu_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menu_panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        

        start_button = new JButton("Start");
        start_button.setFocusable(false);
        start_button.addActionListener(this);
    
        menu_panel.add(start_button);
        menu_panel.add(new JLabel(""));

        LinkedHashMap<String, Double> settings_simulation_parameters_hash_map = new LinkedHashMap<>();
        settings_simulation_parameters_hash_map.put("Planetation", 0.04);
        settings_simulation_parameters_hash_map.put("Size", 50.0);
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
            
            //setting up a labels for text fields 
            JLabel label = new JLabel(key);
            label.setForeground(Color.BLACK);
            label.setBackground(Color.BLACK);
            //label.setFont(new Font("System", Font.ITALIC, 10));
           
            String value = settings_simulation_parameters_hash_map.get(key).toString();
            JTextField text_field = new JTextField(value);
            text_fields_list.add(text_field);
            //text_field.setFont(new Font("System", Font.ITALIC, 10));


            text_field.setForeground(Color.WHITE);
            text_field.setBackground(Color.BLACK);
            
            menu_panel.add(label);
            menu_panel.add(text_field, BorderLayout.CENTER);        
        }

        
            
        this.add(menu_panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        start_button.setEnabled(false);
        get_silmlation_parameters();
        menu_panel.setVisible(false);
        this.dispose();

    }

    ArrayList<Double> get_silmlation_parameters() {

        ArrayList<Double> settings_simulation_parameters_list = new ArrayList<Double>();
            for(JTextField text_field : text_fields_list) {
                settings_simulation_parameters_list.add(Double.parseDouble(text_field.getText()));
            }
        return settings_simulation_parameters_list;
    }
    


}
