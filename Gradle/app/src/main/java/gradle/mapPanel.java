package gradle;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;


public class mapPanel extends  JPanel{

    
    
    private Timer timer;
    private int interval = 100; // Update interval in milliseconds

    int panel_width = 1000;
    int panel_height = 600;

    int drawing_space_i_dim = panel_width - 100;
    int drawing_space_j_dim = panel_height;

    emptySpace map_area[][];
    emptySpace previous_map_area[][];

    Image background;
    Image pac_ship;
    Image agg_ship;
    Image pac_planet;
    Image agg_planet;
    Image emp_planet;
    Image star;
    Image black_hole;

    ImageIcon pac_ship_icon;

    ArrayList<Image> images = new ArrayList<Image>();
    
    mapPanel () {

        //creating elements to be displayed as images
        java.net.URL mapUrl = getClass().getResource("/map_background.png");
        ImageIcon background_icon = new ImageIcon(mapUrl);
        background = background_icon.getImage();

        java.net.URL pac_shipUrl = getClass().getResource("/pac_ship.png");
        ImageIcon pac_ship_icon = new ImageIcon(pac_shipUrl);
        pac_ship = pac_ship_icon.getImage();

        java.net.URL agg_shipUrl = getClass().getResource("/agg_ship.png");
        ImageIcon agg_ship_icon = new ImageIcon(agg_shipUrl);
        agg_ship = agg_ship_icon.getImage();

        java.net.URL agg_planetUrl = getClass().getResource("/agg_planet.png");
        ImageIcon agg_planet_icon = new ImageIcon(agg_planetUrl);
        agg_planet = agg_planet_icon.getImage();

        java.net.URL pac_planetUrl = getClass().getResource("/pac_planet.png");
        ImageIcon pac_planet_icon = new ImageIcon(pac_planetUrl);
        pac_planet = pac_planet_icon.getImage();

        java.net.URL emp_planetUrl = getClass().getResource("/emp_planet.png");
        ImageIcon emp_planet_icon = new ImageIcon(emp_planetUrl);
        emp_planet = emp_planet_icon.getImage();

        java.net.URL black_holeUrl = getClass().getResource("/black_hole.png");
        ImageIcon black_hole_icon = new ImageIcon(black_holeUrl);
        black_hole = black_hole_icon.getImage();

        java.net.URL starUrl = getClass().getResource("/star.png");
        ImageIcon star_icon = new ImageIcon(starUrl);
        star = star_icon.getImage();
       
        this.setPreferredSize(new Dimension(panel_width, panel_height));
        this.setBackground(Color.black);

        pac_ship = scaleImage(pac_ship, 10, 10);
        agg_ship = scaleImage(agg_ship, 10, 10);
        pac_planet = scaleImage(pac_planet, 10, 10);
        agg_planet = scaleImage(agg_planet, 10, 10);
        emp_planet = scaleImage(emp_planet, 10, 10);
        star = scaleImage(star, 10, 10);
        black_hole = scaleImage(black_hole, 10, 10);
        
    }

    public void paint(Graphics g) {

        if(map_area == null) {
            return;
        }
        Graphics2D g2D = (Graphics2D) g;

        drawing_space_i_dim = panel_width - 200;
        drawing_space_j_dim = panel_height;

        // int scale_i = 40;
        // int scale_j = 40;

        // pac_ship_icon = new ImageIcon(pac_ship);
        // pac_ship = pac_ship_icon.getImage();
        // pac_ship = scaleImage(pac_ship, scale_i, scale_j);

        //pac_ship = scaleImage(pac_ship, scale_i, scale_j);

        
        //Scaling the background and images to fit nicely the panel
        panel_height = this.getHeight();
        panel_width = this.getWidth();     
    

        // pac_ship = pac_ship.getScaledInstance(scale, scale, Image.SCALE_SMOOTH);
        // agg_ship = agg_ship.getScaledInstance(scaledWidth, scaledWidth, Image.SCALE_SMOOTH);
        // pac_planet = pac_planet.getScaledInstance(scaledWidth, scaledWidth, Image.SCALE_SMOOTH);
        // agg_planet = agg_planet.getScaledInstance(scaledWidth, scaledWidth, Image.SCALE_SMOOTH);
        // emp_planet = emp_planet.getScaledInstance(scaledWidth, scaledWidth, Image.SCALE_SMOOTH);
        // star = star.getScaledInstance(scaledWidth, scaledWidth, Image.SCALE_SMOOTH);
        // black_hole = black_hole.getScaledInstance(scaledWidth, scaledWidth, Image.SCALE_SMOOTH);


        super.paint(g); // draws background
        //g2D.drawImage(background, 0, 0, null);

        //setting drawing connections between ships and owners 
        g2D.setStroke(new BasicStroke(1));


        g2D.setFont(new Font("Tahoma", Font.PLAIN, panel_height / 50));
                
        for(int j = 0; j < map_area.length; j++) {
            for(int i = 0; i < map_area.length; i++) {

                int i_dim_in_pixels = (i * drawing_space_i_dim / map_area.length);
                int j_dim_in_pixels = (j * drawing_space_j_dim / map_area.length);
                
                g2D.setPaint(Color.WHITE);

                pacifisticShip acc_ship = null;

                
                if(map_area[i][j] instanceof pacifisticShip) {
                    acc_ship = (pacifisticShip) map_area[i][j];
                }
                
                
                if(map_area[i][j] == null || this.map_area[i][j] == null) {
                    continue;
                }              
                else if (map_area[i][j].toString().equals("#")) {
                   
                    
                    //drawing ship image
                    g2D.drawImage(agg_ship, i_dim_in_pixels, j_dim_in_pixels, null);
                    
                    //drawing connection between ship and owner
                    g2D.setPaint(Color.RED);
                    g2D.drawLine(agg_ship.getWidth(null)/2 + i_dim_in_pixels, 
                    agg_ship.getHeight(null)/2 + j_dim_in_pixels, 
                    agg_ship.getWidth(null)/2 + acc_ship.owner.planets_possesed_list.get(0).x_dim * drawing_space_i_dim / map_area.length,
                    agg_ship.getHeight(null)/2 + acc_ship.owner.planets_possesed_list.get(0).y_dim * drawing_space_j_dim / map_area.length);

                    //drawing ship image
                    g2D.drawImage(agg_ship, i_dim_in_pixels, j_dim_in_pixels, null);
                
                }
                else if (map_area[i][j].toString().equals("~")) {

                    if(acc_ship.owner.planets_possesed_list.size() == 0) continue;  
                    g2D.setColor(Color.GREEN);
                    g2D.drawLine(pac_ship.getWidth(null)/2 + i_dim_in_pixels, 
                    pac_ship.getHeight(null)/2 + j_dim_in_pixels, 
                    pac_ship.getWidth(null)/2 + acc_ship.owner.planets_possesed_list.get(0).x_dim * drawing_space_i_dim / map_area.length,
                    pac_ship.getHeight(null)/2 + acc_ship.owner.planets_possesed_list.get(0).y_dim * drawing_space_j_dim / map_area.length);
                    
                    //drawing ship image
                    g2D.drawImage(pac_ship, i_dim_in_pixels, j_dim_in_pixels, null);
                } else if(map_area[i][j] instanceof Star) {
                    g2D.drawImage(star, i_dim_in_pixels, j_dim_in_pixels, null);
                    
                } else if(map_area[i][j] instanceof blackHole) {
                    g2D.drawImage(black_hole, i_dim_in_pixels, j_dim_in_pixels, null);
                    continue;
                }else if(map_area[i][j] instanceof Planet) {

                    Planet planet = (Planet) map_area[i][j];
                    g2D.setPaint(Color.CYAN);

                    //painting resources
                    if(planet.owner != null) {

                        if(planet.owner.planets_possesed_list.get(0).equals(planet)) { 
                            g2D.drawString("Resources: " , 
                            i_dim_in_pixels - 35,
                            j_dim_in_pixels);

                            g2D.drawString(String.valueOf(planet.owner.owned_resources) , 
                            i_dim_in_pixels - 35,
                            j_dim_in_pixels + 15);

                        }
                        g2D.setColor(Color.WHITE);              
                        g2D.drawLine(emp_planet.getWidth(null)/2 + i_dim_in_pixels, 
                        emp_planet.getHeight(null)/2 + j_dim_in_pixels, 
                        emp_planet.getWidth(null)/2 + planet.owner.planets_possesed_list.get(0).x_dim * drawing_space_i_dim / map_area.length,
                        emp_planet.getHeight(null)/2 + planet.owner.planets_possesed_list.get(0).y_dim * drawing_space_j_dim / map_area.length);
                    
                    }
                    if(planet.owner instanceof aggressiveCivilization) {
                        g2D.drawImage(agg_planet, i_dim_in_pixels, j_dim_in_pixels, null);
                    } else if (planet.owner instanceof pacifisticCivilization) {
                        g2D.drawImage(pac_planet, i_dim_in_pixels, j_dim_in_pixels, null);
                    } else {
                        g2D.drawImage(emp_planet, i_dim_in_pixels, j_dim_in_pixels, null);
                    }
                    
                }

            }
            
        }
    }

    public void update_map_frame(emptySpace map_area_passed[][]) {

        map_area = map_area_passed;   

        timer = new Timer(interval, e -> {
            
            repaint();
            
        });
        timer.start(); // Start the timer
        previous_map_area = map_area;
    } 
    
    private Image scaleImage(Image image, int width, int height) {
        return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }
}
