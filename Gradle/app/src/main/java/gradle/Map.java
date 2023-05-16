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
    objectInSpace map_area[][]; //is it a good way to hold our objects?

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

    

    void show_map(){

        for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(map_area[i][j].special_char + " ");
                }
                System.out.println();
            }
    }
    
    
    public void initialize_map(){
        spawn_void();
        spawn_black_holes();
        spawn_stars();
        spawn_planets();
        add_civilizations();
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
                
                pacifisticCivilization pacCiv = new pacifisticCivilization(10, 20);
                civ_list.add(pacCiv);
                pacCiv.planets_possesed_list.add(lifeless_planet_list.get(random_index));
                lifeless_planet_list.get(random_index).special_char = 'F';
                lifeless_planet_list.remove(random_index);
            }
            else{
                aggressiveCivilization agrCiv = new aggressiveCivilization(10, 20);
                civ_list.add(agrCiv);
                agrCiv.planets_possesed_list.add(lifeless_planet_list.get(random_index));
                
                //changing special character for planet to distinguish planet that are taken
                lifeless_planet_list.get(random_index).special_char = 'F'; 
                
                lifeless_planet_list.remove(random_index);
            }
            
        }   
                 
    }
    //function that performs all actions that happend 
    //during one iteration of simulation - called era
    public void mine_resources(){ 

        for(int i = 0; i < civ_list.size(); i++)
        {   
            civ_list.get(i).mine_resources_for_one_civilization();

            // for(int j = 0; j < civ_list.get(i).planets_possesed_list.size(); j++){
                
            //     //case when resources of planet are grater than mining_ablities
            //     if(civ_list.get(i).planets_possesed_list.get(j).resources >= civ_list.get(i).mining_abilities){

            //         //subtracting extracted resources from all planet's resources
            //         civ_list.get(i).planets_possesed_list.get(j).resources -= civ_list.get(i).mining_abilities;
            //         //adding substracted resources to extracted_resources
            //         civ_list.get(i).planets_possesed_list.get(j).extracted_resources += civ_list.get(i).mining_abilities;

            //         resources_mined_in_era += civ_list.get(i).mining_abilities;

            //     }
            //     //if mining ability of a civ is greater than remaining resources on the planet (to evade negative
            //     //resources count)
            //     else{
                    
            //         civ_list.get(i).planets_possesed_list.get(j).extracted_resources 
            //         += civ_list.get(i).planets_possesed_list.get(j).resources;
            //         resources_mined_in_era += civ_list.get(i).planets_possesed_list.get(j).resources;
            //         civ_list.get(i).planets_possesed_list.get(j).resources = 0;
            //     }

            // }
            // civ_list.get(i).owned_resources += resources_mined_in_era;
        }
    }


    //XD we really don't know what is wrong with us, it looks awful:(  it is what it is
    //when the plane to the programmers hell take off? 
    public void spawn_ships(){
       for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {

                if (map_area[i][j].special_char == 'F'){

                    for (pacifisticCivilization civ : civ_list){

                        for (Planet planet_possesed : civ.planets_possesed_list){

                            if(map_area[i][j].equals(planet_possesed)){
                                
                                if(planet_possesed.extracted_resources >= civ.ship_price){
                                    
                                    pacifisticShip pac_ship = new pacifisticShip(10, 2, 1);
                                    civ.ship_possesed_list.add(pac_ship);
                                    planet_possesed.extracted_resources -= civ.ship_price;

                                    if(i == 0) map_area[i+1][j] = pac_ship;
                                    else map_area[i-1][j] = pac_ship;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Function that moves ships:( 
     */ 
    public void move_ships(){
        for(int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                
                if(map_area[x][y].special_char == '^'){
                        
                    Random rand = new Random();

                    int new_x, new_y;
                    do{
                        new_x = rand.nextInt(-2, 1) + x;
                        new_y = rand.nextInt(-2, 1) + y;
                      
                    }while(new_x < 0 || new_x >= size || new_x < 0 || new_y >= size || new_y < 0);

                    if(map_area[new_x][new_y].special_char == '.'){

                        map_area[new_x][new_y] = map_area[x][y];
                        
                        objectInSpace object_in_space = new objectInSpace('.');

                        map_area[x][y] = object_in_space;
                    }
 
                }
            }
        }
    }   
}

