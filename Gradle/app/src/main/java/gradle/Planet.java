package gradle;

public class Planet extends objectInSpace{

   // final char planet_char = '*';
    
    int initial_resources;
    int extracted_resources;

    public Planet(int initial_resources, int extracted_resources) {
        super('P');
        this.initial_resources = initial_resources;
        this.extracted_resources = extracted_resources;
    }
}
