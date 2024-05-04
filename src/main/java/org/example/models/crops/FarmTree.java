package org.example.models.crops;

import org.example.models.PlayerInventory;
import org.example.models.item.SeedBag;
import org.example.models.item.Tool;
import org.example.models.enums.ToolType;

public class FarmTree extends Crop {
    private int fruitCounter = 0;

    public FarmTree(String name, int daysSinceSowed, int daysReadyToHarvest) {
        super(name, daysSinceSowed, daysReadyToHarvest);
    }

    @Override
    public void processNextDay() {
        super.processNextDay();
        if (isRipe()) {
            fruitCounter++;
        }
    }

    @Override
    public int applyTool(Tool tool) throws Exception {
        ToolType type = tool.getToolType();
        return switch (type) {
            case HAND -> {
                if (fruitCounter >= 3) {
                    System.out.println("Collected fruit from " + getName());
                    fruitCounter = 0;
                    try {
                        PlayerInventory.getInstance().addItem(new SeedBag(this.getName(), this, 1)); // Adds the collected fruit to the inventory
                    } catch (Exception e) {
                        throw new Exception("Could not store item to inventory ");
                    }
                }
                yield 2;
            }
            case AXE -> {
                System.out.println("Destroyed tree " + getName());
                yield 0;
            }
            default -> -1;
        };
    }
}
