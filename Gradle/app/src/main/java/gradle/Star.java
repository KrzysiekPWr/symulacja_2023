package gradle;

public class Star implements emptySpace{

    double power_rate; 
    int extinction_time;
    int shining_range; 
    
    public Star(double power_rate, int shining_range) {
        this.power_rate = power_rate;
        this.shining_range = shining_range;
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
