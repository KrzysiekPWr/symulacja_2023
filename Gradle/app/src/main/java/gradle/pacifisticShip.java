package gradle;


public class pacifisticShip implements emptySpace {
    
    int fuel; 
    int jump_cooldown;
    int act_jump_cooldown;
    int speed;
    int x_dim;
    int y_dim;
    pacifisticCivilization owner;
    Planet destination_planet;
    int times_ship_cannot_move_closer_to_destination = 0;

    pacifisticShip(int fuel, int jump_cooldown, int speed, int x_dim, int y_dim, Planet destination_planet, pacifisticCivilization owner)
    {
        this.fuel = fuel;
        this.jump_cooldown = jump_cooldown;
        this.speed = speed;
        this.x_dim = x_dim;
        this.y_dim = y_dim;
        this.destination_planet = destination_planet;
        this.owner = owner;
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
        
        if(owner instanceof aggressiveCivilization){
            return "#";
        }
        else {
            return "~";
        }
    }

}
