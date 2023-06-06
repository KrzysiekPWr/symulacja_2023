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

                    initial_resources = rand.nextInt(100, 1000); 

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
            
            Star star = new Star(1.001, 10);
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
                
                //creating new pacifistic civilization                      THESE SHOULD BE RANDOMIZED!
                pacifisticCivilization pacCiv = new pacifisticCivilization(10, 40, 7,2,2);
                
                // adding civilization to list of civilizations
                civ_list.add(pacCiv);

                Planet emptyPlanet = lifeless_planet_list.get(random_index);

                //adding planet to list of planets possesed by civilization
                pacCiv.planets_possesed_list.add(emptyPlanet);
                emptyPlanet.owner = pacCiv;

                //removing planet from list of lifeless planets that can be possesed in future
                lifeless_planet_list.remove(random_index);

            }
            else{                                                           // THESE SHOULD BE RANDOMIZED!
                aggressiveCivilization agrCiv = new aggressiveCivilization(10, 50, 15,2,2);
                
                civ_list.add(agrCiv);
                Planet emptyPlanet = lifeless_planet_list.get(random_index);
                agrCiv.planets_possesed_list.add(emptyPlanet);
                emptyPlanet.owner = agrCiv;
                
                lifeless_planet_list.remove(random_index);
            }
            
        }   
                 
    }
    
    public void mine_resources(){ 

        for(int i = 0; i < civ_list.size(); i++) {   
            civ_list.get(i).mine_resources_for_one_civilization();
        }
    }

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
                                owned_planet.closest_planets_list.get(owned_planet.fitness_proportionate_selection_index()), civ.owned_resources*100000.0, civ);
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
     * Function that moves ships and checks if they are in range of any planet to conquer it,
     * checks what type of civ is the owner of the planet and plays accordingly
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

    public void conquer_planets_using_ships(){
        for (pacifisticCivilization civ : civ_list) {
            for(int id_ship = 0; id_ship < civ.ship_possesed_list.size(); id_ship++){

                pacifisticShip ship = civ.ship_possesed_list.get(id_ship);
                //if the ship is 1 distance away from the planet then it is removed from the map and the planet is conquered by civilization
                double distance_to_planet = Math.sqrt(Math.pow(ship.x_dim - ship.destination_planet.x_dim, 2) + Math.pow(ship.y_dim - ship.destination_planet.y_dim, 2));
                if(distance_to_planet < 2){
                    if(ship.destination_planet.owner instanceof aggressiveCivilization){
                        map_area[ship.x_dim][ship.y_dim] = null;
                        civ.ship_possesed_list.remove(ship); 
                        //might later add fight betweent aggressive ship and aggresive civ
                        }
                    else if(ship.destination_planet.owner instanceof pacifisticCivilization){
                        
                        if(ship instanceof aggressiveShip){
                            aggressiveShip aggressive_ship = (aggressiveShip) ship;
                            if(aggressive_ship.attack_power > ship.destination_planet.owner.owned_resources*0.1){

                                //deleting ship form map and civilization
                                map_area[ship.x_dim][ship.y_dim] = null;

                                //deleting ship from civilization ship_list
                                civ.ship_possesed_list.remove(ship);

                                //changing ship owner
                                ship.destination_planet.owner = civ;


                                ship.owner.planets_possesed_list.add(ship.destination_planet);
                                ship.destination_planet.owner.planets_possesed_list.remove(ship.destination_planet);

                                aggressive_ship.steal_resources(ship.destination_planet, ship.destination_planet.owner, (aggressiveCivilization) ship.owner);
                            }
                        }
                        else{ // if ship is pacifistic
                            // Random rand_planet_id = new Random();  //what is this for?
                            //if the planet is conquered by other pacifistic civilization, the ship calculates the distance to the new closest planet
                            // and sets it as its destination
                            ship.destination_planet = ship.destination_planet.closest_planets_list.get(ship.destination_planet.fitness_proportionate_selection_index());
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

    /**
     * Function that checks if planet is conquered by civilization
     * @param planet
     * @param civ
     */
    private boolean check_if_planet_is_conquered(Planet planet, pacifisticCivilization civ){
        if(lifeless_planet_list.contains(planet)){
            return false;
        }
        else {
            return true;
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

                } else if (map_area[k][l] instanceof Planet) { 

                    // //deleting all planets ocurrences - I HOPE SO!!!
                    // Planet planet = (Planet) map_area[k][l];

                    // //NEED FOR DELETING PLANETS FROM closest_planets_list of other planets
                    // //checking if planet is possesed by civilization
                    // if(planet.owner != null) planet.owner.planets_possesed_list.remove(planet);
                    // map_area[k][l] = null;
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
                    //we need to do it ONLY ONCE, so we need to check if planet is already shined
                    if(planet.owner != null && planet.owner.is_shined == false && !(planet.owner instanceof aggressiveCivilization)) {
                        planet.owner.mining_abilities = (int) (planet.owner.mining_abilities * star.power_rate);
                        planet.owner.is_shined = true; 
                    }
                    
                } 
            }
        }
        
    }
}

