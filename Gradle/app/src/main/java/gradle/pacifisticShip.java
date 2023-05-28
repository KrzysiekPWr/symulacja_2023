package gradle;

public class pacifisticShip implements emptySpace {
    
    int fuel; //shouldnt we move these three to civilisation class?
    int jump_cooldown;
    int act_jump_cooldown;
    int speed;
    int x_dim;
    int y_dim;
    Planet planet_of_origin;
    Planet destination_planet;

    pacifisticShip(int fuel, int jump_cooldown, int speed, int x_dim, int y_dim, Planet planet_of_origin, Planet destination_planet)
    {
        this.fuel = fuel;
        this.jump_cooldown = jump_cooldown;
        this.speed = speed;
        this.x_dim = x_dim;
        this.y_dim = y_dim;
        this.planet_of_origin = planet_of_origin;
        this.destination_planet = destination_planet;
        act_jump_cooldown = jump_cooldown;
    }

    public boolean canShipMove(){
        if (act_jump_cooldown <= 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isShipAlive(){
        if (fuel > 0){
            return true;
        }
        else{
            return false;
        }
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
