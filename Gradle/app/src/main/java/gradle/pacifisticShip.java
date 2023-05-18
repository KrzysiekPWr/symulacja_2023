package gradle;

public class pacifisticShip extends objectInSpace {
    
    int fuel;
    int jump_cooldown;
    int speed;

    pacifisticShip(int fuel, int jump_cooldown, int speed)
    {
        super('^');
        this.fuel = fuel;
        this.jump_cooldown = jump_cooldown;
        this.speed = speed;
    }

}
