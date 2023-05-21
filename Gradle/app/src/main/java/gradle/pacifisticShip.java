package gradle;

public class pacifisticShip implements emptySpace {
    
    int fuel; //shouldnt we move these three to civilisation class?
    int jump_cooldown;
    int speed;
    int x_dim;
    int y_dim;

    pacifisticShip(int fuel, int jump_cooldown, int speed, int x_dim, int y_dim)
    {
        this.fuel = fuel;
        this.jump_cooldown = jump_cooldown;
        this.speed = speed;
        this.x_dim = x_dim;
        this.y_dim = y_dim;
    }

    @Override
    public int getType() {
        return 2;
    }

    @Override
    public String toString() {
        return "^";
    }

}
