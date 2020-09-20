package bauwerk78.effects;

import javafx.scene.effect.Glow;

public class GlowEffect {

    private final Glow glowEffect = new Glow();

    private double glowLevel = 0;
    private boolean glowLevelGoingUp = true;
    private double x = 0;
    private double k = 0.01;
    private double modifier = 0;

    public GlowEffect() {
    }

    public GlowEffect(double glowLevel) {
        this.glowLevel = glowLevel;
    }

    public void pulsingGlowEffect() {
        if (glowLevelGoingUp) {
            glowLevel += 0.01;
            if (glowLevel >= 1) {
                glowLevel = 1;
                glowLevelGoingUp = false;
            }
        } else {
            glowLevel -= 0.01;
            if (glowLevel <= 0) {
                glowLevel = 0;
                glowLevelGoingUp = true;
            }
        }
        glowEffect.setLevel(glowLevel);
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

    public Glow getGlowEffect() {
        glowEffect.setLevel(getGlowLevel());
        return glowEffect;
    }

    public void setGlowLevel(double glowLevel) {
        glowEffect.setLevel(glowLevel);
    }

    public double getGlowLevel() {
        return glowLevel;
    }
}
