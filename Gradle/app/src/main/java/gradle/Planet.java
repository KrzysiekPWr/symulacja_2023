package gradle;

public class Planet implements emptySpace{

    
    int resources;
    int extracted_resources = 0;
    int x_dim;
    int y_dim;
    int closest_planets_vectors_list[][] = new int[3][2];

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
