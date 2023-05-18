package gradle;

public class blackHole implements emptySpace{
    
    int sucking_range;
    

    public blackHole(int sucking_range) {
        this.sucking_range = sucking_range;
    }


    @Override
    public String toString() {
        return "o";
    }
     
    @Override
    public int getType() {
        return 4;
    }


}
