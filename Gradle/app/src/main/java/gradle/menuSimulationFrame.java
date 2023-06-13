package gradle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


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

        //STAR BUTTON
        start_button = new JButton("Start simulation!");
        start_button.setForeground(Color.CYAN);
        start_button.setBackground(Color.BLACK);
        start_button.setOpaque(true);
        start_button.setFocusable(false);
        start_button.addActionListener(this);

        
        
        
        //List that stores names of parameters and default values
        LinkedHashMap<String, Double> settings_simulation_parameters_hash_map = new LinkedHashMap<>();
        settings_simulation_parameters_hash_map.put("Planetation", 0.025);
        settings_simulation_parameters_hash_map.put("Size", 100.0);
        settings_simulation_parameters_hash_map.put("Stars quantity", 5.0);
        settings_simulation_parameters_hash_map.put("Black holes quantity", 4.0);
        settings_simulation_parameters_hash_map.put("Aggresive civilizations quantity", 3.0);
        settings_simulation_parameters_hash_map.put("Pacifistic civilizations quantity", 5.0);
        
        settings_simulation_parameters_hash_map.put("Default black holes sucking range", 2.0);
        settings_simulation_parameters_hash_map.put("Default stars power rate", 10.0);
        settings_simulation_parameters_hash_map.put("Default lower bound of resources for planets", 100.0);
        settings_simulation_parameters_hash_map.put("Default upper bound of resources for planets", 10000.0);
        settings_simulation_parameters_hash_map.put("Default stars shining range", 2.0);

        settings_simulation_parameters_hash_map.put("Default pacifistic civilizations mining abilities", 50.0);
        settings_simulation_parameters_hash_map.put("Default pacifistic civilizations ship price", 200.0);
        settings_simulation_parameters_hash_map.put("Default pacifistic civilizations ship fuel", 70.0);
        settings_simulation_parameters_hash_map.put("Default pacifistic civilizations ship speed", 10.0);

        settings_simulation_parameters_hash_map.put("Default aggressive civilizations mining abilities", 10.0);
        settings_simulation_parameters_hash_map.put("Default aggressive civilizations ship price", 600.0);
        settings_simulation_parameters_hash_map.put("Default aggressive civilizations ship fuel", 7.0);
        settings_simulation_parameters_hash_map.put("Default aggressive civilizations ship speed", 5.0);
        settings_simulation_parameters_hash_map.put("Default aggressive ships attack power", 100.0);

        
         for(String key : settings_simulation_parameters_hash_map.keySet()) {
            
            //setting up a labels for text fields 
            JLabel label = new JLabel(key, JLabel.CENTER);
            label.setForeground(Color.CYAN);
            label.setBackground(Color.BLACK);
            label.setOpaque(true);
            //label.setFont(new Font("System", Font.ITALIC, 10));
           
            String value = settings_simulation_parameters_hash_map.get(key).toString();
            JTextField text_field = new JTextField(value);
            text_field.setHorizontalAlignment(SwingConstants.CENTER);
            text_field.setCaretColor(Color.CYAN);
            text_field.setCaretPosition(value.length());

            //text_field.setFont(new Font("System", Font.ITALIC, 10));

            text_field.setForeground(Color.CYAN);
            text_field.setBackground(Color.BLACK);
            text_field.setOpaque(true);
            
            text_fields_list.add(text_field);
            
            //adding label - text that descries text field
            menu_panel.add(label);

            //adding text field
            menu_panel.add(text_field, BorderLayout.CENTER);        
        }

        //RADNOM BUTTON
        JButton random_button = new JButton("Random parameters");
        random_button.setForeground(Color.CYAN);
        random_button.setBackground(Color.BLACK);
        random_button.setOpaque(true);
        random_button.setFocusable(false);
        random_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                

                Random rand = new Random();
                DecimalFormat df = new DecimalFormat("#.#####");


                for(String key : settings_simulation_parameters_hash_map.keySet()) {

                    if(key == "Planetation") {
                        text_fields_list.get(0).setText(df.format((rand.nextDouble() / 10)).replace(",", "."));
                    }
                    else if(key == "Size") {
                        text_fields_list.get(1).setText(String.valueOf(rand.nextInt(15, 200)));
                    }
                    else if(key == "Stars quantity") {
                        text_fields_list.get(2).setText(String.valueOf(rand.nextInt(10)));
                    }
                    else if(key == "Black holes quantity") {
                        text_fields_list.get(3).setText(String.valueOf(rand.nextInt(10)));
                    }
                    else if(key == "Aggresive civilizations quantity") {
                        text_fields_list.get(4).setText(String.valueOf(rand.nextInt(10)));
                    }
                    else if(key == "Pacifistic civilizations quantity") {
                        text_fields_list.get(5).setText(String.valueOf(rand.nextInt(10)));
                    }
                    else if(key == "Default black holes sucking range") {
                        text_fields_list.get(6).setText(String.valueOf(rand.nextInt(10)));
                    }
                    else if(key == "Default stars power rate") {
                        text_fields_list.get(7).setText(String.valueOf(rand.nextInt(10)));
                    }
                    else if(key == "Default lower bound of resources for planets") {
                        text_fields_list.get(8).setText(String.valueOf(rand.nextInt(100)));
                    }
                    else if(key == "Default upper bound of resources for planets") {
                        text_fields_list.get(9).setText(String.valueOf(rand.nextInt(100, 10000)));
                    }
                    else if(key == "Default stars shining range") {
                        text_fields_list.get(10).setText(String.valueOf(rand.nextInt(10)));
                    }

                    else if(key == "Default pacifistic civilizations mining abilities" 
                    || key == "Default aggressive civilizations mining abilities") {
                        text_fields_list.get(11).setText(String.valueOf(rand.nextInt(100)));
                    }
                    else if(key == "Default pacifistic civilizations ship price"
                    || key == "Default aggressive civilizations ship price") {
                        text_fields_list.get(12).setText(String.valueOf(rand.nextInt(40, 500)));
                    }
                    else if(key == "Default pacifistic civilizations ship fuel"
                    || key == "Default aggressive civilizations ship fuel") {
                        text_fields_list.get(13).setText(String.valueOf(rand.nextInt(100)));
                    }
                    else if(key == "Default pacifistic civilizations ship speed"
                    || key == "Default aggressive civilizations ship speed") {
                        text_fields_list.get(15).setText(String.valueOf(rand.nextInt(100)));
                    }

                }
                
            }
        });

        menu_panel.add(start_button);
        menu_panel.add(random_button);


        this.add(menu_panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        this.pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        //this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        start_button.setEnabled(false);
        get_simulation_parameters();
        menu_panel.setVisible(false);
        this.dispose();

    }

    ArrayList<Double> get_simulation_parameters() {

        ArrayList<Double> settings_simulation_parameters_list = new ArrayList<Double>();
            for(JTextField text_field : text_fields_list) {
                if(text_field.getText().equals("")) {
                    settings_simulation_parameters_list.add(0.0);
                    continue;
                }
                settings_simulation_parameters_list.add(Double.parseDouble(text_field.getText()));
            }
        return settings_simulation_parameters_list;
    }
    


}
