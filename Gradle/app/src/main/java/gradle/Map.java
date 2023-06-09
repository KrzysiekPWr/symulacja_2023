package gradle;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

class Map {
    
    private double planetation;
    private int size;
    private int stars_quantity;
    private int black_holes_quantity;
    private int aggresive_civilisation_quantity;
    private int pacifistic_civilisation_quantity;
    public List<pacifisticCivilization> civ_list = new ArrayList<>();
    private List<Planet> lifeless_planet_list = new ArrayList<>();
    public emptySpace map_area[][];
    
    //simulation parameters default values
    private int default_black_holes_sucking_range = 2;
    private double default_stars_power_rate = 10.0;
    private int default_lower_bound_of_resources_for_planets = 100;
    private int default_upper_bound_of_resources_for_planets = 1000;
    private int default_stars_shining_range = 2;
    
    //pacifistic civilization default values
    private int default_pac_civ_mining_abilities = 15;
    private int default_pac_civ_ship_price = 40; 
    private int default_pac_civ_ship_fuel = 7;
    private int default_pac_civ_ship_jump_cooldown = 4;
    private int default_pac_civ_ship_speed = 5;
    //aggressive civilization default values
    private int default_agg_civ_mining_abilities = 8;
    private int default_agg_civ_ship_price = 50; 
    private int default_agg_civ_ship_fuel = 7;
    private int default_agg_civ_ship_jump_cooldown = 8;
    private int default_agg_civ_ship_speed = 5;
    private double default_agg_ships_attack_power = 1.1;


    Map(){}
    
    //constructor that uses default, unchanged values
    Map(double planetation, int size, int stars_quantity, int black_holes_quantity,
    int aggresive_civilisation_quantity, int pacifistic_civilisation_quantity){
        this.planetation = planetation;
        this.size = size;
        this.stars_quantity = stars_quantity;
        this.black_holes_quantity = black_holes_quantity;
        this.aggresive_civilisation_quantity = aggresive_civilisation_quantity;
        this.pacifistic_civilisation_quantity = pacifistic_civilisation_quantity;
        this.map_area = new emptySpace[size][size];
    }

    //constructor that uses custom values
    Map(double planetation, int size, int stars_quantity, int black_holes_quantity,
    int aggresive_civilisation_quantity, int pacifistic_civilisation_quantity,
    int default_black_holes_sucking_range, double default_stars_power_rate,
    int default_lower_bound_of_resources_for_planets, int default_upper_bound_of_resources_for_planets,
    int default_stars_shining_range, 

    int default_pac_civ_mining_abilities, int default_pac_civ_ship_price,
    int default_pac_civ_ship_fuel, int default_pac_civ_ship_jump_cooldown, int default_pac_civ_ship_speed,
    
    int default_agg_civ_mining_abilities, int default_agg_civ_ship_price, int default_agg_civ_ship_fuel,
    int default_agg_civ_ship_jump_cooldown, int default_agg_civ_ship_speed, double default_agg_ships_attack_power){
        
        this.planetation = planetation;
        this.size = size;
        this.stars_quantity = stars_quantity;
        this.black_holes_quantity = black_holes_quantity;
        this.aggresive_civilisation_quantity = aggresive_civilisation_quantity;
        this.pacifistic_civilisation_quantity = pacifistic_civilisation_quantity;
        
        this.map_area = new emptySpace[size][size];

        this.default_black_holes_sucking_range = default_black_holes_sucking_range;
        this.default_stars_power_rate = default_stars_power_rate;
        this.default_lower_bound_of_resources_for_planets = default_lower_bound_of_resources_for_planets;
        this.default_upper_bound_of_resources_for_planets = default_upper_bound_of_resources_for_planets;
        this.default_stars_shining_range = default_stars_shining_range;

        this.default_pac_civ_mining_abilities = default_pac_civ_mining_abilities;
        this.default_pac_civ_ship_price = default_pac_civ_ship_price;
        this.default_pac_civ_ship_fuel = default_pac_civ_ship_fuel;
        this.default_pac_civ_ship_jump_cooldown = default_pac_civ_ship_jump_cooldown;
        this.default_pac_civ_ship_speed = default_pac_civ_ship_speed;

        this.default_agg_civ_mining_abilities = default_agg_civ_mining_abilities;
        this.default_agg_civ_ship_price = default_agg_civ_ship_price;
        this.default_agg_civ_ship_fuel = default_agg_civ_ship_fuel;
        this.default_agg_civ_ship_jump_cooldown = default_agg_civ_ship_jump_cooldown;
        this.default_agg_civ_ship_speed = default_agg_civ_ship_speed;
        this.default_agg_ships_attack_power = default_agg_ships_attack_power;
        
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
        activate_static_objects();
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

                    initial_resources = rand.nextInt(default_lower_bound_of_resources_for_planets,
                     default_upper_bound_of_resources_for_planets); 

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
            planet.sort_closest_planets_list();
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
            
            blackHole black_hole = new blackHole(default_black_holes_sucking_range);
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
            
            Star star = new Star(default_stars_power_rate, default_stars_shining_range);
            map_area[random_x][random_y] = star; 
        }
    }
     
    private void add_civilizations(){
        
        Random rand = new Random();
        
        int random_index;

        for(int i = 0; i < (aggresive_civilisation_quantity + pacifistic_civilisation_quantity); i++){
            
            //drawing random planet from list to bring planet to civilization
            if(lifeless_planet_list.size() == 0) break; //if there are no more planets to posses, break loop
            random_index = rand.nextInt(0, lifeless_planet_list.size());
            
            //adding planets to pacifistic civilizations first
            if(i < pacifistic_civilisation_quantity){
                
                //creating new pacifistic civilization                     
                pacifisticCivilization pacCiv = new pacifisticCivilization(default_pac_civ_mining_abilities, default_pac_civ_ship_price,
                    default_pac_civ_ship_fuel, default_pac_civ_ship_jump_cooldown, default_pac_civ_ship_speed);
                
                // adding civilization to list of civilizations
                civ_list.add(pacCiv);

                Planet emptyPlanet = lifeless_planet_list.get(random_index);

                //adding planet to list of planets possesed by civilization
                pacCiv.planets_possesed_list.add(emptyPlanet);
                emptyPlanet.owner = pacCiv;

                //removing planet from list of lifeless planets that can be possesed in future
                lifeless_planet_list.remove(random_index);

            }
            else{                                                           
                aggressiveCivilization agrCiv = new aggressiveCivilization(default_agg_civ_mining_abilities, default_agg_civ_ship_price,
                    default_agg_civ_ship_fuel, default_agg_civ_ship_jump_cooldown, default_agg_civ_ship_speed);
                
                civ_list.add(agrCiv);
                Planet emptyPlanet = lifeless_planet_list.get(random_index);
                agrCiv.planets_possesed_list.add(emptyPlanet);
                emptyPlanet.owner = agrCiv;
                
                lifeless_planet_list.remove(random_index);
            }
            
        }   
                 
    }
    
    public boolean mine_resources(){ 

        boolean were_resources_mined = false;

        for(int i = 0; i < civ_list.size(); i++) {   
            if(civ_list.get(i).mine_resources_for_one_civilization()){
                were_resources_mined = true;
            }
        }
        return were_resources_mined;
    }

   /**
    * Spawns ships for each civilization if it has enough resources
    * looks through all planets possesed by civilization and spawns ship on each of them
    * Tries to spawn ship in the left up corner of Euclidean space iterating right and down
    */
   public void spawn_ships() {
        for (pacifisticCivilization civ : civ_list) {
            for (Planet owned_planet : civ.planets_possesed_list) {
                if(owned_planet.extracted_resources >= civ.ship_price) {
                    owned_planet.extracted_resources -= civ.ship_price;
                    
                    //search for a free slot in euclidean space and spawn ship there
                    int planet_x = owned_planet.x_dim;
                    int planet_y = owned_planet.y_dim;

                    int fuel = civ.ship_fuel;
                    int jump_cooldown = civ.ship_jump_cooldown;
                    int speed = civ.ship_speed;

                    // Random random = new Random();
                    
                    boolean ship_spawned = false;
                    for (int x = planet_x - 1; x <= planet_x + 1 ; x++) {
                        for (int y = planet_y - 1; y <= planet_y + 1; y++) {

                            if(x < 0 || x >= size || y < 0 || y >= size){
                                continue;
                            }

                            if(map_area[x][y] != null) {
                                continue;
                            }   

                            if(civ instanceof aggressiveCivilization) {
                                
                                //IF ITS LESS PLANETS THAN 3 IT WILL THROW AN EXCEPTION!
                                //choosing index of planet to which ship will be sent based on fitness proportionate selection
                                map_area[x][y] = new aggressiveShip(fuel, jump_cooldown, speed, x, y,
                                owned_planet.closest_planets_list.get(owned_planet.fitness_proportionate_selection_index()), civ.owned_resources * default_agg_ships_attack_power, civ);
                                ship_spawned = true;
                                civ.ship_possesed_list.add((aggressiveShip) map_area[x][y]);
                                break;
                                
                            } else {
                                //IF ITS LESS PLANETS THAN 3 IT WILL THROW AN EXCEPTION!
                                map_area[x][y] = new pacifisticShip(fuel, jump_cooldown, speed, x, y,
                                owned_planet.closest_planets_list.get(owned_planet.fitness_proportionate_selection_index()), civ);
                                ship_spawned = true;
                                civ.ship_possesed_list.add((pacifisticShip) map_area[x][y]);
                                break;
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
     * Function that moves ships. Ships get closer to their destination planet (which is randomized using Fitness proportionate selection)
     * Checks if ship is stuck (cannot move for 3 consecutive turn) and if it is, it's rerouted to random planet from empty planet list
    */ 
    public void move_ships(){
        //write function that will move the ship closer to the closest to the destination planet
        for (pacifisticCivilization civ : civ_list) {
            for (int id_ship = 0; id_ship < civ.ship_possesed_list.size(); id_ship++) {
                pacifisticShip ship = civ.ship_possesed_list.get(id_ship);

                Random rand = new Random();
                int ship_x = ship.x_dim;
                int ship_y = ship.y_dim;

                if(ship.isShipAlive() == false){
                    map_area[ship_x][ship_y] = null;
                    civ.ship_possesed_list.remove(ship);
                }
                else if (ship.canShipMove() == true){
                    //check which move will get ship closer to its destination

                    if(ship.times_ship_cannot_move_closer_to_destination >=3){
                        int lifeless_size = lifeless_planet_list.size();
                        ship.times_ship_cannot_move_closer_to_destination = 0;
                        if(lifeless_size >0){
                            ship.destination_planet = lifeless_planet_list.get(rand.nextInt(0, lifeless_size));
                        }
                        continue;
                    }

                    int destination_x = ship.destination_planet.x_dim;
                    int destination_y = ship.destination_planet.y_dim;

                    double act_distance_to_destination = Math.sqrt(Math.pow(ship_x - destination_x, 2) 
                    + Math.pow(ship_y - destination_y, 2));

                    double distance_to_destination_after_move = act_distance_to_destination;

                    //iterate through euclidean space around ship
                    for (int i = ship.x_dim - 1; i <= ship.x_dim + 1; i++) {
                        for (int j = ship.y_dim - 1; j <= ship.y_dim + 1; j++) {
                            if(i >= 0 && i < size && j >= 0 && j < size){
                                if(map_area[i][j] == null){
                                    double distance_to_destination_after_move_temp = Math.sqrt(Math.pow(i - destination_x, 2) 
                                    + Math.pow(j - destination_y, 2));
                                    if(distance_to_destination_after_move_temp < distance_to_destination_after_move){
                                        distance_to_destination_after_move = distance_to_destination_after_move_temp;
                                        ship_x = i;
                                        ship_y = j;
                                    }
                                }
                            }
                        }
                    }
                    if(distance_to_destination_after_move == act_distance_to_destination){
                        ship.times_ship_cannot_move_closer_to_destination++;
                        ship.fuel -= 1;
                    }
                    else{
                        //move ship to new position
                        map_area[ship_x][ship_y] = ship;
                        // clear the previous position
                        map_area[ship.x_dim][ship.y_dim] = null;
                        ship.x_dim = ship_x;
                        ship.y_dim = ship_y;
                        ship.fuel -= 1;
                        ship.act_jump_cooldown = ship.jump_cooldown;
                        ship.times_ship_cannot_move_closer_to_destination = 0;
                    }
                }
                else{
                    ship.act_jump_cooldown -= ship.speed;
                }
            }
        }
    }

    /**
     * Function that checks if ship is close enough to planet to conquer it. If ship is Aggresive, it will conquer Pacifistic planets
     * if it's powerful enough. All ships are destroyed when they arrive at Aggresive Civiliation planet, if Pacifistic ships arrive at
     * Pacifistic planet their destination planet is again randomized using Fitness proportionate selection and fuel is refilled.
    */
    public void conquer_planets_using_ships(){

        for (pacifisticCivilization civ : civ_list) {
            for(int id_ship = 0; id_ship < civ.ship_possesed_list.size(); id_ship++){

                pacifisticShip ship = civ.ship_possesed_list.get(id_ship);
                //if the ship is 1 distance away from the planet then it is removed from the map and the planet is conquered by civilization
                double distance_to_planet = Math.sqrt(Math.pow(ship.x_dim - ship.destination_planet.x_dim, 2) + Math.pow(ship.y_dim - ship.destination_planet.y_dim, 2));
                if(distance_to_planet < 2){
                    if(ship.destination_planet.owner instanceof aggressiveCivilization){
                        
                        if(ship.owner.equals(ship.destination_planet.owner)){
                            ship.fuel = ship.owner.ship_fuel;
                            int index_of_new_planet = ship.destination_planet.fitness_proportionate_selection_index();
                            ship.destination_planet = ship.destination_planet.closest_planets_list.get(index_of_new_planet);
                        }
                        else{
                            map_area[ship.x_dim][ship.y_dim] = null;
                            civ.ship_possesed_list.remove(ship); 
                            //might later add fight betweent aggressive ship and aggresive civ
                        }
                    }
                    else if(ship.destination_planet.owner instanceof pacifisticCivilization){
                        
                        if(ship instanceof aggressiveShip){
                            aggressiveShip aggressive_ship = (aggressiveShip) ship;
                            if(aggressive_ship.attack_power > ship.destination_planet.owner.owned_resources*0.5){

                                //deleting ship form map and civilization
                                map_area[ship.x_dim][ship.y_dim] = null;

                                //deleting ship from civilization ship_list
                                civ.ship_possesed_list.remove(ship);

                                ship.destination_planet.owner.planets_possesed_list.remove(ship.destination_planet);

                                //changing planet owner
                                ship.destination_planet.owner = civ;
                                
                                ship.owner.planets_possesed_list.add(ship.destination_planet);
                                

                                aggressive_ship.steal_resources(ship.destination_planet, ship.destination_planet.owner, (aggressiveCivilization) ship.owner);
                            }
                        }
                        else{ // if ship is pacifistic
                            // Random rand_planet_id = new Random();  //what is this for?
                            //if the planet is conquered by other pacifistic civilization, the ship calculates the distance to the new closest planet
                            // and sets it as its destination
                            ship.destination_planet = ship.destination_planet.closest_planets_list.get(ship.destination_planet.fitness_proportionate_selection_index());
                            ship.fuel = civ.ship_fuel;
                        }
                    }
                    else{ //this means that the planet is lifeless, so it can be possesed by civilization
                        civ.planets_possesed_list.add(ship.destination_planet);
                        ship.destination_planet.owner = civ;
                        lifeless_planet_list.remove(ship.destination_planet);
                        map_area[ship.x_dim][ship.y_dim] = null;
                        civ.ship_possesed_list.remove(ship);
                        //almost the same as above, but no stealing resources
                    }
                }
            }
        }
    }

    //function that activates black holes and stars
    public void activate_static_objects() {

        //searching for static objects in map
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++){

                //for now it's divided into two separate functions, but it might be changed later
                if(map_area[i][j] instanceof blackHole) activate_black_holes(i, j);
                else if(map_area[i][j] instanceof Star) activate_stars(i, j);
            }
        }
    }

    private void activate_black_holes(int i, int j) {
        
        blackHole black_hole = (blackHole) map_area[i][j];
                    
        //checking if there are any objects in sucking range
        for(int k = i - black_hole.sucking_range; k <= i + black_hole.sucking_range; k++){
            for(int l = j - black_hole.sucking_range; l <= j + black_hole.sucking_range; l++){
                
                if(!(k >= 0 && k < size && l >= 0 && l < size)) {
                    continue;
                }
                if(map_area[k][l] instanceof pacifisticShip) {

                    //deleting all ships ocurrences - I HOPE SO!!!
                    pacifisticShip ship = (pacifisticShip) map_area[k][l];
                    ship.owner.ship_possesed_list.remove(ship);
                    map_area[k][l] = null;
                }
            }
        }
          
    }

    private void activate_stars(int i, int j) {
        
        Star star = (Star) map_area[i][j];

        for(int k = i - star.shining_range; k <= i + star.shining_range; k++){
            for(int l = j - star.shining_range; l <= j + star.shining_range; l++){
                
                if(!(k >= 0 && k < size && l >= 0 && l < size)) {
                    continue;
                }
                if (map_area[k][l] instanceof Planet) { 

                    Planet planet = (Planet) map_area[k][l];

                    //checking if planet is possesed by PACIFISTIC civilization and if it is, civilizations mining abilities are increased
                    //kind of bonus for pacifistic civilizations
                    if(planet.owner != null && planet.owner.is_shined == false && !(planet.owner instanceof aggressiveCivilization)) {
                        planet.owner.mining_abilities = (int) (planet.owner.mining_abilities * star.power_rate);
                        planet.owner.is_shined = true; 
                    }
                    
                } 
            }
        }
    }

    public boolean are_there_any_ships () {
        for(pacifisticCivilization civ : civ_list) {
            if(civ.ship_possesed_list.size() > 0) return true;
        }
        return false;
    }
}

