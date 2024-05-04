package org.example.models.crops;

import org.example.models.item.Tool;

public abstract class Crop {
    private final String name;
    private int daysSinceSowed;
    private final int daysReadyToHarvest;
    private boolean isRipe;

    public Crop(String name, int daysSinceSowed, int daysReadyToHarvest) {
        this.name = name;
        this.daysSinceSowed = daysSinceSowed;
        this.daysReadyToHarvest = daysReadyToHarvest;
        this.isRipe = false;
    }

    public void processNextDay() {
        daysSinceSowed++;
        if (daysSinceSowed >= daysReadyToHarvest) {
            setIsRipe(true);
        }
    }

    public abstract int applyTool(Tool tool) throws Exception;

    public String getName() {
        return name;
    }

    public int getDaysSinceSowed() {
        return daysSinceSowed;
    }

    public int getDaysReadyToHarvest() {
        return daysReadyToHarvest;
    }

    public boolean isRipe() {
        return isRipe;
    }

    public void setIsRipe(boolean isRipe) {
        this.isRipe = isRipe;
    }
}
