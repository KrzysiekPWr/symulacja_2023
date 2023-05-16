package gradle;

import java.util.ArrayList;

public class pacifisticCivilization {
    
    int resources;
    int mining_abilities;
    int ship_price;
    ArrayList<Planet> planets_possesed_list = new ArrayList<>();
    ArrayList<Planet> ship_possesed_list = new ArrayList<>();

    public pacifisticCivilization(int resources, int mining_abilities, int ship_price) {
        this.resources = resources;
        this.mining_abilities = mining_abilities;
        this.ship_price = ship_price;
    }

    public pacifisticCivilization() {
    }

    
}
