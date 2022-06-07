package de.propra.defense;

import de.propra.defense.ui.GamePanel;

import java.util.Optional;
import java.util.Set;

public class Bug extends Unit{
    private  int strength;
    private int hitpoints;

    public Bug(GamePanel game, UnitType type, int row, int col) {
        super(game, type, row, col);
        hitpoints = 10;
        strength = 1;
    }



    @Override
    public boolean isLivingEnemy() {
        return hitpoints > 0;
    }


    @Override
    public void act(Set<Unit> units) {
        Optional<Unit> nearestPlant =
                units.stream().filter(Unit::isLivingPlant).min((o1, o2) -> distance(this, o1, o2));
        if (nearestPlant.isPresent()) {
            Unit plant = nearestPlant.get();
            if (isNeighbor(plant, 1)) {
                attack(plant);
            }
            else {
                double decide = Math.random();
                if (decide > 0.8) {
                    moveRandomly();
                }
                else {
                    moveTowards(plant);
                }
            }
        }
    }
}
