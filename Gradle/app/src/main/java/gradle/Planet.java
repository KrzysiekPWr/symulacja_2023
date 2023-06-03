package gradle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    //function that sorts closest planets list
    public void sort_closest_planets_list(){
        
        for(int i = 0; i < closest_planets_list.size(); i++) {

            double distance_i = Math.sqrt(Math.pow(closest_planets_list.get(i).x_dim - x_dim, 2) + Math.pow(closest_planets_list.get(i).y_dim - y_dim, 2));

            for(int j = 0; j < closest_planets_list.size(); j++) {

                double distanace_j = Math.sqrt(Math.pow(closest_planets_list.get(j).x_dim - x_dim, 2) + Math.pow(closest_planets_list.get(j).y_dim - y_dim, 2));

                if(distance_i < distanace_j) {
                    Planet temp = closest_planets_list.get(i);
                    closest_planets_list.set(i, closest_planets_list.get(j));
                    closest_planets_list.set(j, temp);
                }
            }
        }
        // System.out.println("Cords: " + x_dim + " " + y_dim);
        // for(int i = 0; i < closest_planets_list.size(); i++) {
        //     System.out.print(" \n [" + closest_planets_list.get(i).x_dim + " " + closest_planets_list.get(i).y_dim + "] - distance - " + Math.sqrt(Math.pow(closest_planets_list.get(i).x_dim - x_dim, 2) + Math.pow(closest_planets_list.get(i).y_dim - y_dim, 2)));
        // }
        // System.out.println();
    }

    public int fitness_proportionate_selection_index() {
        Random rand = new Random();
        int index = 0;
        double sum_of_indexes_plus_ones = 0;
    
        int random_number = rand.nextInt(1, 101);

        for (int i = 0; i < closest_planets_list.size(); i++) {
            sum_of_indexes_plus_ones += i + 1;
        }
    
        for (int i = 0; i < closest_planets_list.size(); i++) {
            double probability_of_selection = (closest_planets_list.size() - i) / sum_of_indexes_plus_ones;
            int probability_percentage = (int) (probability_of_selection * 100);
            if (random_number <= probability_percentage) {
                index = i;
                return index;
            }
            random_number -= probability_percentage;
        }
    
        index = closest_planets_list.size() - 1;
        return index;
    }
    

    @Override
    public String toString() {
        
        if(owner instanceof aggressiveCivilization)
            return "A";
        else if(owner instanceof pacifisticCivilization)
            return "P";
        else
            return "E";
    
    }

    @Override
    public int getType() {
        return 1;
    }
}
