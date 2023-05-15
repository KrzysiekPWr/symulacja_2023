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
    objectInSpace map_area[][]; //idk if String should be here, maybe we should 
                        //create map of objects that are in the space  
                        //

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
        this.map_area = new objectInSpace[size][size];
    }

    

    void showMap(){

        for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(map_area[i][j].special_char + " ");
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
                int chance_for_planet = rand.nextInt(1,101); 
                    if(chance_for_planet <= (int)(planetation*100)){
                        
                        int initial_resources = rand.nextInt(0, 1000); // 
                        Planet planet = new Planet(initial_resources, 0);
                        map_area[x][y] = planet;
                    }
                    else{ //creating empty spaces in map
                        objectInSpace objectInSpace = new objectInSpace('.'); 
                        map_area[x][y] = objectInSpace;                    
                    }
                    
                }
        }
    }
    
    private void spawn_stars(){

        Random rand = new Random();
        
        int random_x, random_y;

        int i = 0;
        while(i < stars_quantity){
            
            random_x = rand.nextInt(0, size);
            random_y = rand.nextInt(0, size);
            
            if(map_area[random_x][random_y].special_char != '+' 
            && map_area[random_x][random_y].special_char != 'P')
            {
                Star star = new Star(1.5, 100);
                map_area[random_x][random_y] = star; 
                i++;
            }
        }
    }


   
    private void spawn_black_holes(){

        Random rand = new Random();            

        for(int i = 0; i < black_holes_quantity; i++){
            int random_x, random_y;
            do{
            random_x = rand.nextInt(0, size);
            random_y = rand.nextInt(0, size);
            }while(map_area[random_x][random_y].special_char != '.');
            
            blackHole black_hole = new blackHole(2);
            map_area[random_x][random_y] = black_hole; 
        }
    }
}
