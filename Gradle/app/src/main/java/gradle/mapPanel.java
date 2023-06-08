package gradle;

import java.awt.*;

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

        
        
        
        //ImageIcon icon = new ImageIcon("map_background.png");
       // image = new ImageIcon("map_background.png").getImage();
    }

    public void paint(Graphics g) {

        if(map_area == null) {
            return;
        }
        Graphics2D g2D = (Graphics2D) g;

        

        //Scaling the background and images to fit nicely the panel
        panel_height = this.getHeight();
        panel_width = this.getWidth();
    

        // int scale = panel_width / map_area.length;

        

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

                drawing_space_i_dim = panel_width - 200;
                drawing_space_j_dim = panel_height;

                int i_dim_in_pixels = (i * drawing_space_i_dim / map_area.length);
                int j_dim_in_pixels = (j * drawing_space_j_dim / map_area.length);
                g2D.setPaint(Color.WHITE);

                
                // int x_change_in_map = 0;
                // int y_change_in_map = 0;
                // int previous_ship_x = -2; //error code
                // int previous_ship_y = -2; //error code

                // int x_velocity = 2;
                // int y_velocity = 2;

                pacifisticShip acc_ship = null;


                if(this.map_area[i][j] == null) {
                    continue;
                }

                // if(map_area[i][j] instanceof pacifisticShip && !(map_area[i][j] instanceof aggressiveShip)) {
                //     g2D.drawImage(pac_ship, i_dim_in_pixels, j_dim_in_pixels, null);
                //     continue;
                // }
                
                // if(map_area[i][j] instanceof aggressiveShip) {
                //     g2D.drawImage(agg_ship, i_dim_in_pixels, j_dim_in_pixels, null);
                //     continue;
                // } why this dont show properly ships in frame?
                
                //It's the wrong way to do it!

                //Work in progress! Moving ships
                // if(map_area[i][j] instanceof pacifisticShip) {

                //     pacifisticShip ship = (pacifisticShip) map_area[i][j];

                //     for(int k = i - 1 ; k < 2; k++) {
                //         for(int l = j - 1; l < 2; l++) {

                //             if(k < 0 || l < 0 || k >= map_area.length || l >= map_area.length) {
                //                 continue;
                //             }

                //             if(previous_map_area[k][l].equals(ship)) {
                //                 previous_ship_x = k;
                //                 previous_ship_y = l;
                //             }
                //         }
                //     }
                // }

                // x_change_in_map  = i - previous_ship_x;
                // y_change_in_map  = j - previous_ship_y;

                // x_velocity = x_change_in_map * panel_width / map_area.length;

                
                if(map_area[i][j] instanceof pacifisticShip) {
                    acc_ship = (pacifisticShip) map_area[i][j];
                } 

                

                if (map_area[i][j].toString().equals("#")) {
                   
                    //drawing connection between ship and owner
                    g2D.setPaint(Color.RED);
                    
                    g2D.drawLine(pac_ship.getWidth(null)/2 + i_dim_in_pixels, 
                    pac_ship.getHeight(null)/2 + j_dim_in_pixels, 
                    pac_ship.getWidth(null)/2 + acc_ship.owner.planets_possesed_list.get(0).x_dim * drawing_space_i_dim / map_area.length,
                    pac_ship.getHeight(null)/2 + acc_ship.owner.planets_possesed_list.get(0).y_dim * drawing_space_j_dim / map_area.length);

                    //drawing ship image
                    g2D.drawImage(agg_ship, i_dim_in_pixels, j_dim_in_pixels, null);
                
                    continue;
                } else if (map_area[i][j].toString().equals("~")) {

                    if(acc_ship.owner.planets_possesed_list.size() == 0) continue;  
                    g2D.setColor(Color.GREEN);
                    g2D.drawLine(pac_ship.getWidth(null)/2 + i_dim_in_pixels, 
                    pac_ship.getHeight(null)/2 + j_dim_in_pixels, 
                    pac_ship.getWidth(null)/2 + acc_ship.owner.planets_possesed_list.get(0).x_dim * drawing_space_i_dim / map_area.length,
                    pac_ship.getHeight(null)/2 + acc_ship.owner.planets_possesed_list.get(0).y_dim * drawing_space_j_dim / map_area.length);
                    
                    //drawing ship image
                    g2D.drawImage(pac_ship, i_dim_in_pixels, j_dim_in_pixels, null);
                    continue;
                }
            
                if(map_area[i][j] instanceof Planet) {

                    Planet planet = (Planet) map_area[i][j];
                    g2D.setPaint(Color.WHITE);

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
                    continue;
                }

                if(map_area[i][j] instanceof Star) {
                    g2D.drawImage(star, i_dim_in_pixels, j_dim_in_pixels, null);
                    continue;
                }

                if(map_area[i][j] instanceof blackHole) {
                    g2D.drawImage(black_hole, i_dim_in_pixels, j_dim_in_pixels, null);
                    continue;
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
}
