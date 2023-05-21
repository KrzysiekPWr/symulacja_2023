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
    emptySpace map_area[][];

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
        this.map_area = new emptySpace[size][size];
    }

    

    void show_map() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(map_area[i][j] == null) System.out.print(". ");
                else System.out.print(map_area[i][j].toString() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public void initialize_map() {
        
        spawn_planets();
        spawn_black_holes();
        spawn_stars();
        add_closest_planets_vectors_lists_to_planets();
        add_civilizations();
    }

    private void spawn_planets(){
     
        Random rand = new Random();   
        int chance_for_planet;
        int initial_resources;
        
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {

                chance_for_planet = rand.nextInt(1,101); 

                if(chance_for_planet <= (int)(planetation*100)) {

                    initial_resources = rand.nextInt(0, 1000); 

                    Planet planet = new Planet(initial_resources, x, y);
                    map_area[x][y] = planet;
                    lifeless_planet_list.add(planet);
                } 
            }
        }
    }

    private void add_closest_planets_vectors_lists_to_planets() {

        // for each planet searching 3 closest planets
        for (Planet planet : lifeless_planet_list) {
            List<Planet> closest_planets_list_temp = new ArrayList<>();
            
            //iterating throught all planets
            for (Planet act_planet : lifeless_planet_list) {
                if (planet != act_planet) {
                    int x_vector = act_planet.x_dim - planet.x_dim;
                    int y_vector = act_planet.y_dim - planet.y_dim;
    
                    double distance = Math.sqrt(x_vector * x_vector + y_vector * y_vector);
                    
                    //assigning first 3 planets
                    if (closest_planets_list_temp.size() < 3) {
                        closest_planets_list_temp.add(act_planet);
                    } 
                    else {
                        //search for a planet with the biggest distance in closest_planets_list_temp
                        double biggest_distance = -1;
                        int biggest_distance_index = -1;
                        for (Planet closest_planet : closest_planets_list_temp) {
                                x_vector = closest_planet.x_dim - planet.x_dim;
                                y_vector = closest_planet.y_dim - planet.y_dim;
                                double distance_temp = Math.sqrt(x_vector * x_vector + y_vector * y_vector);

                                if(distance_temp > biggest_distance){
                                    biggest_distance = distance_temp;
                                    biggest_distance_index = closest_planets_list_temp.indexOf(closest_planet);
                                }
                        }
                        //check if actual distance is smaller than the biggest one in closest_planets_list_map
                        //and replace it if it is
                        if (distance < biggest_distance) {
                            closest_planets_list_temp.set(biggest_distance_index, act_planet);
                        }
                    }
                }
            }
            planet.closest_planets_list = closest_planets_list_temp;
        }
    }
    
    
    private void spawn_black_holes(){

        Random rand = new Random();            
        
        int random_x, random_y;

        for(int i = 0; i < black_holes_quantity; i++){
            do{
                random_x = rand.nextInt(0, size);
                random_y = rand.nextInt(0, size);
            }while(map_area[random_x][random_y] != null);
            
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
            }while(map_area[random_x][random_y] != null);
            
            Star star = new Star(1.5, 100);
            map_area[random_x][random_y] = star; 
        }
    }
    

    
    private void add_civilizations(){
        
        Random rand = new Random();
        
        int random_index;

        for(int i = 0; i < (aggresive_civilisation_quantity + pacifistic_civilisation_quantity); i++){
            
            //drawing random planet from list to bring planet to civilization
            random_index = rand.nextInt(0, lifeless_planet_list.size());
            
            //adding planets to pacifistic civilizations first
            if(i < pacifistic_civilisation_quantity){
                
                //creating new pacifistic civilization
                pacifisticCivilization pacCiv = new pacifisticCivilization(10, 20);
                
                // adding civilization to list of civilizations
                civ_list.add(pacCiv);

                //adding planet to list of planets possesed by civilization
                pacCiv.planets_possesed_list.add(lifeless_planet_list.get(random_index));

                //removing planet from list of lifeless planets that can be possesed in future
                lifeless_planet_list.remove(random_index);

            }
            else{
                aggressiveCivilization agrCiv = new aggressiveCivilization(10, 20);
                
                civ_list.add(agrCiv);
                agrCiv.planets_possesed_list.add(lifeless_planet_list.get(random_index));
                lifeless_planet_list.remove(random_index);
            }
            
        }   
                 
    }
    
    public void mine_resources(){ 

        for(int i = 0; i < civ_list.size(); i++) {   
            civ_list.get(i).mine_resources_for_one_civilization();
        }
    }


    // method for spawning ships for civilizations
    // it does look a bit messy but it works so im fine with it

   public void spawn_ships() {
        for (pacifisticCivilization pacifisticCivilization : civ_list) {
            for (Planet owned_planet : pacifisticCivilization.planets_possesed_list) {
                if(owned_planet.extracted_resources >= pacifisticCivilization.ship_price){
                    owned_planet.extracted_resources -= pacifisticCivilization.ship_price;
                    
                    //search for a free slot in euclidean space and spawn ship there
                    int planet_x = owned_planet.x_dim;
                    int planet_y = owned_planet.y_dim;

                    int fuel = pacifisticCivilization.ship_fuel;
                    int jump_cooldown = pacifisticCivilization.ship_jump_cooldown;
                    int speed = pacifisticCivilization.ship_speed;
                    
                    boolean ship_spawned = false;
                    for (int x = planet_x - 1; x < planet_x + 1 ; x++) {
                        for (int y = planet_y - 1; y < planet_y + 1; y++) {
                            if(x >= 0 && x < size && y >= 0 && y < size){
                                if(map_area[x][y] == null){
                                    map_area[x][y] = new pacifisticShip(fuel, jump_cooldown, speed, x, y);
                                    ship_spawned = true;
                                    break;
                                }
                            }
                        }
                        if(ship_spawned){
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Function that moves ships:( 
    //  */ 
    // public void move_ships(){
    //     for(int x = 0; x < size; x++) {
    //         for (int y = 0; y < size; y++) {
                
    //             if(map_area[x][y].special_char == '^'){
                        
    //                 Random rand = new Random();

    //                 int new_x, new_y;
    //                 do{
    //                     new_x = rand.nextInt(-1, 2) + x; //from -1 to 2 because if we go for (-2, 1) to spierdolą w róg
    //                     new_y = rand.nextInt(-1, 2) + y;
                      
    //                 }while(new_x < 0 || new_x >= size || new_x < 0 || new_y >= size || new_y < 0);

    //                 if(map_area[new_x][new_y].special_char == '.'){

    //                     map_area[new_x][new_y] = map_area[x][y];
                        
    //                     objectInSpace object_in_space = new objectInSpace('.');

    //                     map_area[x][y] = object_in_space;
    //                 }
 
    //             }
    //         }
    //     }
    //}
}

