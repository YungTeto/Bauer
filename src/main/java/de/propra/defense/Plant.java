package de.propra.defense;

import de.propra.defense.ui.GamePanel;

public class Plant extends Unit{

    private int hitpoints;

    public Plant(GamePanel game, UnitType type, int row, int col) {
        super(game, type, row, col);
        hitpoints = 5;
    }


    @Override
    public boolean isStationary() {
        return true;
    }

    @Override
    public boolean isLivingPlant() {
        return hitpoints > 0;
    }




}
