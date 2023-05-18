package gradle;


public class aggressiveShip extends pacifisticShip{

    int attack_power; 
    
    aggressiveShip(int fuel, int jump_cooldown, int speed, int x_dim, int y_dim, int attack_power)
    {
        super(fuel, jump_cooldown, speed, x_dim, y_dim);
        this.attack_power = attack_power;
    }
}
