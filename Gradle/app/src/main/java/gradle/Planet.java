package gradle;

import java.util.ArrayList;
import java.util.List;

public class Planet implements emptySpace{

    
    int resources;
    int extracted_resources = 0;
    int x_dim;
    int y_dim;
    pacifisticCivilization owner;
    List<Planet> closest_planets_list = new ArrayList<>();

    public Planet(int resources, int x_dim, int y_dim) {
        this.resources = resources;
        this.x_dim = x_dim;
        this.y_dim = y_dim;
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public int getType() {
        return 1;
    }
}
