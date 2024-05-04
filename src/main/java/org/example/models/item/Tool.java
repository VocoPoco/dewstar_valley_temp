package org.example.models.item;

import org.example.models.Farm;
import org.example.models.PlaneVector;
import org.example.models.enums.ItemType;
import org.example.models.enums.ToolType;

public class Tool extends Item{
    private final ToolType toolType;
    Tool(String name, ToolType toolType) {
        super(name, ItemType.TOOL);
        this.toolType = toolType;
    }

    @Override
    public void use(Farm farm, PlaneVector position) {
        useToolOnSpecificFields(farm, position, PlaneVector.up, 1, 1);
    }

    @Override
    public void use(Farm farm, PlaneVector position, PlaneVector direction, int powerLevel) {
        switch (powerLevel) {
            case 1:
                useToolOnSpecificFields(farm, position, direction, 1, 1);
            case 2:
                useToolOnSpecificFields(farm, position, direction, 3, 1);
            case 3:
                useToolOnSpecificFields(farm, position, direction, 3, 3);
        }
    }

    private void useToolOnSpecificFields(Farm farm, PlaneVector centralPosition, PlaneVector effectDirection, int width, int height) {
        int effectOriginX = (int) centralPosition.getX();
        int effectOriginY = (int) centralPosition.getY();
        int directionXStep = (int) effectDirection.getX();
        int directionYStep = (int) effectDirection.getY();

        effectOriginX -= (width - 1) / 2 * directionXStep;
        effectOriginY -= (height - 1) / 2 * directionYStep;

        for (int xOffset = 0; xOffset < width; xOffset++) {
            for (int yOffset = 0; yOffset < height; yOffset++) {
                int currentX = effectOriginX + xOffset * directionXStep;
                int currentY = effectOriginY + yOffset * directionYStep;
                PlaneVector currentPosition = new PlaneVector((double) currentX,(double) currentY);
                try {
                    farm.getField(currentPosition).applyTool(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ToolType getToolType() {
        return toolType;
    }
}
