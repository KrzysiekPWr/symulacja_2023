package gradle;


public class pacifisticShip implements emptySpace {
    
    int fuel; //shouldnt we move these three to civilisation class?
    int jump_cooldown;
    int act_jump_cooldown;
    int speed;
    int x_dim;
    int y_dim;
    pacifisticCivilization owner;
    Planet destination_planet;

    pacifisticShip(int fuel, int jump_cooldown, int speed, int x_dim, int y_dim, Planet destination_planet, pacifisticCivilization owner)
    {
        this.fuel = fuel;
        this.jump_cooldown = jump_cooldown;
        this.speed = speed;
        this.x_dim = x_dim;
        this.y_dim = y_dim;
        this.destination_planet = destination_planet;
        this.owner = owner;
        act_jump_cooldown = jump_cooldown;
    }
   

    //function moved to Planet class - didived to sort_planets and select_index functions 
    //NOT WORKING YET
    //implementing wheel roulete/fitness proportionate selction
    // public void selectRandomDestination(){
    //     Random rand = new Random();

    //     //calculate all distances from closest planets
    //     double sum_of_distances = 0;
    //     double[] distances = new double[planet_of_origin.closest_planets_list.size()];
    //     for (int i = 0; i < planet_of_origin.closest_planets_list.size(); i++) {
    //         distances[i] = Math.sqrt(Math.pow(planet_of_origin.closest_planets_list.get(i).x_dim -  f_origin.x_dim, 2) + 
    //         Math.pow(planet_of_origin.closest_planets_list.get(i).y_dim - planet_of_origin.y_dim, 2));
    //         sum_of_distances += distances[i];
    //     }   

    //     //calculate probability using fitness proportionate selection
    //     double[] probabilities = new double[planet_of_origin.closest_planets_list.size()];
    //     for (int i = 0; i < planet_of_origin.closest_planets_list.size(); i++) {
    //         probabilities[i] = distances[i] / sum_of_distances;
    //     }

    //     //sort probabilities
    //     for (int i = 0; i < probabilities.length; i++) {
    //         for (int j = 0; j < probabilities.length; j++) {
    //             if (probabilities[i] < probabilities[j]){
    //                 double temp = probabilities[i];
    //                 probabilities[i] = probabilities[j];
    //                 probabilities[j] = temp;
    //             }
    //         }
    //     }

    //     //select random planet
    //     double random = rand.nextDouble();
    //     if(random < probabilities[0]){
    //         destination_planet = planet_of_origin.closest_planets_list.get(0);
    //     }
    //     else if(random < probabilities[1]){
    //         destination_planet = planet_of_origin.closest_planets_list.get(1);
    //     }
            
    // }

    public boolean canShipMove(){
        if (act_jump_cooldown <= 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isShipAlive(){
        if (fuel > 0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public int getType() {
        return 2;
    }

    @Override
    public String toString() {
        
        if(owner instanceof aggressiveCivilization){
            return "#";
        }
        else {
            return "~";
        }
    }

}
