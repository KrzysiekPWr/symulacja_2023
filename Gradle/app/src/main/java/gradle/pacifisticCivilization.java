package gradle;

import java.util.ArrayList;

public class pacifisticCivilization {
    
    int owned_resources = 0;
    int mining_abilities;
    int ship_price;
    int ship_fuel;
    int ship_jump_cooldown;
    int ship_speed;
    ArrayList<Planet> planets_possesed_list = new ArrayList<>();
    ArrayList<pacifisticShip> ship_possesed_list = new ArrayList<>();

    public pacifisticCivilization(int mining_abilities, int ship_price, int ship_fuel, 
    int ship_jump_cooldown, int ship_speed) {
        this.mining_abilities = mining_abilities;
        this.ship_price = ship_price;
        this.ship_fuel = ship_fuel;
        this.ship_jump_cooldown = ship_jump_cooldown;
        this.ship_speed = ship_speed;
    }

    public pacifisticCivilization() {
    }

    public void mine_resources_for_one_civilization()
    {
        int resources_mined_in_era = 0;
            
        for(int j = 0; j < planets_possesed_list.size(); j++){
            
            //case when resources of planet are grater than mining_ablities
            if(planets_possesed_list.get(j).resources >= mining_abilities){

                //subtracting extracted resources from all planet's resources
                planets_possesed_list.get(j).resources -= mining_abilities;

                //adding substracted resources to extracted_resources
                planets_possesed_list.get(j).extracted_resources += mining_abilities;

                resources_mined_in_era += mining_abilities;

            }
            //if mining ability of a civ is greater than remaining resources on the planet (to evade negative
            //resources count)
            else{
            
                planets_possesed_list.get(j).extracted_resources 
                +=  planets_possesed_list.get(j).resources;
                resources_mined_in_era +=  planets_possesed_list.get(j).resources;
                planets_possesed_list.get(j).resources = 0;
            }

        }
        owned_resources += resources_mined_in_era;
    }
    
    public void setOwnedResources(int owned_resources){
        this.owned_resources = owned_resources;
    }

    public int getOwnedResources(){
        return owned_resources;
    }
}
