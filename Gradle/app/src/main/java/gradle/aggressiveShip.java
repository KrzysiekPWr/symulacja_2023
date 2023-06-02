package gradle;


public class aggressiveShip extends pacifisticShip{

    double attack_power; 
    
    aggressiveShip(int fuel, int jump_cooldown, int speed, int x_dim, int y_dim, Planet destination_planet, double attack_power)
    {               
        super(fuel, jump_cooldown, speed, x_dim, y_dim, destination_planet);
        this.attack_power = attack_power;
    }

    void steal_resources(Planet planet, pacifisticCivilization pac_civ, aggressiveCivilization agr_civ){
        int stolen_resources = planet.extracted_resources;
        int agr_new_resources = agr_civ.owned_resources + stolen_resources;
        int pac_new_resources = pac_civ.owned_resources - stolen_resources;
        agr_civ.setOwnedResources(agr_new_resources);
        pac_civ.setOwnedResources(pac_new_resources);
        
    }
}
