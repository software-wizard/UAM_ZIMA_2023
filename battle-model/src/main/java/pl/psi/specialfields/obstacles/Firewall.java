package pl.psi.specialfields.obstacles;

import pl.psi.GameEngine;
import pl.psi.fields.ObstacleTypes;
import pl.psi.specialfields.Obstacle;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Firewall posiada obrażenia które będzie zadawał jeśli kreatura przejdzie po polu na którym się znajduje
 * Długość trwania tego zaklęcia wynosi 2 tury więc nasłuchuje na event END_OF_TURN
 */

public class Firewall extends Obstacle implements PropertyChangeListener{
    public final String type = ObstacleTypes.FIRE_WALL;
    public final int dmg;
    private int duration;
    private boolean isActive;

    public Firewall(int dmg) {
        this.dmg = dmg;
        this.duration = 2;
        this.isActive = true;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(GameEngine.END_OF_TURN)){
            endOfTurn();
        }
    }

    private void endOfTurn() {
        duration--;
        if (duration == 0){
            this.isActive = false;
        }
    }

    @Override
    public String getType() {
        return type;
    }
}