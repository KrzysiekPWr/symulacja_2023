package gradle;

import java.util.Random;

abstract class simulationSetup {

    public static Map initializeMap(Map map){
        map = spawn_planets(map.planetation, map);
        map = spawn_stars(map.stars_quantity, map);
        map = spawn_black_holes(map.black_holes_quantity, map);
     
       return map;
    }
    
    /**
     * 
     * @param planetation
     * @param map
     * @return Map
     */

    private static Map spawn_planets(double planetation, Map map){
     
        Random rand = new Random();

        for (int x = 0; x < map.size; x++) {
            for (int y = 0; y < map.size; y++) {
                int chance_for_planet = rand.nextInt(1,101); //random int from 1 to 100
                    if(chance_for_planet <= (int)(planetation*100)){
                        map.map_area[x][y] = "P";
                    }
                    else{
                        map.map_area[x][y] = "*";
                    }
                }
            }
            return map;
    }
    
    private static Map spawn_stars(int stars_quantity, Map map){

        Random rand = new Random();
        
        // //checking if there is a place for stars
        // int counter = 0;
        // for (int x = 0; x < map.size; x++) {
        //     for (int y = 0; y < map.size; y++) {
        //         if(map.map_area[x][y].equals("*")){
        //             counter++;
        //         }
        //     }
        // }
        // if(counter < stars_quantity)return map;
        
        int random_x, random_y;
        for(int i = 0; i < stars_quantity; i++){
            
            random_x = rand.nextInt(1, map.size);
            random_y = rand.nextInt(1, map.size);
            
            map.map_area[random_x][random_y] = "+"; 
        }
        return map;
    }


    /**
     * 
     * @param black_holes_quantity
     * @param map
     * @return Map
     */
    private static Map spawn_black_holes(int black_holes_quantity, Map map){

        Random rand = new Random();            

        int random_x, random_y;
        for(int i = 0; i < black_holes_quantity; i++){
            do{
            random_x = rand.nextInt(1, map.size);
            random_y = rand.nextInt(1, map.size);
            }while(map.map_area[random_x][random_y].equals("+"));
            
            map.map_area[random_x][random_y] = "O"; 
        }
        return map;
    }
}

