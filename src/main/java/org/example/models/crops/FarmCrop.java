package org.example.models.crops;

import org.example.models.PlayerInventory;
import org.example.models.item.SeedBag;
import org.example.models.item.Tool;
import org.example.models.enums.ToolType;

public class FarmCrop extends Crop {
    public FarmCrop(String name, int daysSinceSowed, int daysReadyToHarvest) {
        super(name, daysSinceSowed, daysReadyToHarvest);
    }

    @Override
    public int applyTool(Tool tool) throws Exception {
        ToolType type = tool.getToolType();
        return switch (type) {
            case HAND -> {
                if (isRipe()) {
                    System.out.println("Harvested " + getName());
                    try {
                        PlayerInventory.getInstance().addItem(new SeedBag(this.getName(), this, 1));
                    } catch (Exception e) {
                        throw new Exception("Could not store item to inventory ");
                    }
                }
                yield 2;
            }
            case AXE -> -1;
            case HOE -> {
                System.out.println("Destroyed crop " + getName());
                yield 0;
            }
        };
    }
}
