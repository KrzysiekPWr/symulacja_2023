package gradle;

public class Star implements emptySpace{

    double power_rate; 
    int extinction_time;
    
    public Star(double power_rate, int extinction_time) {
        this.power_rate = power_rate;
        this.extinction_time = extinction_time;
    }

    @Override 
    public String toString() {
        return "+";
    }

    @Override
    public int getType() {
        return 3;
    }


    

   
}
