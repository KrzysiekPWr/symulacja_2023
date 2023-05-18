package gradle;

public class Planet extends objectInSpace{

    
    int resources;
    int extracted_resources;

    public Planet(int resources, int extracted_resources) {
        super('p');
        this.resources = resources;
        this.extracted_resources = extracted_resources;
    }
}
