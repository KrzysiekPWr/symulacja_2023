/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package gradle;
// import java.lang.Integer;

public class Simulation {

    public static void main(String[] args) {

        // Map map = new Map(Double.parseDouble(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]),
        // Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]));
        
        
        Map map = new Map(0.1, 20, 2, 2, 2, 0);

        //add civilizations to the planets1        
        map.initialize_map();

        for(int i = 0; i < 30; i++){
            map.mine_resources();
            map.show_map();
            map.spawn_ships();
            map.move_ships();
            System.out.println();
        }
        //executing simulation round
        
        
        
    }

}
