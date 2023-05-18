package gradle;


public class aggressiveShip extends pacifisticShip{

    int attack_power; 
    
    aggressiveShip(int fuel, int jump_cooldown, int speed, int attack_power)
    {
        super(fuel, jump_cooldown, speed);
        this.attack_power = attack_power;
    }
}
