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
    List<Planet> lifeless_planet_list = new ArrayList<>();
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
        spawn_void();
        spawn_black_holes();
        spawn_stars();
        spawn_planets();
        add_civilizations();
        perform_era();
    }


    private void spawn_void(){
        for(int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                objectInSpace objectInSpace = new objectInSpace('.'); 
                map_area[x][y] = objectInSpace;   
            }
        }                  
    }


    private void spawn_black_holes(){

        Random rand = new Random();            
        
        int random_x, random_y;

        for(int i = 0; i < black_holes_quantity; i++){
            do{
                random_x = rand.nextInt(0, size);
                random_y = rand.nextInt(0, size);
            }while(map_area[random_x][random_y].special_char != '.');
            
            blackHole black_hole = new blackHole(2);
            map_area[random_x][random_y] = black_hole; 
        }
    }
    

    private void spawn_stars(){

        Random rand = new Random();
        
        int random_x, random_y;

        for(int i = 0; i < stars_quantity; i++){
            do{
                random_x = rand.nextInt(0, size);
                random_y = rand.nextInt(0, size);
            }while(map_area[random_x][random_y].special_char != '.' 
            && map_area[random_x][random_y].special_char == 'o');
            
            Star star = new Star(1.5, 100);
            map_area[random_x][random_y] = star; 
        }
    }
    

    private void spawn_planets(){
     
        Random rand = new Random();
        
        int chance_for_planet;
        int initial_resources;
        
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                chance_for_planet = rand.nextInt(1,101); 
                    if(chance_for_planet <= (int)(planetation*100) 
                    && map_area[x][y].special_char == '.'){
                        initial_resources = rand.nextInt(0, 1000); 
                        Planet planet = new Planet(initial_resources, 0);
                        map_area[x][y] = planet;
                        lifeless_planet_list.add(planet);
                    } 
            }
        }
    }
    
    private void add_civilizations(){
        
        Random rand = new Random();
        
        int random_index;

        for(int i = 0; i < (aggresive_civilisation_quantity 
        + pacifistic_civilisation_quantity); i++){
            
            random_index = rand.nextInt(0, lifeless_planet_list.size());
            
            if(i < pacifistic_civilisation_quantity){
                
                pacifisticCivilization pacCiv = new pacifisticCivilization(0, 10, 10);
                civ_list.add(pacCiv);
                pacCiv.planets_possesed_list.add(lifeless_planet_list.get(random_index));
                lifeless_planet_list.remove(random_index);
            }
            else{
                aggressiveCivilization agrCiv = new aggressiveCivilization(0, 10, 10);
                civ_list.add(agrCiv);
                agrCiv.planets_possesed_list.add(lifeless_planet_list.get(random_index));
                lifeless_planet_list.remove(random_index);
            }
            
        }   
                 
    }

    public void perform_era(){ //function that performs all actions that happend 
                               //during one iteration of simulation - called era

        for(int i = 0; i < civ_list.size(); i++)
        {   
            int resources_mined_in_era = 0;
            
            for(int j = 0; j < civ_list.get(i).planets_possesed_list.size(); j++){
                
                if(civ_list.get(i).planets_possesed_list.get(j).resources >= civ_list.get(i).mining_abilities){

                    civ_list.get(i).planets_possesed_list.get(j).resources -= civ_list.get(i).mining_abilities;
                    civ_list.get(i).planets_possesed_list.get(j).extracted_resources += civ_list.get(i).mining_abilities;
                    resources_mined_in_era += civ_list.get(i).mining_abilities;

                }
                else{
                    
                    civ_list.get(i).planets_possesed_list.get(j).extracted_resources 
                    += civ_list.get(i).planets_possesed_list.get(j).resources;
                    resources_mined_in_era += civ_list.get(i).planets_possesed_list.get(j).resources;
                    civ_list.get(i).planets_possesed_list.get(j).resources = 0;
                }

            }
            civ_list.get(i).resources += resources_mined_in_era;
        }
    }
}
