/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package gradle;

import java.time.Instant;

public abstract class Simulation {

    static long seconds_now;
    static long start_time;
    static long seconds_from_start;
    public static void main(String[] args) {

        // Map map = new Map(Double.parseDouble(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]),
        // Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]));
        
        int map_size = 15;
        Map map = new Map(0.05, map_size, 2, 2, 1, 1);

               
        map.initialize_map();
        map.show_map();
        
        //executing simulation era
        for(int i = 0; i < 10; i++) {
            start_time = Instant.now().getEpochSecond();
            map.mine_resources();
            map.spawn_ships();

            wait(2, start_time);
            System.out.println(System.lineSeparator().repeat(map_size+1));
            System.out.println("Ships spawning");
            map.show_map();

            start_time = Instant.now().getEpochSecond();
            map.move_ships();
            wait(2, start_time);
            System.out.println(System.lineSeparator().repeat(map_size+1));
            System.out.println("Ships moving");
            map.show_map();

            start_time = Instant.now().getEpochSecond();
            map.conquer_planets_using_ships();
            wait(2, start_time);
            System.out.println(System.lineSeparator().repeat(map_size+1));
            System.out.println("Ships conquering planets");
            map.show_map();

            map.activate_static_objects();
            // System.out.println("------------------------------------------");
        }
        System.out.println("Simulation ended");
    }

    private static void wait(long seconds, long start_time) {
        do{
            seconds_now = Instant.now().getEpochSecond();
            seconds_from_start = seconds_now - start_time ;
        }while(seconds_from_start < 2);
    }
}
