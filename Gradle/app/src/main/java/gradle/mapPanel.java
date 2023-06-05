package gradle;

import java.awt.*;

import javax.swing.*;


public class mapPanel extends  JPanel{
    
    final int panel_width = 500;
    final int panel_height = 500;

    emptySpace map_area[][];

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
        java.net.URL mapUrl = getClass().getResource("resources\\map_background.png");
        ImageIcon background_icon = new ImageIcon(mapUrl);
        background = background_icon.getImage();

        java.net.URL pac_shipUrl = getClass().getResource("resources\\pac_ship.png");
        ImageIcon pac_ship_icon = new ImageIcon(pac_shipUrl);
        pac_ship = pac_ship_icon.getImage();

        java.net.URL agg_shipUrl = getClass().getResource("resources\\agg_ship.png");
        ImageIcon agg_ship_icon = new ImageIcon(agg_shipUrl);
        agg_ship = agg_ship_icon.getImage();

        java.net.URL agg_planetUrl = getClass().getResource("resources\\agg_planet.png");
        ImageIcon agg_planet_icon = new ImageIcon(agg_planetUrl);
        agg_planet = agg_planet_icon.getImage();

        java.net.URL pac_planetUrl = getClass().getResource("resources\\pac_planet.png");
        ImageIcon pac_planet_icon = new ImageIcon(pac_planetUrl);
        pac_planet = pac_planet_icon.getImage();

        java.net.URL emp_planetUrl = getClass().getResource("resources\\emp_planet.png");
        ImageIcon emp_planet_icon = new ImageIcon(emp_planetUrl);
        emp_planet = emp_planet_icon.getImage();

        java.net.URL black_holeUrl = getClass().getResource("resources\\black_hole.png");
        ImageIcon black_hole_icon = new ImageIcon(black_holeUrl);
        black_hole = black_hole_icon.getImage();

        java.net.URL starUrl = getClass().getResource("resources\\star.png");
        ImageIcon star_icon = new ImageIcon(starUrl);
        star = star_icon.getImage();
        
        this.setPreferredSize(new Dimension(panel_width, panel_height));
        
        
        //ImageIcon icon = new ImageIcon("map_background.png");
       // image = new ImageIcon("map_background.png").getImage();
    }

    public void paint(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;
        
        g2D.drawImage(background, 0, 0, null);
        
        for(int i = 0; i < map_area.length; i++) {
            for(int j = 0; j < map_area.length; j++) {
                

                if(map_area[i][j] == null) {
                    continue;
                }

                // if(map_area[i][j] instanceof pacifisticShip && !(map_area[i][j] instanceof aggressiveShip)) {
                //     g2D.drawImage(pac_ship, i*panel_width/map_area.length, j*panel_height/map_area.length, null);
                //     continue;
                // }
                
                // if(map_area[i][j] instanceof aggressiveShip) {
                //     g2D.drawImage(agg_ship, i*panel_width/map_area.length, j*panel_height/map_area.length, null);
                //     continue;
                // } why this dont show properly ships in frame?
                
                    //It's the wrong way to do it!
                    if (map_area[i][j].toString().equals("#")) {
                        g2D.drawImage(agg_ship, i * panel_width / map_area.length, j * panel_height / map_area.length, null);
                        continue;
                    } else if (map_area[i][j].toString().equals("~")){
                        g2D.drawImage(pac_ship, i * panel_width / map_area.length, j * panel_height / map_area.length, null);
                        continue;
                    }
                
                

                if(map_area[i][j] instanceof Planet) {

                    Planet planet = (Planet) map_area[i][j];
                    if(planet.owner instanceof aggressiveCivilization) {
                        g2D.drawImage(agg_planet, i*panel_width/map_area.length, j*panel_height/map_area.length, null);
                    } else if (planet.owner instanceof pacifisticCivilization) {
                        g2D.drawImage(pac_planet, i*panel_width/map_area.length, j*panel_height/map_area.length, null);
                    }
                    else {
                        g2D.drawImage(emp_planet, i*panel_width/map_area.length, j*panel_height/map_area.length, null);
                    }
                    continue;
                }

                if(map_area[i][j] instanceof Star) {
                    g2D.drawImage(star, i*panel_width/map_area.length, j*panel_height/map_area.length, null);
                    continue;
                }

                if(map_area[i][j] instanceof blackHole) {
                    g2D.drawImage(black_hole, i*panel_width/map_area.length, j*panel_height/map_area.length, null);
                    continue;
                }

                

            }
        }
        
    }

    public void update_map_frame(emptySpace map_area_passed[][]) {

        map_area = map_area_passed;        
        repaint();
    }



    

    

    
}
