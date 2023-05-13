package gradle;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;


class Map {
    double planetation;
    int size;
    int stars_quantity;
    int black_holes_quantity;
    int aggresive_civilisation_quantity;
    int pacifistic_civilisation_quantity;
    List<pacifisticCivilization> civ_list = new ArrayList<>();
    List<pacifisticShip> ship_list = new ArrayList<>();
    String[][] map_area;

    Map(){}
    
    Map(double planetation, int size, int stars_quantity, int black_holes_quantity,
    int aggresive_civilisation_quantity, int pacifistic_civilisation_quantity)
    {
        this.planetation = planetation;
        this.size = size;
        this.stars_quantity = stars_quantity;
        this.black_holes_quantity = black_holes_quantity;
        this.aggresive_civilisation_quantity = aggresive_civilisation_quantity;
        this.pacifistic_civilisation_quantity = pacifistic_civilisation_quantity;
        this.map_area = new String[size][size];
    }

    void showMap(){

        for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(map_area[i][j] + " ");
                }
                System.out.println();
            }
    }

    
    
}
