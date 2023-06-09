package gradle;

import javax.swing.*;
import java.awt.*;

public class simulationTimeSlider {
    
    JSlider slider;

    simulationTimeSlider () {
        
        slider = new JSlider(0, 100, 50);
        slider.setPreferredSize(new Dimension(400, 200));

        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(10);
    }

    
}
