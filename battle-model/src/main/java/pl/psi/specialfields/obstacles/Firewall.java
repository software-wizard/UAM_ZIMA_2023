package pl.psi.specialfields.obstacles;

import pl.psi.GameEngine;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Firewall posiada obrażenia które będzie zadawał jeśli kreatura przejdzie po polu na którym się znajduje
 * Długość trwania tego zaklęcia wynosi 2 tury więc nasłuchuje na event END_OF_TURN
 */

class Firewall implements PropertyChangeListener {
    public final int dmg;
    private int duration;

    public Firewall(int dmg) {
        this.dmg = dmg;
        this.duration = 2;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(GameEngine.END_OF_TURN)){
            endOfTurn();
        }
    }

    private void endOfTurn() {
        duration--;
    }
}