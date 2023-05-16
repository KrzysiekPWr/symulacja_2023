package gradle;

public class Planet extends objectInSpace{

   // final char planet_char = '*';
    
    int resources;
    int extracted_resources;

    public Planet(int resources, int extracted_resources) {
        super('P');
        this.resources = resources;
        this.extracted_resources = extracted_resources;
    }
}
