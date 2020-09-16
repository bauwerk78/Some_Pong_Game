package bauwerk78.effects;

import javafx.scene.effect.Glow;

public class PulsingGlowEffect {

    private final Glow effectGlowHeader = new Glow();

    private double effectGlowLevel = 0;
    private boolean effectGlowLevelGoingUp = true;
    private double x = 0;
    private double k = 0.01;
    private double modifier = 0;

    public PulsingGlowEffect() {
    }

    public void pulsingGlowEffect() {
        if (effectGlowLevelGoingUp) {
            effectGlowLevel += 0.01;
            if (effectGlowLevel >= 1) {
                effectGlowLevel = 1;
                effectGlowLevelGoingUp = false;
            }
        } else {
            effectGlowLevel -= 0.01;
            if (effectGlowLevel <= 0) {
                effectGlowLevel = 0;
                effectGlowLevelGoingUp = true;
            }
        }
    }

    public double pulseTriangular(double input) {
        if(input >= 0.99) {
            modifier = 1;
        }
        if(input <= 0.02) {
            modifier = 0;
        }
        return Math.abs((x++ * k % 1) - modifier);
    }

}
