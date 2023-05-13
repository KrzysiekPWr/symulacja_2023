package gradle;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;


class Map {
    double planetation;
    int size;
    int stars_quantity;
    int black_holes_quantity;
    int aggresive_civilisation_quantity;
    int pacifistic_civilisation_quantity;
    List<pacifisticCivilization> civ_list = new ArrayList<>();
    List<pacifisticShip> ship_list = new ArrayList<>();
    String[][] map_area;

    Map(){}
    
    Map(double planetation, int size, int stars_quantity, int black_holes_quantity,
    int aggresive_civilisation_quantity, int pacifistic_civilisation_quantity)
    {
        this.planetation = planetation;
        this.size = size;
        this.stars_quantity = stars_quantity;
        this.black_holes_quantity = black_holes_quantity;
        this.aggresive_civilisation_quantity = aggresive_civilisation_quantity;
        this.pacifistic_civilisation_quantity = pacifistic_civilisation_quantity;
        this.map_area = new String[size][size];
    }

    

    void showMap(){

        for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(map_area[i][j] + " ");
                }
                System.out.println();
            }
    }
    
    public void initializeMap(){
        spawn_planets();
        spawn_stars();
        spawn_black_holes();
    }

    private void spawn_planets(){
     
        Random rand = new Random();

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                int chance_for_planet = rand.nextInt(1,101); //random int from 1 to 100
                    if(chance_for_planet <= (int)(planetation*100)){
                        map_area[x][y] = "P";
                    }
                    else{
                        map_area[x][y] = "*";
                    }
                }
        }
    }
    
    private void spawn_stars(){

        Random rand = new Random();
        
        int random_x, random_y;
        for(int i = 0; i < stars_quantity; i++){
            
            random_x = rand.nextInt(1, size);
            random_y = rand.nextInt(1, size);
            
            map_area[random_x][random_y] = "+"; 
        }
    }


    /**
     * 
     * @param black_holes_quantity
     * @param map
     * @return Map
     */
    private void spawn_black_holes(){

        Random rand = new Random();            

        int random_x, random_y;
        for(int i = 0; i < black_holes_quantity; i++){
            do{
            random_x = rand.nextInt(1, size);
            random_y = rand.nextInt(1, size);
            }while(map_area[random_x][random_y].equals("+"));
            
            map_area[random_x][random_y] = "O"; 
        }
    }
}
