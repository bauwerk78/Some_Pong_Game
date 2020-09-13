package bauwerk78.effects;

import javafx.scene.effect.Glow;

public class PulsingGlowEffect {

    private final Glow effectGlowHeader = new Glow();

    private double effectGlowLevel = 0;
    private boolean effectGlowLevelGoingUp = true;

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

}
